package com.gifterhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendHistoryFragment extends Fragment {
    ArrayList<Item> history;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View mainView = inflater.inflate(R.layout.friends_history,container,false);

        listView = (ListView) mainView.findViewById(R.id.friend_history);
        //ListView itemView = (ListView) mainView.findViewById(R.id.UserHistoryList);
        history = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<Item>();
        User user = new User();
        String id = getActivity().getIntent().getStringExtra("friendId");
        ParseQuery<User> query = new ParseQuery<User>(User.class);
        query.getInBackground(id , new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                if( user == null) {
                    Log.e("GifterHelper", "id not found");
                }
                else {
                        List<Item> friendItems = user.getHistory();
                        Log.d("GifterHelper", "Friend history size = " + friendItems.size());
                        if(friendItems.size() == 0 || friendItems == null){
                            friendItems = new ArrayList<Item>();
                            Item temp = new Item();
                            temp.setName("No History");
                            friendItems.add(temp);
                        }
                        if(listView == null){
                            Log.e("GifterHelper", "Null listView");
                        }
                        history.addAll(friendItems);
                        FriendHistoryAdapter friendlyAdapter = new FriendHistoryAdapter(getActivity().getBaseContext(),user.getHistory());
                        listView.setAdapter(friendlyAdapter);
                    }
                }

        });
        return mainView;
    }
}