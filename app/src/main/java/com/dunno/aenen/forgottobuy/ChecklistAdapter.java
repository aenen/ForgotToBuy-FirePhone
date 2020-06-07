package com.dunno.aenen.forgottobuy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazon.euclid.widget.ZTextView;

import java.util.List;

/**
 * Created by Yaroslav on 02.06.2020.
 */
public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>{

    private final List<ChecklistDTO> mChecklists;
    private LayoutInflater mInflater;
    private Context mContext;

    public ChecklistAdapter(Context context, List<ChecklistDTO> checklist) {
        mInflater = LayoutInflater.from(context);
        mChecklists = checklist;
        mContext = context;
    }

    @Override
    public ChecklistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.checklist_container, parent, false);
        return new ChecklistViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(ChecklistAdapter.ChecklistViewHolder holder, int position) {
        String mCurrent = mChecklists.get(position).Title;
        holder.mChecklistView.setText(mCurrent == null || mCurrent.isEmpty()?"nonono":mCurrent);
    }

    @Override
    public int getItemCount() {
        return mChecklists.size();
    }

    class ChecklistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ZTextView mChecklistView;
        final ChecklistAdapter mAdapter;

        public ChecklistViewHolder(View itemView, ChecklistAdapter adapter) {
            super(itemView);

            this.mAdapter = adapter;
            mChecklistView = (ZTextView) itemView.findViewById(R.id.checklist_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            ChecklistDTO element = mChecklists.get(mPosition);

            Intent intent = new Intent(mContext, ChecklistDetailActivity.class);
            intent.putExtra(MainActivity.EXTRA_IDCHECKLIST, element.IdList);
            mContext.startActivity(intent);
        }
    }
}
