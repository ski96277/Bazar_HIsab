package com.example.imransk.bazarhisab.Activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.imransk.bazarhisab.AdapterClass.AllTableAdapterClass;
import com.example.imransk.bazarhisab.Databse.DataOperation;
import com.example.imransk.bazarhisab.R;

import java.util.ArrayList;
import java.util.List;

public class All_TableActivity extends AppCompatActivity {

    List<String> alltable;
    RecyclerView recyclerView;
    DataOperation dataOperation;
    AllTableAdapterClass  allTableAdapterClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell__table);

        recyclerView=findViewById(R.id.recyclerView_ID);

         dataOperation = new DataOperation(this);
        alltable = dataOperation.getAllTable();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

          allTableAdapterClass = new AllTableAdapterClass(this, alltable);
        recyclerView.setAdapter(allTableAdapterClass);

//action on recycler item

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();

                final AlertDialog.Builder builder = new AlertDialog.Builder(All_TableActivity.this);
                builder.setTitle("Delete ?")
                        .setMessage("Do you want to delete this ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dataOperation.table_Delete("["+alltable.get(position)+"]");

                                    allTableAdapterClass =new AllTableAdapterClass(All_TableActivity.this,dataOperation.getAllTable());
                                    recyclerView.setAdapter(allTableAdapterClass);

                                    Toast.makeText(All_TableActivity.this, "deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                allTableAdapterClass =new AllTableAdapterClass(All_TableActivity.this,dataOperation.getAllTable());
                                recyclerView.setAdapter(allTableAdapterClass);
                                builder.setCancelable(true);

                            }
                        })
                        .setCancelable(true)
                        .create().show();


            }
        }).attachToRecyclerView(recyclerView);

    }
}
