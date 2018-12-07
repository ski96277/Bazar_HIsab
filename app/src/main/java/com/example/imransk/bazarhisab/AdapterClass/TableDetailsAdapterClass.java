package com.example.imransk.bazarhisab.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imransk.bazarhisab.Activity.Update_Activity;
import com.example.imransk.bazarhisab.POJOClass.Info_POJO;
import com.example.imransk.bazarhisab.R;

import java.util.List;

public class TableDetailsAdapterClass extends RecyclerView.Adapter<TableDetailsAdapterClass.Viewclass> {


    Context context;
    List<Info_POJO> info_pojos;


    public TableDetailsAdapterClass(@NonNull Context context, List<Info_POJO> info_pojos) {
        this.context = context;
        this.info_pojos = info_pojos;
    }

    @NonNull
    @Override
    public Viewclass onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view_edit, null);

        return new Viewclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewclass viewclass, int i) {

        viewclass.serial.setText(info_pojos.get(i).getId());
        viewclass.item.setText(info_pojos.get(i).getItem());
        viewclass.price.setText(info_pojos.get(i).getPrice() + " টাকা");

    }

    @Override
    public int getItemCount() {
        return info_pojos.size();
    }

    public class Viewclass extends RecyclerView.ViewHolder{
        TextView item;
        TextView price;
        TextView serial;


        public Viewclass(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.date_TV_ID);
            price = itemView.findViewById(R.id.total_cost_TV_ID);
            serial = itemView.findViewById(R.id.serial_TV);
    }
}
}
