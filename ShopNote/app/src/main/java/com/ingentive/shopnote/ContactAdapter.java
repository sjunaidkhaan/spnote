package com.ingentive.shopnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class ContactAdapter extends BaseAdapter {

    public List<Contact> data;
    public int res;
    public Context ctx;
    private static LayoutInflater inflater=null;

    public ContactAdapter(Context context, List<Contact> dataC, int rowId) {

        this.ctx = context;
        this.res = rowId;
        this.data = dataC;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        if ( vi == null  ){
            vi = inflater.inflate(res, null);
        }

        TextView tvId, tPhoneNumber;

        tvId = (TextView)vi.findViewById(R.id.tvId);
        tPhoneNumber = (TextView)vi.findViewById(R.id.tvPhone);


        //tvId.setText(data.get(postion).getID()+"");
        //tPhoneNumber.setText(data.get(postion).getPhoneNumber());

        return vi;
    }
}
