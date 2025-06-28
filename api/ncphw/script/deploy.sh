#!/bin/bash

DOCKER_IMAGE=$1
PORT_LIST=(8080 8081)
LOG_FOLDER="/root/logs"
CONSOLELOG="springboot$(date +%Y%m%d).log"
KILL_TRIES=5
KILL_INTERVAL_SECONDS=3
TEST_URL="http://localhost:PORT/health-check"
NGINX_UPSTREAM_CONF="/etc/nginx/conf.d/upstream.conf"
WAIT_SECONDS_BEFORE_TEST=10
TEST_TRIES=7
TEST_INTERVAL_SECONDS=3

function check_running {
    local port=$1
    CONTAINER_ID=$(docker ps -q --filter "publish=$port")
    if [[ -n "$CONTAINER_ID" ]] ; then
        echo "Docker container already running on port $port (Container ID: $CONTAINER_ID)"
        exit 1
    fi
}

function check_started {
    local port=$1
    local test_url
    if [[ -z ${TEST_URL} ]] ; then
        exit 0
    fi
    test_url=$(echo "$TEST_URL" | sed "s/PORT/$port/" )
    if [[ -z ${WAIT_SECONDS_BEFORE_TEST} ]] ; then
        WAIT_SECONDS_BEFORE_TEST=15
    fi
    if [[ -z ${TEST_TRIES} ]] ; then
        TEST_TRIES=7
    fi
    if [[ -z ${TEST_INTERVAL_SECONDS} ]] ; then
        TEST_INTERVAL_SECONDS=5
    fi
    echo "Waiting for the Docker container to start.($WAIT_SECONDS_BEFORE_TEST seconds)"
    sleep ${WAIT_SECONDS_BEFORE_TEST}
    echo "Health checking on $test_url"
    for (( TRY_COUNT=1; TRY_COUNT<=$TEST_TRIES; TRY_COUNT++ ))
    do
        sleep ${TEST_INTERVAL_SECONDS}
        echo "Checking HTTP port. ($TRY_COUNT/$TEST_TRIES)"
        HTTP_STATUS_CODE=$(curl -sL -o /dev/null -I -w "%{http_code}" "${test_url}" --max-time 10)
        if [[ ${HTTP_STATUS_CODE} -gt 199 ]] && [[ ${HTTP_STATUS_CODE} -lt 300 ]] ; then
            echo "The Docker container has started successfully."
            break
        fi
        if [[ ${TRY_COUNT} == ${TEST_TRIES} ]] ; then
            echo "ERROR : The Docker container failed to start."
            exit 1
        fi
    done
}

function start {
    local port=$1
    check_running "$port"
    echo "Starting Docker container on $port port."
    create_log_folder_if_not_exist "$port"
    docker run -d \
        --name "ncphw-release-$port" \
        -p "$port:8080" \
        -v "${LOG_FOLDER}/${port}:/app/logs" \
        -e "PORT=$port" \
        --rm \
        "$DOCKER_IMAGE" >> "${LOG_FOLDER}/${port}/${CONSOLELOG}" 2>&1
    check_started "$port"
}

function stop {
    local port=$1
    if [[ -z ${KILL_INTERVAL_SECONDS} ]] ; then
        KILL_INTERVAL_SECONDS=5
    fi
    if [[ -z ${KILL_TRIES} ]] ; then
        KILL_TRIES=5
    fi
    CONTAINER_ID=$(docker ps -q --filter "publish=$port")
    if [[ -z $CONTAINER_ID  ]] ; then
        echo "Docker container is not running on $port port."
        # 정지된 컨테이너도 정리
        STOPPED_CONTAINER=$(docker ps -aq --filter "name=ncphw-release-$port")
        if [[ -n $STOPPED_CONTAINER ]]; then
            docker rm "$STOPPED_CONTAINER"
        fi
    else
        echo "Docker container is running on $port port. Container ID is $CONTAINER_ID."
        for (( TRY=1; TRY<=$KILL_TRIES; TRY++ ))
        do
            echo "Stopping Docker container... ($TRY/$KILL_TRIES)"
            docker stop "$CONTAINER_ID"
            echo "Sleep $KILL_INTERVAL_SECONDS seconds"
            sleep ${KILL_INTERVAL_SECONDS}
            if [[ -z $(docker ps -q --filter "id=$CONTAINER_ID") ]] ; then
                echo "Stopped Docker container"
                docker rm "$CONTAINER_ID"
                break
            fi
            if [[ ${TRY} == ${KILL_TRIES} ]] ; then
                return 1
            fi
        done
    fi
    return 0
}

function update_nginx {
    local port=$1
    local action=$2
    echo "Updating Nginx to $action traffic for port $port."
    if [[ $action == "remove" ]]; then
        sed -i "/server 127.0.0.1:$port;/d" "$NGINX_UPSTREAM_CONF"
    elif [[ $action == "add" ]]; then
        echo "server 127.0.0.1:$port;" >> "$NGINX_UPSTREAM_CONF"
    fi
    nginx -s reload
    if [[ $? -ne 0 ]]; then
        echo "Failed to reload Nginx. Exiting."
        exit 1
    else
        echo "Nginx update done."
    fi
}

function create_log_folder_if_not_exist {
    local port=$1
    if [[ ! -d "${LOG_FOLDER}/${port}" ]]; then
        mkdir -p "${LOG_FOLDER}/${port}"
        chmod 755 "${LOG_FOLDER}/${port}"
    fi
}

function deploy {
    local port=$1
    update_nginx "$port" "remove"
    stop "$port"
    if [[ $? -eq 1 ]]; then
        update_nginx "$port" "add"
        echo "ERROR : Can't stop Docker container."
        exit 1
    fi
    create_log_folder_if_not_exist "$port"
    start "$port"
    update_nginx "$port" "add"
}

for port in "${PORT_LIST[@]}"; do
    deploy "$port"
done
exit 0
