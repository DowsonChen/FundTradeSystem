package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import databean.PositionInfo;
import databean.TransactionBean;
import formbean.SellFundForm;

public class SellFundAction extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);

	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO historyDAO;

	public SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		historyDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "sellFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			SellFundForm sellFundForm = formBeanFactory.create(request);
			request.setAttribute("form", sellFundForm);

			if (session.getAttribute("user") == null) {
				return "login.do";
			}
			DecimalFormat df3 = new DecimalFormat("#,##0.000");
			DecimalFormat df2 = new DecimalFormat(	"###,##0.00");
			CustomerBean customerBean = (CustomerBean) session.getAttribute("user");
			//PositionBean[] fundList = positionDAO.match(MatchArg.equals("customerId",customerBean.getCustomerId()));
			//request.setAttribute("fundList",fundList);

			PositionBean[] positionList = positionDAO.match(MatchArg.equals("customerId",customerBean.getCustomerId()));
			
			if(positionList != null) {
				List<PositionInfo> positionInfoList = new ArrayList<PositionInfo>();
				for(PositionBean a: positionList) {
					double shares = ((double)(a.getShares())/1000.0);

					double price = ((double)(historyDAO.getLatestFundPrice(a.getFundId()).getPrice() / 100.0));
					double value = shares * price;
					String name=fundDAO.read(a.getFundId()).getName();

					String sharesString = df3.format(shares);
					String priceString = df2.format(price);
					String valueString = df2.format(value);

					PositionInfo aInfo = new PositionInfo(name,sharesString,priceString,"$" + valueString);
					positionInfoList.add(aInfo);
				}
				session.setAttribute("positionInfoList",positionInfoList);
			}

			if (!sellFundForm.isPresent()) {
				return "sellFund.jsp";
			}

			errors.addAll(sellFundForm.getValidationErrors());
			if (errors.size() != 0) {
				return "sellFund.jsp";
			}
			String userName = customerBean.getUserName();
			int customerId = customerBean.getCustomerId();

			// Get the fund ID of the fund name in form
			FundBean fundBean = fundDAO.read(sellFundForm.getName());
			if (fundBean == null) {
				errors.add("Fund does not exist");
				return "sellFund.jsp";
			}
			int fundId = fundBean.getFundId();
			// How to determine whether this customer own this fund or not
			PositionBean position = positionDAO.read(customerId, fundId);
			if (position == null) {
				errors.add("You do not own this fund!");
				return "sellFund.jsp";
			}
			double curShares = (double)position.getShares() / 1000;
			double shares =  Double.parseDouble(sellFundForm.getShares());
			if (shares == 0) {
				errors.add("You can not sell zero shares");
				return "sellFund.jsp"; 
			}
			if ((shares * 1000.0 - (long) (shares * 1000.0)) > 0) {
				errors.add("We only allow at most three decimal for shares");
				return "sellFund.jsp"; 
			}
			
			//Check valid shares
			if (curShares < shares) {
				errors.add("You do not have enough shares!");
				return "sellFund.jsp";				
			}
			double validShares = transactionDAO.getValidShares(customerId, fundId, curShares);
			if (shares > validShares) {
				errors.add("You do not have enough shares! (including pending transaction)");
				return "sellFund.jsp";
			}

			// Create a transaction bean
			TransactionBean transactionBean = new TransactionBean();
			transactionBean.setCustomerId(customerId);
			transactionBean.setFundId(fundId);
			transactionBean.setUserName(userName);
			transactionBean.setShares((long) (shares * 1000l));
			transactionBean.setTransactionType("4");

			transactionDAO.create(transactionBean);
			return "success-customer.jsp";
			
		} catch (NumberFormatException e) {
			errors.add("Either the number is too long or it is not a number. Please enter a valid number.");
			return "sellFund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "sellFund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "sellFund.jsp";
		}
	}

}
