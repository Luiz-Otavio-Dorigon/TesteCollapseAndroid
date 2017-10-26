package br.com.dorigon.testecollapseexpand;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @author Luiz Otávio Dorigon <luiz@datajuri.com.br> on 25/10/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<String> mList;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_BOTTOM = 2;

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == getItemCount()) {
            return TYPE_HEADER;
        } else if (position == (getItemCount() - 1)) {
            return TYPE_BOTTOM;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, Boolean.FALSE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (viewType) {
            case TYPE_HEADER:
                params.setMargins(0, Utils.dpToPx(80), 0, 0);
                break;
            case TYPE_BOTTOM:
                params.setMargins(0, 0, 0, Utils.dpToPx(80));
                break;
        }

        view.setPadding(Utils.dpToPx(16), Utils.dpToPx(16), Utils.dpToPx(16), Utils.dpToPx(16));
        view.setLayoutParams(params);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTextViewLabel;

        private ViewHolder(View itemView) {
            super(itemView);

            mTextViewLabel = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(this.getClass().getName(), "Click position: " + getAdapterPosition());
        }

        private void bind(String label) {
            if (mTextViewLabel != null)
                mTextViewLabel.setText(label);
        }
    }
}
