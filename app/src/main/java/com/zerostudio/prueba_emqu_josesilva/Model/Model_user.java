package com.zerostudio.prueba_emqu_josesilva.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerostudio.prueba_emqu_josesilva.Util.Util;
import com.zerostudio.prueba_emqu_josesilva.Util.db_app;

public class Model_user {

    public static void escribir_image(Context context, String image){
        db_app db=new db_app(context);
        SQLiteDatabase sqlite=db.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.COLUMNA_image,image);
        sqlite.insert(Util.TABLE_USER,null,contentValues);
        db.close();
        sqlite.close();
    }

    public static String leerimagen(Context context){
        String imagen="";
        db_app db=new db_app(context);
        SQLiteDatabase sqlite=db.getReadableDatabase();
        Cursor c=sqlite.rawQuery("select "+Util.COLUMNA_image+" from "+Util.TABLE_USER,null);
        c.moveToFirst();
        imagen=c.getString(c.getColumnIndexOrThrow(Util.COLUMNA_image));
        c.close();
        db.close();
        sqlite.close();
        return imagen;
    }

    public static void actualizar_imagen(Context context,String image){
        db_app db=new db_app(context);
        SQLiteDatabase sqlite=db.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.COLUMNA_image,image);
        sqlite.update(Util.TABLE_USER,values,Util.COLUMNA_id+"="+1,null);
        db.close();
        sqlite.close();
    }

}
