package net.lancaomei;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
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
		mDetail.setDesc(bundle.getString("desc"));
		mDetail.setTag(bundle.getString("tag"));
		mDetail.setImageTagPath(bundle.getString("imagePath"));


		TextView nameView = (TextView) this.findViewById(R.id.card_name);
		nameView.setText(mDetail.getName());

		TextView phoneView = (TextView) this.findViewById(R.id.card_phone);
		phoneView.setText(mDetail.getPhone());
		
		TextView descView = (TextView) this.findViewById(R.id.card_desc);
		descView.setText(mDetail.getDesc());
		
		TextView tagView = (TextView) this.findViewById(R.id.card_tag);
		tagView.setText(mDetail.getTag());

		ImageView imageView = (ImageView) this.findViewById(R.id.card_img);
		File imageFile = new File(mDetail.getImageTagPath());
		if(imageFile.exists()) {
			BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
            imageView.setImageBitmap(myBitmap);
		}
		
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
