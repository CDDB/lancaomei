package net.lancaomei;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class EditorActivity extends Activity{
	private Context mContext = null;
	private Storage storage = null;
	private ContactDetail detail = null;
	
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	
	public static final String cameraStorePath = "lancaomei/camera";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_editer);
		mContext = this;
		
		storage = new Storage(mContext);
		detail = new ContactDetail();
		
		Bundle bundle = this.getIntent().getExtras();
		if (bundle.getBoolean("open_camera")) {
			// create Intent to take a picture and return control to the calling application
		    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		    File imageFile = getOutputMediaFile(MEDIA_TYPE_IMAGE); // create a file to save the image
		    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile)); // set the image file name
		    
		    detail.setImageTagPath(imageFile.getPath());
		    Log.i("image saver", detail.getImageTagPath());

		    // start the image capture Intent
		    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);    
		}
		
		
		EditText nameEditor = (EditText)this.findViewById(R.id.edit_name);
		nameEditor.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable edit) {
				detail.setName(edit.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {			}
		});
		
		EditText tagEditor = (EditText)this.findViewById(R.id.edit_tag);
		tagEditor.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable edit) {
				detail.setTag(edit.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {			}
		});
		
		EditText descEditor = (EditText)this.findViewById(R.id.edit_desc);
		descEditor.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable edit) {
				// TODO Auto-generated method stub
				detail.setDesc(edit.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {			}
		});
		
		EditText phoneEditor = (EditText)this.findViewById(R.id.edit_phone);
		phoneEditor.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable edit) {
				// TODO Auto-generated method stub
				detail.setPhone(edit.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {			}
		});

		Button saver = (Button)this.findViewById(R.id.edit_save);
		saver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				storage.save(detail);
			}
		});	
	}
	
	
	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), cameraStorePath);
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.e(cameraStorePath, "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            ImageView imageTag = (ImageView)this.findViewById(R.id.image_tag);
	            File imageFile = new File(detail.getImageTagPath());
				imageTag.setImageURI(Uri.fromFile(imageFile));

	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        }
	    }

	    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the video capture
	        } else {
	            // Video capture failed, advise user
	        }
	    }
	}

}
