package databean;

public class FundInfoBean {
	private String name;
	private String price;
	public FundInfoBean(String name, String price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
