package com.example.iqbarsurfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class quiz extends AppCompatActivity {
    private List<Question> questionList;
    private int currentQuestionIndex;
    private int score;
    Button  scorebtn;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        // Prepare the questions
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the name of the president in Sri lanka", new String[]{"Me", "You", "Your Mama", "Your Dadda"}, 0));
        questionList.add(new Question("What is wrong with you", new String[]{"NO one knows", "I obviously dont know", "God Might know", "asdkjalsdk"}, 1));
        // Add more questions here...

        currentQuestionIndex = 0;
        score = 0;

        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        questionTextView.setText(currentQuestion.getQuestionText());

        optionsRadioGroup.removeAllViews();
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentQuestion.getOptions()[i]);
            optionsRadioGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        int selectedAnswerIndex = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));

        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questionList.size()) {
            displayQuestion();
        } else {
            // Quiz completed, display the final score
            Toast.makeText(this, "Quiz completed. Score: " + score, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(quiz.this, scoreDisplay.class);
            intent.putExtra("score", score);
            startActivity(intent);

//            scorebtn.setVisibility(View.VISIBLE);
//
//            scorebtn.setOnClickListener(v -> {
//                Intent intent = new Intent(getApplicationContext(), scoreDisplay.class);
//                startActivity(intent);
//                finish();
//            });

        }
    }
}
