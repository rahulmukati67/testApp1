package com.example.testapp;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProjectWise extends Fragment  implements AdapterClass.OnItemClickListener{
    private Button  btnSubmit;
    private LinearLayout linearLayout ;
    private EditText editQty , editNotes;
    private  ArrayList<String> rlist  , list, listMaterialNAme;
    private  AdapterClass adapterClass;
    private  ConstraintLayout const1;
    private AutoCompleteTextView autoCompleteTextView ,MaterialACTV;
    private RecyclerView projestNameRv;
    private ImageView drop_down;
    private LinearLayout linerLayout1 , MaterialNameLinearLayout;
    TextView projectListHint , TextViewMaterialName , btnAdd , btnSub;
    int counter =1;
    ArrayAdapter<String> adapter,a;

    private Spinner spinnerMaterial  , spinnerDevelopment,spinnerPlan ;
    private LinearLayoutListener linearLayoutListener;

    interface LinearLayoutListener {
          void hideLinearLayout();
    }
    public void setLinearLayoutListener(LinearLayoutListener listener) {
        this.linearLayoutListener = listener;
    }
    public void notifyLinearLayoutHidden() {
        if (linearLayoutListener != null) {
            linearLayoutListener.hideLinearLayout();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project_wise, container, false);
        linerLayout1 = rootView.findViewById(R.id.linerLayout1);
        projectListHint = rootView.findViewById(R.id.projectListHint);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                 setVisibilities();
                return false;
            }
        });
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linerLayout1 = view.findViewById(R.id.linerLayout1);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        editNotes   =view.findViewById(R.id.editNotes);
        editQty = view.findViewById(R.id.editQty);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        MaterialACTV = view.findViewById(R.id.MaterialACTV);
        btnSub= view.findViewById(R.id.btnSub);
        linearLayout = view.findViewById(R.id.linerLayout);
        MaterialNameLinearLayout =view.findViewById(R.id.MaterialNameLinearLayout);
        spinnerDevelopment = view.findViewById(R.id.spinnerDevelopment);
        spinnerMaterial  = view.findViewById(R.id.spinnerMaterial );
        spinnerPlan = view.findViewById(R.id.spinnerPlan);
        TextViewMaterialName = view.findViewById(R.id.TextViewMaterialName);
        projestNameRv = view.findViewById(R.id.projestNameRv);
        const1 = view.findViewById(R.id.const1);
        drop_down = view.findViewById(R.id.drop_down);

        ArrayList<String> listDevelopment = new ArrayList<>();
        listDevelopment.add("Please Select");listDevelopment.add("DEV 1"); listDevelopment.add("DEV 2");listDevelopment.add("DEV 3");


        ArrayList<String> listMaterial = new ArrayList<>();
        listMaterial.add("Please Select"); listMaterial.add("Material 1"); listMaterial.add("Material 2") ;listMaterial.add("Material 3");

        ArrayList<String> listPlan = new ArrayList<>();
        listPlan.add("Please Select");listPlan.add("Plan 1"); listPlan.add("Plan 2"); listPlan.add("Plan 3");


        final ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(
                requireContext(),android.R.layout.simple_spinner_item,  listMaterial ){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){

                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(materialAdapter);

        final ArrayAdapter<String> planAdapter = new ArrayAdapter<String>(
                requireContext(),android.R.layout.simple_spinner_item,  listPlan ){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){

                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlan.setAdapter(planAdapter);

        final ArrayAdapter<String> developmentAdapter = new ArrayAdapter<String>(
                requireContext(),android.R.layout.simple_spinner_item,  listDevelopment ){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){

                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        developmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevelopment.setAdapter(developmentAdapter);

        spinnerMaterial.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });
        editQty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });
        btnSubmit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });
        editNotes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });

        spinnerPlan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });

        spinnerDevelopment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });
        TextViewMaterialName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setVisibilities();
                }
                return false;
            }
        });

        list = new ArrayList<>();
        list.add("Proj 1") ; list.add("Proj 2") ; list.add("Proj 3") ;  list.add("Proj 4") ;list.add("Proj 5");
        list.add("Proj 6") ;list.add("Proj 7");list.add("Proj 8");list.add("Proj 9");

        adapter = new ArrayAdapter<>( requireContext(),android.R.layout.simple_dropdown_item_1line,list);
        autoCompleteTextView.setAdapter(adapter);

        rlist = new ArrayList<>();
        adapterClass = new AdapterClass(rlist , getContext(), ProjectWise.this , projestNameRv );
        projestNameRv.setAdapter(adapterClass);
        adapterClass.notifyDataSetChanged();
        projectList();
        adapter.notifyDataSetChanged();
        addLayout();
        subLayout();

        listMaterialNAme = new ArrayList<>();
        listMaterialNAme.add("material 1");
        listMaterialNAme.add("material 2");
        listMaterialNAme.add("material 3");
        listMaterialNAme.add("material 4");
        listMaterialNAme.add("material 5");

        MaterialNameList();


    }
    private  void addLayout(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter <= 4) {
                    setVisibilities();
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    View addMaterialView = layoutInflater.inflate(R.layout.groupview, null);
                    TextView  TextViewMaterial1 = addMaterialView.findViewById(R.id.TextViewMaterial);

                    AutoCompleteTextView MaterialACTV1 = addMaterialView.findViewById(R.id.MaterialACTV);
                    ArrayAdapter<String> MaterialNameListAdapter = new ArrayAdapter<String>(
                            requireContext(),android.R.layout.simple_dropdown_item_1line,  listMaterialNAme );
                    MaterialNameListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MaterialACTV1.setAdapter(MaterialNameListAdapter);
                    LinearLayout MaterialNameLinearLayout1 = addMaterialView.findViewById(R.id.MaterialNameLinearLayout);
                    TextViewMaterial1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(MaterialNameLinearLayout1.getVisibility() != View.VISIBLE) {
                                MaterialNameLinearLayout1.setVisibility(View.VISIBLE);
                                MaterialACTV1.setEnabled(true);
                                MaterialACTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if(!TextViewMaterial1.getText().toString().equals("")) {
                                            listMaterialNAme.add(TextViewMaterial1.getText().toString());
                                            listMaterialNAme.remove(MaterialACTV1.getText().toString());
                                            TextViewMaterial1.setText(MaterialACTV1.getText().toString());
                                        }else {
                                            TextViewMaterial1.setText(MaterialACTV1.getText().toString());
                                            listMaterialNAme.remove(MaterialACTV1.getText().toString());
                                        }
                                        MaterialNameLinearLayout1.setVisibility(View.GONE);
                                        MaterialACTV1.setText("");
                                        Collections.sort(listMaterialNAme);
                                        ArrayAdapter<String> MaterialNameListAdapter = new ArrayAdapter<String>(
                                                requireContext(), android.R.layout.simple_dropdown_item_1line, listMaterialNAme);
                                        MaterialNameListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        MaterialACTV1.setAdapter(MaterialNameListAdapter);
                                    }
                                });
                            }else{
                                MaterialNameLinearLayout1.setVisibility(View.GONE);
                            }
                        }
                    });
                    TextView qty = addMaterialView.findViewById(R.id.editQty);
                    qty.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                setVisibilities();
                            }
                            return false;
                        }
                    });

                    linearLayout.addView(addMaterialView);
                    counter += 1;
                };
                setVisibilities();
            }
        });
    }
    private  void subLayout(){
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = linearLayout.getChildCount();
                if (childCount > 0) {
                    View lastChild = linearLayout.getChildAt(childCount - 1);
                    TextView TextViewMaterial1 = lastChild.findViewById(R.id.TextViewMaterial);
                    String materialText = TextViewMaterial1.getText().toString().trim();
                    if (!materialText.isEmpty()) {
                        listMaterialNAme.add(materialText);
                        Collections.sort(listMaterialNAme);
                    }
                    linearLayout.removeViewAt(childCount - 1);
                    counter--;
                }
                setVisibilities();
            }
        });
    }
    private void MaterialNameList(){
        ArrayAdapter<String> MaterialNameListAdapter = new ArrayAdapter<String>(
                requireContext(),android.R.layout.simple_dropdown_item_1line,  listMaterialNAme );
        MaterialNameListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MaterialACTV.setAdapter(MaterialNameListAdapter);
        TextViewMaterialName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilities();
                if(MaterialNameLinearLayout.getVisibility() != View.VISIBLE) {
                    MaterialNameLinearLayout.setVisibility(View.VISIBLE);
                    MaterialACTV.setEnabled(true);
                    MaterialACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(!TextViewMaterialName.getText().toString().equals("")) {
                                listMaterialNAme.add(TextViewMaterialName.getText().toString());
                                listMaterialNAme.remove(MaterialACTV.getText().toString());
                                TextViewMaterialName.setText(MaterialACTV.getText().toString());
                            }else {
                                TextViewMaterialName.setText(MaterialACTV.getText().toString());
                                listMaterialNAme.remove(MaterialACTV.getText().toString());
                            }
                                MaterialNameLinearLayout.setVisibility(View.GONE);
                                MaterialACTV.setText("");
                                Collections.sort(listMaterialNAme);
                                ArrayAdapter<String> MaterialNameListAdapter = new ArrayAdapter<String>(
                                        requireContext(), android.R.layout.simple_dropdown_item_1line, listMaterialNAme);
                                MaterialNameListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                MaterialACTV.setAdapter(MaterialNameListAdapter);
                        }
                    });
                }else{
                    MaterialNameLinearLayout.setVisibility(View.GONE);
                }
            }
        });
  }
    private void setVisibilities(){
        if (linerLayout1.getVisibility() == View.VISIBLE) {
            linerLayout1.setVisibility(View.GONE);
        }
        if(MaterialNameLinearLayout.getVisibility() == View.VISIBLE) {
            MaterialNameLinearLayout.setVisibility(View.GONE);
        }
    }
    private  void projectList(){
        LinearLayoutManager newLinerLayoutManager = new GridLayoutManager(requireContext(), 3);
        projestNameRv.setLayoutManager(newLinerLayoutManager);
        projectListHint();
        drop_down.setOnClickListener(v -> {
                ItemAddRemove();
        });
        const1.setOnClickListener(v -> {
                ItemAddRemove();
        });
        projestNameRv.setOnClickListener(v -> {
                ItemAddRemove();
        });
        projestNameRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                const1.requestFocus();
            }
        });
    }
    private void projectListHint(){
        if(rlist.isEmpty()){
            projectListHint.setVisibility(View.VISIBLE);
        }else if( projectListHint.getVisibility() == View.VISIBLE){
            projectListHint.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onItemClick(int position) {
         if(position>=0){
             String projectName = rlist.get(position);
             list.add(projectName);
             Collections.sort(list);
             a = new ArrayAdapter<>( requireContext(),android.R.layout.simple_dropdown_item_1line,list);
             autoCompleteTextView.setAdapter(a);
             a.notifyDataSetChanged();
             rlist.remove(position);
             adapterClass.notifyDataSetChanged();
         }
    }
    @Override
    public void onItemClick() {
            ItemAddRemove();
        }
    public void ItemAddRemove(){
        if(linerLayout1.getVisibility() != View.VISIBLE) {
            linerLayout1.setVisibility(View.VISIBLE);
            autoCompleteTextView.setEnabled(true);
            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = autoCompleteTextView.getText().toString();
                    if (!rlist.contains(name)) {
                        rlist.add(name);
                        projectListHint();
                        list.remove(position);
                    }
                    a = new ArrayAdapter<>( requireContext(),android.R.layout.simple_dropdown_item_1line,list);
                    autoCompleteTextView.setAdapter(a);
                    a.notifyDataSetChanged();
                    adapterClass.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                    autoCompleteTextView.setEnabled(false);
                    linerLayout1.setVisibility(View.GONE);
                }
            });
        }else{
            linerLayout1.setVisibility(View.GONE);
        }
        projectListHint();

    }
}