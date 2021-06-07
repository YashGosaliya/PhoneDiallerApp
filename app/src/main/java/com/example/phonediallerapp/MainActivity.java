package com.example.phonediallerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button zero = findViewById(R.id.zero);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button asterisk = findViewById(R.id.asterisk);
        Button hash = findViewById(R.id.hash);

        numberTextView = findViewById(R.id.numberText);
        TextView clearButton = findViewById(R.id.clear);
        ImageButton callButton = findViewById(R.id.call);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        asterisk.setOnClickListener(this);
        hash.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        callButton.setOnClickListener(this);

        requestPermissionFromUser();
    }

    private void requestPermissionFromUser() {
        String permission = Manifest.permission.CALL_PHONE;
        String[] permissions = new String[]{permission};
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 11);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeToast("Call permission granted");
            } else makeToast("Call permission denied");
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View view) {
//        if else
//        switch
        String number = numberTextView.getText().toString();

        switch (view.getId()) {
            case R.id.zero:
                numberTextView.append("0");
                break;
            case R.id.one:
                numberTextView.append("1");
                break;
            case R.id.two:
                numberTextView.append("2");
                break;
            case R.id.three:
                numberTextView.append("3");
                break;
            case R.id.four:
                numberTextView.append("4");
                break;
            case R.id.five:
                numberTextView.append("5");
                break;
            case R.id.six:
                numberTextView.append("6");
                break;
            case R.id.seven:
                numberTextView.append("7");
                break;
            case R.id.eight:
                numberTextView.append("8");
                break;
            case R.id.nine:
                numberTextView.append("9");
                break;
            case R.id.asterisk:
                numberTextView.append("*");
                break;
            case R.id.hash:
                numberTextView.append("#");
                break;
            case R.id.call:
                call(number);
                break;
            case R.id.clear:
                int len = number.length();
                if (len > 0) {
                    String sub = number.substring(0, len - 1);
                    numberTextView.setText(sub);
                }
                break;
        }
    }


    private void call(String number) {
//        Implicit intent
//        tel:123456789

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);

//        if you are passing a USSD code, having a '#' then,
//        you need to replace with it's encoded value

        if (number.contains("#"))
            number = number.replaceAll("#", Uri.encode("#"));

        Uri uri = Uri.parse("tel:" + number);
        intent.setData(uri);
        startActivity(intent);
    }

    void makeToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}