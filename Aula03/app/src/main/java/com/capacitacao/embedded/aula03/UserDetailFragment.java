/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rogerio on 05/09/16.
 */
public class UserDetailFragment extends Fragment implements UserFormFragment.OnUserSavedListener {

    private TextView mBirthDate, mName, mEmail, mPassword, mGender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_user, container, false);

        mName = (TextView) view.findViewById(R.id.full_name);
        mBirthDate = (TextView) view.findViewById(R.id.birthdate);
        mPassword = (TextView) view.findViewById(R.id.password);
        mGender = (TextView) view.findViewById(R.id.gender);
        mEmail = (TextView) view.findViewById(R.id.email);

        return view;
    }

    public static UserDetailFragment newInstance() {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onUserSaved(UserModel user) {
        Log.d("user", user.toString());

        if(user != null) {
            mName.setText(user.getName());
            mEmail.setText(user.getEmail());
            mPassword.setText(user.getPassword());
            mBirthDate.setText(user.getBirthdate());
            mGender.setText(user.getGender());
        }

    }
}
