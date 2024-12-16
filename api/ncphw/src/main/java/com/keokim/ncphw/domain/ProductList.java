package com.keokim.ncphw.domain;

import java.util.List;

public class ProductList {
    private int totalRows;
    private List<Product> productList;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
