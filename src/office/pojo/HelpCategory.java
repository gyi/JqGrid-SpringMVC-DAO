package office.pojo;

public class HelpCategory {
	private String helpCategoryId;
	private String name;
	private String parentCategoryId;
	private String url;
	
	public String getHelpCategoryId() {
		return helpCategoryId;
	}
	public void setHelpCategoryId(String helpCategoryId) {
		this.helpCategoryId = helpCategoryId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
