package sg.edu.rp.c346.id20023837.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDelete;
    EditText etAddNewActivity;
    EditText etDeleteActivity;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvActivity;

    ArrayList<String> alActivity;
    ArrayAdapter<String>aaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddDelete = findViewById(R.id.spinner);
        etAddNewActivity = findViewById(R.id.etAdd);
        etDeleteActivity = findViewById(R.id.etDelete);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvActivity = findViewById(R.id.lvActivity);

        alActivity = new ArrayList<>();

        aaActivity = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alActivity);
        lvActivity.setAdapter(aaActivity);

        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etAddNewActivity.setVisibility(View.VISIBLE);
                        etDeleteActivity.setVisibility(View.GONE);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        etAddNewActivity.setVisibility(View.GONE);
                        etDeleteActivity.setVisibility(View.VISIBLE);
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       btnClear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (alActivity.isEmpty()) {

            }
            else {
                alActivity.clear();
                aaActivity.notifyDataSetChanged();
            }
        }
    });

        btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (alActivity.isEmpty()) {
                Toast toast = Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (Integer.parseInt(etDeleteActivity.getText().toString()) >= alActivity.size()) {
                Toast toast = Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                int pos = Integer.parseInt(etDeleteActivity.getText().toString());
                alActivity.remove(pos);
                aaActivity.notifyDataSetChanged();
                etDeleteActivity.setText(null);
            }
        }
    });


        btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (etAddNewActivity.getText().toString().isEmpty()) {

            } else {
                String activity = etAddNewActivity.getText().toString();
                alActivity.add(activity);
                aaActivity.notifyDataSetChanged();
                etAddNewActivity.setText(null);
                }
            }
        });
    }
}