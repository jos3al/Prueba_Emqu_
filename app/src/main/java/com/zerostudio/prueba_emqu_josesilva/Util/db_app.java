package com.zerostudio.prueba_emqu_josesilva.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class db_app extends SQLiteOpenHelper {

    public static String nombre_recetariodb ="prueba_js.db";

    public db_app(@Nullable Context context ) {
        super(context, nombre_recetariodb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Util.CREATE_USER);
            sqLiteDatabase.execSQL(Util.INSER_IMG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
