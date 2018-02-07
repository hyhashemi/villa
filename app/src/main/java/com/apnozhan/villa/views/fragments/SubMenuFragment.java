package com.apnozhan.villa.views.fragments;

import android.widget.LinearLayout;

import com.apnozhan.villa.views.viewHolders.MenuItemView;
import com.apnozhan.villa.R;
import com.apnozhan.villa.models.MenuItemModel;
import com.apnozhan.villa.models.SubMenuModel;


/**
 * Created by Y50-70 on 2/4/2018.
 */

public class SubMenuFragment extends AbstractFragment {
    public static final String TAG= "SubMenuFragment";
    public static final String SUB_MENU = "sub menu";
    public SubMenuModel subMenuModel;

    LinearLayout linearLayout;


    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.sub_menu_fragment;
    }

    @Override
    public void initialArguments() {
        subMenuModel = (SubMenuModel)getArguments().getSerializable(SUB_MENU);
    }

    @Override
    public void initialViews() {
        linearLayout = (LinearLayout) findViewById(R.id.list);

    }

    @Override
    public void initialContent() {
        for (MenuItemModel menuItemModel : subMenuModel.mi_set) {
            MenuItemView miv = new MenuItemView(linearLayout, menuItemModel);
            linearLayout.addView(miv.getView());
        }
    }

    @Override
    public void commonActions() {

    }

}
