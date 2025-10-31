package com.example.framirproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements plf.OnPDFSelectedListener {

    boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = findViewById(R.id.list_container) != null;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (isLandscape) {
            ft.replace(R.id.list_container, new plf());
        } else {
            ft.replace(R.id.fragment_container, new plf());
        }

        ft.commit();
    }

    @Override
    public void onPDFSelected(String pdfFile) {
        pvf viewerFragment = pvf.newInstance(pdfFile);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (isLandscape) {
            ft.replace(R.id.pdf_container, viewerFragment);
        } else {
            ft.replace(R.id.fragment_container, viewerFragment);
            ft.addToBackStack(null);

            Button btnBack = findViewById(R.id.btnBack);
            if (btnBack != null) {
                btnBack.setVisibility(View.VISIBLE);
                btnBack.setOnClickListener(v -> {
                    getSupportFragmentManager().popBackStack();
                    btnBack.setVisibility(View.GONE);
                });
            }
        }

        ft.commit();
    }
}
