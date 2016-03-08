package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.TransactionBean;
import databean.TransactionShareBean;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.TransactionDAO;

public class TransactionHistoryAction extends Action {
        private TransactionDAO transactionDAO;
        private FundPriceHistoryDAO fundPriceHistoryDAO;
        private FundDAO fundDAO;

        public TransactionHistoryAction(Model model) {
                transactionDAO = model.getTransactionDAO();
                fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
                fundDAO = model.getFundDAO();
        }

        @Override
        public String getName() {
                return "transactionHistory.do";
        }

        @Override
        public String perform(HttpServletRequest request) {
                HttpSession session = request.getSession();
                List<String> errors = new ArrayList<String>();
                request.setAttribute("errors", errors);

                try {
                        if (session.getAttribute("user") != null
                                        && session.getAttribute("user") instanceof CustomerBean) {
                                CustomerBean user = (CustomerBean) request.getSession().getAttribute("user");

                                int customer_Id = user.getCustomerId();

                                List<TransactionShareBean> transactionShares = new ArrayList<TransactionShareBean>();
                                TransactionBean[] transactions = transactionDAO
                                                .match(MatchArg.equals("customerId", customer_Id));
                                for (TransactionBean t : transactions) {
                                        TransactionShareBean tShare = new TransactionShareBean();

                                        tShare.setTransactionId(t.getTransactionId());
                                        tShare.setCustomeId(t.getCustomerId());

                                        if (t.getFundId() == 0) {
                                                tShare.setFundId(-1);
                                                tShare.setShares(-1);
                                                tShare.setSharePrice(-1);
                                                if (t.getTransactionType().equals("2")) {
                                                        tShare.setTransactionType("Request Check");
                                                } else {
                                                        tShare.setTransactionType("Deposit Check");
                                                }
                                                if (t.getExecuteDate() != null) {
                                                        tShare.setExecuteDate(t.getExecuteDate());
                                                } else {
                                                        tShare.setExecuteDate("Pending");
                                                }
                                                tShare.setAmount(t.getAmount() / 100.0);
                                        } else {
                                                int fundId = t.getFundId();
                                                tShare.setFundId(fundId);
                                                String fundName = fundDAO.getFundName(fundId);
                                                tShare.setFundName(fundName);

                                                if (t.getTransactionType().equals("8")) {
                                                        tShare.setTransactionType("Buy Fund");
                                                        
                                                        if (t.getExecuteDate() == null ) {
                                                                tShare.setExecuteDate("Pending");
                                                                tShare.setShares(-1);
                                                                tShare.setSharePrice(-1); 
                                                        } else {
                                                                String executeDate = t.getExecuteDate();
                                                                tShare.setExecuteDate(executeDate);
                                                                tShare.setShares(t.getShares() / 1000.0);
                                                                long sharePrice = fundPriceHistoryDAO.getSharePrice(fundId, executeDate);
                                                                tShare.setSharePrice(sharePrice / 100.0);
                                                        }
                                                        tShare.setAmount(t.getAmount() / 100.0);
                                                } else {
                                                        tShare.setTransactionType("Sell Fund");
                                                        if (t.getExecuteDate() == null ) {
                                                                tShare.setExecuteDate("Pending");
                                                                tShare.setAmount(-1);
                                                                tShare.setSharePrice(-1); 
                                                        } else {
                                                                String executeDate = t.getExecuteDate();
                                                                tShare.setExecuteDate(executeDate);
                                                                tShare.setAmount(t.getAmount() / 100.0);
                                                                long sharePrice = fundPriceHistoryDAO.getSharePrice(fundId, executeDate);
                                                                tShare.setSharePrice(sharePrice / 100.0);
                                                        }
                                                        tShare.setShares(t.getShares() / 1000.0);
                                                }
                                        }
                                        transactionShares.add(tShare);
                                }

                                if (transactionShares.size() == 0) {
                                        errors.add("No transaction history to be viewed");
                                        request.setAttribute("customer", user);
                                        return "transactionHistory_Customer.jsp";
                                } else {
                                        request.setAttribute("customer", user);
                                        request.setAttribute("transactions", transactionShares);
                                        return "transactionHistory_Customer.jsp";
                                }
                        } else {
                                return "login.do";
                        }
                } catch (RollbackException e) {
                        errors.add("System roll back!");
                        e.printStackTrace();
                        return "transactionHistory_Customer.jsp";
                } catch (Exception e2) {
                        errors.add("Other errors!");
                        e2.printStackTrace();
                        return "transactionHistory_Customer.jsp";
                }
        }
}
