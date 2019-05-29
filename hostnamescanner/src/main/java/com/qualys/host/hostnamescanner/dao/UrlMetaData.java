package com.qualys.host.hostnamescanner.dao;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "URL")
@Access(value = AccessType.FIELD)
public class UrlMetaData {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "URL", length = 1000, nullable = false)
	private String url;

	@Column(name = "REDIRECTIONALURL", length = 1000)
	private String redirectionUrl;
	
	@Column(name = "IPADDRESS", length = 100)
	private String ipAddress;
	
	@Column(name = "SCANSTATUS", length = 8, nullable = false)
	private String scanStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTEDON")
	private Date submittedOn;

	@Column(name = "WEBSITETITLE", length = 300)
	private String websiteTitle;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "WEBSITEBODYCONTENT", columnDefinition = "CLOB")
	private String websiteBodyContent;
	
	@Column(name = "IMAGECOUNT", length = 10)
	private int imageCount;
	
	@Column(name = "LINKCOUNT", length = 10)
	private int linkCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getScanStatus() {
		return scanStatus;
	}

	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getWebsiteTitle() {
		return websiteTitle;
	}

	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	public String getWebsiteBodyContent() {
		return websiteBodyContent;
	}

	public void setWebsiteBodyContent(String websiteBodyContent) {
		this.websiteBodyContent = websiteBodyContent;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public int getLinkCount() {
		return linkCount;
	}

	public void setLinkCount(int linkCount) {
		this.linkCount = linkCount;
	}

	
}
