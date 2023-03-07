package com.example.fnvtrail.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fnvtrail.Models.TransferOutModel;
import com.example.fnvtrail.R;
import com.example.fnvtrail.ViewModels.TransferOutFragmentViewModel;
import com.example.fnvtrail.databinding.FragmentTransferOutBinding;

import java.util.List;

public class TransferOutFragment extends Fragment {

    public static TransferOutFragment newInstance() {
        return new TransferOutFragment();
    }
    private FragmentTransferOutBinding binding;
    private RecyclerViewFragment recyclerViewFragment;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // inflating the view with TransferOut Layout
        binding = FragmentTransferOutBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // creating a ViewModel
        TransferOutFragmentViewModel transferOutFragmentViewModel = new ViewModelProvider(requireActivity()).get(TransferOutFragmentViewModel.class);
        recyclerViewFragment = new RecyclerViewFragment();
        binding.getCrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.transfer_out_cardView, recyclerViewFragment);
                transaction.commit();
            }
        });

        transferOutFragmentViewModel.getWarehouseListLiveData().observe(this.requireActivity(), new Observer<List<TransferOutModel>>() {
            @Override
            public void onChanged(List<TransferOutModel> transfer2) {
                if(getContext() != null) {
                    ArrayAdapter<TransferOutModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, transfer2);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    binding.searchWarehouseSpinner.setAdapter(adapter);
                }
            }
        });
        binding.searchWarehouseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TransferOutModel selectedWarehouse = (TransferOutModel) adapterView.getItemAtPosition(i);
                String selectedWarehouseString = selectedWarehouse.getWarehouse();
                transferOutFragmentViewModel.getSelectedWarehouse().setValue(selectedWarehouseString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}