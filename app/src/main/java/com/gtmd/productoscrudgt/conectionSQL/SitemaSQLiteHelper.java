package com.gtmd.productoscrudgt.conectionSQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SitemaSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NOMBRE = "bd_product";
    private static final int VERSION = 1;
    public static final String TABLE_PRODUCT = "t_product";

    public SitemaSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PRODUCT + "(" +
                "id_product INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo TEXT," +
                "nombre TEXT ," +
                "precio NUMBER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_PRODUCT);
        onCreate(db);
    }
}
