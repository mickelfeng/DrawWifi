package com.lfk.drawwifi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lfk.drawwifi.Utils.SpUtils;

import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SpUtils.contains(this, "name")) {
            View view = View.inflate(this, R.layout.input_time, null);
            EditText editText = (EditText) view.findViewById(R.id.input_name);
            materialDialog = new MaterialDialog(this)
                    .setTitle("设定名字")
                    .setContentView(view)
                    .setPositiveButton("确定", v -> {
                        if (!editText.getText().toString().equals("")) {
                            SpUtils.put(MainActivity.this, "name",
                                    editText.getText().toString());
                            Toast.makeText(MainActivity.this, "设定名字为"
                                    + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        materialDialog.dismiss();
                    });
            materialDialog.show();
        }
        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.button_sent).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button_sent:
                intent.setClass(MainActivity.this, ScanActivity.class);
                break;
            case R.id.button_save:
                intent.setClass(MainActivity.this, MessageActivity.class);
                break;
        }
        startActivity(intent);
    }
}
