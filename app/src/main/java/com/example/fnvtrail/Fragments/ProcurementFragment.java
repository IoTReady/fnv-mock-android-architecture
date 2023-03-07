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

import com.example.fnvtrail.Models.ProcurementModel;
import com.example.fnvtrail.R;
import com.example.fnvtrail.ViewModels.ProcurementFragmentViewModel;
import com.example.fnvtrail.databinding.FragmentProcurementBinding;

import java.util.List;

public class ProcurementFragment extends Fragment {

    private ProcurementFragmentViewModel procurementFragmentViewModel;
    private FragmentProcurementBinding binding;
    private RecyclerViewFragment recyclerViewFragment;
    public static ProcurementFragment newInstance() {
        return new ProcurementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        procurementFragmentViewModel = new ViewModelProvider(requireActivity()).get(ProcurementFragmentViewModel.class);
        binding = FragmentProcurementBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerViewFragment = new RecyclerViewFragment();
        binding.completeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.procurement_cardView, recyclerViewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        procurementFragmentViewModel.getSupplierListLiveData().observe(this.requireActivity(), new Observer<List<ProcurementModel>>() {
            public void onChanged(List<ProcurementModel> procurementModels) {
                if(getContext() != null) {
                    ArrayAdapter<ProcurementModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, procurementModels);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    binding.supplierSpinner.setAdapter(adapter);
                }
            }
        });
        binding.supplierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProcurementModel selectedSupplier = (ProcurementModel) adapterView.getItemAtPosition(i);
                String selectedSupplierString = selectedSupplier.getSupplier();
                procurementFragmentViewModel.getSelectedSupplier().setValue(selectedSupplierString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });
        procurementFragmentViewModel.getSkuListLiveData().observe(this.requireActivity(), new Observer<List<ProcurementModel>>() {
            public void onChanged(List<ProcurementModel> procurementModels) {
                if (getContext() != null) {
                    ArrayAdapter<ProcurementModel> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, procurementModels);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    binding.skuSpinner.setAdapter(adapter);
                }
            }
        });
        binding.skuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProcurementModel selectedSKU = (ProcurementModel) adapterView.getItemAtPosition(i);
                String selectedSKUString = selectedSKU.getSKU();
                procurementFragmentViewModel.getSelectedSKU().setValue(selectedSKUString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}