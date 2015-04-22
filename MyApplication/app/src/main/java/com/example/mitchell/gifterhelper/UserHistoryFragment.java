package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserHistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        View view =  inflater.inflate(R.layout.user_history_fragment,container,false);

        ListView listView = (ListView) view.findViewById(R.id.UserHistoryList);
        ArrayList<Item> history = new ArrayList<Item>();
        history.add(new Item("Headphones"));
        history.add(new Item("Design Lamp"));
        history.add(new Item("Coffee Mug"));
        User user = new User();
        user.setHistory(history);
        UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(getActivity().getBaseContext(),user.getHistory());
        listView.setAdapter(userHistoryAdapter);

        return view;
    }
}
