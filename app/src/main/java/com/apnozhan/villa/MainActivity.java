package com.apnozhan.villa;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apnozhan.villa.requester.Request;
import com.apnozhan.villa.requester.Requester;
import com.apnozhan.villa.views.fragments.AbstractFragment;
import com.apnozhan.villa.views.fragments.SearchFragment;

import java.util.ArrayList;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private Requester requester;
    private HashMap<String, ArrayList<AbstractFragment>> rubbishFragments;
    private ArrayList<AbstractFragment> stack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requester = new Requester(this);
        rubbishFragments = new HashMap<>();
        stack = new ArrayList<>();
        SearchFragment searchFragment = SearchFragment.getInstance(this);
        Log.e("before call", "onCreate:" );
        addFragment(searchFragment);
    }


    public void request(Request request) {
        requester.request(request);
    }

    @Override
    public void onBackPressed() {
        if (stack.size() == 1 )
         super.onBackPressed();
        else{
            AbstractFragment toDelete = stack.remove(stack.size()-1);
            toDelete.destroy();
            AbstractFragment next = stack.get(stack.size()-1);
            placeFragment(next);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    //   @Override
    //   public void onSuccess(int requestId, int statusCode, MenuModel response) {
    //       Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

//        ArrayList<SubMenuModel> rubbish = new ArrayList<>();
//        for(SubMenuModel subMenuModel:response.sm_set){
//            if(subMenuModel.mi_set==null || subMenuModel.mi_set.isEmpty()){
//                rubbish.add(subMenuModel);
//            }
//        }
//        response.sm_set.removeAll(rubbish);
//        ViewPager vpPager = (ViewPager) findViewById(R.id.viewPager);
//        myAdapter = new TabsPagerAdapter(getSupportFragmentManager(),response);
//        vpPager.setAdapter(myAdapter);


    //  }


    public void addFragment(AbstractFragment fragment) {
       stack.add(fragment);
       placeFragment(fragment);
    }
    private void placeFragment(AbstractFragment fragment){
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.commit();
    }

    public void addRubbishFragment(AbstractFragment fragment) {
        String fragmentTag = fragment.getFragmentTag();
        ArrayList<AbstractFragment> previousRubbishFragmentsForThisTag = rubbishFragments.get(fragmentTag);
        if (previousRubbishFragmentsForThisTag == null) {
            previousRubbishFragmentsForThisTag = new ArrayList<>();
        }
        previousRubbishFragmentsForThisTag.add(fragment);
        rubbishFragments.put(fragmentTag, previousRubbishFragmentsForThisTag);
    }

    public AbstractFragment getInstance(String tag) {
        ArrayList<AbstractFragment> previousRubbishFragmentsForThisTag = rubbishFragments.get(tag);
        if (previousRubbishFragmentsForThisTag != null && !previousRubbishFragmentsForThisTag.isEmpty()) {
            return previousRubbishFragmentsForThisTag.remove(previousRubbishFragmentsForThisTag.size() - 1);
        } else {
            return null;
        }
    }
}