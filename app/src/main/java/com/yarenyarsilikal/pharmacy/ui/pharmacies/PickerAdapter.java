package com.yarenyarsilikal.pharmacy.ui.pharmacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yarenyarsilikal.pharmacy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PickerAdapter extends BaseExpandableListAdapter {

    public LayoutInflater inflater;
    public TextView country;
    public TextView team;
    private Context context;
    private List<String> cityList = new ArrayList<>();
    private HashMap<String, List<String>> expandableDistrictList;


    public PickerAdapter(Context context, HashMap<String, List<String>> expandableDistrictList) {
        this.context = context;
        this.expandableDistrictList = expandableDistrictList;
        for (String s : expandableDistrictList.keySet()) {
            cityList.add(s);
        }
    }

    @Override
    public int getGroupCount() {
        return this.cityList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return expandableDistrictList.get(cityList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return cityList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return expandableDistrictList.get(cityList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String position = cityList.get(i);

        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_picker, null);
        }

        country = view.findViewById(R.id.textViewFlagName);
        country.setText(position);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz
        String s = getChild(i, i1).toString();


        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_child_picker, null);
            // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
        }

        // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz
        team = view.findViewById(R.id.textViewFlagName);
        team.setText(s);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
