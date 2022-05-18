package com.example.shoponline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ProductClickListener {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<ProductModel> productModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleSearch);
        setData();
        prepareRecycleView();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home_bottom:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.favorite_bottom:
                            selectedFragment = new FavoriteFragment();
                            break;
                    }

                    if(item.getItemId() == R.id.basket_bottom) {
                        Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                        startActivity(intent);
                    }
                    else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    }
                    return true;
                }
            };

    public void setData() {
        productModelList.add(new ProductModel("Pizza", R.drawable.pizza, "Pret: 25 ron"));
        productModelList.add(new ProductModel("Paste", R.drawable.paste, "Pret: 18 ron"));
        productModelList.add(new ProductModel("Supa", R.drawable.soup, "Pret: 10 ron"));
        productModelList.add(new ProductModel("Sos", R.drawable.sos, "Pret: 2 ron"));
    }

    public void prepareRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        prepareAdapter();
    }

    public void prepareAdapter() {
        productAdapter = new ProductAdapter(productModelList, this, this::selectedProduct);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void selectedProduct(ProductModel productModel) {
//        Toast.makeText(this, "Selected product " + productModel.getProductName(), Toast.LENGTH_SHORT).show();
        if(productModel.getProductName() == "Pizza"){
            productModel.setReteta("Ingrediente: sos de rosii, mozarella, carnati, masline\nGramaj: 470g");
        }
        else if(productModel.getProductName() == "Paste") {
            productModel.setReteta("Ingrediente: Rigato, sunca, smantana, parmesan\nGramaj: 370g");
        }
        else if(productModel.getProductName() == "Soup") {
            productModel.setReteta("Ingrediente: carne de vita, morcovi, patrunjel, telina, ceapa, cartofi, fasole verde, suc de rosii\nGramaj: 300g");
        }
        else if(productModel.getProductName() == "Sos") {
            productModel.setReteta("Sos de Rosii\nGramaj: 20g");
        }
        startActivity(new Intent(this, SelectedProductActivity.class).putExtra("data", productModel));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.searchView) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searcStr = newText;
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}