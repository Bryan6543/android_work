package com.example.iqbarsurfer;

import android.content.Intent;
import android.os.Bundle;
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
//    Button  scorebtn;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        Button submitButton = findViewById(R.id.submitButton);

        // Prepare the questions
        questionList = new ArrayList<>();
        questionList.add(new Question("What number comes next in the sequence: 1, 4, 9, 16, 25, ...", new String[]{"28", "32", "38", "36"}, 3));
        questionList.add(new Question("If all Bloops are Razzies and all Razzies are Lazzies, then which of the following statements must be true?", new String[]{"All Lazzies are Bloops", "All Bloops are Lazzies", "All Razzies are Bloops", "All Lazzies are Razzies"}, 3));
        questionList.add(new Question("Which of the following words is the odd one out?", new String[]{"Potato", "Tomato", "Carrot", "Apple"}, 3));
        questionList.add(new Question("A plane crashes on the border of the United States and Canada. Where do they bury the survivors?", new String[]{"United States", "Canada", "Nowhere, you don't bury survivors", "It depends on the nationality of the survivors"}, 2));
        questionList.add(new Question("If you rearrange the letters \"NOON\", you would get the name of a:", new String[]{"Animal", "City", "Time of day", "Flower"}, 2));
        questionList.add(new Question("If a cat is worth 10, a dog is worth 15, and a bird is worth 5, what is the value of a horse?", new String[]{"15", "20", "25", "30"}, 2));
        questionList.add(new Question("How many times can you subtract 10 from 100?", new String[]{"5", "9", "Once", "Infinitely"}, 0));
        questionList.add(new Question("If a quarter and a dime were in your pocket and you took out a nickel, how much money would you have?", new String[]{"25 cents", "30 cents", "40 cents", "60 cents"}, 3));
        questionList.add(new Question("Which one of the four is least like the other four?", new String[]{"Apple", "Banana", "Potato", "Grapes"}, 2));
        questionList.add(new Question("If you rearrange the letters \"EVICTION,\" you would get the name of a:", new String[]{"Country", "Animal", "Profession", "Food"}, 2));
        // Add more questions here...

        currentQuestionIndex = 0;
        score = 0;

        displayQuestion();

        submitButton.setOnClickListener(v -> checkAnswer());
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
