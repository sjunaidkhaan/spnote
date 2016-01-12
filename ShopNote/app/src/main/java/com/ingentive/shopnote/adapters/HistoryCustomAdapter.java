package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.HistoryParentModel;

import java.util.List;

/**
 * Created by PC on 1/6/2016.
 */
public class HistoryCustomAdapter extends BaseExpandableListAdapter {


    private List<HistoryParentModel> histParent;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;

    public HistoryCustomAdapter(Context context, List<HistoryParentModel> parent) {
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
    public View getGroupView(final int groupPosition, boolean b, View rowView, final ViewGroup viewGroup) {

        View vi = rowView;
        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (vi == null) {
            vi = inflater.inflate(R.layout.custom_row_history_parent, viewGroup, false);
        }

        final TextView tvHistParDp = (TextView) vi.findViewById(R.id.tv_dp_his_par);
        tvHistParDp.setText(histParent.get(groupPosition).getHisPaDatePurchased().toString());

        final ImageView ivHistParAdd = (ImageView) vi.findViewById(R.id.iv_add_his_par);
        ivHistParAdd.setBackgroundResource(R.drawable.add_large_unselected);

        vi.setTag(holder);

        ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
        mExpandableListView.expandGroup(groupPosition);

        ivHistParAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivHistParAdd.setBackgroundResource(R.drawable.add_large_selected);
                db = new DatabaseHandler(mContext);
                boolean itemExist = false;
                db = new DatabaseHandler(mContext);
                List<CurrentListModel> currList = db.getCurrList();
                for (int i = 0; i < histParent.get(groupPosition).getArrayChildren().size(); i++) {
                    String itemname = histParent.get(groupPosition).getArrayChildren().get(i).getHisChItemName().toString();
                    for (int j = 0; j < currList.size(); j++) {
                        if (currList.get(j).getItemName().toLowerCase().equals(itemname.toLowerCase())) {
                            itemExist = true;
                        }
                    }
                    if (!itemExist) {
                        db = new DatabaseHandler(mContext);
                        db.addCurrentList(new CurrentListModel(1, itemname, 0, null, "My Firts Shopnote", 1));
                    }
                    //db.addCurrentList(new CurrentListModel(1, histParent.get(groupPosition).getArrayChildren().get(i).getHisChItemName(), 0, null, "My Firts Shopnote", 1));
                }
            }
        });
        return vi;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View view, ViewGroup viewGroup) {

        View childView = view;
        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;
        if (childView == null) {
            childView = inflater.inflate(R.layout.custom_row_history_child, viewGroup, false);
        }

        final TextView textView = (TextView) childView.findViewById(R.id.tv_item_name_his_ch);
        final ImageView ivFavChil = (ImageView) childView.findViewById(R.id.iv_fav_his_ch);
        final ImageView ivAddChil = (ImageView) childView.findViewById(R.id.iv_add_his_ch);

        final String childName = histParent.get(groupPosition).getArrayChildren().get(childPosition).getHisChItemName().toString();
        textView.setText(childName);
        //ivFavChil.setImageResource(R.drawable.favorite_selected);
        db = new DatabaseHandler(mContext);
        final boolean itemIsInList = db.isInList(childName);
        //Toast.makeText(mContext,"itemIsFav "+itemIsFav,Toast.LENGTH_LONG).show();
        if (itemIsInList) {
            ivAddChil.setBackgroundResource(R.drawable.add_selected);
            ivAddChil.setOnClickListener(null);
        } else {
            ivAddChil.setBackgroundResource(R.drawable.add_unselected);
        }
        db = new DatabaseHandler(mContext);
        final boolean itemIsFav = db.isFavorit(childName);
        //Toast.makeText(mContext,"itemIsFav "+itemIsFav,Toast.LENGTH_LONG).show();
        if (itemIsFav) {
            ivFavChil.setBackgroundResource(R.drawable.favorite_unselected);
        } else {
            ivFavChil.setBackgroundResource(R.drawable.favorite_selected);
        }

        childView.setTag(holder);

        ivFavChil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Favorite Clicked: " + itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();
                db = new DatabaseHandler(mContext);
                final String childName = histParent.get(groupPosition).getArrayChildren().get(childPosition).getHisChItemName().toString();
                final boolean itemIsFav = db.isFavorit(childName);
                if (itemIsFav) {
                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(childName);
                    ivFavChil.setBackgroundResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);
                } else {
                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(childName);
                    ivFavChil.setBackgroundResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }
            }
        });
        ivAddChil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHandler(mContext);
                final boolean itemIsInList = db.isInList(childName);
                if (!itemIsInList) {
                    ivAddChil.setBackgroundResource(R.drawable.add_selected);
                    db = new DatabaseHandler(mContext);
                    List<CurrentListModel> currList = db.getCurrList();
                    boolean itemExist = false;
                    for (int i = 0; i < currList.size(); i++) {
                        final String itemname = histParent.get(groupPosition).getArrayChildren().get(childPosition).getHisChItemName().toString();
                        if (currList.get(i).getItemName().toLowerCase().equals(itemname.toLowerCase())) {
                            itemExist = true;
                        }
                    }
                    if (!itemExist) {
                       // Toast.makeText(mContext, "item not exist  " + itemExist, Toast.LENGTH_LONG).show();
                        db = new DatabaseHandler(mContext);
                        db.addCurrentList(new CurrentListModel(1, childName, 0, null, "My Firts Shopnote", 1));
                    }
                }
            }

        });

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