package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	public String getFundName(int fundId) throws RollbackException {
	        String fundName = null;
	        try {
	                Transaction.begin();
	                
                	        FundBean[] arr = this.match(MatchArg.equals("fundId", fundId));
                	        fundName = arr[0].getName();
                	        Transaction.commit();
	        } finally {
                        if (Transaction.isActive()) Transaction.rollback();
                }        
	       return fundName;
	}
	
	public FundBean read(String name) {
		try {
			FundBean[] arr = this.match(MatchArg.equals("name", name));
			if (arr.length == 0) {
				return null;
			} else {
				return arr[0];
			}

		} catch (RollbackException e) {
			return null;
		}
	}
}
