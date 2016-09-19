package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by gfarnsworth6886 on 9/19/2016.
 */
public class Question {
    private boolean mIsCorrect;
    private int mTheQuestion;

    public Question(int theQuestion, boolean isCorrect)
    {
        mTheQuestion = theQuestion;
        mIsCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return mIsCorrect;
    }

    public void setCorrect(boolean correct) {
        mIsCorrect = correct;
    }

    public int getTheQuestion() {
        return mTheQuestion;
    }

    public void setTheQuestion(int theQuestion) {
        mTheQuestion = theQuestion;
    }

    public int testAnswer(boolean theirAnswer)
    {
        if(theirAnswer==mIsCorrect)
            return R.string.correct_toast;
        else
            return R.string.incorrect_toast;
    }
}
