package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.ShopParentModel;
import com.ingentive.shopnote.model.ShopParentModelMerger;
import com.nhaarman.listviewanimations.util.Swappable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 1/6/2016.
 */
public class ShopCustomAdapterSimpleListview extends BaseAdapter implements Swappable {

    private List<ShopParentModelMerger> data;
    private List<ShopParentModel> oData;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public ArrayList<Integer> ignoreList = new ArrayList<>();


    public ShopCustomAdapterSimpleListview(Context context, List<ShopParentModelMerger> data, List<ShopParentModel> oData) {
        this.data = data;
        this.oData = oData;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolderParent vhp = new ViewHolderParent();

        final ImageView ivArrow;

        View vi = convertView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_row_shop_parent_merge, null);
            vhp.tvShopSecNamePar = (TextView) convertView.findViewById(R.id.tv_section_name_par);
            vhp.ivShopSecIcon = (ImageView) convertView.findViewById(R.id.iv_section_shop_par);
            vhp.ivArrow = (ImageView) convertView.findViewById(R.id.iv_grab_shop_par);
            vhp.ivShopChilCheckBox = (ImageView) convertView.findViewById(R.id.iv_checkbox_shop_child);
            vhp.ivHamburger = (ImageView) convertView.findViewById(R.id.iv_grab_shop_ex);
            vhp.tvShopChilItemName = (TextView) convertView.findViewById(R.id.tv_section_name_child);

            int id = convertView.generateViewId();
            convertView.setId(id);
            convertView.setTag(vhp);

        } else {
            vhp = (ViewHolderParent) convertView.getTag();
        }

        final TextView tvShopSecNamePar;
        if (data.get(position).isParent()) {
            vhp.ivArrow.setVisibility(View.VISIBLE);
            vhp.ivShopSecIcon.setVisibility(View.VISIBLE);
            vhp.ivHamburger.setVisibility(View.VISIBLE);
            vhp.tvShopSecNamePar.setVisibility(View.VISIBLE);
            vhp.tvShopChilItemName.setVisibility(View.GONE);
            vhp.ivShopChilCheckBox.setVisibility(View.GONE);
            if (data.get(position).isClick()) {
                vhp.ivArrow.setBackgroundResource(R.drawable.minimize);
            } else {
                vhp.ivArrow.setBackgroundResource(R.drawable.maximize);
            }

            vhp.tvShopSecNamePar.setText(data.get(position).getShopPaSectionName().toString());
            Log.d("ERROR:", position + "");
            //counter=0;;
            if (data.get(position).getShopPaId() <= 10) {
                switch (data.get(position).getShopPaSectionName()) {
                    case "Clothing":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.clothing);
                        break;
                    case "Household and Specialty":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.house);
                        break;
                    case "Pharmacy":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.pharmacy);
                        break;
                    case "Produce":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.produce);
                        break;
                    case "Bakery":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.bakery);
                        break;
                    case "Packaged Foodstuff":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.dry_goods);
                        break;
                    case "Beverages":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.beverages);
                        break;
                    case "Frozen Food":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.freezer);
                        break;
                    case "Dairy and Refridgerated":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.dairy);
                        break;
                    case "Meat and Seafood":
                        vhp.ivShopSecIcon.setBackgroundResource(R.drawable.meat);
                        break;
                }
            } else {
                if (!data.get(position).getShopPaSectionName().equals("Unknown")) {
                    switch (data.get(position).getShopPaSectionName().toLowerCase().charAt(0)) {
                        case 'a':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.a);
                            break;
                        case 'b':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.b);
                            break;
                        case 'c':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.c);
                            break;
                        case 'd':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.d);
                            break;
                        case 'e':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.e);
                            break;
                        case 'f':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.f);
                            break;
                        case 'g':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.g);
                            break;
                        case 'h':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.h);
                            break;
                        case 'i':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.i);
                            break;
                        case 'j':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.j);
                            break;
                        case 'k':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.k);
                            break;
                        case 'l':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.l);
                            break;
                        case 'm':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.m);
                            break;
                        case 'n':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.n);
                            break;
                        case 'o':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.o);
                            break;
                        case 'p':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.p);
                            break;
                        case 'q':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.q);
                            break;
                        case 'r':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.r);
                            break;
                        case 's':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.s);
                            break;
                        case 't':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.t);
                            break;
                        case 'u':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.u);
                            break;
                        case 'v':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.v);
                            break;
                        case 'w':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.w);
                            break;
                        case 'x':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.x);
                            break;
                        case 'y':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.y);
                            break;
                        case 'z':
                            vhp.ivShopSecIcon.setBackgroundResource(R.drawable.z);
                            break;
                        default:
                            break;
                    }
                }
            }
            ivArrow = vhp.ivArrow;
            vhp.ivArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!data.get(position).isClick()) {
                        data.get(position).setIsClick(true);
                        ivArrow.setBackgroundResource(R.drawable.minimize);
                        for (int i = 0; i < ignoreList.size(); ++i) {
                            if (data.get(position).getShopPaId() == ignoreList.get(i)) {
                                ignoreList.remove(i);
                                data = getData(ignoreList);
                                notifyDataSetChanged();
                            }
                        }
                    } else {
                        data.get(position).setIsClick(false);
                        ivArrow.setBackgroundResource(R.drawable.maximize);
                        //remove item of this parent
                        ignoreList.add(data.get(position).getShopPaId());
                        data = getData(ignoreList);
                        notifyDataSetChanged();
                    }
                }
            });
            int childCounter = 0;
            int childArraySize = 0;
            int parentId = data.get(position).getShopPaId();
            for (int i = 0; i < oData.size(); i++) {
                if (oData.get(i).getShopPaId() == parentId) {
                    childArraySize = oData.get(i).getArrayChildren().size();
                    for (int j = 0; j < childArraySize; j++) {
                        if (oData.get(i).getArrayChildren().get(j).getCheckBox() == 1) {
                            childCounter++;
                        }
                    }
                }
            }
            if (childArraySize == childCounter) {
                vhp.tvShopSecNamePar.setTextColor(Color.GRAY);
            } else {
                vhp.tvShopSecNamePar.setTextColor(Color.BLACK);
            }

        } else {
            vhp.ivArrow.setVisibility(View.GONE);
            vhp.ivShopSecIcon.setVisibility(View.GONE);
            vhp.ivHamburger.setVisibility(View.GONE);
            vhp.tvShopSecNamePar.setVisibility(View.GONE);
            vhp.ivShopChilCheckBox.setVisibility(View.VISIBLE);
            vhp.tvShopChilItemName.setVisibility(View.VISIBLE);

            String quantity = "";
            if (data.get(position).getShop_chil_quantity().equals("1") ||
                    data.get(position).getShop_chil_quantity().isEmpty() ||
                    data.get(position).getShop_chil_quantity() == null) {
                quantity = "";

            } else {
                quantity = data.get(position).getShop_chil_quantity();
            }

            vhp.tvShopChilItemName.setText(quantity + " " + data.get(position).getShop_chil_item_name());
            int isChecked = DatabaseHandler.getInstance(mContext).isChecked(data.get(position).getShop_chil_item_name());
            DatabaseHandler.getInstance(mContext).close();
            if (isChecked == 1) {
                vhp.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                vhp.tvShopChilItemName.setTextColor(Color.GRAY);

            } else {
                vhp.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
                vhp.tvShopChilItemName.setTextColor(Color.BLACK);
            }

            final ImageView ivShopChilCheckBox = vhp.ivShopChilCheckBox;
            final TextView tvShopChilItemName = vhp.tvShopChilItemName;
            final TextView tvSectionName = (TextView) vhp.tvShopSecNamePar;
            vhp.ivShopChilCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int isChecked = DatabaseHandler.getInstance(mContext).isChecked(data.get(position).getShop_chil_item_name());
                    if (isChecked == 1) {
                        CurrentListModel unCheck = new CurrentListModel();
                        unCheck.setCurrListId(data.get(position).getShop_chil_id());
                        unCheck.setChecked(0);
                        unCheck.setItemName(data.get(position).getShop_chil_item_name());
                        DatabaseHandler.getInstance(mContext).updateCheckItem(unCheck);
                        ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
                        tvShopChilItemName.setTextColor(Color.BLACK);
                        data.get(position).setCheckBox(0);

                        for (int i = 0; i < oData.size(); i++) {
                            if (oData.get(i).getShopPaId() == data.get(position).getShopPaId()) {
                                for (int j = 0; j < oData.get(i).getArrayChildren().size(); j++) {
                                    if (oData.get(i).getArrayChildren().get(j).getShopChSectionId() == data.get(i).getShop_chil_id()) {
                                        oData.get(i).getArrayChildren().get(j).setCheckBox(0);
                                    }
                                }
                            }
                        }

                        data = getData(ignoreList);
                        notifyDataSetChanged();

                        int childCounter = 0;
                        int childArraySize = 0;
                        int parentId = data.get(position).getShopPaId();
                        for (int i = 0; i < oData.size(); i++) {
                            if (oData.get(i).getShopPaId() == parentId) {
                                childArraySize = oData.get(i).getArrayChildren().size();
                                for (int j = 0; j < childArraySize; j++) {
                                    if (oData.get(i).getArrayChildren().get(j).getCheckBox() == 1) {
                                        childCounter++;
                                    }
                                }
                            }
                        }
                        if (childArraySize == childCounter) {
                            tvSectionName.setTextColor(Color.GRAY);
                            data = getData(ignoreList);
                            notifyDataSetChanged();
                        } else {
                            tvSectionName.setTextColor(Color.BLACK);
                            data = getData(ignoreList);
                            notifyDataSetChanged();
                        }

                    } else {
                        CurrentListModel currCheck = new CurrentListModel();
                        currCheck.setChecked(1);
                        currCheck.setItemName(data.get(position).getShop_chil_item_name());
                        currCheck.setCurrListId(data.get(position).getShop_chil_id());
                        ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                        tvShopChilItemName.setTextColor(Color.GRAY);
                        DatabaseHandler.getInstance(mContext).updateCheckItem(currCheck);
                        data.get(position).setCheckBox(1);

                        for (int i = 0; i < oData.size(); i++) {
                            if (oData.get(i).getShopPaId() == data.get(position).getShopPaId()) {
                                for (int j = 0; j < oData.get(i).getArrayChildren().size(); j++) {
                                    if (oData.get(i).getArrayChildren().get(j).getShopChSectionId() == data.get(i).getShop_chil_id()) {
                                        oData.get(i).getArrayChildren().get(j).setCheckBox(1);
                                    }
                                }
                            }
                        }
                        data = getData(ignoreList);
                        notifyDataSetChanged();

                        int childCounter = 0;
                        int childArraySize = 0;
                        int parentId = data.get(position).getShopPaId();
                        for (int i = 0; i < oData.size(); i++) {
                            if (oData.get(i).getShopPaId() == parentId) {
                                childArraySize = oData.get(i).getArrayChildren().size();
                                for (int j = 0; j < childArraySize; j++) {
                                    if (oData.get(i).getArrayChildren().get(j).getCheckBox() == 1) {
                                        childCounter++;
                                    }
                                }
                            }
                        }
                        if (childArraySize == childCounter) {
                            tvSectionName.setTextColor(Color.GRAY);
                            data = getData(ignoreList);
                            notifyDataSetChanged();
                        } else {
                            tvSectionName.setTextColor(Color.BLACK);
                            data = getData(ignoreList);
                            notifyDataSetChanged();
                        }

                    }
                    DatabaseHandler.getInstance(mContext).close();
                }
            });
        }
        return convertView;
    }

    @Override
    public void swapItems(int to, int from) {
        Object temp = this.data.get(to);
        this.data.set(to, this.data.get(from));
        this.data.set(from, (ShopParentModelMerger) temp);

        notifyDataSetChanged();
    }

    public class ViewHolderParent {
        TextView tvShopSecNamePar;
        ImageView ivShopSecIcon;
        ImageView ivArrow;
        TextView tvShopChilItemName, tvShopChilQuantity;
        ImageView ivShopChilCheckBox;
        ImageView ivHamburger;
    }

    public List<ShopParentModelMerger> getData(ArrayList toIgnore) {

        List<ShopParentModelMerger> dataTemp = new ArrayList<>();

        for (int i = 0; i < this.oData.size(); ++i) {
            if (ignoreList.contains(this.oData.get(i).getShopPaId())) {
                ShopParentModelMerger tParent = new ShopParentModelMerger();
                tParent.setIsParent(true);
                tParent.setIsClick(false);
                tParent.setShopPaId(this.oData.get(i).getShopPaId());
                tParent.setShopPaSectionName(this.oData.get(i).getShopPaSectionName().toString());
                tParent.setShopPaSectionIcon(this.oData.get(i).getShopPaSectionIcon().toString());
                dataTemp.add(tParent);
            } else {
                ShopParentModelMerger tParent = new ShopParentModelMerger();
                tParent.setIsParent(true);
                tParent.setIsClick(true);
                tParent.setShopPaId(this.oData.get(i).getShopPaId());
                tParent.setShopPaSectionName(this.oData.get(i).getShopPaSectionName().toString());
                tParent.setShopPaSectionIcon(this.oData.get(i).getShopPaSectionIcon().toString());

                dataTemp.add(tParent);

                for (int j = 0; j < oData.get(i).getArrayChildren().size(); ++j) {
                    ShopParentModelMerger tChild = new ShopParentModelMerger();
                    tChild.setIsParent(false);
                    tChild.setIsClick(false);
                    tChild.setShopPaId(oData.get(i).getShopPaId());
                    tChild.setShopPaSectionName(oData.get(i).getShopPaSectionName().toString());
                    tChild.setShopPaSectionIcon(oData.get(i).getShopPaSectionIcon().toString());
                    tChild.setShop_chil_id(oData.get(i).getArrayChildren().get(j).getShopChId());
                    tChild.setShop_chil_item_name(oData.get(i).getArrayChildren().get(j).getShopChItemName());
                    tChild.setCheckBox(oData.get(i).getArrayChildren().get(j).getCheckBox());
                    tChild.setShop_chil_quantity(oData.get(i).getArrayChildren().get(j).getShopChQuantity());
                    tChild.setShop_selec_icon(oData.get(i).getArrayChildren().get(j).getShopChSectionId());

                    dataTemp.add(tChild);
                }
            }
        }
        return dataTemp;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}