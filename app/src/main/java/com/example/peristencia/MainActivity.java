package com.example.peristencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editSurname;
    EditText editPhone;
    EditText editEmail;
    Button saveButton;
    Context currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        saveButton = findViewById(R.id.button);
        currentContext = this;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File localFiles = currentContext.getFilesDir();
                try {
                    File writeFile = new File(localFiles.getPath() + "/data");
                    FileOutputStream data = currentContext.openFileOutput("data", MODE_APPEND);
                    String saveData = editName.getText().toString() + ";" +
                            editSurname.getText().toString() + ";" +
                            editPhone.getText().toString() + ";" +
                            editEmail.getText().toString() + "\n" ;
                    data.write(saveData.getBytes(StandardCharsets.UTF_8));
                    data.close();
                    System.out.println("i should toast!");
                    Toast.makeText(currentContext, "Changes Saved!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Toast.makeText(currentContext, "An error ocurred :C", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }

            }
        });
    }
}