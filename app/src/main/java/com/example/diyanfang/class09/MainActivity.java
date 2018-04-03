package com.example.diyanfang.class09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    EditText titleText;
    EditText nameText;
    EditText ageText;
    CheckBox isGraduatedCheckBox;
    TextView displayText;

    String title;
    String name;
    int age;
    boolean isGraduated;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference personRef = database.getReference("Person");
    private DatabaseReference peopleRef = database.getReference("Multiple people");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.title_text);
        nameText = findViewById(R.id.name_text);
        ageText = findViewById(R.id.age_text);
        isGraduatedCheckBox = findViewById(R.id.is_graduated_checkBox);
        displayText = findViewById(R.id.display_text);

        title = titleText.getText().toString();
        name = nameText.getText().toString();
        age = Integer.parseInt(ageText.getText().toString());
        isGraduated = Boolean.valueOf(isGraduatedCheckBox.getText().toString());

        peopleRef.setValue(new Person("Nicole",22,true));


        peopleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                Person p = dataSnapshot.getValue(Person.class);
                displayText.setText(p.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Error loading Firebase", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void setTitle(View view){
        titleText.setText(title);
    }

    public void addPerson(View view){
        peopleRef.setValue(new Person(name, age, isGraduated));
    }


//    public void set(View view){
//        personRef.setValue(new Person("Nicole",22,true));
//    }

}