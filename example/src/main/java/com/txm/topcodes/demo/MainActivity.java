package com.txm.topcodes.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.txm.topcodes.globaldialog.GlobalDialog;
import com.txm.topcodes.globaldialog.OnDialogClickListener;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new GlobalDialog.Builder().setContext(MainActivity.this).setForce(false).setDescription(MainActivity.this.getResources().getString(R.string.ErroAccoutHasLogined)).setStyle(GlobalDialog.Style.SingleAlter).build().show();
	}
}
