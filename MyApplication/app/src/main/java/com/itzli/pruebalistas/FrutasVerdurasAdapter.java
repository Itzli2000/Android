package com.itzli.pruebalistas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by itzli on 21/10/2016.
 */

public class FrutasVerdurasAdapter extends ArrayAdapter<FrutasVerduras> {
    Context myContext;
    int myLayoutResourceID;
    FrutasVerduras mydata[] = null;

    public FrutasVerdurasAdapter (Context context, int LayoutResourceID, FrutasVerduras[] data) {
        super(context, LayoutResourceID, data);

        this.myContext = context;
        this.myLayoutResourceID = LayoutResourceID;
        this.mydata = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        FrutasVerdurasHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)myContext).getLayoutInflater();
            row = inflater.inflate(myLayoutResourceID,parent,false);

            holder = new FrutasVerdurasHolder();
            holder.imagen = (ImageView) row.findViewById(R.id.image);
            holder.textView = (TextView)row.findViewById(R.id.tv);
            row.setTag(holder);

        }else{
            holder = (FrutasVerdurasHolder) row.getTag();
        }


        FrutasVerduras frutasVerduras = mydata[position];
        holder.textView.setText(frutasVerduras.title);
        holder.imagen.setImageResource(frutasVerduras.icon);

        return  row;
    }

    static class FrutasVerdurasHolder{
        ImageView imagen;
        TextView textView;
    }
}


