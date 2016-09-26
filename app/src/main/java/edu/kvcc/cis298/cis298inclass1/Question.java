package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by gfarnsworth6886 on 9/19/2016.
 */
public class Question {
    private int mCorrectAnswer;
    private int mTheQuestion;
    private int[] mAnswers;

    public Question(int theQuestion, int correctAnswer,int[] answers)
    {
        mTheQuestion = theQuestion;
        mCorrectAnswer = correctAnswer;
        mAnswers = answers;
    }

    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public int[] getAnswers() {
        return mAnswers;
    }

    public void setAnswers(int[] answers) {
        mAnswers = answers;
    }

    public int getTheQuestion() {
        return mTheQuestion;
    }

    public void setTheQuestion(int theQuestion) {
        mTheQuestion = theQuestion;
    }

    public int testAnswer(int theirAnswer)
    {
        if(theirAnswer==mCorrectAnswer)
            return R.string.correct_toast;
        else
            return R.string.incorrect_toast;
    }
}
