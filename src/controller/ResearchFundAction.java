package controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.*;
import formbean.*;
import model.*;


public class ResearchFundAction extends Action {
	private FormBeanFactory<ResearchFundForm> formBean = FormBeanFactory
			.getInstance(ResearchFundForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public ResearchFundAction(Model model) {
		this.fundDAO = model.getFundDAO();
		this.fundPriceHistoryDAO =model.getFundPriceHistoryDAO();

	}
	
	@Override
	public String getName() {
		return "researchFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		 
		
		try {
			if (request.getSession().getAttribute("user") == null || 
					request.getSession().getAttribute("user") instanceof EmployeeBean) {
				errors.add("Log in as a customer");
				return "login.jsp";
			}
			
			ResearchFundForm form = formBean.create(request);
			
			FundBean[] funds = fundDAO.match();
			
			request.setAttribute("funds", funds);
			
			if (!form.isPresent()) {
				return "researchFund.jsp";
			}
			
			request.setAttribute("form", form);
			request.setAttribute("description", "");
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				
				return "researchFund.jsp";
			}
			
			String fund = form.getFundName();
			
			
			FundBean[] tmp_fb = fundDAO.match(MatchArg.equalsIgnoreCase("name", fund));
			if (tmp_fb == null || tmp_fb.length == 0) {
				errors.add("Fund name " + fund + " does not exist");
				return "researchFund.jsp";
			}
			
			int fndId = tmp_fb[0].getFundId();
			
			if (form.getAction()!=null && form.getAction().equals("Research")) {
				
				
				List<Map<String,String>> fundPriceHistory = getFundPriceHistory(fndId); 
				if (fundPriceHistory.isEmpty()) 
					errors.add("There is no history for " + fundDAO.read(fndId).getName() + " fund");
				request.setAttribute("symbol", fundDAO.read(fndId).getSymbol());
				request.setAttribute("fundTitle", fundDAO.read(fndId).getName());
				request.setAttribute("chartData", chartData(fndId));
			}
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "researchFund.jsp";
		} catch (Exception e) {
			errors.add("");

			return "researchFund.jsp";
		}
		
			return "researchFund.jsp";	
		
	}

	
	
	private List<Map<String, String>> getFundPriceHistory(
			int fundId) throws RollbackException {
		List<Map<String,String>> fundPriceHistory = new ArrayList<Map<String,String>>();
		FundPriceHistoryBean[] fundPriceHistoryBean;
		
			fundPriceHistoryBean = fundPriceHistoryDAO. match(MatchArg.equals("fundId", fundId));
		if(fundPriceHistoryBean != null){
			for(FundPriceHistoryBean hBean: fundPriceHistoryBean){
				
				String id=Integer.toString(hBean.getFundId());
				Map<String,String> tmp = new HashMap<String,String>();
				tmp.put("fundId",id);
				NumberFormat formatter = new DecimalFormat("#,##0.00");
				String newPrice=formatter.format(hBean.getPrice()/100);
				tmp.put("price",newPrice);
				tmp.put("date",hBean.getExecuteDate());
				tmp.put("fundName", fundDAO.read(hBean.getFundId()).getName());
				
				fundPriceHistory.add(tmp);
			}
			return fundPriceHistory;
		}
		else{
			return fundPriceHistory;
		}

	}
	
	private String chartData(int fundId) throws RollbackException {
		FundPriceHistoryBean[] fundPriceHistoryBean = fundPriceHistoryDAO. match(MatchArg.equals("fundId",fundId));
		StringBuilder data = new StringBuilder();
		if(fundPriceHistoryBean != null){
			for(FundPriceHistoryBean hBean: fundPriceHistoryBean){
				data.append(hBean.getExecuteDate());
				data.append(",");
				data.append(((double)hBean.getPrice())/100);
				data.append(",");
			}
		}
		return data.toString();

	}
}
