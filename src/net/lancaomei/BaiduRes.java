package net.lancaomei;


public class BaiduRes {
	private String name	; //	poi����
	private BaiduLocation	location ;//	poi��γ������

	private String address		;//poi��ַ��Ϣ
	private String telephone		;// poi�绰��Ϣ
	private String uid		;// poi��Ψһ��ʾ
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
	private float lat	;//	γ��ֵ
	private float lng  ;//	����ֵ
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