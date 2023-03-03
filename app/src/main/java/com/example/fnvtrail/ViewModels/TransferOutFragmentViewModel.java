package com.example.fnvtrail.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fnvtrail.Models.TransferOutModel;

import java.util.ArrayList;
import java.util.List;

public class TransferOutFragmentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<List<TransferOutModel>> destinationList;
    private final MutableLiveData<String> selectedWarehouseLiveData;

    public TransferOutFragmentViewModel(){
        destinationList = new MutableLiveData<>();
        selectedWarehouseLiveData = new MutableLiveData<>();
        destinationList.postValue(getDestinationList());
    }

    public MutableLiveData<String> getSelectedWarehouseLiveData() {
        return selectedWarehouseLiveData;
    }

    public List<TransferOutModel> getDestinationList() {
        List<TransferOutModel> destinationList = new ArrayList<>();
        destinationList.add(new TransferOutModel("Warehouse 1"));
        destinationList.add(new TransferOutModel("Warehouse 2"));
        destinationList.add(new TransferOutModel("Warehouse 3"));
        return destinationList;
    }

    public LiveData<List<TransferOutModel>> getDestinationListLiveData() {
        return destinationList;
    }
}