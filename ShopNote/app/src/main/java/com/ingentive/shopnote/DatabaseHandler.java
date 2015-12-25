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
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.InventoryModel;
import com.ingentive.shopnote.model.ScreenTextModel;
import com.ingentive.shopnote.model.SectionModel;
import com.ingentive.shopnote.model.SettingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
 
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
    }
    void addDictionary(DictionaryModel dictionaryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_ITEM, dictionaryModel.getItemName());
        values.put(Const.ID_SECTION_ORDER, dictionaryModel.getSectionId());
        db.insert(Const.TABLE_DICTIONARY, null, values);
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
    }
    void addCurrentList(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_SECTION_ORDER_NO, curr.getOrderNo());
        values.put(Const.NAME_ITEM, curr.getItemName());
        values.put(Const.NAME_CHECKED, curr.getChecked());
        values.put(Const.NAME_QUANTITY, curr.getQuantity());
        values.put(Const.NAME_LIST_NO, curr.getListNo());
        db.insert(Const.TABLE_CURRENT_LIST, null, values);
    }

    void addInventry(InventoryModel invent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_LIST_NO, invent.getListNo());
        values.put(Const.NAME_LIST_NAME, invent.getListName());
        db.insert(Const.TABLE_LIST_INVENTORY, null, values);
    }

    void addHistory(HistoryModel history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_DATE_PURCHASED, history.getDatePurchased());
        values.put(Const.NAME_ITEM, history.getItemName());
        values.put(Const.NAME_QUANTITY, history.getQuantity());
        db.insert(Const.TABLE_HISTORY, null, values);
    }


    void addFavorit(FavoritListModel fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(Const.NAME_ITEM, fav.getItemName());
        db.insert(Const.TABLE_FAVORIT_LIST, null, values);
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
    }

    public List<DictionaryModel> getAllContacts() {
        List<DictionaryModel> dictList = new ArrayList<DictionaryModel>();
        String selectQuery = "SELECT  * FROM " + Const.TABLE_DICTIONARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DictionaryModel dicModel = new DictionaryModel();
                dicModel.setDicId(Integer.parseInt(cursor.getString(0)));
                dicModel.setItemName(cursor.getString(1));
                dicModel.setSectionId(Integer.parseInt(cursor.getString(2)));
                dictList.add(dicModel);
            } while (cursor.moveToNext());
        }
        return dictList;
    }
    /*
    Contact getSection(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }*/
}
