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

public class ItemGrid extends BaseAdapter {
    Context context;
    String[]allItemText;
    int[] imgid;

    View view;
    public ItemGrid(Context ctx)
    {
        context=ctx;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View cview, ViewGroup viewGroup) {
        LayoutInflater inflater=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        return null;
    }
}
