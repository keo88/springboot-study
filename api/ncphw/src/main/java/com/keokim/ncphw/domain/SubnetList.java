package com.keokim.ncphw.domain;

import java.util.List;

public class SubnetList {
    private int totalRows;
    private List<Subnet> subnetList;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<Subnet> getSubnetList() {
        return subnetList;
    }

    public void setSubnetList(List<Subnet> subnetList) {
        this.subnetList = subnetList;
    }
}