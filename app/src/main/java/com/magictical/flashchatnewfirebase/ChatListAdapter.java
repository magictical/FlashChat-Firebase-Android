package com.magictical.flashchatnewfirebase;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by magic on 2018-07-17.
 */

public class ChatListAdapter extends BaseAdapter {
    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mSnapshotList;

    //ListAdapter에서 Chat message를 표시할때 무엇이 필요할지 생각해보자
    //message가 어디에있고 누구것인지 - ref, userName
    //Activity - 컨텍스트가 필요한걸까?
    public ChatListAdapter(Activity activity, DatabaseReference ref, String userName) {
        mActivity = activity;
        mDatabaseReference = ref;
        mDisplayName = userName;
        mSnapshotList = new ArrayList<>();
    }

    static class VIewHolder {
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
