package com.namoo.cookbook.domain;

public class ImageFile {
	//field
	private String contentType;
	private String fileName;
	
	//--------------------------------------------------------------------------
	// constructor
	
	public ImageFile() {
		//
		
	}
	
	public ImageFile(String contentType, String fileName) {
		//
		this.contentType = contentType;
		this.fileName = fileName;
		
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
