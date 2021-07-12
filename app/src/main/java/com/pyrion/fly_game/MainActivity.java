package com.pyrion.fly_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    int targetFlyNum;
    ImageView targetFlyIV;
    ImageView censoredIV;

    int[] flysNumber = new int[9];
    ImageView[] flysIV = new ImageView[9];

    ArrayList<String> targetFlyTagLIst = new ArrayList<>();
    HashMap<String, Integer> targetFlyTagHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        targetFlyIV = findViewById(R.id.targetFly);
        censoredIV = findViewById(R.id.censored);

        for(int count = 0; count< flysIV.length; count++ ) {
            flysIV[count] = findViewById(R.id.fly1 + count);
            flysNumber[count] = random.nextInt(6);
            flysIV[count].setImageResource(R.drawable.fly1 + flysNumber[count]);

            String key = flysIV[count].getTag().toString();
            targetFlyTagHashMap.put(key, flysNumber[count]);

            flysIV[count].setOnClickListener(flyButtonListener);
        }
        setTarget();


    }

    View.OnClickListener flyButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            if(targetFlyTagHashMap.get(tag)==targetFlyNum){
                v.setVisibility(View.INVISIBLE);
                targetFlyTagHashMap.remove(tag);
            }
            if( !targetFlyTagHashMap.containsValue(targetFlyNum) ){

                try {
                    censoredIV.setVisibility(View.VISIBLE);
                    Thread.sleep(1000);//////////////문제:censored가 보여지지않고 멈춤
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                censoredIV.setVisibility(View.INVISIBLE);
                setTarget();
            }

        }
    };

    void setTarget() {
        targetFlyNum = targetFlyTagHashMap.values().iterator().next();
        targetFlyIV.setImageResource(R.drawable.fly1 + targetFlyNum);
    }


}