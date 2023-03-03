package com.example.fnvtrail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fnvtrail.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.example.fnvtrail.Fragments.TransferOutFragment;
import com.example.fnvtrail.Fragments.ProcurementFragment;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new TransferOutFragment());

        binding.botomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.transfer:
                        ReplaceFragment(new TransferOutFragment());
                        break;
                    case R.id.procurement:
                        ReplaceFragment(new ProcurementFragment());
                        break;
                }
                return  true;
            }
        });
    }

    // TODO: 01/03/23 use this ReplaceFragment(Fragment fragment) in other places as well.
    private void ReplaceFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}