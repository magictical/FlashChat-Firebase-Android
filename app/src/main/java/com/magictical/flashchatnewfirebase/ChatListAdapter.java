package com.magictical.flashchatnewfirebase;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

    //this will be called when DB has been changed
    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            mSnapshotList.add(dataSnapshot);
            //refresh the listView - check it if this gonna work or not
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    //ListAdapter에서 Chat message를 표시할때 무엇이 필요할지 생각해보자
    //message가 어디에있고 누구것인지 - ref, userName
    //Activity - 컨텍스트가 필요한걸까?
    public ChatListAdapter(Activity activity, DatabaseReference ref, String userName) {
        mActivity = activity;
        mDisplayName = userName;
        mDatabaseReference = ref.child("messages");
        //attach the childEventListener
        mDatabaseReference.addChildEventListener(mListener);
        mSnapshotList = new ArrayList<>();
    }

    static class VIewHolder {
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }


    @Override
    public int getCount() {

        Log.d("FlashChat", "Number of Chat is " + mSnapshotList.size());
        return mSnapshotList.size();
    }

    //change generic to InstaceMessage object
    @Override
    public InstanceMessage getItem(int position) {
        //get item by index number
        DataSnapshot snapshot = mSnapshotList.get(position);
        //return InstanceMessage which requested from getView
        return snapshot.getValue(InstanceMessage.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //convertView : listItem
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = inflater.inflate(R.layout.chat_msg_row, parent, false);
            final VIewHolder holder = new VIewHolder();
            holder.authorName = (TextView) convertView.findViewById(R.id.author);
            holder.body = (TextView) convertView.findViewById(R.id.chat_message);
            holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            convertView.setTag(holder);
        }

        final InstanceMessage message = getItem(position);
        final VIewHolder holder = (VIewHolder) convertView.getTag();

        String Author = message.getAuthor();
        holder.authorName.setText(Author);

        String msg = message.getMessage();
        holder.body.setText(msg);
        Log.d("FlashChat", "getview is here");

        return convertView;
    }

    //when the app leaves on the foreground
    public void cleanup() {
        mDatabaseReference.removeEventListener(mListener);
    }
}
