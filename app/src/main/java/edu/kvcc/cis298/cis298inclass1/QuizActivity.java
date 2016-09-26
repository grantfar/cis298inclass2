package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String TAG = "QuizActivity";
    private Button mNextButton;
    private Button mSubmitButton;
    private RadioButton mChoice1;
    private RadioButton mChoice2;
    private RadioButton mChoice3;
    private RadioButton mChoice4;
    private RadioGroup mGroupAnswers;
    private TextView mQuestionTextView;
    private int mCurrentQuestion;
    private Question mTheQuestion;
    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_multible_choice_1, 3,
                    new int[]{R.string.question1_answer1,R.string.question1_answer2,
                            R.string.question1_answer3, R.string.question1_answer4}),
            new Question(R.string.question_multible_choice_2, 2,
                    new int[]{R.string.question2_answer1,R.string.question2_answer2,
                            R.string.question2_answer3, R.string.question2_answer4})
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"saveState");
        outState.putInt(KEY_INDEX,mCurrentQuestion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate(Bundle) called");
        mCurrentQuestion = -1;
        mQuestionTextView = (TextView) findViewById(R.id.question_Text_View);
        mChoice1 = (RadioButton) findViewById(R.id.answer1);
        mChoice2 = (RadioButton) findViewById(R.id.answer2);
        mChoice3 = (RadioButton) findViewById(R.id.answer3);
        mChoice4 = (RadioButton) findViewById(R.id.answer4);
        mGroupAnswers = (RadioGroup) findViewById(R.id.groupAnswers);
        if(savedInstanceState != null)
            mCurrentQuestion=savedInstanceState.getInt(KEY_INDEX)-1;
        updateQuestion();
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestion();
            }
        });
    }


    private void updateQuestion() {
        mGroupAnswers.clearCheck();
        mCurrentQuestion = (mCurrentQuestion+1)% mQuestions.length;
        mTheQuestion = mQuestions[mCurrentQuestion];
        mQuestionTextView.setText(mTheQuestion.getTheQuestion());
        int[] theAnswers = mTheQuestion.getAnswers();
        mChoice1.setText(theAnswers[0]);
        mChoice2.setText(theAnswers[1]);
        mChoice3.setText(theAnswers[2]);
        mChoice4.setText(theAnswers[3]);
    }
    private void checkQuestion() {
        if(mChoice1.isChecked())
        {
           Toast.makeText(QuizActivity.this,mTheQuestion.testAnswer(1),Toast.LENGTH_SHORT).show();
        }
        else if(mChoice2.isChecked())
        {
            Toast.makeText(QuizActivity.this,mTheQuestion.testAnswer(2),Toast.LENGTH_SHORT).show();
        }
        else if(mChoice3.isChecked())
        {
            Toast.makeText(QuizActivity.this,mTheQuestion.testAnswer(3),Toast.LENGTH_SHORT).show();
        }
        else if(mChoice4.isChecked())
        {
            Toast.makeText(QuizActivity.this,mTheQuestion.testAnswer(4),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
