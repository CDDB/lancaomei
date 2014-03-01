package net.lancaomei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;

public class Searcher {

	public Searcher() {

	}

	public String search() {
		try {
			return searchDianPing();
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String searchBaidu() {
		String apiUrl = "http://api.map.baidu.com/place/v2/search";
		String ak = "B975e355e8122f2da2af4ecdf38f7344";

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("query", "餐馆");
		paramMap.put("bounds", "39.915,116.404,39.975,116.414");
		paramMap.put("output", "json");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ak=").append(ak);
		for (Entry<String, String> entry : paramMap.entrySet()) {
			stringBuilder.append('&').append(entry.getKey()).append('=')
					.append(entry.getValue());
		}
		String queryString = stringBuilder.toString();

		StringBuffer response = new StringBuffer();
		HttpClientParams httpConnectionParams = new HttpClientParams();
		httpConnectionParams.setConnectionManagerTimeout(1000);
		HttpClient client = new HttpClient(httpConnectionParams);
		HttpMethod method = new GetMethod(apiUrl);

		try {
			if (queryString != null && !queryString.isEmpty()) {
				// Encode query string with UTF-8
				String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8");
				method.setQueryString(encodeQuery);
			}

			client.executeMethod(method);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.append(line).append(
						System.getProperty("line.separator"));
			}
			reader.close();
		} catch (URIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	public String searchDianPing() throws HttpException, IOException {

		String apiUrl = "http://api.dianping.com/v1/business/find_businesses";
		String appKey = "425863366";
		String secret = "11d61cb0910940908ae01326a73d57ce";

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("city", "上海");
		// paramMap.put("latitude", "31.21524");
		// paramMap.put("longitude", "121.420033");
		// paramMap.put("category", "美食");
		// paramMap.put("region", "长宁区");
		// paramMap.put("limit", "20");
		// paramMap.put("radius", "2000");
		// paramMap.put("offset_type", "0");
		// paramMap.put("has_coupon", "1");
		// paramMap.put("has_deal", "1");
		// paramMap.put("keyword", "̩泰国菜");
		// paramMap.put("sort", "7");
		paramMap.put("format", "json");

		return requestApi(apiUrl, appKey, secret, paramMap);
	}

	/**
	 * 获取请求字符串
	 * 
	 * @param appKey
	 * @param secret
	 * @param paramMap
	 * @return
	 */
	public static String getQueryString(String appKey, String secret,
			Map<String, String> paramMap) {
		String sign = sign(appKey, secret, paramMap);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("appkey=").append(appKey).append("&sign=")
				.append(sign);
		for (Entry<String, String> entry : paramMap.entrySet()) {
			stringBuilder.append('&').append(entry.getKey()).append('=')
					.append(entry.getValue());
		}
		String queryString = stringBuilder.toString();
		return queryString;
	}

	/**
	 * 获取请求字符串，参数值进行UTF-8处理
	 * 
	 * @param appKey
	 * @param secret
	 * @param paramMap
	 * @return
	 */
	public static String getUrlEncodedQueryString(String appKey, String secret,
			Map<String, String> paramMap) {
		String sign = sign(appKey, secret, paramMap);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("appkey=").append(appKey).append("&sign=")
				.append(sign);
		for (Entry<String, String> entry : paramMap.entrySet()) {
			try {
				stringBuilder.append('&').append(entry.getKey()).append('=')
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
			}
		}
		String queryString = stringBuilder.toString();
		return queryString;
	}

	/**
	 * 请求API
	 * 
	 * @param apiUrl
	 * @param appKey
	 * @param secret
	 * @param paramMap
	 * @return
	 */
	public static String requestApi(String apiUrl, String appKey,
			String secret, Map<String, String> paramMap) {
		String queryString = getQueryString(appKey, secret, paramMap);

		StringBuffer response = new StringBuffer();
		HttpClientParams httpConnectionParams = new HttpClientParams();
		httpConnectionParams.setConnectionManagerTimeout(1000);
		HttpClient client = new HttpClient(httpConnectionParams);
		HttpMethod method = new GetMethod(apiUrl);

		try {
			if (queryString != null && !queryString.isEmpty()) {
				// Encode query string with UTF-8
				String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8");
				method.setQueryString(encodeQuery);
			}

			client.executeMethod(method);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.append(line).append(
						System.getProperty("line.separator"));
			}
			reader.close();
		} catch (URIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response.toString();

	}

	/**
	 * 签名
	 * 
	 * @param appKey
	 * @param secret
	 * @param paramMap
	 * @return
	 */
	public static String sign(String appKey, String secret,
			Map<String, String> paramMap) {
		// 参数名排序
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);

		// 拼接参数
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(appKey);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key));
		}

		stringBuilder.append(secret);
		String codes = stringBuilder.toString();

		// SHA-1签名
		// For Android
		String sign = new String(Hex.encodeHex(DigestUtils.sha(codes)))
				.toUpperCase();

		return sign;
	}

}
