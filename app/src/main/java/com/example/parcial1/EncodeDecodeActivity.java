package com.example.parcial1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class EncodeDecodeActivity extends AppCompatActivity {
    private EditText inputText;
    private Button encodeButton;
    private Button decodeButton;

    private static final Map<Character, Character> ENCODING_MAP = new HashMap<>();
    static {
        ENCODING_MAP.put('a', '@');
        ENCODING_MAP.put('e', '3');
        ENCODING_MAP.put('i', '1');
        ENCODING_MAP.put('o', '8');
        ENCODING_MAP.put('u', '5');
        ENCODING_MAP.put('m', '&');
        ENCODING_MAP.put('n', '(');
        ENCODING_MAP.put('p', ')');
        ENCODING_MAP.put('r', '#');
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode_decode);

        inputText = findViewById(R.id.inputText);
        encodeButton = findViewById(R.id.encodeButton);
        decodeButton = findViewById(R.id.decodeButton);

        encodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                StringBuilder encoded = new StringBuilder();
                for (char c : input.toCharArray()) {
                    if (ENCODING_MAP.containsKey(c)) {
                        encoded.append(ENCODING_MAP.get(c));
                    } else {
                        encoded.append(c);
                    }
                }
                inputText.setText(encoded.toString());
            }
        });

        decodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                StringBuilder decoded = new StringBuilder();
                for (char c : input.toCharArray()) {
                    char decodedChar = getKeyFromValue(ENCODING_MAP, c);
                    if (decodedChar != 0) {
                        decoded.append(decodedChar);
                    } else {
                        decoded.append(c);
                    }
                }
                inputText.setText(decoded.toString());
            }
        });
    }

    private static char getKeyFromValue(Map<Character, Character> map, char value) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuCalcular) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
