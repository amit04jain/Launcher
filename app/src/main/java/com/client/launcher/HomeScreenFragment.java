package com.client.launcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HomeScreenFragment extends Fragment {


    ImageView imageViewDrawer;

    public HomeScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        setHasOptionsMenu(false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewDrawer = view.findViewById(R.id.icon_drawer);


        imageViewDrawer.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                loadFragment(new DrawerFragment());
            }
        });


    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, "appslist")
                    .commit();
            return true;
        }

        return false;
    }

}