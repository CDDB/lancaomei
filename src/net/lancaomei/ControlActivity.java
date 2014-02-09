package net.lancaomei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 */
public class ControlActivity extends Activity {

	Context mContext = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;

		/** 获取通讯录信息 **/
		Button botton_contact = (Button) findViewById(R.id.Button_contact);
		botton_contact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, ContactsActivity.class);
				startActivity(intent);
			}
		});

		
		/** 获取浮萍信息 **/
		Button botton_lemna = (Button) findViewById(R.id.Button_lemna);
		botton_lemna.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, ContactsActivity.class);
				startActivity(intent);
			}
		});

		
		/** 打开相机用于拍照 **/
		Button button_camera = (Button) findViewById(R.id.Button_camera);
		button_camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, EditorActivity.class);
				Bundle bundle = new Bundle();
				bundle.putBoolean("open_camera", true);
				intent.putExtras(bundle); 
				startActivity(intent);
			}
		});

	}
}