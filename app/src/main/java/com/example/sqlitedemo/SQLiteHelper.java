package com.example.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Used to execute a query
     * @param sql
     */
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    /**
     * Used to insert data to the database
     * @param id
     * @param string
     */
    public void insertData(String id, String string) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO NAMES VALUES (?, ?)";


        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, id);
        statement.bindString(2, string);


        statement.executeInsert();
    }

    /**
     * Used to delete data on the database
     * @param id
     */
    public void deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM NAMES WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, id);

        statement.execute();
        database.close();
    }

    /**
     * Fetches all the data on the database
     * @param sql
     * @return
     */
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
