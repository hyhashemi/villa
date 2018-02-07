package com.apnozhan.villa.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apnozhan.villa.MainActivity;
import com.apnozhan.villa.requester.Request;

public abstract class AbstractFragment extends Fragment {

    private View rootView;
    private boolean initialized;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResId(), container, false);
            initialViews();
        }
        if(!initialized){
            initialized = true;
            initialArguments();
            initialContent();
        }
        commonActions();
        return rootView;
    }

    public void destroy(){
        initialized =false;
        getMainActivity().addRubbishFragment(this);

    }


    public abstract String getFragmentTag();

    public abstract int getLayoutResId();

    public abstract void initialArguments();

    public abstract void initialViews();

    public abstract void initialContent();

    public abstract void commonActions();

    public MainActivity getMainActivity(){
        return (MainActivity)rootView.getContext();
    }
    public void request(Request request){
        getMainActivity().request(request);
    }

    public View findViewById(int viewId) {
        return rootView.findViewById(viewId);
    }


}