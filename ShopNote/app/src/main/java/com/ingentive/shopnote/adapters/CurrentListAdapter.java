package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Context mContext;
    private LayoutInflater inflater = null;
    private SharedPreferences.Editor editor;
    private final String MYQUANTITYPREFERENCES = "MyQuanPrefs";
    private SharedPreferences prefs;
    private final String quantity_dialog = "quantity_dialog";

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
        if (data.get(postion).getChecked() == 1) {
            vh.itemName.setTextColor(Color.GRAY);
            vh.tvQuantity.setTextColor(Color.GRAY);
        } else {
            vh.itemName.setTextColor(Color.BLACK);
            vh.tvQuantity.setTextColor(Color.BLACK);
        }

        boolean isFav = DatabaseHandler.getInstance(mContext).isFavorit(vh.itemName.getText().toString());
        if (isFav) {
            vh.ivFavorit_selected.setBackgroundResource(R.drawable.favorite_unselected);
        }
        String iconSecton = DatabaseHandler.getInstance(mContext).getIconSection(data.get(postion).getItemName().toString());
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
            case "a.png":
                vh.ivSection.setBackgroundResource(R.drawable.a);
                break;
            case "b.png":
                vh.ivSection.setBackgroundResource(R.drawable.b);
                break;
            case "c.png":
                vh.ivSection.setBackgroundResource(R.drawable.c);
                break;
            case "d.png":
                vh.ivSection.setBackgroundResource(R.drawable.d);
                break;
            case "e.png":
                vh.ivSection.setBackgroundResource(R.drawable.e);
                break;
            case "f.png":
                vh.ivSection.setBackgroundResource(R.drawable.f);
                break;
            case "g.png":
                vh.ivSection.setBackgroundResource(R.drawable.g);
                break;
            case "h.png":
                vh.ivSection.setBackgroundResource(R.drawable.h);
                break;
            case "i.png":
                vh.ivSection.setBackgroundResource(R.drawable.i);
                break;
            case "j.png":
                vh.ivSection.setBackgroundResource(R.drawable.j);
                break;
            case "k.png":
                vh.ivSection.setBackgroundResource(R.drawable.k);
                break;
            case "l.png":
                vh.ivSection.setBackgroundResource(R.drawable.l);
                break;
            case "m.png":
                vh.ivSection.setBackgroundResource(R.drawable.m);
                break;
            case "n.png":
                vh.ivSection.setBackgroundResource(R.drawable.n);
                break;
            case "o.png":
                vh.ivSection.setBackgroundResource(R.drawable.o);
                break;
            case "p.png":
                vh.ivSection.setBackgroundResource(R.drawable.p);
                break;
            case "q.png":
                vh.ivSection.setBackgroundResource(R.drawable.q);
                break;
            case "r.png":
                vh.ivSection.setBackgroundResource(R.drawable.r);
                break;
            case "s.png":
                vh.ivSection.setBackgroundResource(R.drawable.s);
                break;
            case "t.png":
                vh.ivSection.setBackgroundResource(R.drawable.t);
                break;
            case "u.png":
                vh.ivSection.setBackgroundResource(R.drawable.u);
                break;
            case "v.png":
                vh.ivSection.setBackgroundResource(R.drawable.v);
                break;
            case "w.png":
                vh.ivSection.setBackgroundResource(R.drawable.w);
                break;
            case "x.png":
                vh.ivSection.setBackgroundResource(R.drawable.x);
                break;
            case "y.png":
                vh.ivSection.setBackgroundResource(R.drawable.y);
                break;
            case "z.png":
                vh.ivSection.setBackgroundResource(R.drawable.z);
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

        vh.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etQuantity.setVisibility(View.VISIBLE);
                etQuantity.requestFocus();
                ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .showSoftInput(etQuantity, InputMethodManager.SHOW_FORCED);
                ivOption.setVisibility(View.GONE);
                prefs = mContext.getSharedPreferences(MYQUANTITYPREFERENCES, Context.MODE_PRIVATE);
                String restoredText = prefs.getString(quantity_dialog, null);
                if (restoredText == null) {
                    showDialog();
                    editor = prefs.edit();
                    editor.putString(quantity_dialog, "success");
                    editor.commit();
                }

            }
        });
        vh.ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent i = new Intent(mContext, ActivityManageSections.class);
                i.putExtra("item_name", data.get(postion).getItemName().toString());
                mContext.startActivity(i);
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
                            if (etQuantity.getText().toString().replaceAll(" ", "").length() > 0 && !etQuantity.getText().toString().trim().isEmpty()) {
                                if (!event.isShiftPressed()) {
                                    ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                                            .hideSoftInputFromWindow(etQuantity.getWindowToken(), 0);
                                    if (!etQuantity.getText().toString().trim().isEmpty() &&
                                            !etQuantity.getText().toString().trim().equals(null) &&
                                            !etQuantity.getText().toString().trim().equals("1")) {
                                        CurrentListModel model = new CurrentListModel();
                                        model.setCurrListId(data.get(postion).getCurrListId());
                                        model.setQuantity(etQuantity.getText().toString().trim());
                                        DatabaseHandler.getInstance(mContext).updateQuantity(model);
                                        ivOption.setVisibility(View.VISIBLE);
                                        etQuantity.setVisibility(View.GONE);
                                        tvQuantity.setVisibility(View.VISIBLE);
                                        tvQuantity.setText(etQuantity.getText().toString().trim());
                                    } else {
                                        CurrentListModel model = new CurrentListModel();
                                        model.setCurrListId(data.get(postion).getCurrListId());
                                        model.setQuantity("1");
                                        DatabaseHandler.getInstance(mContext).updateQuantity(model);
                                        ivOption.setVisibility(View.VISIBLE);
                                        etQuantity.setVisibility(View.GONE);
                                        tvQuantity.setVisibility(View.VISIBLE);
                                        tvQuantity.setText("");
                                    }
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                });

        vh.etQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (etQuantity.getText().toString().replaceAll(" ", "").length() > 0 && !etQuantity.getText().toString().trim().isEmpty()) {
                        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).
                                hideSoftInputFromWindow(etQuantity.getWindowToken(), 0);
                        if (!etQuantity.getText().toString().trim().isEmpty() &&
                                !etQuantity.getText().toString().trim().equals(null) &&
                                !etQuantity.getText().toString().trim().equals("1")) {
                            CurrentListModel model = new CurrentListModel();
                            model.setCurrListId(data.get(postion).getCurrListId());
                            model.setQuantity(etQuantity.getText().toString().trim());
                            DatabaseHandler.getInstance(mContext).updateQuantity(model);
                            ivOption.setVisibility(View.VISIBLE);
                            etQuantity.setVisibility(View.GONE);
                            tvQuantity.setVisibility(View.VISIBLE);
                            tvQuantity.setText(etQuantity.getText().toString().trim());
                        } else {
                            CurrentListModel model = new CurrentListModel();
                            model.setCurrListId(data.get(postion).getCurrListId());
                            model.setQuantity("1");
                            DatabaseHandler.getInstance(mContext).updateQuantity(model);
                            ivOption.setVisibility(View.VISIBLE);
                            etQuantity.setVisibility(View.GONE);
                            tvQuantity.setVisibility(View.VISIBLE);
                            tvQuantity.setText("");
                        }
                    }

                }
            }
        });

        vh.ivFavorit_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db = new DatabaseHandler(mContext);
                boolean itemIsFav = DatabaseHandler.getInstance(mContext).isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {

                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit_selected.setBackgroundResource(R.drawable.favorite_selected);
                    DatabaseHandler.getInstance(mContext).removeFavorit(remFavItem);

                } else {
                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit_selected.setBackgroundResource(R.drawable.favorite_unselected);
                    DatabaseHandler.getInstance(mContext).addFavorit(addFavItem);
                }

            }
        });

        return vi;
    }

    public void showDialog() {
        AlertDialog.Builder
                alertDialogBuilder = new AlertDialog.Builder(mContext)
                .setMessage("You can enter a quantity here\n\t\t\tinclude any units " +
                        "of measurement such as oz, L,\n\t\t\t\t\t\tlb or kg.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
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
