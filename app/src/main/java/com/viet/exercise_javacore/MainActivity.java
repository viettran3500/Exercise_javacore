package com.viet.exercise_javacore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String PHONE_PATTERN = "(0?\\d{9})";
    private static final String YEAR_PATTERN = "([1-2]?\\d{3})";
    EditText editTextName, editTextYear, editTextNum, editTextSearch;
    CheckBox checkBoxCD, checkBoxDH;
    Spinner spinner;
    RadioButton radioButtonName, radioButtonYear, radioButtonNum, radioButtonSpecialized;
    Button buttonAdd, buttonEdit, buttonSearch;
    ListView listView;
    StudentAdapter adapter;
    List<Student> studentArrayList = new ArrayList<>();
    List<Student> studentArrayList2 = new ArrayList<>();
    List<Student> studentArrayList3 = new ArrayList<>();
    List<Student> studentArrayList4 = new ArrayList<>();
    String specialized;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        adapter = new StudentAdapter(getApplicationContext(),studentArrayList);
        listView.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        list.add("Cao đẳng");
        list.add("Đại học");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter1 = adapterView.getAdapter();
                specialized = (String) adapter1.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editTextName.setText(studentArrayList.get(i).getName());
                editTextYear.setText(studentArrayList.get(i).getYearOfBird() + "");
                editTextNum.setText(studentArrayList.get(i).getPhoneNumber());
                if(studentArrayList.get(i).getSpecialized().equals("Cao đẳng"))
                    spinner.setSelection(0);
                else
                    spinner.setSelection(1);
                index = i;
                buttonEdit.setEnabled(true);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteItem(i);
                return false;
            }
        });
        checkBoxCD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeCheckbox(b,"Cao đẳng");
            }
        });
        checkBoxDH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeCheckbox(b,"Đại học");
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextSearch.getText().toString().isEmpty()){
                    if(radioButtonName.isChecked()){
                        search("name");
                    }else if(radioButtonYear.isChecked()){
                        search("year");
                    }else if(radioButtonNum.isChecked()){
                        search("num");
                    }else if(radioButtonSpecialized.isChecked()){
                        search("s");
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"must not be left blank", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean check(){
        if(editTextNum.getText().toString().isEmpty() || editTextName.getText().toString().isEmpty()
                ||editTextYear.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"must not be left blank", Toast.LENGTH_LONG).show();
            return false;
        }
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(editTextNum.getText().toString());
        if(!matcher.matches()){
            Toast.makeText(getApplicationContext(),"Wrong phone number format", Toast.LENGTH_LONG).show();
            return false;
        }

        Pattern pattern2 = Pattern.compile(YEAR_PATTERN);
        Matcher matcher2 = pattern2.matcher(editTextYear.getText().toString());
        if(!matcher2.matches()){
            Toast.makeText(getApplicationContext(),"Wrong year of bird format", Toast.LENGTH_LONG).show();
            return false;
        }

        for (Student student : studentArrayList){
            if(student.getPhoneNumber().equals(editTextNum.getText().toString())){
                Toast.makeText(getApplicationContext(),"Phone number already exists", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    private void search(String s){
        if(s.equals("name")){
            studentArrayList.addAll(studentArrayList4);
            studentArrayList4.clear();
            for(Student student : studentArrayList){
                if(!student.getName().toUpperCase().contains(editTextSearch.getText().toString().toUpperCase())){
                    studentArrayList4.add(student);
                }
            }
            studentArrayList.removeAll(studentArrayList4);
            adapter.notifyDataSetChanged();
        }
        if(s.equals("year")){
            studentArrayList.addAll(studentArrayList4);
            studentArrayList4.clear();
            for(Student student : studentArrayList){
                if(!String.valueOf(student.getYearOfBird()).toUpperCase().contains(editTextSearch.getText().toString().toUpperCase())){
                    studentArrayList4.add(student);
                }
            }
            studentArrayList.removeAll(studentArrayList4);
            adapter.notifyDataSetChanged();
        }
        if(s.equals("num")){
            studentArrayList.addAll(studentArrayList4);
            studentArrayList4.clear();
            for(Student student : studentArrayList){
                if(!student.getPhoneNumber().toUpperCase().contains(editTextSearch.getText().toString().toUpperCase())){
                    studentArrayList4.add(student);
                }
            }
            studentArrayList.removeAll(studentArrayList4);
            adapter.notifyDataSetChanged();
        }
        if(s.equals("s")){
            studentArrayList.addAll(studentArrayList4);
            studentArrayList4.clear();
            for(Student student : studentArrayList){
                if(!student.getSpecialized().toUpperCase().contains(editTextSearch.getText().toString().toUpperCase())){
                    studentArrayList4.add(student);
                }
            }
            studentArrayList.removeAll(studentArrayList4);
            adapter.notifyDataSetChanged();
        }
    }

    private void changeCheckbox(boolean b, String specialized){
        if(specialized.equals("Đại học")){
            if(b == true){
                studentArrayList.addAll(studentArrayList2);
                adapter.notifyDataSetChanged();
                studentArrayList2.clear();
            }else {
                for (Student student : studentArrayList) {
                    if(student.getSpecialized().equals("Đại học")){
                        studentArrayList2.add(student);
                    }
                }
                studentArrayList.removeAll(studentArrayList2);
                adapter.notifyDataSetChanged();
            }
        }
        else if(specialized.equals("Cao đẳng")){
            if(b == true){
                studentArrayList.addAll(studentArrayList3);
                adapter.notifyDataSetChanged();
                studentArrayList3.clear();
            }else {
                for (Student student : studentArrayList) {
                    if(student.getSpecialized().equals("Cao đẳng")){
                        studentArrayList3.add(student);
                    }
                }
                studentArrayList.removeAll(studentArrayList3);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void deleteItem(final int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Bạn có muốn xóa? ");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                studentArrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void add() {
        if(check()){
            String name = editTextName.getText().toString();
            int year = Integer.parseInt(editTextYear.getText().toString());
            String num = editTextNum.getText().toString();
            String specialized1 = specialized;
            studentArrayList.add(new Student(name, year, num, specialized1));
            Collections.sort(studentArrayList);
            adapter.notifyDataSetChanged();
        }
    }

    private void edit() {
        if(check()){
            String name = editTextName.getText().toString();
            int year = Integer.parseInt(editTextYear.getText().toString());
            String num = editTextNum.getText().toString();
            String specialized1 = specialized;
            studentArrayList.set(index, new Student(name, year, num, specialized1));
            Collections.sort(studentArrayList);
            adapter.notifyDataSetChanged();
        }
    }

    private void anhXa() {
        editTextName = findViewById(R.id.edittextName);
        editTextYear = findViewById(R.id.edittextYear);
        editTextNum = findViewById(R.id.edittextPhoneNumber);
        spinner = findViewById(R.id.spinnerSpecialized);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonEdit = findViewById(R.id.buttonEdit);
        listView = findViewById(R.id.listview);
        checkBoxCD = findViewById(R.id.checkboxCD);
        checkBoxDH = findViewById(R.id.checkboxDH);
        editTextSearch = findViewById(R.id.edittextSearch);
        radioButtonName = findViewById(R.id.radioName);
        radioButtonYear = findViewById(R.id.radioYear);
        radioButtonNum = findViewById(R.id.radioNum);
        radioButtonSpecialized = findViewById(R.id.radioSpecialized);
        buttonSearch = findViewById(R.id.buttonSearch);
    }
}
