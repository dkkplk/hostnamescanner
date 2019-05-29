package com.qualys.host.hostnamescanner.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class UrlLinkCountExtractorUtil {

	private Pattern patternTag, patternLink, imgPatternTag;
	private Matcher matcherTag, matcherLink, imgMatcherTag;

	private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	private static final String HTML_A_TAG_PATTERN_IMG = "(?i)<img([^>]+)>(.+?)>";

	public UrlLinkCountExtractorUtil() {
		patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
		imgPatternTag = Pattern.compile(HTML_A_TAG_PATTERN_IMG);
	}

	public int getUrlLinksCount(String url) {

		String html = getUrlContent(url);

		int result = 0;

		matcherTag = patternTag.matcher(html);

		while (matcherTag.find()) {

			String href = matcherTag.group(1); // href

			matcherLink = patternLink.matcher(href);

			while (matcherLink.find()) {
				result++;

			}

		}

		return result;

	}

	public int getUrlImageCount(String url) {

		String html = getUrlContent(url);

		int result = 0;

		imgMatcherTag = imgPatternTag.matcher(html);

		while (imgMatcherTag.find()) {

			result++;

		}

		return result;

	}

	public String getUrlContent(String url) {

		StringBuilder sb = new StringBuilder();

		if (isVaildUrl(url)) {

			try {

				URL newurl = new URL(url);

				URLConnection conn = newurl.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String inputLine;

				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	private boolean isVaildUrl(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
