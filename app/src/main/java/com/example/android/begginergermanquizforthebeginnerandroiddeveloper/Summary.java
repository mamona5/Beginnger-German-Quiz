package com.example.android.begginergermanquizforthebeginnerandroiddeveloper;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Summary extends AppCompatActivity {
    String message;

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

    /** This method is called from onCreate
    *@Param userName input from MainActivity
     * @Param score from question_page
     * @param totalQ is the total number of question from question_page
 */

    public void displayMessage(String userName, int score, int totalQ) {
        TextView printMessage = findViewById(R.id.message);
        message = userName + ", you scored " + score + " out of " + totalQ + ".";
        printMessage.setText(message);
    }

    /* This method is called when the Send Email button is clicked
     */
    public void sendEmail(View view) {
        EditText getUserEmail = findViewById(R.id.Email);
        Editable userEmailEditable = getUserEmail.getText();
        String userEmail = userEmailEditable.toString();
        if (userEmail.trim().length() <= 0) {
            Toast.makeText(this, "You must enter an Email Address in order to send results.", Toast.LENGTH_SHORT).show();
        } else {

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
}




