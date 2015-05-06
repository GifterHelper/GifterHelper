package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserFriendsFragment extends Fragment {
    ListView friends;
    User userLocal;
    String id;
    UserFriendAdapter userFriendAdapter;
    ArrayList<Friend> friendList;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        view =  inflater.inflate(R.layout.user_friends_fragment,container,false);
        friends = (ListView) view.findViewById(R.id.UserFriendsList);

        EditText friendSearch = (EditText) view.findViewById(R.id.UserSearchFriend);
        friendList = new ArrayList<Friend>();
        //We get the id of the user from the UserActivity
        this.id = ((UserActivity)getActivity()).getID();
        Log.d("GifterHelper", "id " + id);
        //Pull list of friends
        ParseQuery<User> userParseQuery = new ParseQuery<User>(User.class);
        userParseQuery.getInBackground(id,new GetCallback<User>() {
                    @Override
                    public void done(User user, ParseException e) {
                        userLocal = user;
                        List<User> friendUser = user.getFriends();
                        for(User friend : friendUser)
                        {
                            friendList.add(new Friend(friend));
                        }
                        userFriendAdapter = new UserFriendAdapter(getActivity().getBaseContext(), friendList);
                        friends.setAdapter(userFriendAdapter);
                    }
                });

        friendSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userFriendAdapter == null || s == null){
                    return;
                }else{
                    userFriendAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button addFriend = (Button) view.findViewById(R.id.AddFriend);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final EditText friendEmail = new EditText(view.getContext());
                builder.setTitle("Add Friend");
                builder.setView(friendEmail);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String email = friendEmail.getText().toString();
                        ParseQuery<User> parseQuery = new ParseQuery<User>(User.class);
                        parseQuery.whereContains("username", email);
                        parseQuery.findInBackground(new FindCallback<User>() {
                            @Override
                            public void done(List<User> list, ParseException e) {
                                if (list.size() > 1 || list.size() == 0) {
                                    Log.d("GifterHelper", "Size : " + list.size());
                                    Log.d("GifterHelper", "Did not add friend " + email);
                                } else {
                                    friendList.add(new Friend(list.get(0)));
                                    userLocal.addFriend(list.get(0));
                                    userLocal.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            Log.d("GifterHelper", "Added friend with email " + email);
                                        }
                                    });
                                }
                            }
                        });

                        //Send pusher to check to check to see if friend e-mail exists
                        //update current;
                        hide_keyboard_from(getActivity().getBaseContext(), getView());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                        Log.d("GifterHelper", "Canceling adding friend");
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
