'use strict';

const vpcList = document.getElementById("vpcList");
const subnetLists = document.getElementById("subnetLists").children;
const acgLists = document.getElementById("acgLists").children;

const vpcInputElements = document.getElementsByName('vpcNo');
const subnetInputElements = document.getElementsByName('subnetNo');
const acgInputElements = document.getElementsByName('acgNo');


const vpcDependentList = [];

for (let i = 0; i < subnetLists.length; i++) {
    vpcDependentList.push(...subnetLists[i].children);
}
for (let i = 0; i < acgLists.length; i++) {
    vpcDependentList.push(...acgLists[i].children);
}

function uncheckVpcDependentList() {
    subnetInputElements.forEach((element) => {
        element.checked = false;
    });
    acgInputElements.forEach((element) => {
        element.checked = false;
    });
}

let lastCheckedVpc = undefined;
vpcInputElements.forEach((element) => {
    element.addEventListener('click', (e) => {
        if (e.target !== lastCheckedVpc)
            uncheckVpcDependentList();
        lastCheckedVpc = e.target;
    })
});

function filterVpcNoItem(selectedVpcNo) {
    vpcDependentList.forEach(function(element) {
        if (element.dataset.parentvpcno === selectedVpcNo) {
            element.style.display = 'block';
        } else {
            element.style.display = 'none';
        }
    });
}


vpcList.addEventListener('click', (event) => {
    if (event.target && event.target.nodeName === "INPUT" && event.target.type === "radio") {
        const selectedVpcNo = event.target.value;
        filterVpcNoItem(selectedVpcNo);
    }
})