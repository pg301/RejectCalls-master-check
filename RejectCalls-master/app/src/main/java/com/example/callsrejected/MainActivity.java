package com.example.callsrejected;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressDialog pDialog;
    String telephone;
    // URL to get contacts JSON



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent object sent from the IncomingCallReceiver


        Bundle extras = getIntent().getExtras();
         TextView tv = (TextView) findViewById(R.id.txtmessage);
//         tv.setText( extras.getString("number"));

        // Toast.makeText(getApplicationContext(),intent.getStringExtra("number"),Toast.LENGTH_LONG).show();

    }
}

/*

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            int ctr;
            int flag = 0;


           // Intent intent = getIntent();
            //intent.getStringExtra("number");
            //Toast.makeText(getApplicationContext(), intent.getStringExtra("number"), Toast.LENGTH_LONG).show();

//            Log.d("phone number is ",b.getString("number"));

            // a simple hashMap declaration with default size and load factor

            HashMap<String, Integer> hash = new HashMap<String, Integer>();
            hash.put("+917599167091", 2);
            hash.put("+9105622217700", 0);

            // Making a array list of name value pair
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("contractor_id", "1"));

            if (getIntent().getStringExtra("number").equalsIgnoreCase("+917599167091"))

                Log.d("Hurray", "it matches");


            // ctr = hash.get(phone);
            // Log.d("hash check ",Integer.toString(ctr));

            if(ctr==0&&flag==0)
            {
                ctr++;
                Log.d(b.getString("number"),"reached");
                hash.put(b.getString("number"),ctr);
                flag++;
            }
            if(ctr==1&&flag==0)
            {
                ctr++;
                Log.d(b.getString("number"),"started");
                hash.put(b.getString("number"),ctr);
                flag++;
            }
            if(ctr==2&&flag==0)
            {
                ctr++;
                Log.d(b.getString("number"),"ended");
                hash.put(b.getString("number"),ctr);
                flag++;
            }
            flag=0;


            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, nameValuePairs);

            Log.d("Response: ", "> " + jsonStr);

            return null;
        }


      //  @Override
        protected void onPostExecute(Void result) {


            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())

                pDialog.dismiss();

        }

    }}
*/

