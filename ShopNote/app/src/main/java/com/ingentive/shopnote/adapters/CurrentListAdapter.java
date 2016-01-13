package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.ActivityManageSections;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.FavoritListModel;

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
    public static final String MYPREFERENCES = "MyPrefs";
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int postion, View rowView, final ViewGroup parent) {

        View vi = rowView;

        ViewHolder vh = new ViewHolder();
        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.custom_row_list, parent, false);

            vh.itemName = (TextView) vi.findViewById(R.id.tvItem_Name);
            vh.ivOption = (ImageView) vi.findViewById(R.id.ivOpt);
            vh.ivFavorit_selected = (ImageView) vi.findViewById(R.id.ivFav_selected);
            vh.ivSection = (ImageView) vi.findViewById(R.id.iv_Section);
            vh.etQuantity = (EditText) vi.findViewById(R.id.etQuantity);
            vh.tvQuantity = (TextView) vi.findViewById(R.id.tvQuantity);
            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }


        final TextView itemName, tvQuantity;
        final ImageView ivOption, ivFavorit_selected, ivSection;
        final EditText etQuantity;


        vh.itemName.setText(data.get(postion).getItemName());
        vh.ivOption.setBackgroundResource(R.drawable.grab_notgrabbed);
        vh.ivFavorit_selected.setBackgroundResource(R.drawable.favorite_selected);
        vh.etQuantity.setText(data.get(postion).getQuantity());
        if (!data.get(postion).getQuantity().equals("1")) {
            vh.tvQuantity.setVisibility(View.VISIBLE);
            vh.tvQuantity.setText(data.get(postion).getQuantity());
        } else {
            vh.tvQuantity.setVisibility(View.GONE);
        }
        db = new DatabaseHandler(mContext);
        boolean isFav = db.isFavorit(vh.itemName.getText().toString());
        if (isFav) {
            vh.ivFavorit_selected.setBackgroundResource(R.drawable.favorite_unselected);
        }
        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());

        switch (iconSecton) {
            case "clothing.png":
                vh.ivSection.setBackgroundResource(R.drawable.clothing);
                break;
            case "house.png":
                vh.ivSection.setBackgroundResource(R.drawable.house);
                break;
            case "pharmacy.png":
                vh.ivSection.setBackgroundResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                vh.ivSection.setBackgroundResource(R.drawable.produce);
                break;
            case "bakery.png":
                vh.ivSection.setBackgroundResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                vh.ivSection.setBackgroundResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                vh.ivSection.setBackgroundResource(R.drawable.beverages);
                break;
            case "freezer.png":
                vh.ivSection.setBackgroundResource(R.drawable.freezer);
                break;
            case "dairy.png":
                vh.ivSection.setBackgroundResource(R.drawable.dairy);
                break;
            case "meat.png":
                vh.ivSection.setBackgroundResource(R.drawable.meat);
                break;
            default:
                vh.ivSection.setBackgroundResource(R.drawable.unknown);
                break;
        }


        tvQuantity = vh.tvQuantity;
        itemName = vh.itemName;
        ivFavorit_selected = vh.ivFavorit_selected;
        ivOption = vh.ivOption;
        etQuantity = vh.etQuantity;
        ivSection = vh.ivSection;

        vh.ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(data.get(postion).getQuantity())
                etQuantity.setVisibility(View.VISIBLE);
                etQuantity.requestFocus();
                ivOption.setVisibility(View.GONE);
                prefs = mContext.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
                String restoredText = prefs.getString(first_time_dialog, null);
                if (restoredText == null) {
                    showDialog();
                    editor = prefs.edit();
                    editor.putString(first_time_dialog, "success");
                    editor.commit();
                }
                //Toast.makeText(mContext, "getQuantity" + data.get(postion).getQuantity().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        vh.ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, ActivityManageSections.class);
                String keyIdentifer  = data.get(postion).getItemName().toString();
                i.putExtra("item_name", keyIdentifer);
                mContext.startActivity(i);
               Toast.makeText(mContext, "section image click  " + data.get(postion).getItemName().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        vh.etQuantity.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                db = new DatabaseHandler(mContext);
                                etQuantity.requestFocus();
                                if (!etQuantity.getText().toString().isEmpty() &&
                                        !etQuantity.getText().toString().equals(null) &&
                                        !etQuantity.getText().toString().equals("1")) {
                                    //Toast.makeText(mContext, "quantity " + etQuantity.getText().toString(), Toast.LENGTH_SHORT).show();
                                    CurrentListModel model = new CurrentListModel();
                                    model.setCurrListId(data.get(postion).getCurrListId());
                                    model.setQuantity(etQuantity.getText().toString());
                                    db.updateQuantity(model);
                                    ivOption.setVisibility(View.VISIBLE);
                                    etQuantity.setVisibility(View.GONE);
                                    tvQuantity.setVisibility(View.VISIBLE);
                                    tvQuantity.setText(etQuantity.getText().toString());
                                } else {
                                    CurrentListModel model = new CurrentListModel();
                                    model.setCurrListId(data.get(postion).getCurrListId());
                                    model.setQuantity("1");
                                    db.updateQuantity(model);
                                    ivOption.setVisibility(View.VISIBLE);
                                    etQuantity.setVisibility(View.GONE);
                                    tvQuantity.setVisibility(View.VISIBLE);
                                    tvQuantity.setText("");
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });

        vh.etQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    db = new DatabaseHandler(mContext);
                    etQuantity.requestFocus();
                    if (!etQuantity.getText().toString().isEmpty() &&
                            !etQuantity.getText().toString().equals(null) &&
                            !etQuantity.getText().toString().equals("1")) {
                        //Toast.makeText(mContext, "quantity " + etQuantity.getText().toString(), Toast.LENGTH_SHORT).show();
                        CurrentListModel model = new CurrentListModel();
                        model.setCurrListId(data.get(postion).getCurrListId());
                        model.setQuantity(etQuantity.getText().toString());
                        db.updateQuantity(model);
                        ivOption.setVisibility(View.VISIBLE);
                        etQuantity.setVisibility(View.GONE);
                        tvQuantity.setVisibility(View.VISIBLE);
                        tvQuantity.setText(etQuantity.getText().toString());
                    } else {
                        CurrentListModel model = new CurrentListModel();
                        model.setCurrListId(data.get(postion).getCurrListId());
                        model.setQuantity("1");
                        db.updateQuantity(model);
                        ivOption.setVisibility(View.VISIBLE);
                        etQuantity.setVisibility(View.GONE);
                        tvQuantity.setVisibility(View.VISIBLE);
                        tvQuantity.setText("");
                    }

                }
            }
        });

        vh.ivFavorit_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Favorite Clicked: " + itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();

                db = new DatabaseHandler(mContext);
                boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {

                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit_selected.setBackgroundResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);

                } else {

                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit_selected.setBackgroundResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }

            }
        });

        return vi;
    }

    public void showDialog() {
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


    public class ViewHolder {
        TextView itemName, tvQuantity;
        ImageView ivOption, ivFavorit_selected, ivSection;
        EditText etQuantity;

    }

}
