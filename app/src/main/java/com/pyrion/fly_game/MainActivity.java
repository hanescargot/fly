package com.pyrion.fly_game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    int targetNum;
    ImageView targetFlyID = findViewById(R.id.targetFly);

    int[] flysNumber = new int[6];
    ImageView[] flysID = new ImageView[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fly이미지 자리 아이디 연결 And 랜덤 플라이 생성
        targetNum = random.nextInt(6);
        targetFlyID.setImageResource(R.drawable.fly1 + targetNum);
        for(int count = 0; count< flysID.length; count++ ) {
            flysID[count] = findViewById(R.id.fly1 + count);

            flysNumber[count] = random.nextInt(6); //0~5
            flysID[count].setImageResource(R.drawable.fly1 + flysNumber[count]);
        }

    }
}