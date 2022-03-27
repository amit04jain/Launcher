package com.client.launcher;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.launcher.mylibrary.AppsUpdateBroadcastReceiver;

public class HomeActivity extends AppCompatActivity {
    AppsUpdateBroadcastReceiver appsUpdateBroadcastReceiver = new AppsUpdateBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeScreenFragment());
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;

        }

        return false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(appsUpdateBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(appsUpdateBroadcastReceiver);

    }

    @Override
    public void onBackPressed() {

        loadFragment(new HomeScreenFragment());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(searchText);
                return false;
            }
        });
        return true;
    }

    private void filter(String searchText) {
        Log.e("Query", searchText);
        try {
            if (null != searchText) {
                DrawerFragment fragment = (DrawerFragment) getSupportFragmentManager().findFragmentByTag("appslist");
                if (null != fragment) {
                    fragment.search(searchText);
                }
            }
        } catch (Exception e) {

        }
    }


}