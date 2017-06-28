package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.adapter.PaletteGridAdapter;


public class PaletteActivity2 extends AppCompatActivity {

    private RecyclerView mRvPalette;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette2);
        mRvPalette = (RecyclerView) findViewById(R.id.rvPalette);
        mRvPalette.setLayoutManager(new GridLayoutManager(this, 2));
        mRvPalette.setAdapter(new PaletteGridAdapter());
    }

}
