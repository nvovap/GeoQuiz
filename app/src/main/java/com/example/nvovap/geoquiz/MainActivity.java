package com.example.nvovap.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    private  Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_city, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_american, true),
    };

    private int mCurrentIndex = 0;


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = R.string.incorrect_toast;

                if (mQuestionBank[mCurrentIndex].isAnswerTrue() == true) {
                    answer = R.string.correct_toast;
                }

                Toast toast = Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        mFalseButton = (Button) findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = R.string.incorrect_toast;

                if (mQuestionBank[mCurrentIndex].isAnswerTrue() == false) {
                    answer =  R.string.correct_toast;
                }

                Toast toast = Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();
            }
        });

        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mCurrentIndex % mQuestionBank.length;

                mCurrentIndex--;

                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length-1;
                };

                updateQuestion();
            }
        });



    }
}
