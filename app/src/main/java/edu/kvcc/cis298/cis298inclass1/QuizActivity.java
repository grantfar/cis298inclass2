package edu.kvcc.cis298.cis298inclass1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private  static  final  int REQUEST_CHEAT_CODE = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentQuestion;
    private boolean mCheatShown;
    private Question mTheQuestion;
    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question( R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mCurrentQuestion=-1;
        mQuestionTextView = (TextView) findViewById(R.id.question_Text_View);
        updateQuestion();
        mCheatButton = (Button) findViewById(R.id.cheatButton);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CheatActivity.newIntent(QuizActivity.this,mTheQuestion.isCorrect());
                startActivityForResult(i,REQUEST_CHEAT_CODE);
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toaster(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toaster(false);
            }
        });
    }

    private void updateQuestion() {
        mCurrentQuestion = (mCurrentQuestion+1)% mQuestions.length;
        mTheQuestion = mQuestions[mCurrentQuestion];
        mQuestionTextView.setText(mTheQuestion.getTheQuestion());
        mCheatShown = false;
    }
    private void toaster(boolean theirAnswer)
    {

        if(mCheatShown)
        {
            Toast.makeText(QuizActivity.this,
                    R.string.cheat_toast,
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(QuizActivity.this,
                    mTheQuestion.testAnswer(theirAnswer),
                    Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_CHEAT_CODE)
        {
            if (data == null)
                return;
            mCheatShown = CheatActivity.wasAnswerShown(data);
        }
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
