package com.example.text_to_speech;

import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextToSpeech toSpeech;
    TextInputLayout editText;
    Button bspeak,bstop;
    String text;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =  findViewById(R.id.editText);
        bspeak = findViewById(R.id.bspeak);
        bstop = findViewById(R.id.bstop);

        bspeak.setOnClickListener(this);
        bstop.setOnClickListener(this);


        toSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    result=toSpeech.setLanguage(Locale.ENGLISH);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech!=null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bspeak:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text = editText.getEditText().getText().toString().trim();
                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            case R.id.bstop:
                if (toSpeech!=null)
                {
                    toSpeech.stop();
                }
                break;
        }

    }
}
