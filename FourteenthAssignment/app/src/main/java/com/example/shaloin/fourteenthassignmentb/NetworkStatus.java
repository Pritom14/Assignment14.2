package com.example.shaloin.fourteenthassignmentb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shaloin on 7/1/17.
 */

public class NetworkStatus {

    private static NetworkStatus instance=new NetworkStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    boolean connected=false;

    public static NetworkStatus getInstance(Context ctx){
        context=ctx;
        return instance;
    }

    public boolean isConectedToInternet(){
        connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=connectivityManager.getActiveNetworkInfo();
        if(activeNetwork!=null){
            return true;
        }
        return false;
    }
}
