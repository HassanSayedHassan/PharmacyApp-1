package com.yarenyarsilikal.pharmacy.ui.pharmacies;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yarenyarsilikal.pharmacy.R;

import java.util.HashMap;
import java.util.List;


public class PickerDialog {
    private HashMap<String, List<String>> items;
    private Activity context;
    private OnSpinerItemClick onSpinerItemClick;
    private AlertDialog alertDialog;
    private int style;

    public PickerDialog(Activity activity, HashMap<String, List<String>> items) {
        this.items = items;
        this.context = activity;
    }


    public void bindOnSpinerListener(OnSpinerItemClick onSpinerItemClick1) {
        this.onSpinerItemClick = onSpinerItemClick1;
    }

    public void showSpinerDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        View v = context.getLayoutInflater().inflate(R.layout.layout_picker_dialog, null);
        TextView rippleViewClose = v.findViewById(R.id.close);
        final ExpandableListView expandableListView = v.findViewById(R.id.list);

        final PickerAdapter adapter = new PickerAdapter(context, items);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener((expandableListView1, view, i, i1, l) -> {
            String city = adapter.getGroup(i).toString();
            String district = adapter.getChild(i, i1).toString();
            onSpinerItemClick.onItemClick(city, district);
            return false;
        });

        adb.setView(v);
        alertDialog = adb.create();
        alertDialog.getWindow().getAttributes().windowAnimations = style;

        rippleViewClose.setOnClickListener(v1 -> closeSpinerDialog());
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    public void closeSpinerDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }


}
