package com.example.shoponline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedProductActivity extends AppCompatActivity {

    TextView textView;
    ProductModel productModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);

        textView = findViewById(R.id.tv_reteta);
        intent = getIntent();

        if(intent != null) {
            productModel = (ProductModel) intent.getSerializableExtra("data");
            String reteta = productModel.getReteta();
            textView.setText(reteta);
        }
    }
}