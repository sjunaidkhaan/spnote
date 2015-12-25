package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ingentive.shopnote.Contact;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.DictionaryModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class DectionaryAdapter extends BaseAdapter {

    public List<DictionaryModel> data;
    public int res;
    public Context ctx;
    private static LayoutInflater inflater=null;

    public DectionaryAdapter(Context context, List<DictionaryModel> dataC, int rowId) {

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

        TextView tvId, tvItemName,tvSectionId;

        tvId = (TextView)vi.findViewById(R.id.tvId);
        tvItemName = (TextView)vi.findViewById(R.id.tvItemName);
        tvSectionId = (TextView)vi.findViewById(R.id.tvSectionId);

        tvId.setText(data.get(postion).getDictionaryId()+"");
        tvItemName.setText(data.get(postion).getItemName());
        tvSectionId.setText(data.get(postion).getSectionId());

        return vi;
    }
}
