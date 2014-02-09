package net.lancaomei;


import java.util.ArrayList;

import android.graphics.Bitmap;

public class ContactDetail {
	/** 联系人名称 **/
	private String mName = null;

	/** 联系人号码 **/
	private String mPhone = null;

	/** 联系人头像 **/
	private Bitmap mPhonto = null;
	
	private String imageTagPath;
	
	private ArrayList<String> mTagList =  new ArrayList<String>();

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public Bitmap getPhonto() {
		return mPhonto;
	}

	public void setPhonto(Bitmap mPhonto) {
		this.mPhonto = mPhonto;
	}

	public String getImageTagPath() {
		return imageTagPath;
	}

	public void setImageTagPath(String imageTagPath) {
		this.imageTagPath = imageTagPath;
	}
	
}
