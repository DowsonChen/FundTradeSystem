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

import databean.CustomerBean;
import databean.TransactionBean;
public class TransactionDAO extends GenericDAO<TransactionBean> {
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
	
	public String getLastDate(CustomerBean c) throws RollbackException{
		String date = null;
		try {
			Transaction.begin();
		
			TransactionBean[] transaction  = match(MatchArg.notEquals("executeDate", null), MatchArg.equals("userName", c.getUserName()));
			if( transaction == null || transaction.length == 0) date = null;
			else {
				Arrays.sort(transaction);
				date = transaction[transaction.length-1].getExecuteDate();
			}
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}
	
	public double getValidBalance (String userName, double amount) throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null), MatchArg.equals("userName", userName));
			
			if (tbs != null) {
				for (TransactionBean t : tbs) {
					switch(Integer.parseInt(t.getTransactionType())) {
					case TransactionBean.SELL_FUND:
						amount += t.getAmount() / 100.00;
						break;
					case TransactionBean.BUY_FUND:
						amount -= t.getAmount() / 100.00;
						break;
					case TransactionBean.REQUEST_CHECK:
						amount -= t.getAmount() / 100.00;
						break;
					case TransactionBean.DEPOSIT_CHECK:
						amount += t.getAmount() / 100.00;
						break;
					default:
						break;
					}
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return amount;
	}
	
	public double getValidBalanceNew (String userName, double amount) throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null), MatchArg.equals("userName", userName));
			
			if (tbs != null) {
				for (TransactionBean t : tbs) {
					switch(Integer.parseInt(t.getTransactionType())) {
					case TransactionBean.SELL_FUND:
						amount += t.getAmount() / 100.00;
						break;
					case TransactionBean.BUY_FUND:
						amount -= t.getAmount() / 100.00;
						break;
					default:
						break;
					}
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		return amount;
	}
	
	public double getValidShares (int customerId, int fundId, double shares) throws RollbackException {
		TransactionBean[] tbs = null;
		try {
			Transaction.begin();
			
			// How to execute select * from table where transactionType IS NULL
			tbs =  match(MatchArg.equals("executeDate", null), MatchArg.equals("fundId", fundId), 
					MatchArg.equals("transactionType", "4"), MatchArg.equals("customerId", customerId));
			
			if (tbs != null) {
				for (TransactionBean t : tbs) {
					System.out.println("inside shares:" + shares);
					switch(Integer.parseInt(t.getTransactionType())) {
					case TransactionBean.SELL_FUND:
						shares -= t.getShares() / 1000.00;
						System.out.println("I break out");
						System.out.println("new inside shares:" + shares);
						break;
//					case TransactionBean.BUY_FUND:
//						shares += t.getShares() / 1000.00;
//						break;
					default:
						break;
					}
				}
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return shares;
	}
	
	public Date getLatestDate () throws RollbackException, ParseException {
		Date date = null;
		try {
			Transaction.begin();
		
			TransactionBean[] t =  match(MatchArg.notEquals("executeDate", null));
			if (t != null && t.length != 0) {
				Arrays.sort(t);
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.setLenient(false);
				date = dateFormat.parse(t[t.length - 1].getExecuteDate());
			}
			
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return date;
	}
	
}
