package bit.hawwag2.languagetrainer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    //Declare a list of questions
    ArrayList<Question> questionlist;


    //Declare global parameters and Attributes
    Resources resources;
    RadioButton rdoButtonDer;
    RadioButton rdoButtonDie;
    RadioButton rdoButtonDas;
    RadioGroup answerGroup;

    int currentQuizSnapShot;
    int totalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_acitivity);

        //instantiate all the attributes
        questionlist = new ArrayList<>();
        totalScore=0;
        currentQuizSnapShot=0;



        setQuestions();
        addListenerOnButton();
        setupQuestion();


    }

    /*public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        Button btnNextQuestion = (Button) findViewById(R.id.btnNext);
        switch(view.getId()) {
            case R.id.radioButtonDie:
                if (checked)
                break;
            case R.id.radioButtonDer:
                if (checked)

                if ("Der".equals(questionlist.get(2).getArticle()))

                {
                    isCorrectFeedBack(true);
                }



                break;
            case R.id.rdoButtonDas:
                if (checked)
                break;
        }


    }

*/


   public void addListenerOnButton() {
        answerGroup = (RadioGroup) findViewById(R.id.radioGroup);
       Button btnCheckAnswer= (Button)findViewById(R.id.btnCheckAnswer);


        final Button btnNextQuestion = (Button) findViewById(R.id.btnNext);
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answerGroup.getCheckedRadioButtonId();
                //Setting  questions and grabbing the index of the current one.

                Question currentQuestion = questionlist.get(currentQuizSnapShot);
                RadioButton ChosenButton = (RadioButton) findViewById(selectedId);
                rdoButtonDer=(RadioButton)findViewById(R.id.radioButtonDer);
                rdoButtonDie=(RadioButton)findViewById(R.id.radioButtonDie);
                rdoButtonDas=(RadioButton)findViewById(R.id.rdoButtonDas);

                if (ChosenButton.isChecked())
                {
                    String ChosenButtonArray= ChosenButton.getText().toString();
                    if ((questionlist.get(6).getArticle()).equals("Der"))

                    {

                        Toast correctAnser= Toast.makeText(QuestionActivity.this,"The answer is Correct",Toast.LENGTH_LONG);
                        correctAnser.show();
                        totalScore++;

                    }
                    else
                    {
                        Toast correctAnser= Toast.makeText(QuestionActivity.this,"The answer is Incorrect",Toast.LENGTH_LONG);
                        correctAnser.show();

                    }


                    }




            }




        });


    }
    //Adding questions to questionlist of  array type  Obj<Question>.

    public void setQuestions() {
        //Stores strings and object associated with a particular Resource ID.
       Question Entry1=new Question("Der", "Buam", ContextCompat.getDrawable(this, R.drawable.der_baum));
        questionlist.add(new Question("Der", "Apfel", ContextCompat.getDrawable(this, R.drawable.der_apfel)));
        questionlist.add(new Question("Der", "Stuhl", ContextCompat.getDrawable(this, R.drawable.der_stuhl)));

        questionlist.add(new Question("Das", "Auto", ContextCompat.getDrawable(this, R.drawable.das_auto)));
        questionlist.add(new Question("Das", "Haus", ContextCompat.getDrawable(this, R.drawable.das_haus)));
        questionlist.add(new Question("Das", "Schaf", ContextCompat.getDrawable(this, R.drawable.das_schaf)));

        questionlist.add(new Question("Die", "Hexe", ContextCompat.getDrawable(this, R.drawable.die_hexe)));
        questionlist.add(new Question("Die", "Ente", ContextCompat.getDrawable(this, R.drawable.die_ente)));
        questionlist.add(new Question("Die", "Strasse", ContextCompat.getDrawable(this, R.drawable.die_strasse)));
        questionlist.add(new Question("Die", "Kuh", ContextCompat.getDrawable(this, R.drawable.die_kuh)));
        questionlist.add(new Question("Die", "Milch", ContextCompat.getDrawable(this, R.drawable.die_milch)));

    }

// Sets up the entirety of the question.
    public void setupQuestion()
    {

        if (currentQuizSnapShot > 10)
        {
            Intent scoreIntent = new Intent(QuestionActivity.this,Score.class);

            scoreIntent.putExtra("userFinalScore", totalScore);

            startActivity(scoreIntent);
        }

        else
        {
            Question currentQuestion=(Question)questionlist.get(currentQuizSnapShot) ;
            ImageView quizImage=(ImageView) findViewById(R.id.imageViewQuizImage);
            quizImage.setImageDrawable(currentQuestion.getImage().getCurrent());
            TextView txtViewQuestionNumber= (TextView) findViewById(R.id.tvQuestionNumber);
            txtViewQuestionNumber.setText("This is Question"+currentQuizSnapShot+1);
            TextView txtNoun=(TextView)findViewById(R.id.textViewNoun);
            txtNoun.setText(currentQuestion.getNoun());
        }
    }

    /*public void isCorrectFeedBack(boolean answer) {
        if (answer == true) {


            totalScore++;

            Toast correctAnser= Toast.makeText(this,"The answer is correct",Toast.LENGTH_LONG);

            correctAnser.show();

        }
        */





    }


