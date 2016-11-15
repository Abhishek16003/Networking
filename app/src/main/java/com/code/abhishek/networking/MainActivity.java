package com.code.abhishek.networking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button b1;
    Button b2;
    private static final String url = "https://www.iiitd.ac.in/about";
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //starting an activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView);
        b1 = (Button) findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {   //setting onClickListener on next button
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {      //setting onClickListener on Download button
            @Override
            public void onClick(View view) {
                DownloadFromWeb dweb = new DownloadFromWeb();    //instantiating of class in order to download data
                dweb.execute();
            }
        });
    }

    private class DownloadFromWeb extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;    //declaration of variable of ProgressDialog
        String heading;
        @Override
        protected void onPreExecute() {   //get invoked on UI thread before the task is executed
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Downloading data......");       //to display title on ProgressDialog
            progressDialog.setCancelable(true);
            progressDialog.show();                                  //showing ProgressDialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {   //It will display the string in user interface
         tv1.setText(heading);
         progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {       //This method is used to download the content from url of webpage
        try{
            Document doc= Jsoup.connect(url).get();         //Jsoup is ussed to parse HTML code
            heading=doc.title();
        }catch(Exception e){
            e.printStackTrace();
        }
            return null;
        }
    }
}
