package com.example.cookingsuko;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText ingredientInput, intoleranceInput;
    private Button searchButton;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientInput = findViewById(R.id.ingredientInput);
        intoleranceInput = findViewById(R.id.intoleranceInput);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredients = ingredientInput.getText().toString();
                String intolerances = intoleranceInput.getText().toString();
                if (!ingredients.isEmpty()) {
                    fetchRecipes(ingredients, intolerances);
                } else {
                    Toast.makeText(MainActivity.this, "재료를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchRecipes(String ingredients, String intolerances) {
        RecipeApiService apiService = ApiClient.getRetrofitInstance().create(RecipeApiService.class);
        Call<List<Recipe>> call = apiService.getRecipes(ingredients, 5, intolerances);

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipeAdapter.setRecipes(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "레시피를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "오류가 발생했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
