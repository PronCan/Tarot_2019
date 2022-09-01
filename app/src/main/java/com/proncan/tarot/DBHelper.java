package com.proncan.tarot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tarot_data";

    static ArrayList<Data> cardList;
    SQLiteDatabase database;
    ContentValues values;
    Cursor cursor;

    static boolean checkUpdate = false;

    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        checkUpdate = true;
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        checkUpdate = true;

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "cardNo TEXT, " +
                "cardName TEXT, " +
                "cardEngName TEXT, " +
                "keyword TEXT, " +
                "comment1 TEXT, " +
                "comment2 TEXT, " +
                "comment3 TEXT, " +
                "comment4 TEXT, " +
                "comment5 TEXT, " +
                "comment1_rev TEXT, " +
                "comment2_rev TEXT, " +
                "comment3_rev TEXT, " +
                "comment4_rev TEXT, " +
                "comment5_rev TEXT)");
        // 순서대로 : 카드번호, 카드이름, 카드영문이름, 키워드, 애정, 직업, 건강, 취미, 기타운
    }

    public ArrayList<Data> getAllCard() {
        database = this.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cardList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Data data = new Data(
                        cursor.getString(cursor.getColumnIndex("cardNo")),
                        cursor.getString(cursor.getColumnIndex("cardName")),
                        cursor.getString(cursor.getColumnIndex("cardEngName")),
                        cursor.getString(cursor.getColumnIndex("keyword")),
                        cursor.getString(cursor.getColumnIndex("comment1")),
                        cursor.getString(cursor.getColumnIndex("comment2")),
                        cursor.getString(cursor.getColumnIndex("comment3")),
                        cursor.getString(cursor.getColumnIndex("comment4")),
                        cursor.getString(cursor.getColumnIndex("comment5")),
                        cursor.getString(cursor.getColumnIndex("comment1_rev")),
                        cursor.getString(cursor.getColumnIndex("comment2_rev")),
                        cursor.getString(cursor.getColumnIndex("comment3_rev")),
                        cursor.getString(cursor.getColumnIndex("comment4_rev")),
                        cursor.getString(cursor.getColumnIndex("comment5_rev"))
                );

                cardList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cardList;
    }

    public void addQuestion(JSONObject obj) {
        database = this.getWritableDatabase();
        values = new ContentValues();

        try {
            String cardNo = obj.getString("cardNo");
            String cardName = obj.getString("cardName");
            String cardEngName = obj.getString("cardEngName");
            String keyword = obj.getString("keyword");
            String comment1 = obj.getString("comment1");
            String comment2 = obj.getString("comment2");
            String comment3 = obj.getString("comment3");
            String comment4 = obj.getString("comment4");
            String comment5 = obj.getString("comment5");
            String comment1_rev = obj.getString("comment1_rev");
            String comment2_rev = obj.getString("comment2_rev");
            String comment3_rev = obj.getString("comment3_rev");
            String comment4_rev = obj.getString("comment4_rev");
            String comment5_rev = obj.getString("comment5_rev");

            values.put("cardNo", cardNo);
            values.put("cardName", cardName);
            values.put("cardEngName", cardEngName);
            values.put("keyword", keyword);
            values.put("comment1", comment1);
            values.put("comment2", comment2);
            values.put("comment3", comment3);
            values.put("comment4", comment4);
            values.put("comment5", comment5);
            values.put("comment1_rev", comment1_rev);
            values.put("comment2_rev", comment2_rev);
            values.put("comment3_rev", comment3_rev);
            values.put("comment4_rev", comment4_rev);
            values.put("comment5_rev", comment5_rev);
            Log.e("TTT", values.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        database.insert(TABLE_NAME, null, values);
        database.close();
    }
}
