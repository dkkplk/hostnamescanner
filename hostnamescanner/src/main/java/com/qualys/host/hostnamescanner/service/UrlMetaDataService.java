package com.qualys.host.hostnamescanner.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualys.host.hostnamescanner.dao.UrlMetaData;
import com.qualys.host.hostnamescanner.repo.UrlMetaDataRepository;

@Service
public class UrlMetaDataService {

	@Autowired
	UrlMetaDataRepository urlMetaDataRepository;

	public List<UrlMetaData> getUrlByDate(Date startDate, Date endDate) {
		List<UrlMetaData> urlList = new ArrayList<UrlMetaData>();

		urlMetaDataRepository.findAllBySubmittedOnBetween(startDate, endDate)
				.forEach(UrlMetaData -> urlList.add(UrlMetaData));
		return urlList;
	}

	public List<UrlMetaData> getUrlByCount(int urlCount) {
		List<UrlMetaData> urlList = new ArrayList<UrlMetaData>();

		urlMetaDataRepository.findAllByOrderByIdDesc().stream().limit(urlCount)
				.forEach(UrlMetaData -> urlList.add(UrlMetaData));
		return urlList;
	}

	public List<UrlMetaData>  getUrlById(long id) {
		List<UrlMetaData> urlList = new ArrayList<UrlMetaData>();
		urlList.add(urlMetaDataRepository.findById(id));
		return urlList;
	}

	public void saveOrUpdate(UrlMetaData urlMetaData) {
		urlMetaDataRepository.save(urlMetaData);
	}

}
