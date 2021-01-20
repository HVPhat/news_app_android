package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password, c_password, phone;
    private Button btn_register;
    private ProgressBar loading;
    private static String URL_REGIST = "https://10.0.2.2:8000/user/create";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loading = findViewById(R.id.loading);
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        c_password = findViewById(R.id.editTextConFrimPassword);
        btn_register = findViewById(R.id.btnRegister);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check()) {
                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    loading.setVisibility(View.VISIBLE);
                    btn_register.setVisibility(View.GONE);
//                    final String name = this.name.getText().toString().trim();
//                    final String email = this.email.getText().toString().trim();
//                    final String phone = this.email.getText().toString().trim();
//                    final String password = this.password.getText().toString().trim();
                    StringRequest stringrequest = new StringRequest(Request.Method.POST, URL_REGIST,
                            new Response.Listener<String>() {
                                @Override

                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String success = jsonObject.getString("success");
                                        if (success.equals("1")) {
                                            Toast.makeText(RegisterActivity.this, "Register Success! ", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(RegisterActivity.this, "Register Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                                        loading.setVisibility(View.GONE);
                                        btn_register.setVisibility(View.VISIBLE);
                                    }
                                }

                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name.getText().toString());
                            params.put("email", email.getText().toString());
                            params.put("phone", phone.getText().toString());
                            params.put("password", password.getText().toString());
                            return super.getParams();
                        }
                    };

                    requestQueue.add(stringrequest);
                    Toast.makeText(getApplicationContext(), "Sussces!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), " Nhập Chua Đủ Thông Tin!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean Check (){
        if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||password.getText().toString().isEmpty()||phone.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Đủ Thông Tin!", Toast.LENGTH_LONG).show();
        }
        if(name.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Tên!", Toast.LENGTH_LONG).show();
        }
        if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Email!", Toast.LENGTH_LONG).show();
        }
        if(password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập PasWord!", Toast.LENGTH_LONG).show();
        }
        if(c_password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập ConfirmPassWprd!", Toast.LENGTH_LONG).show();
        }
        if(phone.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Số Điện Thoại!", Toast.LENGTH_LONG).show();
        }
        return true;
    }


}
