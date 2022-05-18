package com.example.shoponline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BasketActivity extends AppCompatActivity {

    EditText editText;
    CheckBox checkBox;
    Button button;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        editText = findViewById(R.id.specification);
        checkBox = findViewById(R.id.checkBox);
        button = findViewById(R.id.bottom_specification);

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String specifications = editText.getText().toString();
                boolean checked = checkBox.isChecked();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SPECIFICATII", specifications);
                editor.putBoolean("CHECKBOX", checked);
                editor.apply();

                Toast.makeText(BasketActivity.this, "Informatii salvate", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BasketActivity.this, RestaurantActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textView = findViewById(R.id.observatii_restaurant);
        String observatii = sharedPreferences.getString("OBSERVATII", "");
        textView.setText(observatii);
    }
}