package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	public void setPassword(String userName, String password) throws RollbackException {
        try {
            Transaction.begin();
            EmployeeBean dbUser = read(userName);

            if (dbUser == null) {
                throw new RollbackException("Employee Name " + userName + " no longer exists");
            }

            dbUser.setPassword(password);

            update(dbUser);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
}
