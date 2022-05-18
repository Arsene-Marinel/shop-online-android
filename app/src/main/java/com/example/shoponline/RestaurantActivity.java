package com.example.shoponline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        textView = findViewById(R.id.specificatie_comanda);
        editText = findViewById(R.id.observatii);
        button = findViewById(R.id.bottom_observatii);

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        String specifications = sharedPreferences.getString("SPECIFICATII", "");
        textView.setText(specifications);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String observatii = editText.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("OBSERVATII", observatii);
                editor.apply();

                Toast.makeText(RestaurantActivity.this, "Informatii salvate", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RestaurantActivity.this, BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}