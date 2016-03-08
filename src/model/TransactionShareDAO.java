package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericViewDAO;
import databean.TransactionShareBean;

public class TransactionShareDAO extends GenericViewDAO<TransactionShareBean> {
	public TransactionShareDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionShareBean.class, cp);
	}
}
