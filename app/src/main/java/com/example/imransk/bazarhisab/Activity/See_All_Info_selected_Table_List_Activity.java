package com.example.imransk.bazarhisab.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.imransk.bazarhisab.AdapterClass.AdapterClass;
import com.example.imransk.bazarhisab.AdapterClass.TableDetailsAdapterClass;
import com.example.imransk.bazarhisab.Databse.DataOperation;
import com.example.imransk.bazarhisab.POJOClass.Info_POJO;
import com.example.imransk.bazarhisab.R;

import java.util.List;

public class See_All_Info_selected_Table_List_Activity extends AppCompatActivity {

    List<Info_POJO> info_pojos;
    RecyclerView recyclerView;
    DataOperation dataOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__all__list);
        recyclerView = findViewById(R.id.recyclerView_ID);


        Intent intent = getIntent();
        String table_Name = intent.getStringExtra("table_name");

        dataOperation = new DataOperation(this);
        info_pojos = dataOperation.get_Table_Info("[" + table_Name + "]");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        TableDetailsAdapterClass adapterClass = new TableDetailsAdapterClass(this, info_pojos);
        recyclerView.setAdapter(adapterClass);

        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
