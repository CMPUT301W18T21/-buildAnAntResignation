package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class ProviderMainPage extends AppCompatActivity {

    private ListView showContent;
    private SearchView keyWords;
    private Button myTask;
    private Button viewOnMap;

    String [] name = {"apple","alpha","bad","battle","cover","demand","global","instance","identity","voice","zelda",};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_page);
        showContent = (ListView)findViewById(R.id.listView);
        keyWords = (SearchView)findViewById(R.id.searchView);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, name);
        showContent.setAdapter(adapter);
        keyWords.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                /* this is just for temporary show case */
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        myTask = (Button)findViewById(R.id.myTask);
        myTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_content(showContent);
            }
        });
        viewOnMap = (Button) findViewById(R.id.viewOnMap);
        viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_handler();
            }
        });


    }


    public void show_content(ListView thisList){


        /* This part need searching function to implement */


    }



    public void map_handler(){

        /* this part should handle google map api connection */

    }


}
