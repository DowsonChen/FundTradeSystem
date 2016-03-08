package databean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.genericdao.PrimaryKey;

public class HistoryBean {
	
	private String 		type;
	private String 		date;
	private String  	operation;
	private String		fund;
	private String		totShares;
	private String		price;
	private String		total;
	
	public void setType	 				(String v) 		{ type = v; }
	public void setDate					(String v)		{ date = v;	}
	public void setOperation			(String v)		{ operation = v; 	}
	public void setFund					(String v) 		{ fund = v; 	}
	public void setTotShares 			(String v)		{ totShares = v;	}
	public void setPrice				(String v)		{ price = v; 	}
	public void setTotal				(String v)		{ total = v; 	}
	
	public String 	getType()	 		{ return type;}
	public String 	getDate()	 		{ return date;}
	public String	getOperation()		{ return operation;}
	public String 	getFund() 			{ return fund;}
	public String	getTotShares() 		{ return totShares;}
	public String	getPrice()			{ return price;}
	public String	getTotal()			{ return total;}
	
}