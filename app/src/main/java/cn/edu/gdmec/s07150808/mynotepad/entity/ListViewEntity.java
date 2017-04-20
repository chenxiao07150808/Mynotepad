package cn.edu.gdmec.s07150808.mynotepad.entity;

import java.io.Serializable;

public class ListViewEntity implements Serializable{

	private String text_title_of_item;
	private String text_content_of_item;
	private String dtimes;
	public ListViewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ListViewEntity(String text_title_of_item, String text_content_of_item) {
		super();
		this.text_title_of_item = text_title_of_item;
		this.text_content_of_item = text_content_of_item;
	}
	
	public ListViewEntity(String text_title_of_item,
			String text_content_of_item, String dtimes) {
		super();
		this.text_title_of_item = text_title_of_item;
		this.text_content_of_item = text_content_of_item;
		this.dtimes = dtimes;
	}
	
	public String getDtimes() {
		return dtimes;
	}
	public void setDtimes(String dtimes) {
		this.dtimes = dtimes;
	}
	public String getText_title_of_item() {
		return text_title_of_item;
	}
	public void setText_title_of_item(String text_title_of_item) {
		this.text_title_of_item = text_title_of_item;
	}
	public String getText_content_of_item() {
		return text_content_of_item;
	}
	public void setText_content_of_item(String text_content_of_item) {
		this.text_content_of_item = text_content_of_item;
	}
	
}
