//package com.apnozhan.villa;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//
//import com.apnozhan.villa.models.MenuModel;
//import com.apnozhan.villa.models.SubMenuModel;
//import com.apnozhan.villa.requester.ListWaiter;
//
//import java.util.List;
//
///**
// * Created by Y50-70 on 1/31/2018.
// */
//
//public class TabsPagerAdapter extends FragmentPagerAdapter implements ListWaiter<SubMenuModel> {
//    private MenuModel menu;
//
//    public TabsPagerAdapter(FragmentManager fm, MenuModel menuModel) {
//        super(fm);
//        this.menu = menuModel;
//
//    }
//
//    @Override
//    public int getCount() {
//        return menu.sm_set.size();
//    }
//
//
//    @Override
//    public Fragment getItem(int position) {
//        AbstractFragment subMenuFragment = new SubMenuFragment();
//        Bundle firstArgs = new Bundle();
//        firstArgs.putSerializable(SubMenuFragment.SUB_MENU, menu.sm_set.get(position));
//        subMenuFragment.setArguments(firstArgs);
//        return subMenuFragment;
//    }
//
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return menu.sm_set.get(position).title;
//    }
//
//    @Override
//    public boolean isAlive() {
//        return false;
//    }
//
//    @Override
//    public void onFailure(int requestId, int statusCode) {
//
//    }
//
//    @Override
//    public void onSuccess(int requestId, int statusCode, List<SubMenuModel> response) {
//
//        AbstractFragment subMenuFragment = new SubMenuFragment();
//        Bundle firstArgs = new Bundle();
//        firstArgs.putSerializable(SubMenuFragment.SUB_MENU, menu.sm_set.get());
//        subMenuFragment.setArguments(firstArgs);
//        ViewPager vpPager = (ViewPager) findViewById(R.id.viewPager);
//        myAdapter = new TabsPagerAdapter(getSupportFragmentManager(), response);
//        vpPager.setAdapter(myAdapter);
//    }
//}
