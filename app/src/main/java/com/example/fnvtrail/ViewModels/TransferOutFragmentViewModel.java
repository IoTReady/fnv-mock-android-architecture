package com.example.fnvtrail.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fnvtrail.Models.TransferOutModel;

import java.util.ArrayList;
import java.util.List;

public class TransferOutFragmentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<List<TransferOutModel>> warehouseList;
    private final MutableLiveData<String> selectedWarehouse;

    public TransferOutFragmentViewModel(){
        warehouseList = new MutableLiveData<>();
        warehouseList.postValue(getWarehouseList());

        selectedWarehouse = new MutableLiveData<>();
        selectedWarehouse.postValue("");
    }

    public MutableLiveData<String> getSelectedWarehouse() {
        return selectedWarehouse;
    }

    public List<TransferOutModel> getWarehouseList() {
        List<TransferOutModel> warehouseList = new ArrayList<>();
        warehouseList.add(new TransferOutModel("Warehouse 1"));
        warehouseList.add(new TransferOutModel("Warehouse 2"));
        warehouseList.add(new TransferOutModel("Warehouse 3"));
        return warehouseList;
    }

    public LiveData<List<TransferOutModel>> getWarehouseListLiveData() {
        return warehouseList;
    }
}