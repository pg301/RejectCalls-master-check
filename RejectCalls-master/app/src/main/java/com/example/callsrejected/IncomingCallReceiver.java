package com.example.callsrejected;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class IncomingCallReceiver extends BroadcastReceiver {
    String incomingNumber = "";
    AudioManager audioManager;
    TelephonyManager telephonyManager;

    public static Map<String, Integer> hash = new HashMap<String, Integer>();



    private static String url = "http://128.199.206.145/vigo/v1/displayalldrivers";


    public void onReceive(Context context, Intent intent) {

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            }
        }

        if (!incomingNumber.equals("")) {
            rejectCall();
            startApp(context, incomingNumber);
        }
    }


    private void startApp(Context context, String number) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("number", number);
        context.startActivity(intent);
        new GetContacts().execute();

    }

    private void rejectCall() {


        try {

            Class<?> classTelephony = Class.forName(telephonyManager.getClass().getName());
            Method method = classTelephony.getDeclaredMethod("getITelephony");
            method.setAccessible(true);
            Object telephonyInterface = method.invoke(telephonyManager);
            Class<?> telephonyInterfaceClass = Class.forName(telephonyInterface.getClass().getName());
            Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");
            methodEndCall.invoke(telephonyInterface);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();


        }

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected Void doInBackground(Void... arg0) {

            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();


            int ctr;

            // variable to ensure that the control enters the loop only one time because everytime the counter is increased the first part of if statement(mentioned below) holds true
            int flag = 0;

            Log.d("phone number is ", incomingNumber);


            // Making a array list of name value pair
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("contractor_id", "1"));

            //just a random check no importance in code
            if (incomingNumber.equalsIgnoreCase("+917599167091")) ;
            {
                Log.d("hurray", "it matches");
            }

            //the phone no(key) of driver is set in the hashmap on his first call
            //Control enters this loop only when the driver makes his first call
            if(!hash.containsKey(incomingNumber))
            {
                Log.d("entered loop","creating user");
                hash.put(incomingNumber,0);//setting 0 as the default value of the key

            }

                ctr = hash.get(incomingNumber);
                Log.d(" counter check ", Integer.toString(ctr));

                if (ctr == 0 && flag == 0) {

                    Log.d(incomingNumber, "reached");
                    hash.put(incomingNumber,++ctr);
                    flag++;
                }
                if (ctr == 1 && flag == 0) {
                    Log.d(incomingNumber, "started");

                    hash.put(incomingNumber,++ctr);
                    flag++;
                }
                if (ctr == 2 && flag == 0) {

                    Log.d(incomingNumber, "ended");
                    hash.put(incomingNumber,++ctr);
                    flag++;
                }

                if(ctr==3)//Ride is finished
                {
                    hash.remove(incomingNumber);//remove the user
                }

                flag = 0;

                //making service call
                String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, nameValuePairs);

                //printing response value in logcat
                Log.d("Response: ", "> " + jsonStr);


            return null;
        }


        //  @Override
        protected void onPostExecute(Void result) {


            super.onPostExecute(result);
            // Dismiss the progress dialog


        }
    }
}