package com.ingentive.shopnote;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.InventoryModel;
import com.ingentive.shopnote.model.SectionModel;
import com.ingentive.shopnote.model.SettingModel;

/**
 * Created by PC on 12/22/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shopnote_db";
    // Tables List
    private static final String TABLE_DICTIONARY = "dictionary_tbl";
    private static final String TABLE_SECTION_ORDER = "section_tbl";
    private static final String TABLE_FAVORIT_LIST = "favorit_list_tbl";
    private static final String TABLE_CURRENT_LIST = "current_list_tbl";
    private static final String TABLE_LIST_INVENTORY = "list_inventory_tbl";
    private static final String TABLE_HISTORY = "history_tbl";
    private static final String TABLE_SCREEN_TEXT = "screen_text_tbl";
    private static final String TABLE_SETTING = "setting_tbl";
    //ID List
    private static final String ID_PRIMARY_KEY = "id";
    private static final String ID_DICTIONARY = "dictionary_id";
    private static final String ID_SECTION_ORDER = "section_id";
    private static final String ID_ORDER_NO= "order_id";

    // Name LIST
    private static final String NAME_SECTION_ORDER = "section_name";
    private static final String IMAGE_SECTION_ORDER = "section_image";
    private static final String DEFAULT_SECTION_ORDER = "section_default";
    private static final String NAME_ITEM = "item_name";
    private static final String NAME_CHECKED = "checked";
    private static final String NAME_QUANTITY = "quantity";
    private static final String NAME_LIST_NAME = "list_name";
    private static final String NAME_LIST_NO = "list_num";
    private static final String NAME_DATE_PURCHASED = "date_purchased";
    private static final String NAME_NAME = "name";
    private static final String NAME_TEXT = "text";
    private static final String NAME_REGISTERED_EMAIL = "reg_email";
    private static final String NAME_FAVORIES_BY_SECTION = "fav_by_secr";
    private static final String NAME_SHOP_BY_SECTION = "shop_by_secr";
    private static final String NAME_LIST_INTRO = "list_intro";
    private static final String NAME_SHOP_INTRO = "shop_intro";
    private static final String NAME_FAVORIT_INTRO= "fav_Intro";
    private static final String NAME_HISTORY_INTRO = "history_intro";
    private static final String NAME_LIST_EDIT_INTRO = "list_edit_intro";
    private static final String NAME_SECTIONS_INTRO = "sections_intro";
    private static final String NAME_SEARCH_INTRO =  "search_Intro";
    private static final String NAMe_ADD_INTRO =  "add_intro";
    private static final String NAMe_USER_RATED=  "user_rated";
    private static final String NAMe_SECTION_ORDER_NO=  "order_no";



    // DICTIONARY TABLE
   /* String  = "CREATE TABLE " + TABLE_DICTIONARY + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + NAME_ITEM + " TEXT ,"
            + ID_SECTION_ORDER + " INTEGER" + ")";*/

   String CREATE_TABLE_DICTIONARY = "CREATE TABLE " + TABLE_DICTIONARY + "("
            + ID_DICTIONARY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_ITEM + " TEXT,"
            + ID_SECTION_ORDER + " INTEGER" + ")";

    // SECTION ORDER TABLE
    String CREATE_TABLE_SECTION_ORDER = "CREATE TABLE " + TABLE_SECTION_ORDER + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAMe_SECTION_ORDER_NO + " INTEGER,"
            + NAME_SECTION_ORDER + " TEXT,"
            + IMAGE_SECTION_ORDER + " TEXT,"
            + DEFAULT_SECTION_ORDER + " INTEGER" +")";

    // CURRENT LIST TABLE
    String CREATE_TABLE_CURRENT_LIST = "CREATE TABLE " + TABLE_CURRENT_LIST + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAMe_SECTION_ORDER_NO + " INTEGER,"
            + NAME_ITEM + " TEXT,"
            + NAME_CHECKED + " INTEGER,"
            + NAME_QUANTITY + " TEXT,"
            + NAME_LIST_NO + " INTEGER" +")";

    // LIST INVENTORY TABLE
    String CREATE_TABLE_LIST_INVENTORY = "CREATE TABLE " + TABLE_LIST_INVENTORY + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_LIST_NO + " INTEGER,"
            + NAME_LIST_NAME + " TEXT" +")";
    // HISTORY TABLE
    String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_DATE_PURCHASED + " TEXT,"
            + NAME_ITEM + " TEXT,"
            + NAME_QUANTITY + " TEXT" +")";

    // FAVORIT LIST TABLE
    String CREATE_TABLE_FAVORIT_LIST = "CREATE TABLE " + TABLE_FAVORIT_LIST + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_ITEM + " TEXT" +")";

    // SCREEN TABLE
   String CREATE_TABLE_SCREEN_TEXT = "CREATE TABLE " + TABLE_SCREEN_TEXT + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_NAME + " TEXT,"
            + NAME_TEXT + " TEXT" +")";

    // SETTING TABLE
    String CREATE_TABLE_SETTING = "CREATE TABLE " + TABLE_SETTING + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_REGISTERED_EMAIL + " TEXT,"
            + NAME_LIST_NO + " flag INTEGER DEFAULT 0,"
            + NAME_SHOP_BY_SECTION + " flag INTEGER DEFAULT 0,"
            + NAME_LIST_INTRO + " flag INTEGER DEFAULT 0,"
            + NAME_SEARCH_INTRO + " flag INTEGER DEFAULT 0,"
            + NAME_FAVORIT_INTRO + " flag INTEGER DEFAULT 0,"
            + NAME_HISTORY_INTRO + " flag INTEGER DEFAULT 0,"
            + NAME_LIST_EDIT_INTRO + " flag INTEGER DEFAULT 0,"
            + NAME_SECTIONS_INTRO + " flag INTEGER DEFAULT 0,"
            + NAMe_ADD_INTRO + " flag INTEGER DEFAULT 0,"
            + NAMe_USER_RATED + " flag INTEGER DEFAULT 0" +")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.d("DbHandler", "Constructor"+TABLE_DICTIONARY);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d("DbHandler", "OnCreate()"+TABLE_DICTIONARY);
        db.execSQL(CREATE_TABLE_DICTIONARY);
        db.execSQL(CREATE_TABLE_SECTION_ORDER);
        db.execSQL(CREATE_TABLE_CURRENT_LIST);
        db.execSQL(CREATE_TABLE_LIST_INVENTORY);
        db.execSQL(CREATE_TABLE_HISTORY);
        db.execSQL(CREATE_TABLE_FAVORIT_LIST);
        //db.execSQL(CREATE_TABLE_SCREEN_TEXT);
        db.execSQL(CREATE_TABLE_SETTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_INVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORIT_LIST);
        //db.execSQL("DROP TABLE IF EXISTS " + );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(db);
    }

    void addDictionary(DictionaryModel dictionaryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAME_ITEM, dictionaryModel.getItemName());
        values.put(ID_SECTION_ORDER, dictionaryModel.getSectionId());
        db.insert(TABLE_DICTIONARY, null, values);
        db.close();
    }

    void addSection(SectionModel sectionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAMe_SECTION_ORDER_NO, sectionModel.getSectionOrderNo());
        values.put(NAME_SECTION_ORDER, sectionModel.getSectionName());
        values.put(IMAGE_SECTION_ORDER, sectionModel.getSectionImage());
        values.put(DEFAULT_SECTION_ORDER, sectionModel.getDefaultSection());
        db.insert(TABLE_SECTION_ORDER, null, values);
        db.close();
    }
    void addCurrentList(CurrentListModel curr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAMe_SECTION_ORDER_NO, curr.getOrderNo());
        values.put(NAME_ITEM, curr.getItemName());
        values.put(NAME_CHECKED, curr.getChecked());
        values.put(NAME_QUANTITY, curr.getQuantity());
        values.put(NAME_LIST_NO, curr.getListNo());
        db.insert(TABLE_SECTION_ORDER, null, values);
        db.close();
    }

    void addInventry(InventoryModel invent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAME_LIST_NO, invent.getListNo());
        values.put(NAME_LIST_NAME, invent.getListName());
        db.insert(TABLE_LIST_INVENTORY, null, values);
        db.close();
    }

    void addHistory(HistoryModel history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAME_DATE_PURCHASED, history.getDatePaurchased());
        values.put(NAME_ITEM, history.getItemName());
        values.put(NAME_QUANTITY, history.getQuantity());
        db.insert(CREATE_TABLE_HISTORY, null, values);
        db.close();
    }


    void addFavorit(FavoritListModel fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAME_ITEM, fav.getItemName());
        db.insert(CREATE_TABLE_FAVORIT_LIST, null, values);
        db.close();
    }

    void addSetting(SettingModel setting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(NAME_REGISTERED_EMAIL, setting.getRegEmail());
        values.put(NAME_LIST_NO, setting.isListNo());
        values.put(NAME_SHOP_BY_SECTION, setting.isShopBySection());
        values.put(NAME_LIST_INTRO, setting.isListIntro());
        values.put(NAME_SEARCH_INTRO, setting.isSectionIntro());
        values.put(NAME_FAVORIT_INTRO, setting.isFavIntro());
        values.put(NAME_HISTORY_INTRO, setting.isHistoryIntro());
        values.put(NAME_LIST_EDIT_INTRO, setting.isListEditIntro());
        values.put(NAME_SECTIONS_INTRO, setting.isSearchIntro());
        values.put(NAMe_ADD_INTRO, setting.isAddIntro());
        values.put(NAMe_USER_RATED, setting.isUserRated());
        db.insert(CREATE_TABLE_SETTING, null, values);
        db.close();
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
