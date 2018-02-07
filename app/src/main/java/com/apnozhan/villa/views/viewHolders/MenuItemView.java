package com.apnozhan.villa.views.viewHolders;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apnozhan.villa.R;
import com.apnozhan.villa.models.MenuItemModel;
import com.apnozhan.villa.views.utils.FormatHelper;
import com.apnozhan.villa.views.utils.ImageLoader;

/**
 * Created by Y50-70 on 2/1/2018.
 */

public class MenuItemView  extends AbstractViewHolder{
    public MenuItemView(ViewGroup parent, MenuItemModel menuItemModel){
        super(parent, R.layout.menu_item_view_layout);
        ImageView img = (ImageView) findViewById(R.id.food_pic);
        ImageLoader.loadImage(menuItemModel.image, img);
        TextView name = (TextView) findViewById(R.id.name_view);
        name.setText(menuItemModel.name);
        TextView desc = (TextView) findViewById(R.id.view_desc);
        desc.setText(menuItemModel.desc);
        TextView price_view = (TextView) findViewById(R.id.view_price);
        String myPrice = FormatHelper.getPrice(menuItemModel.price) + " تومان";
        price_view.setText(myPrice);
        TextView promo_per = (TextView) findViewById(R.id.promo_per);
        promo_per.setText(String.valueOf(menuItemModel.promo_per) + "% ");
        TextView count_l_view = (TextView) findViewById(R.id.count_l);
        float count_l_num = menuItemModel.count_l*5/10 -1;
        count_l_view.setText(String.valueOf(count_l_num));
    }
}
