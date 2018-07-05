package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText price;
    private Button addBtn;
    private int n;

    private static final String TAG = "AddItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        setTitle(R.string.add_item_title);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        addBtn=findViewById(R.id.add_btn);



        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String itemPrice=price.getText().toString();
                if (!TextUtils.isEmpty(itemPrice)) n=1;

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if ((!TextUtils.isEmpty(editable))&(n==1)) addBtn.setEnabled(true);
            }
        });




//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String itemName=name.getText().toString();
//                String itemPrice=price.getText().toString();
//            }
//        });
    }

}
