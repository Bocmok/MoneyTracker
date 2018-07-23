package com.loftschool.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText price;
    private Button addBtn;

    public static final String TYPE_KEY="type";

    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        setTitle(R.string.add_item_title);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.add_item_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        addBtn=findViewById(R.id.add_btn);

        type=getIntent().getStringExtra(TYPE_KEY);

        EditText[] edList = {name, price};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, addBtn);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName=name.getText().toString();
                String itemPrice=price.getText().toString();

                Item item=new Item(itemName,itemPrice,type);

                Intent intent=new Intent();
                intent.putExtra("item",item);
//                intent.putExtra("name", itemName);
//                intent.putExtra("price",itemPrice);

                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }


}





