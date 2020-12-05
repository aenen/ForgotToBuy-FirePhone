package com.dunno.aenen.forgottobuy.checklist.details;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazon.euclid.widget.ZCheckBox;
import com.dunno.aenen.forgottobuy.R;
import com.dunno.aenen.forgottobuy.database.ChecklistItemDTO;
import com.dunno.aenen.forgottobuy.database.ForgotToBuyDbHelper;

import java.util.List;

/**
 * Created by Yaroslav on 07.06.2020.
 */
public class ChecklistItemAdapter  extends RecyclerView.Adapter<ChecklistItemAdapter.ChecklistItemViewHolder>{

    private final List<ChecklistItemDTO> mChecklists;
    private LayoutInflater mInflater;
    private ForgotToBuyDbHelper mDbHelper;

    public ChecklistItemAdapter(Context context, List<ChecklistItemDTO> checklist, ForgotToBuyDbHelper dbHelper) {
        mInflater = LayoutInflater.from(context);
        mChecklists = checklist;
        mDbHelper = dbHelper;
    }

    @Override
    public ChecklistItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.checklist_item_container, parent, false);
        return new ChecklistItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ChecklistItemAdapter.ChecklistItemViewHolder holder, int position) {
        ChecklistItemDTO mCurrent = mChecklists.get(position);

        holder.mChecklistItemView.setText(mCurrent == null || mCurrent.Name.isEmpty() ? "???" : mCurrent.Name);

        holder.mChecklistItemView.setChecked(false);
        holder.mChecklistItemView.setTextColor(Color.BLACK);

        if(mCurrent.IsChecked) {
            holder.mChecklistItemView.setChecked(true);
            holder.mChecklistItemView.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return mChecklists.size();
    }

    class ChecklistItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ZCheckBox mChecklistItemView;
        final ChecklistItemAdapter mAdapter;

        public ChecklistItemViewHolder(View itemView, ChecklistItemAdapter adapter) {
            super(itemView);
            Log.d("test", "clicked");

            this.mAdapter = adapter;
            mChecklistItemView = (ZCheckBox) itemView.findViewById(R.id.checklist_item);
            mChecklistItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            ChecklistItemDTO element = mChecklists.get(mPosition);

            element.IsChecked = ((ZCheckBox) view).isChecked();

            mDbHelper.setChecklistItemIsChecked(element.IdChecklistItem, element.IsChecked);

            if (element.IsChecked)
                mChecklistItemView.setTextColor(Color.GRAY);
            else
                mChecklistItemView.setTextColor(Color.BLACK);
        }
    }
}