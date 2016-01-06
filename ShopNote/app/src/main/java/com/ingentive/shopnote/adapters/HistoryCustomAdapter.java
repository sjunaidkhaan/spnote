package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.HistoryParentModel;

import java.util.List;

/**
 * Created by PC on 1/6/2016.
 */
public class HistoryCustomAdapter extends BaseExpandableListAdapter {


    private List<HistoryParentModel> histParent;
    public Context mContext;
    private static LayoutInflater inflater = null;

    public HistoryCustomAdapter(Context context, List<HistoryParentModel> parent){
        this.histParent = parent;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return histParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return histParent.get(i).getArrayChildren().size();
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return histParent.get(i).getHisPaDatePurchased().toString();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return histParent.get(i).getArrayChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int groupPosition, boolean b, View rowView, ViewGroup viewGroup) {

        View vi = rowView;
        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (vi == null) {
            vi = inflater.inflate(R.layout.custom_row_history_parent, viewGroup,false);
        }

        //itemName.setText(data.get(postion).getItemName());
        final TextView tvHistParDp = (TextView) vi.findViewById(R.id.tv_dp_his_par);
        tvHistParDp.setText(histParent.get(groupPosition).getHisPaDatePurchased().toString());

        final ImageView ivHistParAdd = (ImageView) vi.findViewById(R.id.iv_add_his_par);
        ivHistParAdd.setImageResource(R.drawable.add_large_unselected);

        vi.setTag(holder);

        //return the entire view
        return vi;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        View childView = view;
        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (childView == null) {
            childView = inflater.inflate(R.layout.custom_row_history_child, viewGroup,false);
        }

        final TextView textView = (TextView) childView.findViewById(R.id.tv_item_name_his_ch);
        textView.setText(histParent.get(groupPosition).getArrayChildren().get(childPosition).getHisChItemName().toString());

        final ImageView ivFavChil = (ImageView) childView.findViewById(R.id.iv_fav_his_ch);
        ivFavChil.setImageResource(R.drawable.favorite_selected);

        final ImageView ivAddChil = (ImageView) childView.findViewById(R.id.iv_add_his_ch);
        ivAddChil.setImageResource(R.drawable.add_unselected);
        childView.setTag(holder);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

// Intentionally put on comment, if you need on click deactivate it
/*  @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder)view.getTag();
        if (view.getId() == holder.button.getId()){

           // DO YOUR ACTION
        }
    }*/


    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }
}