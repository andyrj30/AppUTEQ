package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.example.andres_dell.uteqdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UniversidadFragment extends Fragment {

    View view;
    private FragmentPagerItemAdapter adapter;
    private ViewPager viewPager;

    public UniversidadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_universidad, container, false);

        return view;
    }

    @Override
    public void onResume() {
        String ip= "http://"+UIUtil.ipAConetarse(view.getContext());

        adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(view.getContext())
                .add(FragmentPagerItem.of("Historia", WebViewFragment.class, WebViewFragment.arguments(ip+Constants.WS_HISTORIA)))
                .add(FragmentPagerItem.of("Acerca de.", WebViewFragment.class, WebViewFragment.arguments(ip+Constants.WS_ACERCADE)))
                .add(FragmentPagerItem.of("Autoridades", WebViewFragment.class, WebViewFragment.arguments(ip+Constants.WS_AUTORIDADES)))
                .add(FragmentPagerItem.of("Consejo Universitario", WebViewFragment.class, WebViewFragment.arguments(ip+Constants.WS_CONSEJO)))
                .add(FragmentPagerItem.of("Identidad corporativa", WebViewFragment.class, WebViewFragment.arguments(ip+Constants.WS_IDENTIDAD)))
                .create());

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("La universidad");

        super.onResume();
    }

}