package com.example.android.begginergermanquizforthebeginnerandroiddeveloper;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Summary extends AppCompatActivity {
    String message;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent launchSummary = getIntent();
        String userName = launchSummary.getStringExtra("Name");
        int score = launchSummary.getIntExtra("Score", 0);
        int totalQ = launchSummary.getIntExtra("Total Question", 0);
        setContentView(R.layout.activity_summary);
        displayMessage(userName, score, totalQ);
    }

    /**
     * This method is called from onCreate
     *
     * @param userName input from MainActivity
     * @param score    from question_page
     * @param totalQ   is the total number of question from question_page
     */
    public void displayMessage(String userName, int score, int totalQ) {
                     TextView printMessage = findViewById(R.id.message);
        Log.v(TAG, "LALALALLALALALA");
                     message = getString(R.string.thank_you_message, userName, score, totalQ);
                     printMessage.setText(message);
                     }


    /* This method is called when the Send Email button is clicked
     */
    public void sendEmail(View view) {
        EditText getUserEmail = findViewById(R.id.Email);
        Editable userEmailEditable = getUserEmail.getText();
        String userEmail = userEmailEditable.toString();

        String germanReminderMessage = checkGermanUserInput();

        if (germanReminderMessage != null) {
            Toast.makeText(this, germanReminderMessage + getString(R.string.no_email), Toast.LENGTH_SHORT).show();
        }else if (userEmail.trim().length() <= 0) {
            Toast.makeText(this, getString(R.string.no_email), Toast.LENGTH_SHORT).show();
        }  else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, userEmail);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Beginner German Quiz");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
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






