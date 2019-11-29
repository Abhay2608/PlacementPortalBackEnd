package iiitb.placement_portal.dto;


public class FilterDTO {
	private Integer companyId;
	private String year;
	private String type;
	public FilterDTO() {
		
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public FilterDTO(Integer companyId, String year, String type) {
		super();
		this.companyId = companyId;
		this.year = year;
		this.type = type;
	}
	@Override
	public String toString() {
		return "FilterDTO [companyId=" + companyId + ", year=" + year + ", type=" + type + "]";
	}
	
}