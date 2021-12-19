package com.example.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ApplyActivity extends AppCompatActivity {
Button btn;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    String count="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        btn=findViewById(R.id.button9);
        queue= Volley.newRequestQueue(ApplyActivity.this);
        sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid=sharedPreferences.getString("userid","0");
                String name=sharedPreferences.getString("name","0");
                String email=sharedPreferences.getString("email","0");
                String mobile=sharedPreferences.getString("mobile","0");
               String url="http://project.pchauhan.xyz/WebService1.asmx/Atmrequest?userid="+userid+"&name="+name+"&email="+email+"&mobile="+mobile+"&status=activate";
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String aa1=sharedPreferences.getString("myfile","0");
                        if (aa1 != "0"
                        ) {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ApplyActivity.this);
                            View layoutView = getLayoutInflater().inflate(R.layout.custom_alert_dialog_box, null);
                            Button dialogButton = layoutView.findViewById(R.id.btnDialog);
                            TextView text=layoutView.findViewById(R.id.textView);
                            TextView text2=layoutView.findViewById(R.id.textView2);
                            text.setText("Thanku");
                            dialogBuilder.setCancelable(false);
                            text2.setText("You Are Already MemBer");
                            dialogBuilder.setView(layoutView);
                          final AlertDialog alertDialog = dialogBuilder.create();
                            alertDialog.show();
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                    finish();
                                }
                            });

                           // Toast.makeText(getApplicationContext(), "Your Are Already MemBer", Toast.LENGTH_LONG).show();
                        }
                        else {
                            //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("myfile", count);
                            editor.apply();
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ApplyActivity.this);
                            View layoutView = getLayoutInflater().inflate(R.layout.custom_alert_dialog_box, null);
                            Button dialogButton = layoutView.findViewById(R.id.btnDialog);
                            TextView text=layoutView.findViewById(R.id.textView);
                            TextView text2=layoutView.findViewById(R.id.textView2);
                            text.setText("Thanku for choosing");
                            text2.setText("Your Request for Debit Card with Location Chip is Accepted.");
                            dialogBuilder.setView(layoutView);
                            final AlertDialog alertDialog = dialogBuilder.create();
                            alertDialog.show();
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                    finish();
                                }
                            });


                            //Toast.makeText(getApplicationContext(), "Your Request for Debit Card with Location Chip is Accepted.", Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);

                //Toast.makeText(getApplicationContext(),"Your Request for Debit Card with Location Chip is Accepted.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
