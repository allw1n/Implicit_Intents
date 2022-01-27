package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editURL, editLocation, editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editURL = findViewById(R.id.editURL);
        editLocation = findViewById(R.id.editLocation);
        editText = findViewById(R.id.editText);
        Button buttonURL = findViewById(R.id.buttonURL);
        Button buttonLocation = findViewById(R.id.buttonLocation);
        Button buttonText = findViewById(R.id.buttonText);

        buttonURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editURL.getText().toString();
                if (!url.toUpperCase().contains("HTTPS")) {
                    url = "https://" + url;
                }
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editLocation.getText().toString();
                Uri uriLocation = Uri.parse("geo:0,0?q=" + location);
                Intent intent = new Intent(Intent.ACTION_VIEW, uriLocation);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                String mimeType = "text/plain";
                new ShareCompat.IntentBuilder(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Share text with: ")
                        .setText(text)
                        .startChooser();
            }
        });
    }
}