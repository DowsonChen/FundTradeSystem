package databean;

public class PositionInfo {
	private String name;
	private String shares;
	private String price;
	private String total;
	
	public PositionInfo(String name, String shares, String price, String total) {
		this.name = name;
		this.shares = shares;
		this.price = price;
		this.total = total;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setShares(String shares) {
		this.shares = shares;
	}
	
	public String getShares() {
		return shares;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getTotal() {
		return total;
	}
}
