package net.lancaomei;


public class BaiduRes {
	private String name	; //	poi名称
	private BaiduLocation	location ;//	poi经纬度坐标

	private String address		;//poi地址信息
	private String telephone		;// poi电话信息
	private String uid		;// poi的唯一标示
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public BaiduLocation getLocation() {
		return location;
	}
	public void setLocation(BaiduLocation location) {
		this.location = location;
	}


}

class BaiduLocation {
	private float lat	;//	纬度值
	private float lng  ;//	经度值
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
}