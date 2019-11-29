package iiitb.placement_portal.dto;


public class FilterDTO {
	private Integer id;
	private String year;
	private String type;
	public Integer getCompany() {
		return id;
	}
	public void setCompany(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}