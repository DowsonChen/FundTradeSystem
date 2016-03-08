package databean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.genericdao.PrimaryKey;

import databean.FundPriceHistoryBean;

@PrimaryKey("fundId,executeDate")
public class FundPriceHistoryBean implements Comparable<FundPriceHistoryBean>{
	private int fundId;
	private String executeDate;
	private long price;

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public int compareTo(FundPriceHistoryBean fb) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(this.executeDate).compareTo(dateFormat.parse(fb.executeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
