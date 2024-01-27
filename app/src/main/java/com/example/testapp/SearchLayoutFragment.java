package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class SearchLayoutFragment extends Fragment {
    public androidx.appcompat.widget.SearchView search;
    public ArrayList<String> list , resultList;
    private TextView txtName;
    public ListView listView;

    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search);
        listView = view.findViewById(R.id.listView);
//        txtName = view.findViewById(R.id.txtName);
        list = new ArrayList<>();
        list.add("1");list.add("2");list.add("3");list.add("2");list.add("5");list.add("6");list.add("7");list.add("8");list.add("9");list.add("10");

        resultList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, resultList);
        listView.setAdapter(adapter);
        FunSearch();


    }

    public void FunSearch() {
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resultList.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                resultList.clear();
                for (String item : list) {
                    if (item.toLowerCase().contains(newText.toLowerCase())) {
                        resultList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
                setName();
                return false;
            }
        });
    }
    public void setName() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = resultList.get(position);

                Log.d("Function call", "OnItemClick Called");
                Log.d("Name test", name);
//                txtName.setText(name);

                ProjectWise projectWiseFragment = new ProjectWise();
                Bundle args = new Bundle();
                args.putString("Name", name);
                projectWiseFragment.setArguments(args);

//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .remove(projectWiseFragment)
//                        .commit();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(getId(), projectWiseFragment);
                transaction.commit();
            }
        });
    }
}