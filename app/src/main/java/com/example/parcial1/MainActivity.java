package com.example.parcial1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Spinner spinner;
    private TextView resultText;
    private Button calculateButton;
    private Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);
        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);
        generateButton = findViewById(R.id.generateButton);

        String[] options = new String[]{"Promedio de los números", "Decir los pares e impares de la lista", "Ordenar los números"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] numberStrings = editText.getText().toString().split(",");
                List<Integer> numbers = Arrays.stream(numberStrings)
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                switch (spinner.getSelectedItem().toString()) {
                    case "Promedio de los números":
                        double average = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
                        resultText.setText(String.valueOf(average));
                        break;
                    case "Decir los pares e impares de la lista":
                        List<Integer> even = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
                        List<Integer> odd = numbers.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
                        resultText.setText("Pares: " + even.toString() + "\nImpares: " + odd.toString());
                        break;
                    case "Ordenar los números":
                        Collections.sort(numbers);
                        resultText.setText(numbers.toString());
                        break;
                }
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                String randomNumbers = random.ints(10, 0, 100)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(", "));
                editText.setText(randomNumbers);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuCodDeco) {
            Intent intent = new Intent(this, EncodeDecodeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
