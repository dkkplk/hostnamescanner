package com.qualys.host.hostnamescanner.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qualys.host.hostnamescanner.dao.UrlMetaData;

@Repository
public interface UrlMetaDataRepository extends PagingAndSortingRepository<UrlMetaData, Long> {

	UrlMetaData findById(long id);
	
	List<UrlMetaData> findAllByOrderByIdDesc();

	List<UrlMetaData> findAllBySubmittedOnBetween(Date submittedOnStartDate,Date submittedOnEndDate);
}
