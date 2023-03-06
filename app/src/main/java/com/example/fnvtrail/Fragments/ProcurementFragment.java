package com.example.fnvtrail.Fragments;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.fnvtrail.Models.ProcurementModel;
import com.example.fnvtrail.ViewModels.ProcurementFragmentViewModel;
import com.example.fnvtrail.R;

import java.util.List;

public class ProcurementFragment extends Fragment {

    private ProcurementFragmentViewModel mViewModel;
    public static ProcurementFragment newInstance() {
        return new ProcurementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_procurement, container, false);

        ProcurementFragmentViewModel procurementFragmentViewModel = new ViewModelProvider(requireActivity()).get(ProcurementFragmentViewModel.class);

        Button completeActivity = view.findViewById(R.id.complete_activity);
        completeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.procurement_cardView, recyclerViewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // creating a spinner
        Spinner supplierSpinner =  view.findViewById(R.id.supplier_spinner);
        procurementFragmentViewModel.getSupplierListLiveData().observe(this.requireActivity(), new Observer<List<ProcurementModel>>() {
            public void onChanged(List<ProcurementModel> procurementModels) {
                if(getContext() != null) {
                    ArrayAdapter<ProcurementModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, procurementModels);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    supplierSpinner.setAdapter(adapter);
                }
            }
        });
        supplierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProcurementModel selectedSupplier = (ProcurementModel) adapterView.getItemAtPosition(i);
                String selectedSupplierString = selectedSupplier.getSupplier();
                procurementFragmentViewModel.getSelectedSupplier().setValue(selectedSupplierString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinner skuSpinner =  view.findViewById(R.id.sku_spinner);

        procurementFragmentViewModel.getSkuListLiveData().observe(this.requireActivity(), new Observer<List<ProcurementModel>>() {
            public void onChanged(List<ProcurementModel> procurementModels) {
                if (getContext() != null) {
                    ArrayAdapter<ProcurementModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, procurementModels);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    skuSpinner.setAdapter(adapter);
                }
            }
        });
        skuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProcurementModel selectedSKU = (ProcurementModel) adapterView.getItemAtPosition(i);
                String selectedSKUString = selectedSKU.getSKU();
                procurementFragmentViewModel.getSelectedSKU().setValue(selectedSKUString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProcurementFragmentViewModel.class);
        // TODO: Use the ViewModel
    }
}