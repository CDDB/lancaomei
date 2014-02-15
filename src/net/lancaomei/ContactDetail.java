package net.lancaomei;


public class ContactDetail {
	
	private String mName = null;

	private String mPhone = null;

	private String desc;
	
	private String tag;
	
	private String imageTagPath;
	

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

	public String getImageTagPath() {
		return imageTagPath;
	}

	public void setImageTagPath(String imageTagPath) {
		this.imageTagPath = imageTagPath;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
