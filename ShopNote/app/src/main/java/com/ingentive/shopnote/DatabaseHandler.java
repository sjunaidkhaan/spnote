package com.ingentive.shopnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ingentive.shopnote.constents.Const;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.HistoryChildModel;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.HistoryParentModel;
import com.ingentive.shopnote.model.InventoryModel;
import com.ingentive.shopnote.model.ListAddBasicModel;
import com.ingentive.shopnote.model.ManageSectionModel;
import com.ingentive.shopnote.model.ScreenTextModel;
import com.ingentive.shopnote.model.SectionModel;
import com.ingentive.shopnote.model.SettingModel;
import com.ingentive.shopnote.model.ShopChildModel;
import com.ingentive.shopnote.model.ShopParentModel;

import java.util.ArrayList;
import java.util.List;
//jk

/**
 * Created by PC on 12/22/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    List<String> mList;

    public DatabaseHandler(Context context) {
        super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
        //Log.d("DbHandler", "Constructor"+TABLE_DICTIONARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d("DbHandler", "OnCreate()"+TABLE_DICTIONARY);
        db.execSQL(Const.CREATE_TABLE_DICTIONARY);
        db.execSQL(Const.CREATE_TABLE_SECTION_ORDER);
        db.execSQL(Const.CREATE_TABLE_CURRENT_LIST);
        db.execSQL(Const.CREATE_TABLE_LIST_INVENTORY);
        db.execSQL(Const.CREATE_TABLE_HISTORY);
        db.execSQL(Const.CREATE_TABLE_FAVORIT_LIST);
        db.execSQL(Const.CREATE_TABLE_SCREEN_TEXT);
        db.execSQL(Const.CREATE_TABLE_SETTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_DICTIONARY);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_SECTION_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_CURRENT_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_LIST_INVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_FAVORIT_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_SCREEN_TEXT);
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_SETTING);
        onCreate(db);
    }

    void addScreenText(ScreenTextModel screen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_NAME, screen.getmName());
        values.put(Const.NAME_TEXT, screen.getmTxt());
        db.insert(Const.TABLE_SCREEN_TEXT, null, values);
        db.close();
    }

    void addDictionary(DictionaryModel dictionaryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_ITEM, dictionaryModel.getItemName());
        values.put(Const.ID_SECTION_ORDER, dictionaryModel.getSectionId());
        db.insert(Const.TABLE_DICTIONARY, null, values);
        db.close();
    }

    void addSection(SectionModel sectionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_SECTION_ORDER_NO, sectionModel.getSectionOrderNo());
        values.put(Const.NAME_SECTION_ORDER, sectionModel.getSectionName());
        values.put(Const.IMAGE_SECTION_ORDER, sectionModel.getSectionImage());
        values.put(Const.DEFAULT_SECTION_ORDER, sectionModel.getDefaultSection());
        db.insert(Const.TABLE_SECTION_ORDER, null, values);
        db.close();
    }

    /*void addCurrentList(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_SECTION_ORDER_NO, curr.getOrderNo());
        values.put(Const.NAME_ITEM, curr.getItemName());
        values.put(Const.NAME_CHECKED, curr.getChecked());
        values.put(Const.NAME_QUANTITY, curr.getQuantity());
        values.put(Const.NAME_LIST_NAME, curr.getListName());
        values.put(Const.NAME_LIST_NO, curr.getListNo());
        db.insert(Const.TABLE_CURRENT_LIST, null, values);
    }*/
    public void addCurrentList(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long rows = 0;
        values.put(Const.NAME_SECTION_ORDER_NO, curr.getOrderNo());
        values.put(Const.NAME_ITEM, curr.getItemName());
        values.put(Const.NAME_CHECKED, curr.getChecked());
        values.put(Const.NAME_QUANTITY, curr.getQuantity());
        values.put(Const.NAME_LIST_NAME, curr.getListName());
        values.put(Const.NAME_LIST_NO, curr.getListNo());
        String sqlQuery = "SELECT * FROM " + Const.TABLE_CURRENT_LIST + " WHERE " + Const.NAME_ITEM
                + " = " + "\"" + curr.getItemName() + "\"";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            Log.d("addCurrentList ", "item already exit ");
        } else {
            db.insert(Const.TABLE_CURRENT_LIST, null, values);
        }
        cursor.close();
        db.close();
    }

    void addDictionaryNewItem(DictionaryModel dic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NAME_ITEM, dic.getItemName());
        values.put(Const.ID_SECTION_ORDER, dic.getSectionId());
        String sqlQuery = "SELECT * FROM " + Const.TABLE_DICTIONARY + " WHERE " + Const.NAME_ITEM
                + " = "+"\""+ dic.getItemName()+"\"";
        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null && c.getCount() != 0) {

        } else {
            db.insert(Const.TABLE_DICTIONARY, null, values);
        }
        db.close();
    }

    public void addInventry(InventoryModel invent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_LIST_NO, invent.getListNo());
        values.put(Const.NAME_LIST_NAME, invent.getListName());
        db.insert(Const.TABLE_LIST_INVENTORY, null, values);
        db.close();
    }

    public void addHistory(HistoryModel history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_DATE_PURCHASED, history.getDatePurchased());
        values.put(Const.NAME_ITEM, history.getItemName());
        values.put(Const.NAME_QUANTITY, history.getQuantity());
        db.insert(Const.TABLE_HISTORY, null, values);
        db.close();
    }

    public void addFavorit(FavoritListModel fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_ITEM, fav.getItemName());
        db.insert(Const.TABLE_FAVORIT_LIST, null, values);
        db.close();
    }

    public void addNewSection(SectionModel section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NAME_SECTION_ORDER_NO, section.getSectionOrderNo());
        values.put(Const.NAME_SECTION_ORDER, section.getSectionName());
        values.put(Const.IMAGE_SECTION_ORDER, section.getSectionImage());
        values.put(Const.DEFAULT_SECTION_ORDER, section.getDefaultSection());
        db.insert(Const.TABLE_SECTION_ORDER, null, values);
        db.close();
    }

    void addSetting(SettingModel setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_REGISTERED_EMAIL, setting.getRegEmail());
        values.put(Const.NAME_FAVORIES_BY_SECTION, setting.getFavBySection());
        values.put(Const.NAME_SHOP_BY_SECTION, setting.getShopBySection());
        values.put(Const.NAME_LIST_INTRO, setting.getListIntro());
        values.put(Const.NAME_SHOP_INTRO, setting.getShopIntro());
        values.put(Const.NAME_FAVORIT_INTRO, setting.getFavIntro());
        values.put(Const.NAME_HISTORY_INTRO, setting.getHistoryIntro());
        values.put(Const.NAME_LIST_EDIT_INTRO, setting.getListEditIntro());
        values.put(Const.NAME_SECTIONS_INTRO, setting.getSectionIntro());
        values.put(Const.NAME_SEARCH_INTRO, setting.getSearchIntro());
        values.put(Const.NAME_ADD_INTRO, setting.getAddIntro());
        values.put(Const.NAME_USER_RATED, setting.getUserRated());
        db.insert(Const.TABLE_SETTING, null, values);
        db.close();
    }
    void makeFav(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_ITEM, "\""+itemName+"\"");
        db.insert(Const.TABLE_FAVORIT_LIST, null, values);
    }
    public void updateCheckItem(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NAME_CHECKED, curr.getChecked());
        db.update(Const.TABLE_CURRENT_LIST, values, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(curr.getCurrListId())});
        db.close();
    }

    public List<String> getHistoryItems() {
        mList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_HISTORY + " ORDER BY " + Const.NAME_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                mList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }
    public List<String> getFavItems() {
        mList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_FAVORIT_LIST + " INNER JOIN " +
                Const.TABLE_HISTORY + " ON " + Const.TABLE_FAVORIT_LIST + "." +
                Const.NAME_ITEM + " = " + Const.TABLE_HISTORY + "." + Const.NAME_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                mList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }

    public void removeFavorit(FavoritListModel fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE from " + Const.TABLE_FAVORIT_LIST + " where " + Const.NAME_ITEM + "=" +"'" +fav.getItemName() +"'";
        Log.d("QueryFavoriteDelete:", query);
        db.execSQL(query);
        db.close();
    }
    /*public List<String> getDicItems() {
        List<String> mList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_DICTIONARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //DictionaryModel dicList = new DictionaryModel();
                //dicList.setItemName(cursor.getString(1));
                mList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return mList;
    }*/
    /*public boolean CheckIsFavItem(String itemName) {
        String selectQuery = "SELECT * FROM " + Const.TABLE_FAVORIT_LIST +
                " WHERE " + Const.NAME_ITEM + "='"+itemName+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }*/

    public List<DictionaryModel> getDicItems() {
        List<DictionaryModel> dicList = new ArrayList<DictionaryModel>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_DICTIONARY + " ORDER BY " + Const.NAME_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DictionaryModel dicModel = new DictionaryModel();
                dicModel.setDicId(Integer.parseInt(cursor.getString(0)));
                dicModel.setItemName(cursor.getString(1));
                dicModel.setFavItem(0);
                dicModel.setHistoryItem(0);
                dicModel.setFavIcon(R.drawable.favorite_unselected);
                dicModel.setHistoryIcon(R.drawable.hisb);
                dicList.add(dicModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dicList;
    }

    public List<ListAddBasicModel> getItemName() {
        List<ListAddBasicModel> mList = new ArrayList<ListAddBasicModel>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_DICTIONARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ListAddBasicModel listModel = new ListAddBasicModel();
                listModel.setItemName(cursor.getString(1));
                mList.add(listModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }

    public List<String> getFavItem() {
        List<String> mList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_FAVORIT_LIST + " ORDER BY " + Const.NAME_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                mList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }

    public void updateListName(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("updateListName ", " getListName " + curr.getListName());
        values.put(Const.NAME_LIST_NAME, curr.getListName());
        int reows = db.update(Const.TABLE_CURRENT_LIST, values, null, null);
        Log.d("effected rows ", "" + reows);
        db.close();
    }

    public String getListName() {
        String title = "";
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CURRENT_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            if (cursor != null && cursor.getCount() != 0) {
                title = cursor.getString(5).toString();
                cursor.close();
                db.close();
                return title;
            }
        cursor.close();
        db.close();
        return "My First Shopnote";
    }

    public List<ManageSectionModel> getSectionData() {
        List<ManageSectionModel> secList = new ArrayList<ManageSectionModel>();
        String selectQuery = "SELECT * FROM " + Const.TABLE_SECTION_ORDER + " ORDER BY " + Const.NAME_SECTION_ORDER_NO + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ManageSectionModel secModel = new ManageSectionModel();
                secModel.setManageSectionId(Integer.parseInt(cursor.getString(0)));
                secModel.setOrderNo(Integer.parseInt(cursor.getString(1)));
                secModel.setSectionName(cursor.getString(2));
                secModel.setOptionIcon(cursor.getString(3));
                secList.add(secModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return secList;
    }

    public String getIconSection(String itemName) {
        String selectQuery = "SELECT * FROM " + Const.TABLE_SECTION_ORDER + " INNER JOIN " +
                Const.TABLE_DICTIONARY + " ON " + Const.TABLE_SECTION_ORDER + "." +
                Const.ID_PRIMARY_KEY + " = " + Const.TABLE_DICTIONARY + "." + Const.ID_SECTION_ORDER
                + " WHERE " + Const.NAME_ITEM + "=" + "\"" + itemName + "\"";
        String iconName = "unknown.png";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            iconName = cursor.getString(3).toString();
            cursor.close();
            db.close();
            return iconName;
        }
        cursor.close();
        db.close();
        return iconName;
    }
    public String getSectionIcon(String sectionName) {
        String selectQuery = "SELECT * FROM " + Const.TABLE_SECTION_ORDER
                + " WHERE " + Const.NAME_SECTION_ORDER + "=" + "\"" + sectionName + "\"";
        String iconName = "unknown.png";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            iconName = cursor.getString(3).toString();
            cursor.close();
            db.close();
            return iconName;
        }
        cursor.close();
        db.close();
        return iconName;
    }
    public boolean isFavorit(String itemName) {
        String sqlQuery = "SELECT * FROM " + Const.TABLE_FAVORIT_LIST + " WHERE " + Const.NAME_ITEM
                + " = " + "\"" + itemName + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public int isChecked(String itemName) {
        String sqlQuery = "SELECT * FROM " + Const.TABLE_CURRENT_LIST + " WHERE " + Const.NAME_ITEM
                + " = " + "\"" + itemName + "\"";
        int check = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            check = Integer.parseInt(cursor.getString(3));
            cursor.close();
            db.close();
            return check;
        }
        cursor.close();
        db.close();
        return check;
    }

    public boolean isInList(String itemName) {
        String sqlQuery = "SELECT * FROM " + Const.TABLE_CURRENT_LIST + " WHERE " + Const.NAME_ITEM
                + " = " + "\"" + itemName + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean isExist(int secId, String itemName) {
        String sqlQuery = "SELECT * FROM " + Const.TABLE_DICTIONARY + " WHERE " + Const.NAME_ITEM
                + " = " + "\"" + itemName + "\"" + " AND " + Const.ID_SECTION_ORDER + " = " + secId;
        Log.d("isExist ", "sqlQuery " + sqlQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public List<CurrentListModel> getCurrList() {
        List<CurrentListModel> mList = new ArrayList<CurrentListModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CURRENT_LIST + " ORDER BY " + Const.NAME_SECTION_ORDER_NO + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CurrentListModel currModel = new CurrentListModel();
                currModel.setCurrListId(Integer.parseInt(cursor.getString(0)));
                currModel.setOrderNo(Integer.parseInt(cursor.getString(1)));
                currModel.setItemName(cursor.getString(2));
                currModel.setChecked(Integer.parseInt(cursor.getString(3)));

                String str = cursor.getString(4);
                if (str == null || str.isEmpty() || str.equalsIgnoreCase("null")) {
                    currModel.setQuantity("1");
                } else {
                    currModel.setQuantity(cursor.getString(4));
                }
                currModel.setListName(cursor.getString(5));
                currModel.setListNo(Integer.parseInt(cursor.getString(6)));
                currModel.setFavUnselectedIcon(R.drawable.favorite_unselected);
                currModel.setFavSelectedIcon(R.drawable.favorite_selected);
                mList.add(currModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }

    public List<FavoritListModel> getFavList() {
        List<FavoritListModel> favList = new ArrayList<FavoritListModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_FAVORIT_LIST + " ORDER BY " + Const.NAME_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FavoritListModel favModel = new FavoritListModel();
                favModel.setFavListId(Integer.parseInt(cursor.getString(0)));
                favModel.setItemName(cursor.getString(1));
                favList.add(favModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favList;
    }

    public List<HistoryModel> getHistory() {
        List<HistoryModel> histList = new ArrayList<HistoryModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_HISTORY + " ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HistoryModel histModel = new HistoryModel();
                histModel.setHistoryId(Integer.parseInt(cursor.getString(0)));
                histModel.setDatePurchased(cursor.getString(1));
                histModel.setItemName(cursor.getString(2));
                histModel.setQuantity(cursor.getString(3));
                histList.add(histModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return histList;
    }

    public List<HistoryParentModel> getHisPar() {
        List<HistoryParentModel> histParList = new ArrayList<HistoryParentModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HistoryParentModel histPaModel = new HistoryParentModel();
                histPaModel.setHisPaId(Integer.parseInt(cursor.getString(0)));
                histPaModel.setHisPaDatePurchased(cursor.getString(1));
                histParList.add(histPaModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return histParList;
    }

    public List<ShopParentModel> getShopParSection() {
        List<ShopParentModel> shopParList = new ArrayList<ShopParentModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_SECTION_ORDER + " ORDER BY " + Const.NAME_SECTION_ORDER_NO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ShopParentModel shopPaModel = new ShopParentModel();
                shopPaModel.setShopPaId(Integer.parseInt(cursor.getString(0)));
                shopPaModel.setShopPaSectionName(cursor.getString(2));
                shopPaModel.setShopPaSectionIcon(cursor.getString(3));
                shopParList.add(shopPaModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return shopParList;
    }

    public List<ShopChildModel> getShopChil() {
        List<ShopChildModel> shopChilList = new ArrayList<ShopChildModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CURRENT_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ShopChildModel shopChilModel = new ShopChildModel();
                shopChilModel.setShopChId(Integer.parseInt(cursor.getString(0)));
                shopChilModel.setShopChItemName(cursor.getString(2));
                shopChilModel.setShopChQuantity(cursor.getString(4));
                shopChilList.add(shopChilModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return shopChilList;
    }

    public List<ShopChildModel> getShopChilData(int secId) {
        List<ShopChildModel> shopChilList = new ArrayList<ShopChildModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CURRENT_LIST + " INNER JOIN " +
                Const.TABLE_DICTIONARY + " ON " + Const.TABLE_CURRENT_LIST + "." +
                Const.NAME_ITEM + " = " + Const.TABLE_DICTIONARY + "." + Const.NAME_ITEM +
                " WHERE " + Const.ID_SECTION_ORDER + " = " + secId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst() && cursor != null && cursor.getCount() != 0) {
            do {
                ShopChildModel shopChilModel = new ShopChildModel();
                shopChilModel.setShopChId(Integer.parseInt(cursor.getString(0)));
                shopChilModel.setShopChItemName(cursor.getString(2));
                shopChilModel.setCheckBox(Integer.parseInt(cursor.getString(3)));

                String str = cursor.getString(4);
                if (str == null || str.isEmpty() || str.equalsIgnoreCase("null")) {
                    shopChilModel.setShopChQuantity("1");
                    Log.d("ShopChildModel ", "if quantity  " + shopChilModel.getShopChQuantity());
                } else {
                    Log.d("ShopChildModel ", "else quantity  " + str);
                    shopChilModel.setShopChQuantity(cursor.getString(4));
                }
                shopChilList.add(shopChilModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return shopChilList;
    }

    /*public List<HistoryChildModel>getHisChil(){
        List<HistoryChildModel> histChilList = new ArrayList<HistoryChildModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HistoryChildModel histChilModel = new HistoryChildModel();
                histChilModel.setHisChId(Integer.parseInt(cursor.getString(0)));
                histChilModel.setHisChDatePurchased(cursor.getString(1));
                histChilModel.setHisChItemName(cursor.getString(2));
                histChilList.add(histChilModel);
            } while (cursor.moveToNext());
        }
        return histChilList;
    }*/
    public List<HistoryChildModel> getHisChil(String datePur) {
        List<HistoryChildModel> histChilList = new ArrayList<HistoryChildModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_HISTORY
                + " WHERE " + Const.NAME_DATE_PURCHASED + "=" + "\"" + datePur + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() != 0) {
            HistoryChildModel histChilModel = new HistoryChildModel();
            if (cursor.moveToFirst()) {
                do {
                    histChilModel = new HistoryChildModel();
                    histChilModel.setHisChId(Integer.parseInt(cursor.getString(0)));
                    histChilModel.setHisChItemName(cursor.getString(2));
                    histChilList.add(histChilModel);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return histChilList;
    }

    public void updateQuantity(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NAME_QUANTITY, curr.getQuantity());
        db.update(Const.TABLE_CURRENT_LIST, values, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(curr.getCurrListId())});
        db.close();
    }

    public void updateOrderNo(ManageSectionModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.NAME_SECTION_ORDER_NO, model.getOrderNo());
        db.update(Const.TABLE_SECTION_ORDER, values, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(model.getManageSectionId())});
        db.close();
    }
    public int getSectionId(String sectionName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int secId;
        String selectQuery = "SELECT  * FROM " + Const.TABLE_SECTION_ORDER
                + " WHERE " + Const.NAME_SECTION_ORDER + "=" + "\"" + sectionName + "\"";

        Log.d("secAssignToItem ", "selectQuery " + selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        secId = Integer.parseInt(cursor.getString(0));
        cursor.close();
        db.close();
        return secId;
    }

    public void secAssignToItem(String itemName, int secId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values = new ContentValues();
        values.put(Const.ID_SECTION_ORDER, secId);
        int rows = db.update(Const.TABLE_DICTIONARY, values, Const.NAME_ITEM + " = ?",
                new String[]{String.valueOf(itemName)});
        Log.d("Affectedrows; ", rows + "");
        db.close();
    }

    public int getMaxOrderNo() {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        int orderNo = 0;
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CURRENT_LIST + " ORDER BY "
                + Const.NAME_SECTION_ORDER_NO + " DESC LIMIT 1";
        Log.d("secAssignToItem ", "selectQuery " + selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() != 0) {
            orderNo = Integer.parseInt(cursor.getString(1));
            cursor.close();
            db.close();
            return orderNo;
        }
        cursor.close();
        db.close();
        return orderNo;
    }
    public void secAssignIcon(String newIcon, int unknownsecId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values = new ContentValues();
        values.put(Const.IMAGE_SECTION_ORDER, newIcon);
        int rows = db.update(Const.TABLE_SECTION_ORDER, values, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(unknownsecId)});
        Log.d("newIcon; ", newIcon + "");
        Log.d("unknownsecId; ", unknownsecId + "");
        db.close();
    }

    public void deleteItem(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_CURRENT_LIST, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(curr.getCurrListId())});
        db.close();
    }
    public void deleteDateBaseFromHistory(String hisDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_HISTORY, Const.NAME_DATE_PURCHASED + " = ?",
                new String[]{String.valueOf(hisDate)});
        db.close();
    }
    public void deleteItemBaseFromHistory(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_HISTORY, Const.ID_PRIMARY_KEY + " = ?",
                new String[]{String.valueOf(itemId)});
        db.close();
    }
}
