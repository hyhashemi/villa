package com.apnozhan.villa.models;

import android.support.design.widget.TabLayout;

import java.util.List;

/**
 * Created by Y50-70 on 1/23/2018.
 */

public class VillaModel implements Model{
    public int id;
    public int adress;
    public String type;
    public List<VillaImage> image_set;
    public List<DatePrice> price;
    public VillaFeature features;
    public int room_count;
    public String area;
    public int floor_count;
    public String date;
    public String propertyType;
    public String viewType;
    public String address;
    public float lat;
    public float lng;
    public int capacity;
    public String view;
    public String submit_date;
    public String submit_time;

}
