package com.client.launcher;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.launcher.mylibrary.AppInfo;
import com.launcher.mylibrary.AppsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DrawerFragment extends Fragment {

    private static List<AppInfo> appsList;
    RecyclerView recyclerView;
    DrawerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        setUpApps(view);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void setUpApps(View view) {
        appsList = new ArrayList<>();
        appsList = AppsManager.getInstance().getInstalledApps(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recylerView);

        adapter = new DrawerAdapter(appsList, getContext());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    public void search(String searchText) {
        Log.e("Search", "" + searchText);

        List<AppInfo> filteredlist = new ArrayList<>();
        filteredlist = appsList.stream()
                .filter(str -> str.getLabel().trim().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
        adapter.filterList(filteredlist);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.actionSearch).setVisible(true);
    }
}