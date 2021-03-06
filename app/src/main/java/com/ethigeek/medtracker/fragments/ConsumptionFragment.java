package com.ethigeek.medtracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethigeek.medtracker.R;
import com.ethigeek.medtracker.activities.AddOrUpdateConsumptionActivity;
import com.ethigeek.medtracker.activities.MainActivity;
import com.ethigeek.medtracker.adapters.ConsumptionListAdapter;

import static com.ethigeek.medtracker.utils.Constants.*;


/**
 * Class for consumption fragement operation
 *
 * @author Ethiraj Srinivasan
 */
public class ConsumptionFragment extends Fragment {

    private ConsumptionListAdapter mAdapter;
    private Context context;
    private TabLayout tabs;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consumption_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        FloatingActionButton fabSOS = (FloatingActionButton) getActivity().findViewById(R.id.fabSOS);
        TextView tvSOS = (TextView) getActivity().findViewById(R.id.tv_sos);
        fabSOS.setVisibility(View.GONE);
        tvSOS.setVisibility(View.GONE);

        ((MainActivity) getActivity()).setFloatingActionButtonAction(AddOrUpdateConsumptionActivity.class);
        context = getActivity().getApplicationContext();
        getActivity().setTitle(CONSUMPTIONS);

        // Setting ViewPager for each Tabs
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        //hide the share button
        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

        // Set Tabs inside Toolbar
        tabs = (TabLayout) getActivity().findViewById(R.id.tabs);
        tabs.setVisibility(View.VISIBLE);
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        Adapter adapter = new Adapter(getChildFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabs.post(new Runnable() {
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
            }
        });


        return view;

    }


    // Add Fragments to Tabs


    static class Adapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        String[] tabTitles = {"Consumption by Category", "Consumption by Medicine", "Unconsumed Medicines"};

        public Adapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        /**
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        /**
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ConsumptionByCategoryTab categories = new ConsumptionByCategoryTab();
                    return categories;
                case 1:
                    ConsumptionByMedicineTab medicines = new ConsumptionByMedicineTab();
                    return medicines;
                case 2:
                    UnConsumedMedicineTab unconsumedMedicines = new UnConsumedMedicineTab();
                    return unconsumedMedicines;

                default:
                    return null;
            }
        }

        /**
         * @return
         */
        @Override
        public int getCount() {
            return mNumOfTabs;
        }

    }


}
