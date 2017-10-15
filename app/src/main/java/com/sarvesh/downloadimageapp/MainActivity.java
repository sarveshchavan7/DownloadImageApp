package com.sarvesh.downloadimageapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    String[] arrayLinks;
    UnDestructedFragment unDestructedFragment;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_items));
        listView.setAdapter(arrayAdapter);
        arrayLinks = getResources().getStringArray(R.array.links);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        //savedInstanceState is null mean activity is running for the first time
        if (savedInstanceState == null) {
            unDestructedFragment = new UnDestructedFragment();
            getSupportFragmentManager().beginTransaction().add(unDestructedFragment, "MyFragment").commit();
        } else {
            unDestructedFragment = (UnDestructedFragment) getSupportFragmentManager().findFragmentByTag("MyFragment");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(arrayLinks[i]);
            }
        });
    }

    public void DownloadImageButton(View view) {
        if(editText.getText()!=null){
            unDestructedFragment.beginTask(editText.getText().toString());
        }
    }

    public void updateProgress(int progress){
        progressBar.setProgress(progress);
    }
}
