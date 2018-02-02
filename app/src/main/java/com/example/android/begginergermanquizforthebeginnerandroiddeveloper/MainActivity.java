package com.example.android.begginergermanquizforthebeginnerandroiddeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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
        String germanReminderMessage = checkGermanUserInput();
        if (userName.trim().length() <= 0) {
            Toast.makeText(MainActivity.this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
        } else if (germanReminderMessage != null) {
            Toast.makeText(this, germanReminderMessage, Toast.LENGTH_SHORT).show();
        } else {
            Intent launchQuiz = new Intent(this, question_page.class);
            launchQuiz.putExtra("Name", userName);
            startActivity(launchQuiz);
        }
    }

    public String checkGermanUserInput() {
        String germanReminder;
        CheckBox checkedBooks = findViewById(R.id.books);
        boolean books = checkedBooks.isChecked();
        CheckBox checkedFaceToFace = findViewById(R.id.face_to_face);
        boolean faceToFace = checkedFaceToFace.isChecked();
        CheckBox checkedOnline = findViewById(R.id.online);
        boolean online = checkedOnline.isChecked();
        CheckBox checkedOthers = findViewById(R.id.others);
        boolean others = checkedOthers.isChecked();
        if (books || faceToFace || online || others) {
            germanReminder = null;
        } else {
            germanReminder = getString(R.string.germanReminder);
        }
        return germanReminder;
    }
}