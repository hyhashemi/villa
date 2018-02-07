package com.apnozhan.villa.views.fragments;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apnozhan.villa.MainActivity;
import com.apnozhan.villa.R;
import com.apnozhan.villa.views.viewHolders.ThemeView;
import com.apnozhan.villa.models.ArticleModel;
import com.apnozhan.villa.models.SearchModel;
import com.apnozhan.villa.models.ThemeModel;
import com.apnozhan.villa.requester.ModelWaiter;
import com.apnozhan.villa.requester.Request;
import com.apnozhan.villa.requester.RequestParams;
import com.apnozhan.villa.views.viewHolders.ArticleView;

/**
 * Created by Y50-70 on 2/4/2018.
 */

public class SearchFragment extends AbstractFragment implements ModelWaiter<SearchModel> {
    public static final String TAG = "searchFragment";
    private SearchModel searchModel;
    private LinearLayout articlesContainer;
    private LinearLayout themesContainerColumn1;
    private LinearLayout themesContainerColumn2;


    @Override
    public int getLayoutResId() {
        return R.layout.base_layout;
    }

    @Override
    public void initialArguments() {
    }

    @Override
    public void initialViews() {
        articlesContainer = (LinearLayout) findViewById(R.id.article_box);
        themesContainerColumn1 = (LinearLayout) findViewById(R.id.theme_column1);
        themesContainerColumn2 = (LinearLayout) findViewById(R.id.theme_column2);

    }

    @Override
    public void destroy() {
        super.destroy();
        articlesContainer.removeAllViews();
        themesContainerColumn1.removeAllViews();
        themesContainerColumn2.removeAllViews();
    }

    @Override
    public void initialContent() {
        RequestParams params = new RequestParams();
        params.put("lang", "ar");
        Request<SearchModel> myreq = new Request<>(Request.METHOD_GET, "configs/", SearchModel.class, this, params);
        request(myreq);
    }

    public static SearchFragment getInstance(MainActivity mainActivity) {
        AbstractFragment toReturn = mainActivity.getInstance(TAG);
        if (toReturn != null) {
            return (SearchFragment) toReturn;
        } else {
            return new SearchFragment();
        }
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void commonActions() {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void onFailure(int requestId, int statusCode) {
        Toast.makeText(getMainActivity(), "failure", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(int requestId, int statusCode, SearchModel response) {
        this.searchModel = response;
        if (searchModel.article_set != null) {
            for (ArticleModel articleModel : searchModel.article_set) {
                ArticleView articleView = new ArticleView(articlesContainer, articleModel);
                articlesContainer.addView(articleView.getView());
            }
        }

        for (int i = 0; i < searchModel.theme_set.size(); i++) {
            ThemeModel themeModel = searchModel.theme_set.get(i);
            if (themeModel != null) {
                LinearLayout parent;
                if (i % 2 == 0) {
                    parent = themesContainerColumn1;

                } else {
                    parent = themesContainerColumn2;
                }
                ThemeView themeView = new ThemeView(parent, themeModel);
                parent.addView(themeView.getView());

            }
        }
    }
}
