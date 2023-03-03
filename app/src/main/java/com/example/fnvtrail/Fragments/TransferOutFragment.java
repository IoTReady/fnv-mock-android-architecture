package com.example.fnvtrail.Fragments;

import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fnvtrail.Models.TransferOutModel;
import com.example.fnvtrail.ViewModels.TransferOutFragmentViewModel;
import com.example.fnvtrail.R;

import java.util.List;
import java.util.Objects;

public class TransferOutFragment extends Fragment {

    public static TransferOutFragment newInstance() {
        return new TransferOutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // inflating the view with TransferOut Layout
        View view=inflater.inflate(R.layout.fragment_transfer_out, container, false);

        // creating a ViewModel
        TransferOutFragmentViewModel transferOutFragmentViewModel = new ViewModelProvider(requireActivity()).get(TransferOutFragmentViewModel.class);
        // show the recycler view when the button is clicked
        Button transferOutButton = view.findViewById(R.id.transfer_out_button);
        transferOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating a recyclerViewFragment instance and replacing it with the card-view
                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.transfer_out_cardView, recyclerViewFragment);
                transaction.commit();
            }
        });

        // creating a spinner
        Spinner spinner =  view.findViewById(R.id.search_hub_spinner);
        transferOutFragmentViewModel.getDestinationListLiveData().observe(this.requireActivity(), new Observer<List<TransferOutModel>>() {
            @Override
            public void onChanged(List<TransferOutModel> transfer2) {
                if(getContext() != null) {
                    ArrayAdapter<TransferOutModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, transfer2);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner.setAdapter(adapter);
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TransferOutModel selectedDestination = (TransferOutModel) adapterView.getItemAtPosition(i);
                String selectedWarehouse = selectedDestination.getDestination();
                transferOutFragmentViewModel.getSelectedWarehouseLiveData().setValue(selectedWarehouse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }
}