package com.example.a1;



        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.SearchView;

        import java.util.ArrayList;

public class ProviderMainPage extends AppCompatActivity {

    private ListView showContent;
    private SearchView keyWords;
    private Button myTask;
    private Button viewOnMap;




    String[] name_test = {"apple", "alpha", "bad", "battle", "cover"};
    String [] task_test = {"task1","task2","task3","task4","task5"};
    String [] status_test = {"On","off","on","on","off"};
    int [] lowest_test = {1,2,3,4,5};

    //ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_page);
        showContent = (ListView) findViewById(R.id.listView);
        keyWords = (SearchView) findViewById(R.id.searchView);
        final pmpCustomAdapter customAdapter = new pmpCustomAdapter(this,boundinfo());
        showContent.setAdapter(customAdapter);


        keyWords.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                customAdapter.getFilter().filter(newText);


                return false;
            }
        });



        myTask = (Button) findViewById(R.id.myTask);
        myTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show_content(showContent);
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

    private ArrayList<ProviderAdaptInfo> boundinfo()
    {
        ArrayList<ProviderAdaptInfo> providerAdaptInfos = new ArrayList<ProviderAdaptInfo>();
        ProviderAdaptInfo p;
        for (int i = 0; i< name_test.length;i++){
            p = new ProviderAdaptInfo(name_test[i],task_test[i],status_test[i],lowest_test[i]);
            providerAdaptInfos.add(p);
        }
        return providerAdaptInfos;
    }



    public void map_handler() {

        /* this part should handle google map api connection */

    }


}






