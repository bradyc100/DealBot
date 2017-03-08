package com.freelance.dealbot;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CraigslistBot {
	
	public static void main(String[] args) throws Exception {
		final String boatType = "ranger";
		DealObject dealObj = new DealObject();
		String boatUrl = "https://memphis.craigslist.org/search/boo?query=" + boatType;
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(boatUrl);

		// add request header
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		System.out.println(result);
		
		Document doc = Jsoup.parse(result.toString());
		Elements priceElements = doc.select("span.result-price");
		
	}
}
