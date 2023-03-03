package com.example.fnvtrail.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fnvtrail.Adapters.RecyclerViewAdapter;
import com.example.fnvtrail.Models.ProcurementModel;
import com.example.fnvtrail.ViewModels.ProcurementFragmentViewModel;
import com.example.fnvtrail.ViewModels.RecyclerViewViewModel;
import com.example.fnvtrail.R;

import java.util.List;

public class RecyclerViewFragment extends Fragment {

    private RecyclerViewViewModel mViewModel;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.common_recycler_view);
        ProcurementFragmentViewModel mViewModel = new ProcurementFragmentViewModel();

        mViewModel.getSkuListLiveData().observe(getViewLifecycleOwner(), new Observer<List<ProcurementModel>>() {
            @Override
            public void onChanged(List<ProcurementModel> procurementModels) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
//                recyclerView.setAdapter(new RecyclerViewAdapter.ViewHolder());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecyclerViewViewModel.class);
        // TODO: Use the ViewModel
    }
}