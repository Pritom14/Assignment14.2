package com.example.shaloin.fourteenthassignmentb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult {

    String url="http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model=new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewID);

        hitRequest();

    }
    private void hitRequest(){
        FormBody.Builder parameters=new FormBody.Builder();
        parameters.add("page","1");
        if(NetworkStatus.getInstance(this).isConectedToInternet()){
            CallAddr call=new CallAddr(this,url,parameters, CommonUtilities.SERVICE_TYPE.GET_DATA,this);
            call.execute();

        }
        else {
            Toast.makeText(this,"No Network!!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {

        try{
            Log.i("Main Activity","result "+result);
            JSONObject obj=new JSONObject(result);
            JSONArray arr=obj.getJSONArray("results");
            for (int i=0;i<arr.length();i++){
                JSONObject object=arr.getJSONObject(i);
                DataHandler handler=new DataHandler();
                handler.setId(object.getInt("id"));
                handler.setName(object.getString("name"));
                handler.setVotes(object.getInt("vote_count"));
                model.add(handler);
            }
            DataAdapter adapter=new DataAdapter(this,model);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
