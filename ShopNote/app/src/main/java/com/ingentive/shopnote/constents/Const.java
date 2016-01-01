package com.ingentive.shopnote.constents;

/**
 * Created by PC on 12/24/2015.
 */
public class Const {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shopnote_db";
    // Tables List
    public static final String TABLE_DICTIONARY = "dictionary_tbl";
    public static final String TABLE_SECTION_ORDER = "section_tbl";
    public static final String TABLE_FAVORIT_LIST = "favorit_list_tbl";
    public static final String TABLE_CURRENT_LIST = "current_list_tbl";
    public static final String TABLE_LIST_INVENTORY = "list_inventory_tbl";
    public static final String TABLE_HISTORY = "history_tbl";
    public static final String TABLE_SCREEN_TEXT = "screen_text_tbl";
    public static final String TABLE_SETTING = "setting_tbl";
    //ID List
    public static final String ID_PRIMARY_KEY = "id";
    public static final String ID_DICTIONARY = "dictionary_id";
    public static final String ID_SECTION_ORDER = "section_id";
    public static final String ID_ORDER_NO= "order_id";

    // Name LIST
    public static final String NAME_SECTION_ORDER = "section_name";
    public static final String IMAGE_SECTION_ORDER = "section_image";
    public static final String DEFAULT_SECTION_ORDER = "section_default";
    public static final String NAME_ITEM = "item_name";
    public static final String NAME_CHECKED = "checked";
    public static final String NAME_QUANTITY = "quantity";
    public static final String NAME_LIST_NAME = "list_name";
    public static final String NAME_LIST_NO = "list_num";
    public static final String NAME_DATE_PURCHASED = "date_purchased";
    public static final String NAME_NAME = "name";
    public static final String NAME_TEXT = "text";
    public static final String NAME_REGISTERED_EMAIL = "reg_email";
    public static final String NAME_FAVORIES_BY_SECTION = "fav_by_section";
    public static final String NAME_SHOP_BY_SECTION = "shop_by_section";
    public static final String NAME_LIST_INTRO = "list_intro";
    public static final String NAME_SHOP_INTRO = "shop_intro";
    public static final String NAME_FAVORIT_INTRO= "fav_Intro";
    public static final String NAME_HISTORY_INTRO = "history_intro";
    public static final String NAME_LIST_EDIT_INTRO = "list_edit_intro";
    public static final String NAME_SECTIONS_INTRO = "sections_intro";
    public static final String NAME_SEARCH_INTRO =  "search_Intro";
    public static final String  NAME_ADD_INTRO=  "add_intro";
    public static final String NAME_USER_RATED=  "user_rated";
    public static final String NAME_SECTION_ORDER_NO=  "order_no";

    // DICTIONARY TABLE
    public static String CREATE_TABLE_DICTIONARY = "CREATE TABLE " + TABLE_DICTIONARY + "("
            + ID_DICTIONARY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_ITEM + " TEXT,"
            + ID_SECTION_ORDER + " INTEGER" + ")";

    // SECTION ORDER TABLE
    public static String CREATE_TABLE_SECTION_ORDER = "CREATE TABLE " + TABLE_SECTION_ORDER + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_SECTION_ORDER_NO + " INTEGER,"
            + NAME_SECTION_ORDER + " TEXT,"
            + IMAGE_SECTION_ORDER + " TEXT,"
            + DEFAULT_SECTION_ORDER + " INTEGER" +")";

    // CURRENT LIST TABLE
    public static String CREATE_TABLE_CURRENT_LIST = "CREATE TABLE " + TABLE_CURRENT_LIST + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_SECTION_ORDER_NO + " INTEGER,"
            + NAME_ITEM + " TEXT,"
            + NAME_CHECKED + " INTEGER,"
            + NAME_QUANTITY + " TEXT,"
            + NAME_LIST_NAME + " TEXT,"
            + NAME_LIST_NO + " INTEGER" +")";

    // LIST INVENTORY TABLE
    public static String CREATE_TABLE_LIST_INVENTORY = "CREATE TABLE " + TABLE_LIST_INVENTORY + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_LIST_NO + " INTEGER,"
            + NAME_LIST_NAME + " TEXT" +")";
    // HISTORY TABLE
    public static String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_DATE_PURCHASED + " TEXT,"
            + NAME_ITEM + " TEXT,"
            + NAME_QUANTITY + " TEXT" +")";

    // FAVORIT LIST TABLE
    public static String CREATE_TABLE_FAVORIT_LIST = "CREATE TABLE " + TABLE_FAVORIT_LIST + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_ITEM + " TEXT" +")";

    // SCREEN TABLE
    public static String CREATE_TABLE_SCREEN_TEXT = "CREATE TABLE " + TABLE_SCREEN_TEXT + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_NAME + " TEXT,"
            + NAME_TEXT + " TEXT" +")";

    // SETTING TABLE
    public static String CREATE_TABLE_SETTING = "CREATE TABLE " + TABLE_SETTING + "("
            + ID_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_REGISTERED_EMAIL + " TEXT,"
            + NAME_FAVORIES_BY_SECTION + " INTEGER,"
            + NAME_SHOP_BY_SECTION + " INTEGER,"
            + NAME_LIST_INTRO + " INTEGER,"
            + NAME_SHOP_INTRO + " INTEGER,"
            + NAME_FAVORIT_INTRO + " INTEGER,"
            + NAME_HISTORY_INTRO + " INTEGER,"
            + NAME_LIST_EDIT_INTRO + " INTEGER,"
            + NAME_SECTIONS_INTRO + " INTEGER,"
            + NAME_SEARCH_INTRO + " INTEGER,"
            + NAME_ADD_INTRO + " INTEGER,"
            + NAME_USER_RATED + " INTEGER" +")";
}
