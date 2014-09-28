package com.amigos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amigos.R;
import com.amigos.util.ItemDrawer;

import java.util.List;

/**
 * Created by sati on 28/09/2014.
 */
public class MyAdapterDrawer extends BaseAdapter{


    private List<ItemDrawer> itemDrawers;
    private LayoutInflater layoutInflater;


    public MyAdapterDrawer(List<ItemDrawer> itemDrawers, Context context) {
        this.itemDrawers = itemDrawers;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemDrawers.size();
    }

    @Override
    public Object getItem(int i) {
        return itemDrawers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itemDrawers.indexOf(itemDrawers.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = layoutInflater.inflate(R.layout.content_section_layout, viewGroup,false);

        ImageView imageView = (ImageView) view1.findViewById(R.id.icon_content_section);
        TextView textView = (TextView) view1.findViewById(R.id.content_title);

        imageView.setImageResource(itemDrawers.get(i).getImage());
        textView.setText(itemDrawers.get(i).getTitle());

        return view1;
    }
}
