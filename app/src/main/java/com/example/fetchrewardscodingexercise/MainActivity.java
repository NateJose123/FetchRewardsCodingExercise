package com.example.fetchrewardscodingexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<HiringDataItem> hiringDataItemList;
    private static String JSON_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    Adapter adapter;
    Menu menu;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.hiringData);
        hiringDataItemList = new ArrayList<>();  //Array for data from API

        extractHiringDataItems(); //fetching data from API and populating list

    }

    //Adding sorting options in menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);

        return true;
    }

    //sorting data fetched from API in ascending or descending order
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_asc:
                Collections.sort(hiringDataItemList,  HiringDataItem.listIdAscComparator.thenComparing(HiringDataItem.itemNameAZComparator));
                Toast.makeText(MainActivity.this, "Sorting in Ascending Order", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_desc:
                Collections.sort(hiringDataItemList,  HiringDataItem.listIdDescComparator.thenComparing(HiringDataItem.itemNameZAComparator));
                Toast.makeText(MainActivity.this, "Sorting in Descending Order", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //fetching data from API
    private void extractHiringDataItems() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject hiringDataObject = response.getJSONObject(i);

                        HiringDataItem hiringDataItem = new HiringDataItem();
                        String itemNameString=hiringDataObject.getString("name").toString();
                        //check whether "name" is empty or null and filter data accordingly
                        if (itemNameString.equals("null") || itemNameString.equals("")){
                            continue;
                        } else {
                            hiringDataItem.setListId(hiringDataObject.getInt("listId"));
                            hiringDataItem.setItemName(itemNameString);
                        }
                        hiringDataItemList.add(hiringDataItem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                //sorting data in ascending order immediately after fetching and adding it to array.
                Collections.sort(hiringDataItemList,  HiringDataItem.listIdAscComparator.thenComparing(HiringDataItem.itemNameAZComparator));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), hiringDataItemList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }


    @Override
    protected void onStart() {
        //refresh data everytime app lifecycle is restarted
        super.onStart();
        extractHiringDataItems();
    }
}