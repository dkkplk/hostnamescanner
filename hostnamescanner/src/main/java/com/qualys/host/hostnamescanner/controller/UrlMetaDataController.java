package com.qualys.host.hostnamescanner.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualys.host.hostnamescanner.dao.UrlMetaData;
import com.qualys.host.hostnamescanner.service.UrlMetaDataService;
import com.qualys.host.hostnamescanner.util.UrlMetaDataUtil;

@Controller
@ResponseBody
public class UrlMetaDataController {

	private static final Logger log = LoggerFactory.getLogger(UrlMetaDataController.class);

	private static final int URL_COUNT = 10;

	@Autowired
	UrlMetaDataUtil urlMetaDataUtil;

	@Autowired
	UrlMetaDataService urlMetaDataService;

	@RequestMapping(path = "/submit", method = RequestMethod.POST)
	public void putUrlMetaData(@RequestParam("url") String url) throws Exception {
		urlMetaDataService.saveOrUpdate(urlMetaDataUtil.getUrlMetadata(url));
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<UrlMetaData> getUser(@RequestParam("urlCount") int urlCount,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {

		if (urlCount != 0) {
			return urlMetaDataService.getUrlByCount(urlCount);
		} else if (!(startDate.isEmpty() && endDate.isEmpty())) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date startDateFormated = null;
			Date endDateFormated = null;
			try {
				startDateFormated = formatter.parse(startDate);
				endDateFormated = formatter.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return urlMetaDataService.getUrlByDate(startDateFormated, endDateFormated);
		} else {
			return urlMetaDataService.getUrlByCount(URL_COUNT);
		}

	}
	
	@RequestMapping(path = "/url", method = RequestMethod.GET)
	public List<UrlMetaData> getUser(@RequestParam("id") long id) {
		
		return urlMetaDataService.getUrlById(id);
		
	}

}
