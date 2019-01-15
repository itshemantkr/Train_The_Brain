package com.example.hemant.trainthebrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    boolean isActive=true;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRealtiveLayout;

    ArrayList<Integer>answers=new ArrayList<Integer>();
    int locationOfAnswer;
    int score=0;
    int numberOfQuestion=0;

    public void playAgain(View view){
        score=0;
        isActive=true;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30000+100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                isActive=false;
                resultTextView.setText("Your Score = "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
            }
        }.start();
    }

    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfAnswer=rand.nextInt(4);

        answers.clear();

        int incorrectAnser;

        for(int i=0;i<4;i++){

            if(i==locationOfAnswer)
                answers.add(a+b);
            else{
                incorrectAnser=rand.nextInt(41);
                while (incorrectAnser==a+b){
                    incorrectAnser=rand.nextInt(41);
                }
                answers.add(incorrectAnser);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void answerChoosed(View view) {

        if (isActive == true) {
            if (view.getTag().toString().equals(Integer.toString(locationOfAnswer))) {
                score++;
                resultTextView.setText("CORRECT!");
            } else {
                resultTextView.setText("WRONG!");
            }
            numberOfQuestion++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            generateQuestion();
        }
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRealtiveLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=findViewById(R.id.startButton);
        sumTextView=findViewById(R.id.sumTextView);
        button0=findViewById(R.id.button);
        button1=findViewById(R.id.button2);
        button2=findViewById(R.id.button3);
        button3=findViewById(R.id.button4);
        resultTextView=findViewById(R.id.answerTextView);
        pointsTextView=findViewById(R.id.pointsTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameRealtiveLayout=findViewById(R.id.ndrelativeLayout);



    }
}
