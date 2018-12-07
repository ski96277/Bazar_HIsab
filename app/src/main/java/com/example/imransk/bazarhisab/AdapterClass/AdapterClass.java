package com.example.imransk.bazarhisab.AdapterClass;

import android.app.Activity;
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

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Viewclass> {


    Context context;
    List<Info_POJO> info_pojos;


    public AdapterClass(@NonNull Context context, List<Info_POJO> info_pojos) {
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

            //Update today list
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();


    context.startActivity(new Intent(context, Update_Activity.class)
                    .putExtra("id", info_pojos.get(position).getId())
                            .putExtra("item",info_pojos.get(position).getItem())
                            .putExtra("quantity",info_pojos.get(position).getQuentity())
                            .putExtra("price",info_pojos.get(position).getPrice())
                            .putExtra("spinner",info_pojos.get(position).getSpinner()));
                }
            });

        }


    }
//set new array list after delete a row
    public void setInfo_pojos(List<Info_POJO> info_pojos) {
        this.info_pojos = info_pojos;
        notifyDataSetChanged();
    }


}
