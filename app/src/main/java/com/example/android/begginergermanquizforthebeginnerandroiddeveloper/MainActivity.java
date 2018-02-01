package com.example.android.begginergermanquizforthebeginnerandroiddeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickedStart(View view) {

        EditText getUserName = findViewById(R.id.user_name);
        Editable userNameEditable = getUserName.getText();
        String userName = userNameEditable.toString();
        /*if (userName.trim().length() <= 0) {
            Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
        } else {
            */
        Intent launchQuiz = new Intent(this, question_page.class);
           launchQuiz.putExtra("Name", userName);
            startActivity(launchQuiz);

    }
}
