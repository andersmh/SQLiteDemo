package com.example.sqlitedemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;
    ArrayList<Name> names;


    /**
     * Constructor for the adapter.
     *
     * @param ct
     */
    public MyAdapter(Context ct, ArrayList<Name> names) {
        context = ct;
        this.names = names;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.name_row, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Sets the individual elements in the list.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.myText.setText(names.get(position).getName());


        /**
         * When long clicking the element a dialog pops up, and you can delete the element.
         */
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("You longed clicked with: " + position);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Log.e("Answer", "Yes");
                                MainActivity.sqLiteHelper.deleteData(names.get(position).getId());
                                names.remove(position);
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Log.e("Answer", "No");
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(context);
                ab.setMessage("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });


    }

    /**
     * returns the size of the list.
     *
     * @return The size of the list
     */

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.person_name);
        }
    }
}
