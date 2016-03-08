package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}

	public CustomerBean read(String userName) {

		try {
			CustomerBean[] arr = this.match(MatchArg.equals("userName", userName));
			if (arr.length == 0) {
				return null;
			} else {
				return arr[0];
			}

		} catch (RollbackException e) {
			return null;
		}

	}
	
	public void setPassword(String userName, String password) throws RollbackException {
        try {
            Transaction.begin();
            CustomerBean dbUser = read(userName);

            if (dbUser == null) {
                throw new RollbackException("User Name " + userName + " no longer exists");
            }

            dbUser.setPassword(password);

            update(dbUser);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
}
