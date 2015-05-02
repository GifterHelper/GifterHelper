package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserHistoryFragment extends Fragment {
    ArrayList<Item> history;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        View view =  inflater.inflate(R.layout.user_history_fragment,container,false);

        listView = (ListView) view.findViewById(R.id.UserHistoryList);
        history = new ArrayList<Item>();
        User user = new User();
        String id = getActivity().getIntent().getStringExtra("id");
        ParseQuery<User> query = new ParseQuery<User>(User.class);
        query.getInBackground(id,new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                if(user == null){
                    Log.e("GifterHelper", "Could not get id");
                }else{
                    List<Item> userItems = user.getHistory();
                    history.addAll(userItems);
                    UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(getActivity().getBaseContext(), user.getHistory());
                    listView.setAdapter(userHistoryAdapter);
                }
            }
        });

        return view;
    }
}
