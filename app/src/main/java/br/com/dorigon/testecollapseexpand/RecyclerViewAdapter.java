package br.com.dorigon.testecollapseexpand;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
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

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == mList.size())
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER){
            view = new Space(mContext);
            view.setMinimumHeight((int) (80 * Resources.getSystem().getDisplayMetrics().density));
            return new ViewHolder(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, Boolean.FALSE);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position > 0 && position < mList.size())
            holder.bind(mList.get(position-1));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 0;
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
