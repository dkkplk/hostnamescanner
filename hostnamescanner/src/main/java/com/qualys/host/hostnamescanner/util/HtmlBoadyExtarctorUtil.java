package com.qualys.host.hostnamescanner.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

import org.springframework.stereotype.Component;

@Component
public class HtmlBoadyExtarctorUtil {

	public List<String> extractText(String url) throws IOException {

		Reader reader = getUrlContent(url);
		final ArrayList<String> list = new ArrayList<String>();

		if (reader != null) {

			ParserDelegator parserDelegator = new ParserDelegator();
			ParserCallback parserCallback = new ParserCallback() {
				public void handleText(final char[] data, final int pos) {
					list.add(new String(data));
				}

				public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) {
				}

				public void handleEndTag(Tag t, final int pos) {
				}

				public void handleSimpleTag(Tag t, MutableAttributeSet a, final int pos) {
				}

				public void handleComment(final char[] data, final int pos) {
				}

				public void handleError(final java.lang.String errMsg, final int pos) {
				}
			};
			parserDelegator.parse(reader, parserCallback, true);
		}
		return list;
	}

	public Reader getUrlContent(String url) {

		BufferedReader br = null;

		if (isVaildUrl(url)) {

			try {

				URL newurl = new URL(url);

				URLConnection conn = newurl.openConnection();

				// open the stream and put it into BufferedReader
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return br;

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
