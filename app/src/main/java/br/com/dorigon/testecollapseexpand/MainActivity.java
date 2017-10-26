package br.com.dorigon.testecollapseexpand;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_names);
        mLayoutTop = findViewById(R.id.linearLayout_top);
        mLayoutBottom = findViewById(R.id.linearLayout_bottom);

        recyclerView.setHasFixedSize(Boolean.TRUE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter(this, getNames()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("dy", dy + "");
                collapse(dy);

            }
        });
    }

    private void collapse(int y) {
        if (y > 0) {
            expand(mLayoutBottom, y);
            collapse(mLayoutTop, y);
        } else if (y < 0) {
            collapse(mLayoutBottom, y * -1);
            expand(mLayoutTop, y * -1);
        }
    }

    private List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            names.add("My name is " + i);
        }
        return names;
    }

    public static int dpToPx(int px) {
        return (int) (px * Resources.getSystem().getDisplayMetrics().density);
    }

    public static void expand(final View v, int y) {
        final int targetHeight = dpToPx(80);
        if (v.getLayoutParams().height < targetHeight) {
            if (v.getLayoutParams().height + y > targetHeight) {
                v.getLayoutParams().height = targetHeight;
            } else {
                v.getLayoutParams().height += y;
            }
            v.requestLayout();
        }
    }

    public static void collapse(final View v, int y) {
        if (v.getLayoutParams().height > 0) {
            if (v.getLayoutParams().height - y < 0) {
                v.getLayoutParams().height = 0;
            } else {
                v.getLayoutParams().height -= y;
            }
            v.requestLayout();
        }
    }
}
