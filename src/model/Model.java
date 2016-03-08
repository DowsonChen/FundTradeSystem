package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.EmployeeBean;

public class Model {
        private CustomerDAO customerDAO;
        private EmployeeDAO employeeDAO;
        private FundDAO fundDAO;
        private FundPriceHistoryDAO fundPriceHistoryDAO;
        private PositionDAO positionDAO;
        private TransactionDAO transactionDAO;
        private TransactionShareDAO transactionShareDAO;

        public Model(ServletConfig config) throws ServletException {

                String jdbcDriver = config.getInitParameter("jdbcDriverName");
                String jdbcURL = config.getInitParameter("jdbcURL");
                ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

                try {
                        customerDAO = new CustomerDAO(pool, "Customer");
                        employeeDAO = new EmployeeDAO(pool, "Employee");
                        fundDAO = new FundDAO(pool, "Fund");
                        fundPriceHistoryDAO = new FundPriceHistoryDAO(pool, "Fund_Price_History");
                        transactionDAO = new TransactionDAO(pool, "Transaction");
                        transactionShareDAO = new TransactionShareDAO(pool, "Transaction_Share");
                        positionDAO = new PositionDAO(pool, "Position");

                        EmployeeBean eb = employeeDAO.read("root");
                        if (eb == null) {
                                eb = new EmployeeBean();
                                eb.setUserName("root");
                                eb.setFirstName("root");
                                eb.setLastName("root");
                                eb.setPassword("root");
                                employeeDAO.create(eb);
                        }
                } catch (DAOException | RollbackException e) {
                        throw new ServletException(e);
                }
        }

        public CustomerDAO getCustomerDAO() {
                return customerDAO;
        }

        public EmployeeDAO getEmployeeDAO() {
                return employeeDAO;
        }

        public FundDAO getFundDAO() {
                return fundDAO;
        }

        public FundPriceHistoryDAO getFundPriceHistoryDAO() {
                return fundPriceHistoryDAO;
        }

        public PositionDAO getPositionDAO() {
                return positionDAO;
        }

        public TransactionDAO getTransactionDAO() {
                return transactionDAO;
        }

        public TransactionShareDAO getTransactionShareDAO() {
                return transactionShareDAO;
        }
}
