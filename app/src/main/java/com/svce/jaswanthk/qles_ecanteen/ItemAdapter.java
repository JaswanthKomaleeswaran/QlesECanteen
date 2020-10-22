package com.svce.jaswanthk.qles_ecanteen;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JASWANTH K on 20-02-2018.
 */

public class ItemAdapter extends ArrayAdapter {

    private final Activity context;
    private final String[] itemtext,itemprice;
    private final int[] imgId;
    public ItemAdapter(@NonNull ItemListActivity context, String[] itemname,String[] itemprices,int[] imgid) {
        super(context, R.layout.item_grid_layout,itemname);
        this.context=context;
        itemtext=itemname;
        itemprice=itemprices;
        imgId=imgid;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_grid_layout, null,true);

        TextView itemname = (TextView) rowView.findViewById(R.id.catitemname);
        TextView itemrate = (TextView) rowView.findViewById(R.id.catitemrate);
        ImageView itemimage=(ImageView)rowView.findViewById(R.id.catitemimage);

        itemname.setText(itemtext[position]);
        itemrate.setText(itemprice[position]);
        itemimage.setImageResource(imgId[position]);

        return rowView;
    }
}
