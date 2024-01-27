//package com.example.testapp;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//
//
//import java.util.ArrayList;
//
//public class SearchLayout extends AppCompatActivity {
//    public androidx.appcompat.widget.SearchView search;
//    public ArrayList<String> list;
//    public ListView listView;
//
//    private ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_layout);
//
//        search = findViewById(R.id.search);
//        listView = findViewById(R.id.listView);
//        list = new ArrayList<>();
//        list.add("1"); list.add("2"); list.add("3");
//        list.add("2");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        list.add("8");
//        list.add("9");
//        list.add("10");
//
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//
//        Log.d("SearchLayout", "onQueryTextSubmit:");
//
//    }
//    public void FunSearch(){
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("SearchLayout", "onQueryTextSubmit: " + query);
//                adapter.getFilter().filter(query);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        }) ;
//
//
//    }
//}
