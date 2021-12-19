package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
Button btn;
 TextView txt;
 EditText ed1,ed2;
    String email;

    RequestQueue queue;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue= Volley.newRequestQueue(LoginActivity.this);
        sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);
        String aa=sharedPreferences.getString("key","0");
        //Toast.makeText(getApplicationContext(),""+aa,Toast.LENGTH_SHORT).show();
      if (aa!="0")
        {
            Intent in=new Intent(getApplicationContext(),ServiceActivity.class);

            startActivity(in);
            finish();
        }
        btn=findViewById(R.id.button);
        txt=findViewById(R.id.editText11);
        ed1=findViewById(R.id.editText2);
        ed2=findViewById(R.id.editText3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getApplicationContext(),"Login Sucessfully",Toast.LENGTH_LONG).show();
                Intent in=new Intent(getApplicationContext(),ServiceActivity.class);
                startActivity(in);*/

                email=ed1.getText().toString();
                String pass=ed2.getText().toString();
                //Toast.makeText(getApplicationContext(),""+email+pass,Toast.LENGTH_SHORT).show();

                String url="http://project.pchauhan.xyz/WebService1.asmx/login?email="+email+"&password="+pass;
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String name=jsonObject.getString("message");
                            //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();

                            if (name.equals("Login SucessFully"))
                            {
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("key",email);
                                editor.apply();
                                Intent in=new Intent(getApplicationContext(),ServiceActivity.class);

                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_LONG).show();
                            }
                           // Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);

            }

        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),RegisterationActivity.class);
                startActivity(in);
            }
        });
    }
}
