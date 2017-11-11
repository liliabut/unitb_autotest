package com.unitb.autotest;

import java.util.ArrayList;

public class MainActivity {

	public static void main(String[] args) {
		// don't generate sitemap, but use local existing sitemap.txt
		boolean generateSitemap = false;
		byte urlsBufferSize = 10;
		byte retries = 3;
		byte timeout = 5;

		// TODO: read from config/profile file later
		if (args != null) {

			// 0-argument: generate sitemap true oder false
			if (args[0] != null && !args[0].isEmpty()) {
				generateSitemap = Boolean.getBoolean(args[0]);
			}

			// 1 argument: number of URLs processed per tact
			if (args[1] != null && !args[1].isEmpty()) {
				urlsBufferSize = Byte.parseByte(args[1]);
			}

			// 2 argument: number of retries
			if (args[2] != null && !args[2].isEmpty()) {
				retries = Byte.parseByte(args[2]);
			}
			
			// 3 argument: timeout in sec
			if (args[3] != null && !args[3].isEmpty()) {
				timeout = Byte.parseByte(args[3]);
			}
		}

		// generate sitemap
		SitemapThread st = new SitemapThread(generateSitemap, urlsBufferSize);
		st.start();

		// read sitemap
		// and execute every N urls
		ArrayList<String> urls = st.getBuffUrls();

		while (urls != null) {
			DumpThread dt = new DumpThread();
			dt.start();
		}
	}
}