package com.keokim.ncphw.domain;

import java.util.List;

public class AccessControlGroupList {
    private Integer totalRows;
    private List<AccessControlGroup> accessControlGroupList;

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List<AccessControlGroup> getAccessControlGroupList() {
        return accessControlGroupList;
    }

    public void setAccessControlGroupList(List<AccessControlGroup> accessControlGroupList) {
        this.accessControlGroupList = accessControlGroupList;
    }
}

