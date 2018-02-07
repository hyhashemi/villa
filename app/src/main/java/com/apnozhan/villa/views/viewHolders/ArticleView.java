package com.apnozhan.villa.views.viewHolders;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apnozhan.villa.views.utils.ImageLoader;
import com.apnozhan.villa.R;
import com.apnozhan.villa.models.ArticleModel;

/**
 * Created by Y50-70 on 2/4/2018.
 */

public class ArticleView extends AbstractViewHolder {


    public ArticleView(ViewGroup parent, ArticleModel article_set) {
        super(parent, R.layout.article_layout);
        ImageView img = (ImageView) findViewById(R.id.article_pic);
        ImageLoader.loadImage(article_set.image, img);

        TextView name = (TextView) findViewById(R.id.article_title);
        name.setText(article_set.title);
    }


}
