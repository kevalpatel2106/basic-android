package com.baseapplication.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;

/**
 * Created by Keval on 31-May-17.
 * Base class for loading {@link android.support.v7.widget.RecyclerView.Adapter} that handles page
 * complete callbacks. Use this class instead of {@link RecyclerView.Adapter} through out the application.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 * @see PageCompleteListener
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final Collection mCollection;
    private PageCompleteListener mListener;

    /**
     * Public constructor.
     *
     * @param collection Collection list of the data to bind.
     * @param listener   {@link PageCompleteListener} to get notified when all items bound.
     */
    public BaseRecyclerViewAdapter(@NonNull Collection collection,
                                   @Nullable PageCompleteListener listener) {
        mCollection = collection;
        mListener = listener;
    }

    /**
     * Public constructor.
     *
     * @param collection Collection list of the data to bind.
     */
    public BaseRecyclerViewAdapter(@NonNull Collection collection) {
        mCollection = collection;
        mListener = null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        //notify on pag complete
        if (mListener != null && position == mCollection.size() - 1) mListener.onPageComplete();

        bindView(holder, position);
    }

    /**
     * Here you should bind the view holder with your view and data.
     *
     * @param holder   {@link android.support.v7.widget.RecyclerView.ViewHolder}
     * @param position position of the row.
     */
    public abstract void bindView(VH holder, int position);

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    /**
     * Listener to get notify when the list ends.
     */
    public interface PageCompleteListener {

        /**
         * Callback to call when whole list is displayed.
         */
        void onPageComplete();
    }
}
