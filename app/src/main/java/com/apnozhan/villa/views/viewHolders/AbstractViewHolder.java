package com.apnozhan.villa.views.viewHolders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apnozhan.villa.MainActivity;
import com.apnozhan.villa.requester.Request;

/**
 * Created by Y50-70 on 2/4/2018.
 */

public class AbstractViewHolder {
    private View view;

    public AbstractViewHolder(View view) {
        this.view = view;
    }

    public AbstractViewHolder(ViewGroup parent, int resId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate( resId, parent, false);
    }

    public View findViewById(int viewResId){
        return view.findViewById(viewResId);
    }

    public View getView() {
        return view;
    }

    public MainActivity getMainActivity(){
        return (MainActivity) view.getContext();
    }
    public void request(Request request){
        getMainActivity().request(request);
    }
    public void setOnClickListener(View.OnClickListener listener){
        getView().setOnClickListener(listener);
    }
}
