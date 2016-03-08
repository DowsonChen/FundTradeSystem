package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
        public long getSharePrice(int fundId, String executeDate) throws RollbackException{
                long sharePrice = 0;
                try {
                        Transaction.begin();
                        
                        FundPriceHistoryBean[] arr = match(MatchArg.equals("fundId", fundId), MatchArg.equals("executeDate", executeDate));
                        sharePrice = arr[0].getPrice();
                        
                        Transaction.commit();
                } finally {
                        if (Transaction.isActive()) Transaction.rollback();
                }
                return sharePrice;
        }
	
	public FundPriceHistoryBean getLatestFundPrice(int fundId) throws RollbackException{
		FundPriceHistoryBean[] fundPriceHistory = match(MatchArg.equals("fundId", fundId));
		if(fundPriceHistory.length == 0) return null;
		return fundPriceHistory[fundPriceHistory.length-1];
	}
	
	public String getLatestTradingDayDateString () throws RollbackException, ParseException {
		String date = null;
		try {
			Transaction.begin();
			
			FundPriceHistoryBean[] fb = match();
			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				date = fb[fb.length - 1].getExecuteDate();
			}
			

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return date;
	}
	
	public Date getLatestTradingDayDate () throws RollbackException, ParseException {

		Date date = null;
		try {
			Transaction.begin();

			FundPriceHistoryBean[] fb = match();

			if (fb != null && fb.length != 0) {
				Arrays.sort(fb);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setLenient(false);
				date = dateFormat.parse(fb[fb.length - 1].getExecuteDate());
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}
	
}
