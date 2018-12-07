package com.example.imransk.bazarhisab.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imransk.bazarhisab.AdapterClass.AdapterClass;
import com.example.imransk.bazarhisab.Databse.DataOperation;
import com.example.imransk.bazarhisab.Databse.DatabaseHelperClass;
import com.example.imransk.bazarhisab.POJOClass.Info_POJO;
import com.example.imransk.bazarhisab.R;

import java.util.ArrayList;
import java.util.List;

public class EditListActivity extends AppCompatActivity {

    private  RecyclerView editrecyler;
    List<Info_POJO> info_pojos = new ArrayList<>();
    DataOperation dataOperation;
    TextView totalCostTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        this.editrecyler = (RecyclerView) findViewById(R.id.edit_recyler);
        totalCostTV=findViewById(R.id.totalCost_TV);

        dataOperation = new DataOperation(this);
        info_pojos = dataOperation.get_ALL_Info_Table();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        editrecyler.setLayoutManager(linearLayoutManager);

        final AdapterClass adapterClass = new AdapterClass(this, info_pojos);
        editrecyler.setAdapter(adapterClass);

        //set Toat price
        totalCostTV.setText("Total ="+dataOperation.TotalPrice(DatabaseHelperClass.table_name));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();

                final AlertDialog.Builder builder=new AlertDialog.Builder(EditListActivity.this);
                builder.setTitle("Delete ?")
                        .setMessage("Do you want to delete this ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String id = info_pojos.get(position).getId();
                                boolean status = dataOperation.deleteUser(id);

                                if (status) {
                                    adapterClass.setInfo_pojos(dataOperation.get_ALL_Info_Table());
                                    Toast.makeText(EditListActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(EditListActivity.this, "No delete", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                builder.setCancelable(true);
                                adapterClass.setInfo_pojos(dataOperation.get_ALL_Info_Table());
                            }
                        })
                        .setCancelable(true)
                        .create().show();


            }
        }).attachToRecyclerView(editrecyler);

    }
}