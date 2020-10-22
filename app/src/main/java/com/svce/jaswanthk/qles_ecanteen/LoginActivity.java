package com.svce.jaswanthk.qles_ecanteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {
    EditText user,pass;
    TextView userpwd;
    Button logbtn;
    ProgressDialog dialog;
    String urldata;
    String usertext,passtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=(EditText) findViewById(R.id.user);
        pass=(EditText) findViewById(R.id.pass);
        userpwd = (TextView) findViewById(R.id.userpwd);

        user.setText("");
        pass.setText("");
//        Bundle bundle=getIntent().getExtras();
//        String mainactivity=bundle.getString("main");

        logbtn=(Button)findViewById(R.id.logbtn);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertext=user.getText().toString();
                passtext=pass.getText().toString();

                if (usertext.indexOf(" ")!=-1  || usertext.trim().length() == 0) {
                    LinearLayout l1 = (LinearLayout) findViewById(R.id.llay1);
                    l1.setBackgroundColor(Color.parseColor("#ff0000"));
                    user.setBackgroundColor(Color.parseColor("#ff0000"));
                    user.requestFocus();
                } else if (passtext.trim().length() == 0) {
                    LinearLayout l1 = (LinearLayout) findViewById(R.id.llay2);
                    l1.setBackgroundColor(Color.parseColor("#ff0000"));
                    pass.setBackgroundColor(Color.parseColor("#ff0000"));
                    pass.requestFocus();
                }
                else {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            loadDataFromServer();
                        }
                    });
                    thread.start();
                    dialog = ProgressDialog.show(LoginActivity.this, "Validating", "Please Wait...", true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                }
            }
        });
    }
    public void loadDataFromServer()
    {
        urldata="http://602024.com/jaswanthkomaleeswaran/login.php?name="+ user.getText().toString()+"," +pass.getText().toString();
//        urldata="127.0.0.1:8012/jaswanthkomaleeswaran/login.php?name="+ user.getText().toString()+"," +pass.getText().toString();
//        Toast.makeText(this, urldata, Toast.LENGTH_SHORT).show();
        GetOutputFromServer getOutputFromServer=new GetOutputFromServer(LoginActivity.this);
        getOutputFromServer.execute(urldata);
    }

    @Override
    public void processFinish(String output) {
//        Toast.makeText(this, output+"", Toast.LENGTH_SHORT).show();
        dialog.dismiss();

        if(output.equals("0"))
        {
            userpwd.setText("Username is Wrong");
            userpwd.setVisibility(View.VISIBLE);
        }
        else if (output.equals("1"))
        {
            userpwd.setText("Password is Wrong");
            userpwd.setVisibility(View.VISIBLE);
        }
        else
        {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }


    }
}
