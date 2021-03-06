package com.poepoemyintswe.weeklyenglish.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by poepoe on 21/3/15.
 * Code is taken from https://gist.github.com/xingrz/95e4aa31e386b3629bc5
 */

public abstract class RealmAdapter<E extends RealmObject, VH extends RealmAdapter.ViewHolder>
    extends BaseAdapter {

  private RealmResults<E> results;

  public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

  public abstract void onBindViewHolder(VH holder, int position);

  public void onViewRecycled(VH holder) {
  }

  public void setResults(RealmResults<E> results) {
    this.results = results;
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return results == null ? 0 : results.size();
  }

  @Override public E getItem(int position) {
    return results == null ? null : results.get(position);
  }

  @Override public long getItemId(int position) {
    return -1;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = onCreateViewHolder(parent, getItemViewType(position)).itemView;
    } else {
      onViewRecycled(getViewHolder(convertView));
    }

    onBindViewHolder(getViewHolder(convertView), position);

    return convertView;
  }

  @SuppressWarnings("unchecked") private VH getViewHolder(View view) {
    return (VH) view.getTag();
  }

  public static class ViewHolder {
    public final View itemView;

    public ViewHolder(View itemView) {
      this.itemView = itemView;
      this.itemView.setTag(this);
    }
  }
}