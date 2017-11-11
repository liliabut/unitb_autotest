package com.unitb.autotest;

import java.util.ArrayList;

public class SitemapThread extends Thread {
	private boolean generateSitemap = false;
	private byte urlsBufferSize = 10;
	private ArrayList<String> sitemap = new ArrayList<String>();

	public SitemapThread(boolean gs, byte ubs) {
		this.generateSitemap = gs;
		this.urlsBufferSize = ubs;
	}

	public void run() {
	}

	public ArrayList<String> getBuffUrls() {
		if (this.sitemap.size() == 0) {
			return null;
		}

		ArrayList<String> result = new ArrayList<String>();

		int count = this.urlsBufferSize;

		if (this.sitemap.size() < this.urlsBufferSize) {
			count = this.sitemap.size();
		}

		for (int i = 0; i < count; i++) {
			result.add(this.sitemap.get(i));
		}

		return result;
	}

	public void removeBuffUrls(ArrayList<String> urls) {
		for (int i = 0; i < urls.size(); i++) {
			this.sitemap.remove(urls.get(i));
		}
	}
}