package com.amigos.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amigos.R;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.w3c.dom.Text;

/**
 * Created by leonelmendez on 28/09/14.
 */
public class ParseAdapter extends ParseQueryAdapter<ParseObject> {


    public ParseAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public ParseAdapter(Context context){
        super(context,new QueryFactory<ParseObject>(){

            @Override
            public ParseQuery<ParseObject> create() {

                ParseQuery parseQuery = new ParseQuery("Paquete");
                return parseQuery;
            }
        });

    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {

        if(v == null){
            v = View.inflate(getContext(), R.layout.paquetes_row,null);
        }

        super.getItemView(object, v, parent);

        ParseImageView parseImageView = (ParseImageView)v.findViewById(R.id.paq_parse_image);
        ParseFile image = object.getParseFile("imagen");

        if(image != null){
            parseImageView.setParseFile(image);
            parseImageView.loadInBackground();

        }

        TextView namePaq = (TextView)v.findViewById(R.id.paq_name);
        namePaq.setText(object.getString("colonia"));

        return v;
    }
}
