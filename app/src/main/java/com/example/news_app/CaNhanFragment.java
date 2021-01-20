package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CaNhanFragment extends Fragment {
    ImageView avataImg;
    EditText txtEmail;
    EditText txtpassword;
    Button login;
    Button logout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String USERNAME_KEY = "name";
    String EMAIL_KEY = "email";
    int gan=0;
    public static final String URL="https://10.0.2.2:8000/api/user/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        txtEmail = (EditText) view.findViewById(R.id.editTextTextEmailAddress2);
        txtpassword = (EditText) view.findViewById(R.id.editTextTextPassword2);
        login = (Button) view.findViewById(R.id.btnDangNhap);
        logout = (Button) view.findViewById(R.id.btnLogout);
        sharedPreferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        txtEmail.setText(sharedPreferences.getString(USERNAME_KEY,""));
        if(sharedPreferences.getString(USERNAME_KEY,null)!=null)
        {
            txtpassword.setVisibility(View.INVISIBLE);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    new KetNoi().execute(URL);


            }
        });
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                CaNhanFragment caNhanFragment = new CaNhanFragment();
                txtEmail.setText("");
                txtpassword.setVisibility(View.VISIBLE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

            }
        });
        return view;
    }
    class KetNoi extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return Doc_noi_dung_url("http://10.0.2.2:8000/api/user");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("S", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArrayItems=jsonObject.getJSONArray("user");
                String name;
                String email;
                String password;
                for(int i=0;i<jsonArrayItems.length();i++) {
                    JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                    name = jsonObjectItem.getString("name");
                    email = jsonObjectItem.getString("email");
                    password = jsonObjectItem.getString("password");
                    if(email.equals(txtEmail.getText().toString()) && password.equals(txtpassword.getText().toString()))
                    {
                        gan=1;
                        Toast.makeText(getActivity(),"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                        txtEmail.setText(name);


                        Intent intent = new Intent(getActivity(),MainActivity.class);

                            editor = sharedPreferences.edit();
                            editor.putString(USERNAME_KEY, txtEmail.getText().toString());
                            editor.commit();

                        startActivity(intent);
                    }
                }
                if(gan==0){
                    Toast.makeText(getActivity(),"email hoac password khong dung!",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static String Doc_noi_dung_url(String urls) {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            //create object url
            java.net.URL url=new URL(urls);
            //create urlconnection
            URLConnection urlConnection=url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            bufferedReader.close();
            if (builder.length() == 0) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        Log.d("LOG_TAG", builder.toString());
        return builder.toString();
    }



}
