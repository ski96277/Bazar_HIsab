package com.example.imransk.bazarhisab.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imransk.bazarhisab.Databse.DataOperation;
import com.example.imransk.bazarhisab.POJOClass.Info_POJO;
import com.example.imransk.bazarhisab.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView textView2;
    private TextView textView3;
    private EditText itemnameET;
    private TextView textView4;
    private EditText porimanET;
    private Spinner spinnerID;
    private TextView textView5;
    private EditText priceET;
    private TextView textView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        String[] list = {"কেজি",
                "গ্রাম"
                , "টি"};

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, list);

//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerID.setAdapter(spinnerArrayAdapter);
    }

    private void initialize() {

        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.priceET = (EditText) findViewById(R.id.price_ET);
        this.textView5 = (TextView) findViewById(R.id.textView5);
        this.spinnerID = (Spinner) findViewById(R.id.spinner_ID);
        this.porimanET = (EditText) findViewById(R.id.poriman_ET);
        this.textView4 = (TextView) findViewById(R.id.textView4);
        this.itemnameET = (EditText) findViewById(R.id.item_name_ET);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.textView2 = (TextView) findViewById(R.id.textView2);


    }

    public void editButton(View view) {
        startActivity(new Intent(this, EditListActivity.class));
    }

    public void save_all(View view) {

        String item = itemnameET.getText().toString();
        String quantity = porimanET.getText().toString();
        String price = priceET.getText().toString();
        String spinner = spinnerID.getSelectedItem().toString();

        if (item.isEmpty()) {
            itemnameET.requestFocus();
            itemnameET.setError("Enter item");
            return;
        }
        if (quantity.isEmpty()) {
            porimanET.setError("Enter quantity");
            porimanET.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            priceET.setError("Enter Price");
            priceET.requestFocus();
            return;
        }
        Info_POJO info_pojo = new Info_POJO(item, quantity, price, spinner);

        DataOperation dataOperation = new DataOperation(this);

        boolean status = dataOperation.insertDB(info_pojo);
        if (status) {
            itemnameET.setText("");
            porimanET.setText("");
            priceET.setText("");
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.english_ID:
                Toast.makeText(this, "english", Toast.LENGTH_SHORT).show();
                break;
            default:

                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void see_All_List(View view) {

        startActivity(new Intent(this, All_TableActivity.class));
    }


}
