package com.svce.jaswanthk.qles_ecanteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity implements AsyncResponse {
    public static String currentCategory="";
    String[] itemname,itemprices,itemid,itemdesc;
    int[] imgid;
    ProgressDialog dialog;
    String cur;
    String urldata;
    ListView listView;
    ArrayList<ProductClass> productarray;
    TextView cartcount;
    ImageView loginimage,cartimage,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setCustomView(R.layout.actionbar_menu);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        cartcount=(TextView)findViewById(R.id.cartCounterText);
        loginimage=(ImageView)findViewById(R.id.loginImage);
        loginimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemListActivity.this,LoginActivity.class);
                String activity="itemlistactivity";
                intent.putExtra("itemlist",activity);
                startActivity(intent);
            }
        });
        cartimage=(ImageView)findViewById(R.id.cartCounterImage);
        back=(ImageView)findViewById(R.id.backImage);


//        ArrayList list=new ArrayList();
//        list.add("Idly");
//        list.add("Vadai");
//        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        listView.setAdapter(adapter);
//        Toast.makeText(this, currentCategory+"", Toast.LENGTH_SHORT).show();


        Object obj=getIntent().getSerializableExtra("cart");
        if(obj==null) {
            productarray = new ArrayList<ProductClass>();
        }
        else
        {
            productarray = (ArrayList)obj;
        }
        cartcount.setText(productarray.size()+"");
        cur=currentCategory.toLowerCase();
//        Toast.makeText(this, cur+"", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadDataFromServer();

            }});
        thread.start();
        dialog= ProgressDialog.show(ItemListActivity.this,"Getting Data","Loading..Please Wait...",true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);


    }


    public void loadDataFromServer()
    {
        urldata="http://602024.com/jaswanthkomaleeswaran/getitemdetail.php?category="+cur;
        GetOutputFromServer getOutputFromServer=new GetOutputFromServer(ItemListActivity.this);
        getOutputFromServer.execute(urldata);
    }
    @Override
    public void processFinish(String output) {
        dialog.dismiss();
//        Toast.makeText(this, output+"", Toast.LENGTH_LONG).show();
        try {
            JSONArray js = new JSONArray(output);
            itemname=new String[js.length()];
            itemprices=new String[js.length()];
            itemdesc=new String[js.length()];
            itemid=new String[js.length()];


            for (int i = 0; i < js.length(); i++) {
                JSONArray js1 = (JSONArray) js.get(i);
                itemid[i]=js1.getString(0);
                itemname[i]=js1.getString(2);
                itemdesc[i]=js1.getString(3);
                itemprices[i]=js1.getString(4);


            }
        }
        catch(Exception e)
        {}
        switch(cur)
        {
            case "tiffin":
                imgid=new int[]{R.drawable.p00001,R.drawable.p00002,R.drawable.p00003,R.drawable.p00004,R.drawable.p00005,R.drawable.p00006,
                        R.drawable.p00007};
                break;
            case "meals":
                imgid=new int[]{R.drawable.p00008,R.drawable.p00009,R.drawable.p00010,R.drawable.p00011};
                break;
            case "beverages":
                imgid=new int[]{R.drawable.p00012,R.drawable.p00013,R.drawable.p00014};
                break;
            case "chats":
                imgid=new int[]{R.drawable.p00015,R.drawable.p00016,R.drawable.p00017,R.drawable.p00018};
                break;
            case "drinks":
                imgid=new int[]{R.drawable.p00019,R.drawable.p00020,R.drawable.p00021,R.drawable.p00022,R.drawable.p00023,R.drawable.p00024};
                break;
            case "snacks":
                imgid=new int[]{R.drawable.p00025,R.drawable.p00026,R.drawable.p00027,R.drawable.p00028,R.drawable.p00029,R.drawable.p00030,
                        R.drawable.p00031};
                break;
            case "namkeens":
                imgid=new int[]{R.drawable.p00032,R.drawable.p00033,R.drawable.p00034,R.drawable.p00035};
                break;
            case "chocolates":
                imgid=new int[]{R.drawable.p00036,R.drawable.p00037,R.drawable.p00038,R.drawable.p00039,R.drawable.p00040};
                break;

        }

        final ItemAdapter adapter=new ItemAdapter(ItemListActivity.this,itemname,itemprices,imgid);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id=itemid[i];
                String name=itemname[i];
                String rate=itemprices[i];
                String desc=itemdesc[i];
                int itemimage=imgid[i];
//                Toast.makeText(ItemListActivity.this, id+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ItemListActivity.this,ItemDescription.class);
                intent.putExtra("productid",id);
                intent.putExtra("productname",name);
                intent.putExtra("productrate",rate);
                intent.putExtra("productimage",itemimage);
                intent.putExtra("productdesc",desc);
                intent.putExtra("cart",productarray);
                intent.putExtra("rrr",cartcount.getText().toString());
//                startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cartcount.setText(data.getStringExtra("rrr"));

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
