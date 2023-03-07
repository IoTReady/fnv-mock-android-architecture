    package com.example.fnvtrail.Fragments;

    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.recyclerview.widget.DividerItemDecoration;
    import androidx.recyclerview.widget.LinearLayoutManager;

    import com.example.fnvtrail.Adapters.RecyclerViewAdapter;
    import com.example.fnvtrail.GenerateCrateID;
    import com.example.fnvtrail.R;
    import com.example.fnvtrail.ViewModels.ProcurementFragmentViewModel;
    import com.example.fnvtrail.ViewModels.TransferOutFragmentViewModel;
    import com.example.fnvtrail.databinding.FragmentRecyclerViewBinding;

    import java.util.Arrays;

    public class RecyclerViewFragment extends Fragment {

        private RecyclerViewAdapter adapter;
        private FragmentRecyclerViewBinding binding;
        public static RecyclerViewFragment newInstance() {
            return new RecyclerViewFragment();
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            binding = FragmentRecyclerViewBinding.inflate(getLayoutInflater(), container, true);
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ProcurementFragmentViewModel procurementFragmentViewModel = new ViewModelProvider(requireActivity()).get(ProcurementFragmentViewModel.class);
            TransferOutFragmentViewModel transferOutFragmentViewModel = new ViewModelProvider(requireActivity()).get(TransferOutFragmentViewModel.class);

            binding.commonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.commonRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
            adapter = new RecyclerViewAdapter(Arrays.asList());
            binding.commonRecyclerView.setAdapter(adapter);

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
        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }

        private void generateCrateIDList() {
            for(int i = 0; i < 4; i++) {
                String crateID = "Crate ID: " + String.valueOf(GenerateCrateID.generateCrateID());
                adapter.addData(crateID);
                adapter.notifyItemRangeChanged(0,10);
            }
        }
    }