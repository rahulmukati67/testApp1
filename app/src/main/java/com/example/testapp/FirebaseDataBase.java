package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FirebaseDataBase extends AppCompatActivity {
    private TableLayout table;
    private FirebaseDatabase dataBase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_data_base);
        table = findViewById(R.id.table);
        dataBase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        getHeader();
        getTableContent();

    }

    private void getHeader(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot userSnapshot = dataSnapshot.child("User1");

                String  Header1 = userSnapshot.child("User1").child("Amount").getKey();
                String Header2= userSnapshot.child("User1").child("UserName").getKey();
                String Header3 = userSnapshot.child("User1").child("Priority").getKey();


                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(2, 2, 2, 2);

                int paddingInDp = 5;
                float scale = getResources().getDisplayMetrics().density;
                int paddingInPx = (int) (paddingInDp * scale + 0.5f);

                TableRow row = new TableRow(FirebaseDataBase.this);
                CheckBox checkBox = new CheckBox(FirebaseDataBase.this);
                checkBox.setBackgroundResource(R.drawable.cell_border);
                checkBox.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                row.addView(checkBox);

                TextView UserName = new TextView(FirebaseDataBase.this);
                UserName.setText(Header2);
                UserName.setTextSize(18);
                UserName.setBackgroundResource(R.drawable.cell_border_header);
                UserName.setTextColor(Color.WHITE);
                UserName.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                UserName.setTypeface(null, Typeface.BOLD);
                row.addView(UserName);

                TextView amountDueTextView = new TextView(FirebaseDataBase.this);
                amountDueTextView.setText(Header1);
                amountDueTextView.setTextSize(18);
                amountDueTextView.setBackgroundResource(R.drawable.cell_border_header);
                amountDueTextView.setTextColor(Color.WHITE);
                amountDueTextView.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                amountDueTextView.setTypeface(null, Typeface.BOLD);
                row.addView(amountDueTextView);

                TextView PriorityTextView = new TextView(FirebaseDataBase.this);
                PriorityTextView.setText(Header3);
                PriorityTextView.setTextSize(18);
                PriorityTextView.setBackgroundResource(R.drawable.cell_border_header);
                PriorityTextView.setTextColor(Color.WHITE);
                PriorityTextView.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                PriorityTextView.setTypeface(null, Typeface.BOLD);
                row.addView(PriorityTextView);

//                row.setBackgroundResource(R.drawable.row_border);
                table.addView(row);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read value: " + error.toException());
            }
        });
    }

    private void getTableContent(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TableRow.LayoutParams layoutParamsRow = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                layoutParamsRow.setMargins(10, 10, 10, 10);

                int paddingInDp = 5;
                float scale = getResources().getDisplayMetrics().density;
                int paddingInPx = (int) (paddingInDp * scale + 0.5f);

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    Integer amountDueInteger = userSnapshot.child("AmountDue").getValue(Integer.class);

                    if (amountDueInteger != null) {
                        int amountDue = amountDueInteger;
                        String userName = userSnapshot.child("UserName").getValue(String.class);
                        String priority = userSnapshot.child("Priority").getValue(String.class);

                        User user = new User(amountDue , userName , priority);
                        TableRow row = new TableRow(FirebaseDataBase.this);

                        TextView UserName = new TextView(FirebaseDataBase.this);
                        CheckBox checkBox = new CheckBox(FirebaseDataBase.this);
                        checkBox.setBackgroundResource(R.drawable.cell_border);
                        row.addView(checkBox);
                        UserName.setTextColor(Color.BLACK);
                        UserName.setText(user.getUserName());
                        UserName.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                        UserName.setBackgroundResource(R.drawable.cell_border);
                        UserName.setTextSize(16);
                        row.addView(UserName);


                        LinearLayout linearLayout = new LinearLayout(FirebaseDataBase.this);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                        TextView amountDueTextView = new TextView(FirebaseDataBase.this);
                        amountDueTextView.setText(String.valueOf(user.getAmountDue()));
                        amountDueTextView.setTextSize(16);
                        amountDueTextView.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);

                        TextView priorityTxtView = new TextView(FirebaseDataBase.this);
                        priorityTxtView.setText(String.valueOf(user.getPriority()));
                        priorityTxtView.setTextSize(16);
                        priorityTxtView.setTextColor(Color.BLACK);
                        priorityTxtView.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
                        priorityTxtView.setBackgroundResource(R.drawable.cell_border);
                        if(Objects.equals(priority, "high")){
                            priorityTxtView.setTextColor(Color.RED);
                        }else{
                            priorityTxtView.setTextColor(Color.GREEN);
                        }

                        ImageView imageView = new ImageView(FirebaseDataBase.this);
                        imageView.setImageResource(R.drawable.baseline_edit_square_24);
                        imageView.setForegroundGravity(View.FOCUS_LEFT);
                        linearLayout.setBackgroundResource(R.drawable.cell_border);
                        linearLayout.addView(amountDueTextView);
                        linearLayout.addView(imageView);
                        row.addView(linearLayout);
                        row.addView(priorityTxtView);
//                      row.setBackgroundResource(R.drawable.row_border);
                        table.addView(row);

                        checkBoxClickListener(imageView , amountDueTextView);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void checkBoxClickListener(ImageView edit, TextView amountDueTextView){
        EditText amountEdit = findViewById(R.id.amountEdit);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        CardView cardView = findViewById(R.id.editCard);
        final String[] amount = new String[1];
          edit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(cardView.getVisibility() != View.VISIBLE) {
                      cardView.setVisibility(View.VISIBLE);
                  }
                  btnUpdate.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          amount[0] = String.valueOf(amountEdit.getText());
                          amountDueTextView.setText(amount[0]);
                          amountEdit.setText("");
                          cardView.setVisibility(View.INVISIBLE);
                      }
                  });

              }
          });
    }
}