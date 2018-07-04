package bit.hawwag2.languagetrainer;

import android.graphics.drawable.Drawable;

/**
 * Created by hawwag2 on 15/03/2017.
 */

public class Question {
    private String article;
    private String noun;
    private Drawable image;
    private Boolean correctAnswer;



    public Question(String article, String noun, Drawable image) {
        this.article=article;
        this.noun = noun;
        this.image = image;




    }


    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    private String userAnswer;



}

