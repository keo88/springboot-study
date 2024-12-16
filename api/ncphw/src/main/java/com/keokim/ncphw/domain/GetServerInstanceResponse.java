package com.keokim.ncphw.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GetServerInstanceResponse {
    @JsonAlias({"getServerInstanceListResponse", "createServerInstancesResponse", "startServerInstancesResponse", "stopServerInstancesResponse", "rebootServerInstancesResponse", "terminateServerInstancesResponse"})
    private ServerInstanceList serverList;

    public ServerInstanceList getServerList() {
        return serverList;
    }

    public void setServerList(ServerInstanceList serverList) {
        this.serverList = serverList;
    }
}
