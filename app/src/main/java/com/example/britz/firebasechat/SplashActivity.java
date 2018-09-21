package com.example.britz.firebasechat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.britz.firebasechat.data.Pref;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.lang.ref.WeakReference;


public class SplashActivity extends AppCompatActivity {
    Pref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pref = Pref.getInstance(this);
        if(pref.getGoogleAdId() == null || pref.getUserName() == null) {
            new GoogleAppIdTask(this).execute();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
    private static class GoogleAppIdTask extends AsyncTask<Void, Void, String> {
        private WeakReference<SplashActivity> activityReference;

        GoogleAppIdTask(SplashActivity context) {
            activityReference = new WeakReference<>(context);
        }
        protected String doInBackground(final Void... params) {
            String adId = null;
            try {
                adId = AdvertisingIdClient.getAdvertisingIdInfo(activityReference.get().getApplicationContext()).getId();
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
            } catch (GooglePlayServicesRepairableException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException ex) {
                ex.printStackTrace();
            }
            return adId;
        }

        protected void onPostExecute(String adId) {
            final SplashActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            final Pref pref = Pref.getInstance(activityReference.get());
            final EditText userName = (EditText) activity.findViewById(R.id.user_name);
            Button submit = (Button) activity.findViewById(R.id.button_submit);
            if(adId != null) {
                pref.setGoogleAdId(adId);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(userName.getText().toString().length() > 0) {
                            pref.setUserName(userName.getText().toString());
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        } else {
                            Toast.makeText(activity, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
