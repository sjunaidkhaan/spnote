package com.ingentive.shopnote.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.ListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
//jk
public class CurrentListAdapter extends ArrayAdapter<CurrentListModel> {

    public List<CurrentListModel> data;
     SwipeMenuListView mListView;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;
    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs" ;
    public static final String dbCreated = "dbKey";
    public static SharedPreferences prefs;
    public static final String first_time_dialog = "first_time";

    public CurrentListAdapter(Context context, List<CurrentListModel> dataC, int rowId) {
        super(context, rowId, dataC);

        this.mContext = context;
        this.res = rowId;
        this.data = dataC;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi = inflater.inflate(R.layout.custom_row_list, parent, false);

        final TextView itemName,tvQuantity;
        final ImageView ivOption, ivFavorit_selected, ivSection;
        final EditText etQuantity;

        itemName = (TextView) vi.findViewById(R.id.tvItem_Name);
        ivOption = (ImageView) vi.findViewById(R.id.ivOpt);
        ivFavorit_selected = (ImageView) vi.findViewById(R.id.ivFav_selected);
        ivSection = (ImageView) vi.findViewById(R.id.iv_Section);
        etQuantity = (EditText) vi.findViewById(R.id.etQuantity);
        tvQuantity =(TextView) vi.findViewById(R.id.tvQuantity);

        itemName.setText(data.get(postion).getItemName());
        ivOption.setImageResource(R.drawable.grab_grabbed);
        ivFavorit_selected.setImageResource(R.drawable.favorite_selected);
        etQuantity.setText(data.get(postion).getQuantity());
        if(!data.get(postion).getQuantity().equals("1")){
            tvQuantity.setVisibility(View.VISIBLE);
            tvQuantity.setText(data.get(postion).getQuantity());
        }
        db = new DatabaseHandler(mContext);
        boolean isFav = db.isFavorit(itemName.getText().toString());
        if (isFav) {
            ivFavorit_selected.setImageResource(R.drawable.favorite_unselected);
        }
        ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(data.get(postion).getQuantity())
                etQuantity.setVisibility(View.VISIBLE);
                ivOption.setVisibility(View.GONE);
                prefs = mContext.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
                String restoredText = prefs.getString(first_time_dialog, null);
                if (restoredText == null) {
                    showDialog();
                    editor = prefs.edit();
                    editor.putString(first_time_dialog,"success");
                    editor.commit();
                }
                //Toast.makeText(mContext, "getQuantity" + data.get(postion).getQuantity().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "section image click" + postion, Toast.LENGTH_SHORT).show();
            }
        });

       /* etQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    db = new DatabaseHandler(mContext);
                    //db.addContact(new Contact("Azhar Abbas", "0346-6969193"));
                    CurrentListModel model = new CurrentListModel();
                    model.setCurrListId(data.get(postion).getCurrListId());
                    model.setQuantity(etQuantity.getText().toString());
                    db.updateQuantity(model);
                    //Toast.makeText(mContext, "ID " + data.get(postion).getCurrListId(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "onFocus Quantity is" + etQuantity.getText().toString(), Toast.LENGTH_SHORT).show();
                    ivOption.setVisibility(View.VISIBLE);
                    etQuantity.setVisibility(View.GONE);
                }
            }
        });*/

        etQuantity.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                db = new DatabaseHandler(mContext);
                                //db.addContact(new Contact("Azhar Abbas", "0346-6969193"));
                                CurrentListModel model = new CurrentListModel();
                                model.setCurrListId(data.get(postion).getCurrListId());
                                model.setQuantity(etQuantity.getText().toString());
                                db.updateQuantity(model);
                                Toast.makeText(mContext, "ID " + data.get(postion).getCurrListId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(mContext, " in editor Quantity is" + etQuantity.getText().toString(), Toast.LENGTH_SHORT).show();
                                ivOption.setVisibility(View.VISIBLE);
                                etQuantity.setVisibility(View.GONE);
                                tvQuantity.setVisibility(View.VISIBLE);
                                tvQuantity.setText(etQuantity.getText().toString());
                                return true;
                            }
                        }
                        return false;
                    }
                });


        ivFavorit_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Favorite Clicked: " + itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();

                db = new DatabaseHandler(mContext);
                boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {

                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit_selected.setImageResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);

                } else {

                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit_selected.setImageResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }

            }
        });

        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());

        switch (iconSecton) {
            case "clothing.png":
                ivSection.setImageResource(R.drawable.clothing);
                break;
            case "house.png":
                ivSection.setImageResource(R.drawable.house);
                break;
            case "pharmacy.png":
                ivSection.setImageResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                ivSection.setImageResource(R.drawable.produce);
                break;
            case "bakery.png":
                ivSection.setImageResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                ivSection.setImageResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                ivSection.setImageResource(R.drawable.beverages);
                break;
            case "freezer.png":
                ivSection.setImageResource(R.drawable.freezer);
                break;
            case "dairy.png":
                ivSection.setImageResource(R.drawable.dairy);
                break;
            case "meat.png":
                ivSection.setImageResource(R.drawable.meat);
                break;
            default:
                ivSection.setImageResource(R.drawable.unknown);
                break;
        }
        return vi;
    }
    public void showDialog(){
        AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(mContext)
                .setMessage("You can enter a quantity here include any units " +
                        "of measurement such as oz,L,lb or kg.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
