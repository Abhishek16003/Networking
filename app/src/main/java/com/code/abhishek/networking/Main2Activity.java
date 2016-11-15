package com.code.abhishek.networking;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    TextView tv2;
    Button b2;
    private static final String url = "https://www.iiitd.ac.in/about";
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //starting an activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv2 = (TextView) findViewById(R.id.describe);
        tv2.setMovementMethod(new ScrollingMovementMethod());
        b2= (Button) findViewById(R.id.button11);
        b2.setOnClickListener(new View.OnClickListener() {      //setting onClickListener on Download button
            @Override
            public void onClick(View view) {
                DownloadFromWeb1 dweb1 = new DownloadFromWeb1();    //instantiating of class in order to download data
                dweb1.execute();
            }
        });

    }

    private class DownloadFromWeb1 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;
        String d;
        @Override
        protected void onPreExecute() {  //get invoked on UI thread before the task is executed
            progressDialog=new ProgressDialog(Main2Activity.this);
            progressDialog.setTitle("Downloading data......");   //to display title on ProgressDialog
            progressDialog.setCancelable(true);
            progressDialog.show();      //showing ProgressDialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {     //It will display the string in user interface
            tv2.setText(d);
            Log.d(TAG,d);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {    //This method is used to download the content from url of webpage
            try{
                Document doc= Jsoup.connect(url).get();    //Jsoup is ussed to parse HTML code
                d=doc.body().text();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }





}
