/*
 * 1. 각각 컨트롤의 클릭 이벤트에서 동작추가
 * 2. 각각 컨트롤의 클릭 이벤트에서 공통된 동작을 처리하는 메소드와 연결
 * 3. 이벤트를 동적으로 추가해서 동작하기
 * 4. 디자인 모드에서 컨트롤의 이벤트에 동작 추가하기
 * 5. 태그를 사용해서 컨트롤의 이벤트에 동작 추가하기
 * 출처 : https://blog.naver.com/curlicu/40135756908*/
package org.techtown.dutchpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.dutchpay.R;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText peopleString;
    EditText moneyString;
    TextView AmountPerPersonText;
    TextView ManyAmountText;
    int DropNum=0, AmountPerPerson,ManyAmount;  //버림할 단위 //인당 내야 할 금액 //좀 더 내야 할 사람의 금액

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleString = (EditText) findViewById(R.id.numText);
        moneyString = (EditText) findViewById(R.id.moneyText);

        AmountPerPersonText= (TextView) findViewById(R.id.AmountPerPersonText);
        ManyAmountText = (TextView) findViewById(R.id.ManyAmountText);

    }

    //10에서 버림
    public void onButton10_Clicked(View v){
        DropNum=1;
        Drop();
    }
    //100에서 버림
    public void onButton100_Clicked(View v){
        DropNum=2;
        Drop();
    }
    //1000에서 버림
    public void onButton1000_Clicked(View v){
        DropNum=3;
        Drop();
    }

    public void onRunButton(View v){
        run();
    }


    protected void Drop(){	//버림 선택

        int people =Integer.parseInt(peopleString.getText().toString());
        int money =Integer.parseInt(moneyString.getText().toString());
        //getText() : EditText에서 입력값을 받아올 떄 사용한다.
        int DivValue = money/people;

        if(DropNum==1) {	//10에서 버림
            DivValue = DivValue/10;
            AmountPerPerson = (int)DivValue*10;
            ManyAmount = money - (people-1)*AmountPerPerson;
        }
        else if(DropNum==2) {	//100에서 버림
            DivValue=DivValue/100;
            AmountPerPerson=(int)DivValue*100;
            ManyAmount = money - (people-1)*AmountPerPerson;
        }
        else if(DropNum==3) {	//1000에서 버림
            DivValue=DivValue/1000;
            AmountPerPerson=(int)DivValue*1000;
            ManyAmount = money - (people-1)*AmountPerPerson;
        }

    }

    public void run() {    //결과
        if(DropNum==0) {
            Toast toastView = Toast.makeText(this, "버림할 단위를 선택하여주세요.", Toast.LENGTH_SHORT);
            toastView.show();
        }

        else{
            String AmountPerPersonString = Integer.toString(AmountPerPerson) + "원";
            String ManyAmountString = "한명은 " + Integer.toString(ManyAmount) + "원 내야합니다.^^";

            AmountPerPersonText.setText(AmountPerPersonString);
            ManyAmountText.setText(ManyAmountString);
            DropNum=0;
            /*setText()
             * 1. setText(int resid) : resid는 res폴더 안의 변수이다. String Type의 리소스를 넣어야한다.
             * 2. setText(CharSequence text) : CharSequence는 String의 상위 타입으로 interface이다.
             * 3. setText(CharSequence text, TextView.BufferType type)
             * 4. setText(int resid, TextView.BufferType type)
             * 3,4 : 1,2와 다른 점은 BufferType인데 세종류가 있다. 디폴트값인 NORMAL, SPANNABLE, EDITABLE
             * 5. setText(char[] text, int start, int len): 첫번째 인자 : char 배열,두번째 인자 : char 배열에서 가져올 start index, 세번째 인자 : char 배열에서 가져올 길이
             */
        }
    }




}
