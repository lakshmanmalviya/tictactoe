package com.example.tictactoe;

import static com.example.tictactoe.R.drawable.r_bg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bnd;
    int count=1;
    int flag =0;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        mp = MediaPlayer.create(this,R.raw.click);
        bnd.restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBlank();
                bnd.result.setText("");
                count=0;
                changeLight();
            }
        });
        bnd.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),NewCode.class));
            }
        });
    }
    public void doBlank(){
        bnd.t1.setText("");
        bnd.t2.setText("");
        bnd.t3.setText("");
        bnd.t4.setText("");
        bnd.t5.setText("");
        bnd.t6.setText("");
        bnd.t7.setText("");
        bnd.t8.setText("");
        bnd.t9.setText("");
    }
    public void check(View view){
        mp = MediaPlayer.create(this,R.raw.click);
        TextView curtext = (TextView)view;
        if(curtext.getText().equals("")){
            mp.start();
        count++;
        if (count==1){
            bnd.result.setText("");
            changeLight();
        }
        if(flag==0){
            curtext.setText("X");
            flag=1;
        }
        else{
            curtext.setText("O");
            flag=0;
        }
        if (count>4 && count<=9){
            if (!isBlank(bnd.t1,bnd.t2,bnd.t3)&& bnd.t1.getText().equals(bnd.t2.getText()) && bnd.t2.getText().equals(bnd.t3.getText())){
                gameOver(bnd.t1);
                highLight(bnd.t1,bnd.t2, bnd.t3);
            }
            else if (!isBlank(bnd.t4,bnd.t5,bnd.t6)&&bnd.t4.getText().equals(bnd.t5.getText()) && bnd.t5.getText().equals(bnd.t6.getText())){
                gameOver(bnd.t4);highLight(bnd.t4,bnd.t5, bnd.t6);
            }
            else if (!isBlank(bnd.t7,bnd.t8,bnd.t9)&&bnd.t7.getText().equals(bnd.t8.getText()) && bnd.t8.getText().equals(bnd.t9.getText())){
                gameOver(bnd.t7);highLight(bnd.t7,bnd.t8, bnd.t9);

            }
            else if (!isBlank(bnd.t1,bnd.t5,bnd.t9)&&bnd.t1.getText().equals(bnd.t5.getText()) && bnd.t5.getText().equals(bnd.t9.getText())){
                gameOver(bnd.t1);highLight(bnd.t1,bnd.t5, bnd.t9);
            }
            else if (!isBlank(bnd.t3,bnd.t5,bnd.t7)&&bnd.t3.getText().equals(bnd.t5.getText()) && bnd.t5.getText().equals(bnd.t7.getText())){
                gameOver(bnd.t3);highLight(bnd.t3,bnd.t5, bnd.t7);
            }
            else if (!isBlank(bnd.t1,bnd.t4,bnd.t7)&&bnd.t1.getText().equals(bnd.t4.getText()) && bnd.t4.getText().equals(bnd.t7.getText())){
                gameOver(bnd.t1);highLight(bnd.t1,bnd.t4, bnd.t7);
            }
            else if (!isBlank(bnd.t2,bnd.t5,bnd.t8)&&bnd.t2.getText().equals(bnd.t5.getText()) && bnd.t5.getText().equals(bnd.t8.getText())){
                gameOver(bnd.t2);highLight(bnd.t2,bnd.t5, bnd.t8);
            }
            else if (!isBlank(bnd.t3,bnd.t6,bnd.t9)&&bnd.t3.getText().equals(bnd.t6.getText()) && bnd.t6.getText().equals(bnd.t9.getText())){
                gameOver(bnd.t3);highLight(bnd.t3,bnd.t6, bnd.t9);
            }
            else if(count==9){
                bnd.result.setText("Game over : Drow happened Try Again");
                doBlank();
                count=0;
                mp = MediaPlayer.create(getApplicationContext(),R.raw.gameover);
                mp.start();
            }
         }
        }
    }
    public void gameOver(TextView textView){
        mp.release();
        mp=null;
        bnd.result.setText("Game over : Winner is "+textView.getText().toString());
        doBlank();
        count=0;
        mp = MediaPlayer.create(getApplicationContext(),R.raw.gameover);
        mp.start();
    }
    public void highLight(TextView t1,TextView t2,TextView t3 ){
        t1.setBackgroundResource(r_bg);
        t2.setBackgroundResource(r_bg);
        t3.setBackgroundResource(r_bg);
        t1.setText("*");
        t2.setText("*");
        t3.setText("*");
    }
    public void changeLight(){
        bnd.t1.setBackgroundResource(R.drawable.text_bg);
        bnd.t2.setBackgroundResource(R.drawable.text_bg);
        bnd.t3.setBackgroundResource(R.drawable.text_bg);
        bnd.t4.setBackgroundResource(R.drawable.text_bg);
        bnd.t5.setBackgroundResource(R.drawable.text_bg);
        bnd.t6.setBackgroundResource(R.drawable.text_bg);
        bnd.t7.setBackgroundResource(R.drawable.text_bg);
        bnd.t8.setBackgroundResource(R.drawable.text_bg);
        bnd.t9.setBackgroundResource(R.drawable.text_bg);
        bnd.t1.setText("");
        bnd.t2.setText("");
        bnd.t3.setText("");
        bnd.t4.setText("");
        bnd.t5.setText("");
        bnd.t6.setText("");
        bnd.t7.setText("");
        bnd.t8.setText("");
        bnd.t9.setText("");
    }
    public boolean isBlank(TextView t1,TextView t2,TextView t3){
        if (t1.getText().equals("")||t2.getText().equals("")|| t1.getText().equals("")){
            return  true;
        }
        return false;
    }
}