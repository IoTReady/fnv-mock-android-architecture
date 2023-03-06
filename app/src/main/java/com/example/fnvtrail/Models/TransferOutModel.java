package com.example.fnvtrail.Models;

import androidx.annotation.NonNull;

public class TransferOutModel {
    String warehouse;

    public TransferOutModel(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @NonNull
    @Override
    public String toString() {
        return warehouse;
    }
}