package com.example.android.begginergermanquizforthebeginnerandroiddeveloper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class question_page extends AppCompatActivity {

    /* Change value of TotalQ when number of questions changes*/
    int totalQ = 5;
    int score = 0;
    int questionNumber = 1;
    String correctAnswer;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        updateValues();
        Intent launchQuiz = getIntent();
        userName = launchQuiz.getStringExtra("Name");
    }

    /*Restore values if system destroys the activity due to system constraints (rather than normal app behavior) ie screen orientation
    *
    * */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt("currentScore");
        questionNumber = savedInstanceState.getInt("currentQuestionNumber");
        userName = savedInstanceState.getString("userName");
        if (questionNumber >= totalQ) {
            questionNumber = 5;
        }
        updateValues();
    }

    /*Save  values if system destroys the activity due to system constraints (rather than normal app behavior) ie screen orientation.
*
* */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentScore", score);
        savedInstanceState.putInt("currentQuestionNumber", questionNumber);
        savedInstanceState.putString("userName", userName);
        super.onSaveInstanceState(savedInstanceState);
    }

    /*Updates questions text on screen based on questionNumber. It is being called from methods onCreate and checkAnswers.
*
* */
    public void updateValues() {

        TextView question = (TextView) findViewById(R.id.placeholder_question);
        TextView optionA = (TextView) findViewById(R.id.placeholder_a);
        TextView optionB = (TextView) findViewById(R.id.placeholder_b);
        TextView optionC = (TextView) findViewById(R.id.placeholder_c);

        String mQuestion = "q" + questionNumber + "_question";
        String mOptionA = "q" + questionNumber + "_a";
        String mOptionB = "q" + questionNumber + "_b";
        String mOptionC = "q" + questionNumber + "_c";
        String mCorrectAnswer = "q" + questionNumber + "_correct_answer";

        int resIDQuestion = getResources().getIdentifier(mQuestion, "string", getPackageName());
        int resIDOptionA = getResources().getIdentifier(mOptionA, "string", getPackageName());
        int resIDOptionB = getResources().getIdentifier(mOptionB, "string", getPackageName());
        int resIDOptionC = getResources().getIdentifier(mOptionC, "string", getPackageName());
        int resIDCorrectAnswer = getResources().getIdentifier(mCorrectAnswer, "string", getPackageName());

        RadioGroup mc = findViewById(R.id.mc);
        mc.clearCheck();

        optionA.setText(getText(resIDOptionA).toString());
        optionB.setText(getText(resIDOptionB).toString());
        optionC.setText(getText(resIDOptionC).toString());
        question.setText(getText(resIDQuestion).toString());

        correctAnswer = getText(resIDCorrectAnswer).toString();
    }

    /*This method is called when the next button on quiz page is clicked
*
* */
    public void checkAnswers(View view) {
        if (questionNumber <= totalQ) {
            if (correctAnswer.equals("a")) {
                Boolean clickedA = ((RadioButton) findViewById(R.id.placeholder_a)).isChecked();
                if (clickedA) {
                    score += 1;
                }
            }
            if (correctAnswer.equals("b")) {
                Boolean clickedB = ((RadioButton) findViewById(R.id.placeholder_b)).isChecked();
                if (clickedB) {
                    score += 1;
                }
            }
            if (correctAnswer.equals("c")) {
                Boolean clickedC = ((RadioButton) findViewById(R.id.placeholder_c)).isChecked();
                if (clickedC) {
                    score += 1;
                }
            }
            if (questionNumber == (totalQ - 1)) {
                Button clickFinish = (findViewById(R.id.next));
                clickFinish.setText(R.string.finished);
            }
            questionNumber += 1;
            if (questionNumber <= totalQ) {
                updateValues();
            } else {
                Toast.makeText(this, getString(R.string.quiz_result, userName, score, totalQ), Toast.LENGTH_SHORT).show();

                Intent launchSummary = new Intent(this, Summary.class);
                launchSummary.putExtra("Total Question", totalQ);
                launchSummary.putExtra("Name", userName);
                launchSummary.putExtra("Score", score);
                startActivity(launchSummary);
            }
        }
    }
}




