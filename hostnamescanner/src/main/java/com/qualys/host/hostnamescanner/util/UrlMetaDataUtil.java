package com.qualys.host.hostnamescanner.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qualys.host.hostnamescanner.dao.UrlMetaData;

@Component
public class UrlMetaDataUtil {

	private final static String SUCCESS_STATUS = "SUCCESS";

	private final static String FAILED_STATUS = "FAILED";

	@Autowired
	TitleExtractorUtil titleExtractorUtil;

	@Autowired
	RedirectionUrlUtil redirectionUrlUtil;

	@Autowired
	UrlLinkCountExtractorUtil urlLinkCountExtractorUtil;
	
	@Autowired
	HtmlBoadyExtarctorUtil htmlBoadyExtarctorUtil;

	public UrlMetaData getUrlMetadata(String url) {

		String newUrl = getPerfectUrl(url);

		UrlMetaData urlMetaData = new UrlMetaData();

		urlMetaData.setUrl(url);

		urlMetaData.setSubmittedOn(getCurrentTime());

		if (isVaildUrl(newUrl)) {

			String redirectionUrl = redirectionUrlUtil.getRedirectedUrl(newUrl);

			urlMetaData.setRedirectionUrl(redirectionUrl);

			urlMetaData.setWebsiteTitle(getWebsiteTitle(redirectionUrl));

			urlMetaData.setWebsiteBodyContent(getWebsiteBody(redirectionUrl));

			urlMetaData.setImageCount(urlLinkCountExtractorUtil.getUrlImageCount(redirectionUrl));

			urlMetaData.setIpAddress(getIpAddress(redirectionUrl));

			urlMetaData.setLinkCount(urlLinkCountExtractorUtil.getUrlLinksCount(redirectionUrl));

		}

		if (urlMetaData.getUrl() != null && urlMetaData.getRedirectionUrl() != null
				&& !(urlMetaData.getWebsiteTitle().equals("Not Found")) && urlMetaData.getImageCount() > 0
				&& urlMetaData.getLinkCount() > 0 && !(urlMetaData.getIpAddress().equals("Not Found"))
				&& urlMetaData.getWebsiteBodyContent() != null)
			urlMetaData.setScanStatus(SUCCESS_STATUS);
		else
			urlMetaData.setScanStatus(FAILED_STATUS);

		return urlMetaData;

	}

	private boolean isVaildUrl(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private String getPerfectUrl(String url) {
		// TODO Auto-generated method stub
		if (!(url.startsWith("https://") || url.startsWith("http://"))) {
			url = "https://" + url;
		}

		return url;
	}

	private String getIpAddress(String url) {
		InetAddress ip = null;
		try {

			ip = InetAddress.getByName(new URL(url).getHost());

		} catch (MalformedURLException | UnknownHostException e) {
			System.out.println("Invalid URL");
		}
		return ip != null ? ip.toString() : "Not Found";
	}

	private String getWebsiteBody(String url) {
		// TODO Auto-generated method stub
		List<String> list = null;
		try {
			list = htmlBoadyExtarctorUtil.extractText(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String textContent = String.join(" ", list);
		return textContent != null ? textContent : "Not Found";
	}

	private String getWebsiteTitle(String url) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();

		try {
			if (isVaildUrl(url) && titleExtractorUtil.getPageTitle(url) != null)
				sb.append(titleExtractorUtil.getPageTitle(url));
			else
				sb.append("Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}

	private Date getCurrentTime() {
		// TODO Auto-generated method stub
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.println(localDateTime);
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		
		return date;
	}

}
