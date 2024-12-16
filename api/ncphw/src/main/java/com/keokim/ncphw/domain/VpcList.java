package com.keokim.ncphw.domain;

import java.util.ArrayList;
import java.util.List;

public class VpcList {
    private int totalRows;
    private List<Vpc> vpcList = new ArrayList<>();

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<Vpc> getVpcList() {
        return vpcList;
    }

    public void setVpcList(List<Vpc> vpcList) {
        this.vpcList = vpcList;
    }
}
