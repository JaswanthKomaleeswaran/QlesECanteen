package com.svce.jaswanthk.qles_ecanteen;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDescription extends AppCompatActivity {

ArrayList<ProductClass> productarray;
    TextView cartcount;
    ImageView loginimage,cartimage,back;
    int xx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        Bundle bundle=new Bundle();
        bundle=getIntent().getExtras();
        final String productid=bundle.getString("productid");
        final String productname=bundle.getString("productname");
        final String productrate=bundle.getString("productrate");
        final String productdesc=bundle.getString("productdesc");
       Object obj=getIntent().getSerializableExtra("cart");
       if(obj==null) {
           productarray = new ArrayList<ProductClass>();
       }
       else
       {
           productarray = (ArrayList)obj;
       }
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setCustomView(R.layout.actionbar_menu);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        cartcount=(TextView)findViewById(R.id.cartCounterText);
        loginimage=(ImageView)findViewById(R.id.loginImage);
        cartimage=(ImageView)findViewById(R.id.cartCounterImage);
        back=(ImageView)findViewById(R.id.backImage);
        loginimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemDescription.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        xx=Integer.parseInt(getIntent().getStringExtra("rrr"));

        cartcount.setText(xx+"");
//        int  productimage=Integer.parseInt(bundle.getString("productimage"));


        ImageView itemimage=(ImageView) findViewById(R.id.itemimage);
        TextView itemname=(TextView)findViewById(R.id.itemname);
        TextView itemdesc=(TextView)findViewById(R.id.itemdescription);
        TextView itemrate=(TextView)findViewById(R.id.itemrate);
        Button buynow=(Button)findViewById(R.id.buynow);
        Button addcart=(Button)findViewById(R.id.addcart);
        itemname.setText(productname);
        itemdesc.setText(productdesc);
        itemrate.setText(productrate);
//        itemimage.setImageResource(productimage);

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductClass productClass=new ProductClass();
                productClass.productid=productid;
//                productClass.productimage=
                productClass.productname=productname;
                productClass.productrate=productrate;
                productClass.productdesc=productdesc;
                productarray.add(productClass);
                Bundle b1=new Bundle();
                b1.putSerializable("cart",productarray);
                xx++;
                //cartcount.setText(productarray.size()+"");
                cartcount.setText(xx+"");


            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemDescription.this,PaymentGateway.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
//        Bundle b2=new Bundle();
//        b2.putSerializable("cart",productarray);
//        intent.putExtras(b2);

        intent.putExtra("rrr", xx+"");
        setResult(RESULT_OK,intent);
        finish();
    }
}
