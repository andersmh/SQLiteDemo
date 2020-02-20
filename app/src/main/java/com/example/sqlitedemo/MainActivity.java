package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;
    ArrayList<Name> names = new ArrayList<>();
    RecyclerView recyclerView;
    EditText textInput;
    Button addButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        /**
         * Link the edit text field and the button
         */
        recyclerView = findViewById(R.id.recyclerview);
        textInput = findViewById(R.id.textInput);
        addButton = findViewById(R.id.addButton);

        /**
         * Adapter shit
         */
        MyAdapter myAdapter = new MyAdapter(this, names);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**
         * Creates SQLite database and creates the database if it is not created
         */
        sqLiteHelper = new SQLiteHelper(this, "NamesDB.sqlite", null, 1);


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


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textInput.getText().toString().equals("")){
                    String id = UUID.randomUUID().toString();
                    /**
                     * Adds name to list
                     */
                    names.add(new Name(id, textInput.getText().toString()));
                    /**
                     * Adds name to database
                     */
                    sqLiteHelper.insertData(
                            id,
                            textInput.getText().toString()
                    );
                    recreate();
                    textInput.setText("");

                }
            }
        });
    }
}
