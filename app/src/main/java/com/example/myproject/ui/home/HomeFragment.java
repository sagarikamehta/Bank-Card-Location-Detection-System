package com.example.myproject.ui.home;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.ApplyActivity;
import com.example.myproject.MapsActivity;
import com.example.myproject.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import static com.karumi.dexter.Dexter.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    CardView cardView,cardView2;
    View root;

    SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences=getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
        // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        /*withActivity(getActivity())
                .withPermission(Manifest.permission.SEND_SMS)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {

                        cardView=root.findViewById(R.id.cardview5);
                        cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();

                                Intent in=new Intent(getContext(), ApplyActivity.class);
                                startActivity(in);





                            }
                        });
                        cardView2=root.findViewById(R.id.cardview6);
                        cardView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();


                                Intent in=new Intent(getContext(), MapsActivity.class);
                                startActivity(in);
                                //Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                PendingIntent pi= PendingIntent.getActivity(getContext(), 0, in, 0);

//Get the SmsManager instance and call the sendTextMessage method to send message
                                SmsManager sms= SmsManager.getDefault();
                                //9502064576
                                //8448035096 Arvind
                                sms.sendTextMessage("9502064576", null, "Send Card Location", pi,null);
                                Toast.makeText(getContext(), "Message Sent successfully!",
                                        Toast.LENGTH_LONG).show();




                            }
                        });
                    }

                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {*//* ... *//*}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {*//* ... *//*}
                }).check();*/

        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {

                cardView=root.findViewById(R.id.cardview5);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();

                        Intent in=new Intent(getContext(), ApplyActivity.class);
                        startActivity(in);





                    }
                });
                cardView2=root.findViewById(R.id.cardview6);
                cardView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();

                        String aa = sharedPreferences.getString("myfile", "0");
                        //Toast.makeText(getContext(),""+aa,Toast.LENGTH_SHORT).show();
                       if (aa!="0") {
                            Intent in = new Intent(getContext(), MapsActivity.class);

                            //Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                           PendingIntent pi = PendingIntent.getActivity(getContext(), 0, in, 0);

                            //Get the SmsManager instance and call the sendTextMessage method to send message
                             SmsManager sms = SmsManager.getDefault();
                            //9502064576
                            //8448035096 Arvind
                           sms.sendTextMessage("9006494238", null, "Send Card Location",pi, null);
                            //Toast.makeText(getContext(), "Message Sent successfully!",
                                  //  Toast.LENGTH_LONG).show();

                           startActivity(in);

                        }
                        else
                        {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                            View layoutView = getLayoutInflater().inflate(R.layout.custom_alert_dialog_box, null);
                            Button dialogButton = layoutView.findViewById(R.id.btnDialog);
                            TextView text=layoutView.findViewById(R.id.textView);
                            TextView text2=layoutView.findViewById(R.id.textView2);
                            text.setText("Error");
                            text2.setText("You Are not a Member firstly apply the for Chip Card");
                            dialogBuilder.setView(layoutView);
                            final AlertDialog alertDialog = dialogBuilder.create();
                            alertDialog.show();
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                   // getActivity().finish();
                                    Intent in=new Intent(getContext(),ApplyActivity.class);
                                    startActivity(in);
                                }
                            });

                        }
                    }

                });
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();




        return root;
    }
}