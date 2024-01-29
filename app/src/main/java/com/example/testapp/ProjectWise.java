package com.example.testapp;

import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class ProjectWise extends Fragment  implements AdapterClass.OnItemClickListener{

    private Button btnAdd , btnSub;
    private LinearLayout linearLayout ;
    private  ArrayList<String> rlist  , list;
    private  AdapterClass adapterClass;
    private  ConstraintLayout const1;
    private AutoCompleteTextView autoCompleteTextView;
    private RecyclerView projestNameRv;
    private ImageView drop_down;
    private LinearLayout linerLayout1;

     private Spinner spinnerMaterial  , spinnerDevelopment,spinnerPlan ,spinnerProjectList , spinnerMaterialName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_wise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linerLayout1 = view.findViewById(R.id.linerLayout1);
        btnAdd = view.findViewById(R.id.btnAdd);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        btnSub= view.findViewById(R.id.btnSub);
        linearLayout = view.findViewById(R.id.linerLayout);
        spinnerDevelopment = view.findViewById(R.id.spinnerDevelopment);
        spinnerMaterial  = view.findViewById(R.id.spinnerMaterial );
        spinnerPlan = view.findViewById(R.id.spinnerPlan);
//      spinnerProjectList = view.findViewById(R.id.spinnerProjectList);
        spinnerMaterialName = view.findViewById(R.id.spinnerMaterialName);
        projestNameRv = view.findViewById(R.id.projestNameRv);
        const1 = view.findViewById(R.id.const1);
        drop_down = view.findViewById(R.id.drop_down);
        ArrayList<String> listDevelopment = new ArrayList<>();
         listDevelopment.add("DEV 1"); listDevelopment.add("DEV 2");listDevelopment.add("DEV 3");

        ArrayList<String> listMaterialNAme = new ArrayList<>();
        listMaterialNAme.add("M 1"); listMaterialNAme.add("M 2");listMaterialNAme.add("M3");

        ArrayList<String> listMaterial = new ArrayList<>();
        listMaterial.add("Material 1"); listMaterial.add("Material 1") ;listMaterial.add("Material 1");

        ArrayList<String> listPlan = new ArrayList<>();
        listPlan.add("Plan 1"); listPlan.add("Plan 2"); listPlan.add("Plan 3");

        ArrayList<String> listProjectList= new ArrayList<>();
        listProjectList.add("Project 1"); listProjectList.add("Project 2"); listProjectList.add("Project 3");

        ArrayAdapter<String> materialAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listMaterial);
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(materialAdapter);

        ArrayAdapter<String> planAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listPlan);
        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlan.setAdapter(planAdapter);

        ArrayAdapter<String> developmentAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listDevelopment);
        developmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevelopment.setAdapter(developmentAdapter);

//        ArrayAdapter<String> projectListAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listProjectList);
//        projectListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerProjectList.setAdapter(projectListAdapter);


        ArrayAdapter<String> MaterialNameListAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listMaterialNAme);
        MaterialNameListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterialName.setAdapter(MaterialNameListAdapter);

        list = new ArrayList<>();
        list.add("aaaa") ; list.add("aa") ; list.add("a") ;  list.add("b") ;list.add("abc") ; list.add("def") ;list.add("bbb");list.add("cc");list.add("dd");list.add("eee");list.add("ffff");list.add("ghg");list.add("88");list.add("999");list.add("110");

        rlist = new ArrayList<>();

        adapterClass = new AdapterClass(rlist , getContext(), ProjectWise.this , projestNameRv );
        projestNameRv.setAdapter(adapterClass);

      projectList();
      addLayout();
      subLayout();

    }

    private  void addLayout(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMaterialView();
            }
            private void addMaterialView() {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View addMaterialView =  layoutInflater.inflate(R.layout.groupview,null);
                linearLayout.addView(addMaterialView);
            }
        });

    }
    private  void subLayout(){
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subMaterialView();
            }
            private void subMaterialView() {
                int childCount  = linearLayout.getChildCount();
                if(childCount>0){
                    linearLayout.removeViewAt(childCount-1);
                }
            }
        });

    }

    private  void projectList(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_dropdown_item_1line,list);
        autoCompleteTextView.setAdapter(adapter);
        LinearLayoutManager newLinerLayoutManager = new GridLayoutManager(requireContext(), 3);
        projestNameRv.setLayoutManager(newLinerLayoutManager);

        drop_down.setOnClickListener(v -> {
            if(linerLayout1.getVisibility() != View.VISIBLE) {
                linerLayout1.setVisibility(View.VISIBLE);
                autoCompleteTextView.setEnabled(true);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = autoCompleteTextView.getText().toString();
                        rlist.add(name);
                        adapterClass.notifyDataSetChanged();
                        autoCompleteTextView.setText("");
                        autoCompleteTextView.setEnabled(false);
                        linerLayout1.setVisibility(View.GONE);
                    }
                });
            }else{
                linerLayout1.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void onItemClick(int position) {
         if(position>=0){
             rlist.remove(position);
             adapterClass.notifyDataSetChanged();
         }
    }
    @Override
        public void onItemClick() {
            if(linerLayout1.getVisibility() != View.VISIBLE) {
                linerLayout1.setVisibility(View.VISIBLE);
                autoCompleteTextView.setEnabled(true);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name = autoCompleteTextView.getText().toString();
                        rlist.add(name);
                        adapterClass.notifyDataSetChanged();
                        autoCompleteTextView.setText("");
                        autoCompleteTextView.setEnabled(false);
                        linerLayout1.setVisibility(View.GONE);
                    }
                });
            }else{
                linerLayout1.setVisibility(View.GONE);
            }
        }
}