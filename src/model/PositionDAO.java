package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
		
}
