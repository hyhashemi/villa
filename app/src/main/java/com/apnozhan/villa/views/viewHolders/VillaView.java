package com.apnozhan.villa.views.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apnozhan.villa.R;
import com.apnozhan.villa.models.VillaModel;
import com.apnozhan.villa.views.utils.FormatHelper;
import com.apnozhan.villa.views.utils.ImageLoader;

/**
 * Created by Y50-70 on 1/23/2018.
 */

class VillaView {
    private View rootView;

    public VillaView(View rootView, VillaModel villa) {
        this.rootView = rootView;

        TextView capacity = (TextView) findViewById(R.id.villa_view_capacity);
        String capacityText = villa.capacity + " نفر";
        capacity.setText(capacityText);
        TextView area = (TextView) findViewById(R.id.villa_view_address);
        String areaText = "شهر " + villa.address;
        area.setText(areaText);
        TextView price = (TextView) findViewById(R.id.villa_view_price);
        String priceText = FormatHelper.getPrice(villa.price.get(0).price) + " تومان";
        price.setText(priceText);
        ImageView image = (ImageView) findViewById(R.id.villa_view_image);
        ImageLoader.loadImage(villa.image_set.get(0).image, image);

        TextView viewType = (TextView) findViewById(R.id.villa_view_type);
        String type = villa.type;
        viewType.setText(type);

    }

    private View findViewById(int viewResId) {
        return rootView.findViewById(viewResId);
    }

}
