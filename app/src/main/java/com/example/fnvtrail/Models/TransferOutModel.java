package com.example.fnvtrail.Models;

import androidx.annotation.NonNull;

public class TransferOutModel {
    String destination;

    public TransferOutModel(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @NonNull
    @Override
    public String toString() {
        return destination;
    }
}