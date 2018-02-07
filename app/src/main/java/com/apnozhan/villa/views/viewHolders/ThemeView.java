package com.apnozhan.villa.views.viewHolders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apnozhan.villa.MainActivity;
import com.apnozhan.villa.R;
import com.apnozhan.villa.models.ThemeModel;
import com.apnozhan.villa.views.fragments.SearchFragment;
import com.apnozhan.villa.views.utils.ImageLoader;

/**
 * Created by Y50-70 on 2/4/2018.
 */

public class ThemeView extends AbstractViewHolder implements View.OnClickListener{
    public ThemeView(ViewGroup parent, ThemeModel theme_set) {
        super(parent, R.layout.theme_layout);
        ImageView img = (ImageView) findViewById(R.id.theme_pic);
        ImageLoader.loadImage(theme_set.cp, img);
        TextView name = (TextView) findViewById(R.id.theme_title);
        name.setText(theme_set.name);
        this.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        SearchFragment sf = SearchFragment.getInstance(getMainActivity());
        MainActivity ma = getMainActivity();
        ma.addFragment(sf);
    }
}
