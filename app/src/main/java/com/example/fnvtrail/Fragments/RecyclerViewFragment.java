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

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.example.fnvtrail.Adapters.RecyclerViewAdapter;
    import com.example.fnvtrail.generateCrateID;
    import com.example.fnvtrail.ViewModels.ProcurementFragmentViewModel;
    import com.example.fnvtrail.ViewModels.RecyclerViewViewModel;
    import com.example.fnvtrail.R;
    import com.example.fnvtrail.ViewModels.TransferOutFragmentViewModel;

    import java.util.Arrays;

    public class RecyclerViewFragment extends Fragment {

        private RecyclerViewViewModel mViewModel;

        private RecyclerViewAdapter adapter;
        public static RecyclerViewFragment newInstance() {
            return new RecyclerViewFragment();
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.common_recycler_view);
            ProcurementFragmentViewModel procurementFragmentViewModel = new ViewModelProvider(requireActivity()).get(ProcurementFragmentViewModel.class);
            TransferOutFragmentViewModel transferOutFragmentViewModel = new ViewModelProvider(requireActivity()).get(TransferOutFragmentViewModel.class);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
            adapter = new RecyclerViewAdapter(Arrays.asList());
            recyclerView.setAdapter(adapter);

            if (requireActivity().findViewById(R.id.fragment_procurement_id) != null) {
                procurementFragmentViewModel.getSelectedSupplier().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String selectedSupplier) {
                        String selectedSKU = procurementFragmentViewModel.getSelectedSKU().getValue();
                        adapter.setData(Arrays.asList(selectedSupplier, selectedSKU));
                        generateCrateIDList();
                        Log.d("selected Supplier", selectedSupplier + " | selected SKU: " + selectedSKU);
                    }
                });
                procurementFragmentViewModel.getSelectedSKU().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String selectedSKU) {
                        String selectedSupplier = procurementFragmentViewModel.getSelectedSupplier().getValue();
                        adapter.setData(Arrays.asList(selectedSupplier, selectedSKU));
                        generateCrateIDList();
                        Log.d("selected SKU", selectedSKU + " | selected Supplier: " + selectedSupplier);
                    }
                });
            }
            else if (requireActivity().findViewById(R.id.fragment_transferout_id) != null) {
                transferOutFragmentViewModel.getSelectedWarehouse().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String selectedWarehouse) {
                        Log.d("selected Warehouse", selectedWarehouse);
                        adapter.setData(Arrays.asList(selectedWarehouse));
                        generateCrateIDList();
                    }
                });
            }
            return view;
        }
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            mViewModel = new ViewModelProvider(this).get(RecyclerViewViewModel.class);
            // TODO: Use the ViewModel
        }

        private void generateCrateIDList() {
            for(int i = 0; i < 4; i++) {
                String crateID = "Crate ID: " + String.valueOf(generateCrateID.generateCrateID());
                adapter.addData(crateID);
                adapter.notifyItemRangeChanged(0,10);
            }
        }
    }