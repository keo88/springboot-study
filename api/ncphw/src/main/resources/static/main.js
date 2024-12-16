'use strict'

function refreshPage() {
    location.reload();
}

function goToServerCreatePage() {
    location.href = '/server/create';
}

async function fetchActionResponse(path) {
    let baseUrl = "http://localhost:8080/"
    const res = await fetch(baseUrl + path);
    if (res.ok) {
        refreshPage();
    } else {
        location.href = baseUrl + "error"
    }
}

const refreshButton = document.getElementById("refreshButton");
refreshButton.addEventListener('click', refreshPage);
const createButton = document.getElementById("createButton");
createButton.addEventListener('click', goToServerCreatePage);

const tRows = document.getElementById("table").children;

for (const row of tRows) {
    if (JSON.parse(row.dataset.pending)) {
        for (let i = 3; i < row.children.length; i++) {
            console.log(row.children[i].children[0]);
            row.children[i].querySelector("button").setAttribute("disabled", true);
        }

        continue;
    }

    const status = row.dataset.status;

    console.log('status ' + row.dataset.status);

    if (status.includes("INIT") || status.includes("CREAT")) {
        for (let i = 3; i < row.children.length; i++) {
            console.log(row.children[i].children[0]);
            row.children[i].querySelector("button").setAttribute("disabled", true);
        }

        continue;
    }

    if (status.includes("RUN")) {
        row.children[3].children[0].setAttribute("disabled", true);
    } else {
        row.children[3].addEventListener("click", (e) => {
            console.log("start server");
            fetchActionResponse(`server/${e.target.dataset.id}/start`);
        });
    }
    if (status.includes("STOP")) {
        row.children[4].children[0].setAttribute("disabled", true);
    } else {
        row.children[4].addEventListener("click", (e) => {
            console.log("stop server");
            fetchActionResponse(`server/${e.target.dataset.id}/stop`);
        })
    }
    row.children[5].addEventListener("click", (e) => {
        console.log("reboot server");
        fetchActionResponse(`server/${e.target.dataset.id}/reboot`);
    })
    row.children[6].addEventListener("click", (e) => {
        console.log("terminate server");
        fetchActionResponse(`server/${e.target.dataset.id}/terminate`);
    })
}