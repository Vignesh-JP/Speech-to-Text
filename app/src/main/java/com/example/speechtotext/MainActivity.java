package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    EditText mTexttv,mClgname,mDept,mTextreg;
    ImageButton mMic,mMicClg,mMicdept,mMicreg;
    public static final int Recogniser_result=1;
    public String a;
    int btnseclector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTexttv = findViewById(R.id.texttv);
        mMic = findViewById(R.id.mic);
        mClgname = findViewById(R.id.clgname);
        mMicClg = findViewById(R.id.micClg);
        mDept = findViewById(R.id.department);
        mMicdept = findViewById(R.id.micdept);
        mTextreg = findViewById(R.id.textreg);
        mMicreg = findViewById(R.id.micreg);

        mMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnseclector=1;
                speak(btnseclector);
            }
        });
        mMicClg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnseclector=2;
                speak(btnseclector);
            }
        });
        mMicdept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnseclector=3;
                speak(btnseclector);
            }
        });
        mMicreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnseclector=4;
                speak(btnseclector);
            }
        });
    }public void speak(int btnsec) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");
        startActivityForResult(intent, Recogniser_result);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case Recogniser_result:{
                if(resultCode == RESULT_OK && null!=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    a=result.get(0);
                    if(btnseclector==1) {
                        mTexttv.setText(a);
                        a = "";
                    }else if(btnseclector==2){
                        mClgname.setText(a);
                        a = "";
                    }else if(btnseclector==3){
                        mDept.setText(a);
                        a = "";
                    }else{
                        mTextreg.setText(a);
                        a = "";
                    }
                }
            }
        }
    }
}
