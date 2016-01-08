package com.ingentive.shopnote.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.ListAddBasicModel;

import java.util.ArrayList;
import java.util.List;

public class ListAddBasicAdapter extends ArrayAdapter<ListAddBasicModel> {

    public List<ListAddBasicModel> mData;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater=null;
    private ArrayList<ListAddBasicModel> suggestions;

    public ListAddBasicAdapter(Context context, int rowId,List<ListAddBasicModel> dataC) {
        super(context, rowId, dataC);
        this.mContext = context;
        this.res = rowId;
        this.mData = dataC;
        this.suggestions = new ArrayList<ListAddBasicModel>();
    }
    @Override
    public View getView(int postion, View rowView, ViewGroup parent) {
        if(rowView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            rowView = inflater.inflate(res, parent, false);
        }

        ListAddBasicModel item = mData.get(postion);

        if(item!=null){
            TextView tvItemName;
            tvItemName = (TextView)rowView.findViewById(R.id.tv_item_name);
            tvItemName.setText(item.getItemName());
        }

        return rowView;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((ListAddBasicModel)(resultValue)).getItemName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (ListAddBasicModel item : mData) {
                    if(item.getItemName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(item);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<ListAddBasicModel> filteredList = (ArrayList<ListAddBasicModel>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (ListAddBasicModel c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
