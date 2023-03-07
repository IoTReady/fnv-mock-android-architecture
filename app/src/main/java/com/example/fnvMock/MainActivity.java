package com.example.fnvMock;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fnvMock.Fragments.ProcurementFragment;
import com.example.fnvMock.Fragments.TransferOutFragment;
import com.example.fnvMock.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // to save app state throughout system configurations
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
        } else {
            currentFragment = new TransferOutFragment();
        }

        ReplaceFragment(currentFragment);

        binding.botomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.transfer:
                        currentFragment = new TransferOutFragment();
                        ReplaceFragment(currentFragment);
                        break;
                    case R.id.procurement:
                        currentFragment = new ProcurementFragment();
                        ReplaceFragment(currentFragment);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
    }

    private void ReplaceFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}