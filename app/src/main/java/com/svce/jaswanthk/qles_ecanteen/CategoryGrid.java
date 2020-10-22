package com.svce.jaswanthk.qles_ecanteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JASWANTH K on 18-02-2018.
 */

public class CategoryGrid extends BaseAdapter {
    Context context;
    String[] allCategoryText;
    int[] imgid;
    View view;
    public CategoryGrid(Context ctx,String[] itemtext,int[] img)
    {
        context=ctx;
        allCategoryText=itemtext;
        imgid=img;
    }
    @Override
    public int getCount() {
        return allCategoryText.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View cview, ViewGroup viewGroup) {
        LayoutInflater inflater=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if(cview==null) {
            view=new View(context);
            view=inflater.inflate(R.layout.category_grid_layout, null);
            TextView textView=(TextView)view.findViewById(R.id.itemText);
            ImageView imageView=(ImageView)view.findViewById(R.id.itemImage);
            textView.setText(allCategoryText[i]);
            imageView.setImageResource(imgid[i]);

        }
        else
        {
            view=cview;
        }
        return view;
    }
}
