package com.dunno.aenen.forgottobuy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazon.euclid.widget.ZTextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yaroslav on 02.06.2020.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private final List<ListDTO> mLists;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, List<ListDTO> list) {
        mInflater = LayoutInflater.from(context);
        this.mLists = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_container, parent, false);
        return new ListViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {
        String mCurrent = mLists.get(position).Title;
        holder.mListView.setText(mCurrent == null || mCurrent.isEmpty()?"nonono":mCurrent);
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ZTextView mListView;
        final ListAdapter mAdapter;

        public ListViewHolder(View itemView, ListAdapter adapter) {
            super(itemView);

            this.mAdapter = adapter;
            mListView = (ZTextView) itemView.findViewById(R.id.list_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            ListDTO element = mLists.get(mPosition);
            element.Title = "Clicked! " + element.Title;

            // Change the word in the mWordList.
            mLists.set(mPosition, element);

            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }
}
