package com.example.python_compiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
    TextView output;
    TextView tv_rows;
    EditText codearea;
    Button run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        output=(TextView) findViewById(R.id.output);
        tv_rows = (TextView) findViewById(R.id.tv_rows);
        codearea=(EditText) findViewById(R.id.codearea);
        run=(Button) findViewById(R.id.run);



        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        codearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int lines = codearea.getLineCount();
                String linesText="";
                for(int z =1;z<=lines;z++){
                    linesText = linesText+z+".\n";

                }
                tv_rows.setText(linesText);



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Python py = Python.getInstance();
                PyObject pyobj = py.getModule("myscript");
                PyObject obj = pyobj.callAttr("main",codearea.getText().toString());
                output.setText(obj.toString());

            }
        });









    }
}