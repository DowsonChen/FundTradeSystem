package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class FundBean {
	private int 	fundId;
	private String 	name;
	private String 	symbol;
	
	public void setFundId(int v)	{ fundId = v; 	}
	public void setName(String  v)	{ name = v;		}
	public void setSymbol(String  v) { symbol = v;	}
	
	public int	getFundId()		{ return fundId; 	}
	public String getName()	 	{ return name;		}
	public String getSymbol() 	{ return symbol;	}
}
