package net.lancaomei;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends Activity {
	Context mContext = null;
	Searcher searcher;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		mContext = this;
		searcher = new Searcher();

		Button botton_search = (Button) findViewById(R.id.search);
		botton_search.setOnClickListener(new RequestAPILickListener(this,
				searcher, mContext));

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	static class RequestAPILickListener implements OnClickListener {

		private Activity activity;
		Searcher searcher;
		Context mContext;


		public RequestAPILickListener(Activity activity, Searcher searcher, Context mContext) {
			this.activity = activity;
			this.searcher = searcher;
			this.mContext = mContext;
		}

		@Override
		public void onClick(View v) {
			String res = searcher.search();						
			Intent intent = new Intent(this.mContext, CardListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putBoolean("input", true);
			bundle.putString("search_res", res);

			intent.putExtras(bundle); 
			this.activity.startActivity(intent);
		}
	}

}
