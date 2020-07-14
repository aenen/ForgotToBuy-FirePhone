package com.dunno.aenen.forgottobuy.checklist.insertion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazon.euclid.widget.ZTextView;
import com.dunno.aenen.forgottobuy.R;
import com.dunno.aenen.forgottobuy.checklist.details.ChecklistDetailActivity;
import com.dunno.aenen.forgottobuy.checklist.list.MainActivity;
import com.dunno.aenen.forgottobuy.database.ChecklistDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 02.06.2020.
 */
public class ChecklistInsertionItemAdapter extends RecyclerView.Adapter<ChecklistInsertionItemAdapter.ChecklistInsertionItemViewHolder>{

    private final List<String> mChecklistItemNames;
    private LayoutInflater mInflater;
    private Context mContext;

    public ChecklistInsertionItemAdapter(Context context, List<String> checklistItemNames) {
        mInflater = LayoutInflater.from(context);
        mChecklistItemNames = checklistItemNames;
        mContext = context;
    }

    @Override
    public ChecklistInsertionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.checklist_insertion_item_container, parent, false);
        return new ChecklistInsertionItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ChecklistInsertionItemAdapter.ChecklistInsertionItemViewHolder holder, int position) {
        holder.mChecklistView.setText(position + 1 + ". " + mChecklistItemNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mChecklistItemNames.size();
    }

    class ChecklistInsertionItemViewHolder extends RecyclerView.ViewHolder {
        public final ZTextView mChecklistView;
        final ChecklistInsertionItemAdapter mAdapter;

        public ChecklistInsertionItemViewHolder(View itemView, ChecklistInsertionItemAdapter adapter) {
            super(itemView);

            this.mAdapter = adapter;
            mChecklistView = (ZTextView) itemView.findViewById(R.id.checklist_item_title);
        }
    }
}
