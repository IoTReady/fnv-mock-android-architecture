package com.example.fnvMock.Models;

import androidx.annotation.NonNull;

public class ProcurementModel {

    String supplier, sku;

    public ProcurementModel(String supplier, String sku) {
        this.supplier = supplier;
        this.sku = sku;
    }
    public String getSupplier() { return supplier; }
    public String getSKU() { return sku; }
    @NonNull
    @Override
    public String toString() {
        return supplier + "" + sku;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public void setSKU(String sku) {
        this.sku = sku;
    }
}
