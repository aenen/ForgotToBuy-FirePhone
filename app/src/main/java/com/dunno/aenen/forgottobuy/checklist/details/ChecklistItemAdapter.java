package com.dunno.aenen.forgottobuy.checklist.details;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
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
    private Context mContext;
    private ForgotToBuyDbHelper mDbHelper;

    public ChecklistItemAdapter(Context context, List<ChecklistItemDTO> checklist, ForgotToBuyDbHelper dbHelper) {
        mInflater = LayoutInflater.from(context);
        mChecklists = checklist;
        mContext = context;
        mDbHelper = dbHelper;
    }

    @Override
    public ChecklistItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.checklist_item_container, parent, false);
        return new ChecklistItemViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(ChecklistItemAdapter.ChecklistItemViewHolder holder, int position) {
        ChecklistItemDTO mCurrent = mChecklists.get(position);

        holder.mChecklistItemView.setText(mCurrent == null || mCurrent.Name.isEmpty() ? "nonono" : mCurrent.Name);
        if(mCurrent.IsChecked) {
            holder.mChecklistItemView.setChecked(mCurrent.IsChecked);
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

            this.mAdapter = adapter;
            mChecklistItemView = (ZCheckBox) itemView.findViewById(R.id.checklist_item);
            mChecklistItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            ChecklistItemDTO element = mChecklists.get(mPosition);
            mDbHelper.setChecklistItemIsChecked(element.IdChecklistItem, ((ZCheckBox) view).isChecked());
            if (((ZCheckBox) view).isChecked())
                mChecklistItemView.setTextColor(Color.GRAY);
            else
                mChecklistItemView.setTextColor(Color.BLACK);
        }
    }
}