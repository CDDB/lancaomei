package net.lancaomei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CardActivity extends Activity {
	Context mContext = null;
	ContactDetail mDetail = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		mContext = this;
		Bundle bundle = getIntent().getExtras();
		mDetail = new ContactDetail();
		mDetail.setName(bundle.getString("name"));
		mDetail.setPhone(bundle.getString("phone"));

		TextView nameView = (TextView) this.findViewById(R.id.card_name);
		nameView.setText(mDetail.getName());

		TextView phoneView = (TextView) this.findViewById(R.id.card_phone);
		phoneView.setText(mDetail.getPhone());

		phoneView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
						.parse("tel:" + mDetail.getPhone()));
				startActivity(dialIntent);
			}
		});
	}

}
