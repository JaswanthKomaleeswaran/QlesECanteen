package com.svce.jaswanthk.qles_ecanteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentGateway extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        EditText cardname=(EditText)findViewById(R.id.cardname);
        EditText expiry=(EditText)findViewById(R.id.expirydate);
        EditText cvv=(EditText)findViewById(R.id.cvv);
        EditText cardholder=(EditText)findViewById(R.id.name);
        Button makepayment=(Button)findViewById(R.id.payment);
        Button cancel=(Button)findViewById(R.id.cancel);
        dialog=new ProgressDialog(this);

        makepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentGateway.this, "Payment Done Successfully..", Toast.LENGTH_SHORT).show();
                dialog= ProgressDialog.show(PaymentGateway.this,"QR Image","Your QR Image is Loading..Please Wait....",true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(5000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        threaddismiss();
                    }});

                thread.start();



            }


        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(PaymentGateway.this,ItemDescription.class);
//                startActivity(intent);
//            }
//        });



    }
    public  void threaddismiss()
    {
        dialog.dismiss();
        Intent intent=new Intent(PaymentGateway.this,QRPageClass.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
