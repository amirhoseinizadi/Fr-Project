package com.example.framirproject;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class plf extends Fragment {

    String[] pdfFiles = {"file1.pdf", "file2.pdf", "file3.pdf"};

    OnPDFSelectedListener listener;

    public interface OnPDFSelectedListener {
        void onPDFSelected(String pdfFile);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPDFSelectedListener) {
            listener = (OnPDFSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPDFSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fpl, container, false);

        ListView listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                pdfFiles
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemView, int position, long id) {
                listener.onPDFSelected(pdfFiles[position]);
            }
        });

        return view;
    }
}
