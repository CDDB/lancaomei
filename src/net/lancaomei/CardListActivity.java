package net.lancaomei;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CardListActivity extends ListActivity {

	private Context mContext = null;
	private Storage storage = null;
	
	private ArrayList<ContactDetail> detailList = new ArrayList<ContactDetail>();

	ListView mListView = null;
	CardListAdapter myAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = this;
		mListView = this.getListView();
		storage = new Storage(mContext);
		
		
		Bundle bundle = getIntent().getExtras();
		if (bundle.getBoolean("input")) {
			String json_res = bundle.getString("search_res");
			getDetailFromJson(json_res);
			
		} else {
			/** �õ��ֻ�ͨѶ¼��ϵ����Ϣ **/
			getPhoneContacts();
		}

		myAdapter = new CardListAdapter(this);
		setListAdapter(myAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Intent intent = new Intent(mContext, CardActivity.class);
				Bundle bundle = new Bundle();
				ContactDetail detail = detailList.get(position);
				bundle.putString("name", detail.getName());
				bundle.putString("phone", detail.getPhone());
				bundle.putString("desc", detail.getDesc());
				bundle.putString("tag", detail.getTag());
				bundle.putString("imagePath", detail.getImageTagPath());
				intent.putExtras(bundle); 
				startActivity(intent);
			}
		});

		super.onCreate(savedInstanceState);
	}

	/** �õ��ֻ�ͨѶ¼��ϵ����Ϣ **/
	private void getPhoneContacts() {
		storage.get(detailList);
	}
	
	private void getDetailFromJson(String json) {
		Gson gson = new GsonBuilder().create();
		DianPingBusinesseRes business =  gson.fromJson(json, DianPingBusinesseRes.class);
		ArrayList<DianPingBusiness> items = business.getBusinesses();
		
		for(DianPingBusiness item :  items) {
			ContactDetail detail = new ContactDetail();
			detail.setImageTagPath(item.getPhoto_url());
			detail.setTag(item.getCategories().toString());
			detail.setName(item.getName());
			detail.setPhone(item.getTelephone());
			if (1 == item.getHas_coupon()) {
				detail.setDesc(item.getCoupon_description());
			}
			detailList.add(detail);
		}
	}

	

	class CardListAdapter extends BaseAdapter {
		public CardListAdapter(Context context) {
			mContext = context;
		}

		public int getCount() {
			// ���û�������
			return detailList.size();
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView image = null;
			TextView title = null;
			TextView text = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.colorlist, null);
			}
			image = (ImageView) convertView.findViewById(R.id.color_image);
			title = (TextView) convertView.findViewById(R.id.color_title);
			text = (TextView) convertView.findViewById(R.id.color_text);
			// ������ϵ������
			title.setText(detailList.get(position).getName());
			// ������ϵ�˺���
			text.setText(detailList.get(position).getPhone());
			return convertView;
		}

	}
}
