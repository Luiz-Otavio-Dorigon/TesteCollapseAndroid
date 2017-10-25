package br.com.dorigon.testecollapseexpand;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayoutTop, mLayoutBottom;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView_names);
        mLayoutTop = findViewById(R.id.linearLayout_top);
        mLayoutBottom = findViewById(R.id.linearLayout_bottom);

        mRecyclerView.setHasFixedSize(Boolean.TRUE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this, getNames()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                collapse(dy);

            }
        });
    }

    private void collapse(int y) {
        if (y > 0) {
            if (!mLayoutBottom.isShown()) {
                mLayoutTop.setVisibility(View.GONE);
                mLayoutBottom.setVisibility(View.VISIBLE);
                mRecyclerView.setPadding(0, 0, 0, dpToPx(80));
            }
        } else {
            if (!mLayoutTop.isShown()) {
                mLayoutTop.setVisibility(View.VISIBLE);
                mLayoutBottom.setVisibility(View.GONE);
                mRecyclerView.setPadding(0, dpToPx(80), 0, 0);
            }
        }
    }

    private List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            names.add("My name is " + i);
        }
        return names;
    }

    public int dpToPx(int px) {
        return (int) (px * Resources.getSystem().getDisplayMetrics().density);
    }
}
