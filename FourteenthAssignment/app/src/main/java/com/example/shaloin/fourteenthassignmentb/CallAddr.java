package com.example.shaloin.fourteenthassignmentb;

import android.annotation.TargetApi;
import android.content.Context;

import android.os.AsyncTask;
import android.os.Build;

import android.telecom.Call;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shaloin on 7/1/17.
 */

public class CallAddr extends AsyncTask<String,Void,String> {
    Context context;
    String result="";
    FormBody.Builder formBody;
    String url;
    OnWebServiceResult resultListener;
    CommonUtilities.SERVICE_TYPE ServiceType;
    Request request;

    public Request getRequest(){return  request;}

    public CallAddr(Context context,String url,FormBody.Builder formBody,CommonUtilities.SERVICE_TYPE ServiceType,OnWebServiceResult resultListener){
        this.context=context;
        this.formBody=formBody;
        this.url=url;
        this.resultListener=resultListener;
        this.ServiceType=ServiceType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder().url(url).build();
        try {
            Response response=client.newCall(request).execute();
            if (!response.isSuccessful()){
                result=response.toString();
                Log.i("Call ADDr","result "+result);

                if (result.equals("")|| result.equals("null") || result.length()==0){

                }
                else {

                }
                //result=response.body().string();
            }
            result=response.body().string();
            Log.i("Call ADDr bakgnd","result "+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("Call ADDr","esult "+s);
        resultListener.getWebResponse(s, ServiceType);
    }
}
