package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterationActivity extends AppCompatActivity {
Button btn;

EditText ed1,ed2,ed3,ed4,ed5;
RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        queue= Volley.newRequestQueue(RegisterationActivity.this);
        btn=findViewById(R.id.button2);
        ed1=findViewById(R.id.edittxt);
        ed2=findViewById(R.id.editTxt2);
        ed3=findViewById(R.id.editTxt3);
        ed4=findViewById(R.id.editTxt4);
        ed5=findViewById(R.id.editTxt5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Registeration Done",Toast.LENGTH_LONG).show();
                String name=ed1.getText().toString();
                String email=ed2.getText().toString();
                String password=ed3.getText().toString();
                String mobile=ed4.getText().toString();
                String address=ed5.getText().toString();
                String url="http://project.pchauhan.xyz/WebService1.asmx/Category?name="+name+"&email="+email+"&password="+password+"&mobile="+mobile+"&address="+address;
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String name=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_SHORT).show();
                            finish();
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
    }
}
