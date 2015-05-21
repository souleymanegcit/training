package com.gcit.training.lms.entity;


public class Publisher {

	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;

	
	public int getPublisherId() {
		return publisherId;
	}
	public Publisher(){
		
	}
public Publisher(String publisherName){
	
		this.publisherName = publisherName;
	}
	
	public Publisher(int publisherId ){
		this.publisherId=publisherId;
	}
	public Publisher(String publisherName, String publisherAddress,  String publisherPhone ){
		this.publisherName=publisherName;
		this.publisherAddress=publisherAddress;
		this.publisherPhone=publisherPhone;
	}
	public Publisher(String publisherName, String publisherAddress,  String publisherPhone, int publisherId ){
		this.publisherName=publisherName;
		this.publisherAddress=publisherAddress;
		this.publisherPhone=publisherPhone;
		this.publisherId=publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherPhone() {
		return publisherPhone;
	}
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
 		
}
