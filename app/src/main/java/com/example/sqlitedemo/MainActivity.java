package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textInput;
    Button addButton;
    public static SQLiteHelper sqLiteHelper;
    ArrayList<Name> names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        /**
         * Link the edit text field and the button
         */
        textInput = findViewById(R.id.textInput);
        addButton = findViewById(R.id.addButton);


        /**
         * Creates SQLite database and creates the database if it is not created
         */
        sqLiteHelper = new SQLiteHelper(this, "NamesDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS NAMES(id VARCHAR PRIMARY KEY, name VARCHAR)");


        /**
         * Get all the data from SQLite and puts them in a cursor
         */
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM NAMES");


        /**
         * Fetches the saved questions using SQLite
         */
        while (cursor.moveToNext()) {
            String sqlId = cursor.getString(0);
            String sqlName = cursor.getString(1);

            names.add(new Name(sqlId, sqlName));

        }
    }
}
