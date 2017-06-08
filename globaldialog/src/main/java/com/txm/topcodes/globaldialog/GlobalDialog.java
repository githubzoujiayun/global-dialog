package com.txm.topcodes.globaldialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * GlobalDialog
 *
 * @author TXM  2016/11/30.
 *         Util for dialog.
 */
public class GlobalDialog {
	public enum Style {
		SingleAlter,
		DoubleAlert
	}

	private WeakReference<Context> contextWeak;
	private String description;
	private Style style;
	private static Map<String, OnDialogClickListener> listenerMap = new HashMap<>();
	private String key;
	private Boolean isForce;

	public GlobalDialog(Builder builder) {
		this.contextWeak = new WeakReference<Context>(builder.context);
		this.description = builder.description;
		this.isForce = builder.isForce;
		this.style = builder.style;
		//Solve thread-safe problems.
		key = UUID.randomUUID().toString();
		listenerMap.put(key, builder.onDialogClickListener);
	}

	public void show() {
		if (contextWeak.get() != null && description != null) {
			if (style == Style.SingleAlter) {
				contextWeak.get().startActivity(new Intent(contextWeak.get(), SingleAlterDialog.class).putExtra("description", description).putExtra("key", key).putExtra("isForce", isForce));
			}
			if (style == Style.DoubleAlert) {
				contextWeak.get().startActivity(new Intent(contextWeak.get(), DoubleAlterDialog.class).putExtra("description", description).putExtra("key", key).putExtra("isForce", isForce));
			}
		} else {
			//This is a runtimeException.
			if (contextWeak.get() == null) {
				throw new UnsupportedOperationException("The reference of dialog has not exist,please execute setContext() to solve it.");
			}
			if (description == null) {
				throw new UnsupportedOperationException("The description has not exist,please execute setDescription() to solve it.");
			}
		}
	}

	public static class Builder {
		private Context context;
		private String description;
		private Style style;
		//default isn't force.
		private Boolean isForce;

		private OnDialogClickListener onDialogClickListener;

		public Builder setStyle(Style style) {
			if (style != null) {
				this.style = style;
			}
			return this;
		}

		public Builder setContext(Context context) {
			if (context != null) {
				this.context = context;
			}
			return this;
		}

		public Builder setDescription(String description) {
			if (description != null) {
				this.description = description;
			}
			return this;
		}

		public Builder setForce(Boolean b) {
			this.isForce = b;
			return this;
		}

		public Builder setDialogClickListener(OnDialogClickListener onDialogClickListener) {
			if (onDialogClickListener != null) {
				this.onDialogClickListener = onDialogClickListener;
			}
			return this;
		}

		public GlobalDialog build() {
			return new GlobalDialog(this);
		}
	}

	public static class SingleAlterDialog extends Activity {
		Boolean mIsForce;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dialog_single_alter);
			mIsForce = getIntent().getBooleanExtra("isForce", false);
			TextView message = (TextView) findViewById(R.id.tv_description);
			setFinishOnTouchOutside(false);
			getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			String description = getIntent().getStringExtra("description");
			message.setText(description);
			Button button = (Button) findViewById(R.id.bt_ok);
			final String key = getIntent().getStringExtra("key");
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listenerMap.get(key).onSure();
					//Release reference.
					listenerMap.remove(key);
					//Close Dialog.
					finish();
				}
			});
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if (!mIsForce) {
					finish();
				}
			}
			return false;
		}
	}

	public static class DoubleAlterDialog extends Activity {
		private Boolean mIsForce;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dialog_double_alter);
			mIsForce = getIntent().getBooleanExtra("isForce", false);
			TextView message = (TextView) findViewById(R.id.tv_description);
			setFinishOnTouchOutside(false);
			getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			String description = getIntent().getStringExtra("description");
			message.setText(description);
			Button b1 = (Button) findViewById(R.id.bt_ok);
			Button b2 = (Button) findViewById(R.id.btn_cancel);
			final String key = getIntent().getStringExtra("key");
			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listenerMap.get(key).onSure();
					//Release reference.
					listenerMap.remove(key);
					//Close Dialog.
					finish();
				}
			});
			b2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listenerMap.get(key).onCancel();
					//Release reference.
					listenerMap.remove(key);
					//Close Dialog.
					finish();
				}
			});
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if (!mIsForce) {
					finish();
				}
			}
			return false;
		}
	}


}
