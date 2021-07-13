package com.pyrion.fly_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    int targetFlyNum;
    ImageView targetFlyIV, censoredIV;

    int[] flysNumber = new int[9];
    ImageView[] flysIV = new ImageView[9];

    HashMap<String, Integer> targetFlyTagHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        targetFlyIV = findViewById(R.id.target_fly);
        censoredIV = findViewById(R.id.censored);
        //맵 생성
        for(int count = 0; count< flysIV.length; count++ ) {
            flysIV[count] = findViewById(R.id.fly1 + count);
            flysNumber[count] = random.nextInt(6);
            flysIV[count].setImageResource(R.drawable.fly1 + flysNumber[count]);

            String key = flysIV[count].getTag().toString();
            targetFlyTagHashMap.put(key, flysNumber[count]);

        }
        setTarget();

    }

    //onClick 속성이 부여된 뷰를 클릭하면 자동으로 실행되는 콜백메소드
    public void flyButtonListener(View v){
            String tag = v.getTag().toString();
            if(targetFlyTagHashMap.get(tag)==targetFlyNum){
                v.setVisibility(View.INVISIBLE);
                targetFlyTagHashMap.remove(tag);
            }//TODO 타겟이 아닌 image 클릭 했을 때의 Game over
            if( !targetFlyTagHashMap.containsValue(targetFlyNum) ) {

                try {
                    censoredIV.setVisibility(View.VISIBLE);
                    Thread.sleep(1000);//////////////TODO 문제:censored가 보여지지않고 멈춤
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                censoredIV.setVisibility(View.INVISIBLE);
                if (targetFlyTagHashMap.size()==0){
                    censoredIV.setVisibility(View.VISIBLE);
                    //TODO 게임 finish : 보상 Image
                }else{
                    setTarget();
                }
            }
    }

    public void setTarget() {
        targetFlyNum = targetFlyTagHashMap.values().iterator().next();
        targetFlyIV.setImageResource(R.drawable.fly1 + targetFlyNum);
    }


}