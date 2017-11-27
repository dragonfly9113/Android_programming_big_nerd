package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t;
                if (checkAnswer(true))
                    t = Toast.makeText(QuizActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT);
                else
                    t = Toast.makeText(QuizActivity.this, R.string.incorrect_toast,Toast.LENGTH_SHORT);

                t.setGravity(Gravity.TOP, 0,300);
                t.show();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t;
                if (checkAnswer(true))
                    t = Toast.makeText(QuizActivity.this, R.string.incorrect_toast,Toast.LENGTH_SHORT);
                else
                    t = Toast.makeText(QuizActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT);

                t.setGravity(Gravity.TOP, 0,300);
                t.show();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                setQuestionTextView();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        setQuestionTextView();
    }

    private void setQuestionTextView() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private boolean checkAnswer(boolean trueButton) {
        if (trueButton == mQuestionBank[mCurrentIndex].isAnswerTrue())
            return true;
        else
            return false;
    }
}
