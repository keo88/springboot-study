package com.keokim.ncphw.domain;

import java.util.ArrayList;
import java.util.List;

public class ServerInstanceList {
    public Integer totalRows;
    public List<ServerInstance> serverInstanceList = new ArrayList<>();

    public List<ServerInstance> getServerInstanceList() {
        return serverInstanceList;
    }

    public void setServerInstanceList(List<ServerInstance> serverInstanceList) {
        this.serverInstanceList = serverInstanceList;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }
}
