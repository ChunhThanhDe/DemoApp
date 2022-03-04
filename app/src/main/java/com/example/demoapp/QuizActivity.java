package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {

    ImageView imgage;
    TextView questionText, timer;
    Button answer1, answer2, answer3, answer4;

    DatabaseReference reference;

    int total =1;
    int correct = 0;
    int incorrect = 0;
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //set find by id
        imgage = findViewById(R.id.image);
        questionText = findViewById(R.id.questionText);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        timer = findViewById(R.id.timer);

        //set default button color
        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));


        updateQuestion();
        //set up to out
        reverseTimer(30, timer);


    }

    private void updateQuestion() {
        if(total > 2){
            //open result activity
            total--;
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);

            // intent.putExtra để truyền dữ liệu qua actitity Result
            // tham số đầu tiên là key , tham số thứ 2 là value
            //ngoài ra có thể dùng blunde
            intent.putExtra("total", String.valueOf(total));
            intent.putExtra("correct", String.valueOf(correct));
            intent.putExtra("incorrect", String.valueOf(incorrect));
            startActivity(intent);

        } else{
            reference = FirebaseDatabase.getInstance().getReference().child("Question").child(String.valueOf(total) );
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    questionText.setText(question.getQuestionText());
                    answer1.setText(question.getAnswer1());
                    answer2.setText(question.getAnswer2());
                    answer3.setText(question.getAnswer3());
                    answer4.setText(question.getAnswer4());

                    answer1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //muốn lấy chuổi thuần phải dùng thêm toString()
                            //không dùng toString() sẽ trả về một editable
                            if (answer1.getText().toString().equals(question.rightAnswer)){

                                Toast.makeText(getApplicationContext(),"Correct answer", Toast.LENGTH_SHORT).show();
                                answer1.setBackgroundColor(Color.GREEN);
                                correct ++;

                                //Handler là 1 class sẫn có
                                //Chức năng giống listener nhưng thay vì lắng nghe onclick thì là handleMessage
                                final Handler handler = new Handler();

                                //postDelayed để dừng màn hình
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);
                            } else {
                                //answer wrong ... we will find correct answer and make it green
                                Toast.makeText(getApplicationContext(), "inCorrect", Toast.LENGTH_SHORT).show();
                                incorrect ++;
                                answer1.setBackgroundColor(Color.RED);
                                if (answer2.getText().toString().equals(question.rightAnswer)){
                                    answer2.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer3.getText().toString().equals(question.rightAnswer)){
                                    answer3.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer4.getText().toString().equals(question.rightAnswer)){
                                    answer4.setBackgroundColor(Color.GREEN);
                                }

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });

                    answer2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (answer2.getText().toString().equals(question.rightAnswer)){

                                Toast.makeText(getApplicationContext(),"Correct answer", Toast.LENGTH_SHORT).show();
                                answer2.setBackgroundColor(Color.GREEN);
                                correct ++;

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);
                            } else {
                                //answer wrong ... we will find correct answer and make it green
                                Toast.makeText(getApplicationContext(), "inCorrect", Toast.LENGTH_SHORT).show();
                                incorrect ++;
                                answer2.setBackgroundColor(Color.RED);
                                if (answer1.getText().toString().equals(question.rightAnswer)){
                                    answer1.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer3.getText().toString().equals(question.rightAnswer)){
                                    answer3.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer4.getText().toString().equals(question.rightAnswer)){
                                    answer4.setBackgroundColor(Color.GREEN);
                                }

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });

                    answer3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (answer3.getText().toString().equals(question.rightAnswer)){

                                Toast.makeText(getApplicationContext(),"Correct answer", Toast.LENGTH_SHORT).show();
                                answer3.setBackgroundColor(Color.GREEN);
                                correct ++;

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);
                            } else {
                                //answer wrong ... we will find correct answer and make it green
                                Toast.makeText(getApplicationContext(), "inCorrect", Toast.LENGTH_SHORT).show();
                                incorrect ++;
                                answer3.setBackgroundColor(Color.RED);
                                if (answer2.getText().toString().equals(question.rightAnswer)){
                                    answer2.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer1.getText().toString().equals(question.rightAnswer)){
                                    answer1.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer4.getText().toString().equals(question.rightAnswer)){
                                    answer4.setBackgroundColor(Color.GREEN);
                                }

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });

                    answer4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (answer4.getText().toString().equals(question.rightAnswer)){

                                Toast.makeText(getApplicationContext(),"Correct answer", Toast.LENGTH_SHORT).show();
                                answer4.setBackgroundColor(Color.GREEN);
                                correct ++;

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);
                            } else {
                                //answer wrong ... we will find correct answer and make it green
                                Toast.makeText(getApplicationContext(), "inCorrect", Toast.LENGTH_SHORT).show();
                                incorrect ++;
                                answer4.setBackgroundColor(Color.RED);
                                if (answer2.getText().toString().equals(question.rightAnswer)){
                                    answer2.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer3.getText().toString().equals(question.rightAnswer)){
                                    answer3.setBackgroundColor(Color.GREEN);
                                }
                                else if (answer1.getText().toString().equals(question.rightAnswer)){
                                    answer1.setBackgroundColor(Color.GREEN);
                                }

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        answer1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        answer4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                                        total ++;
                                        updateQuestion();
                                    }
                                }, 1500);


                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void reverseTimer( int Seconds, final TextView textView){
        new CountDownTimer(Seconds * 1000 + 1000,1000){
            @Override
            public void onTick(long l) {
                int seconds = (int) (l/1000);
                int minutes  = seconds/60;
                textView.setText(String.format("%02d", minutes)+ " : " + String.format("%02d", seconds));

            }
            public void onFinish(){
                textView.setText("Completed");
                Intent myInrent = new Intent(QuizActivity.this,ResultActivity.class);
                myInrent.putExtra("total", String.valueOf(total--));
                myInrent.putExtra("correct", String.valueOf(correct));
                myInrent.putExtra("incorrect", String.valueOf(incorrect));
                startActivity(myInrent);

            }
        }.start();
    }
}