package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.BreakIterator;

public class ResultActivity extends AppCompatActivity {

    TextView totalQuestion, correctAns, incorrectAns, lastScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        totalQuestion =(TextView) findViewById(R.id.total);
        correctAns = (TextView) findViewById(R.id.correct);
        incorrectAns = (TextView) findViewById(R.id.incorrect);
        lastScore = (TextView) findViewById((R.id.lastScore));

        //nhận giữ liệu qua intent từ mainActivity
        // intent.xxxExtra(0 với xxx phù hợp với key bên dóng gói
        // tham số thứ nhất là key, tham số thứ 2 có thể có sẽ là sữ liệu mặc định nếu hệ thống không tìm thấy dữ liệu với key (thường là int và boolean)
        Intent intent = getIntent();
        String totalQuestions = intent.getStringExtra("total");
        String correct = intent.getStringExtra("correct");
        String incorrect = intent.getStringExtra("incorrect");
        String score = intent.getStringExtra("score");

        totalQuestion.setText(totalQuestions);
        correctAns.setText(correct);
        incorrectAns.setText(incorrect);
        lastScore.setText(score);
    }
}