package databean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.genericdao.PrimaryKey;

import databean.TransactionBean;

@PrimaryKey("transactionId")
public class TransactionBean implements Comparable<TransactionBean>  {

        private int customerId;
		public final static int BUY_FUND = 8;
		public final static int SELL_FUND = 4;
		public final static int REQUEST_CHECK = 2;
		public final static int DEPOSIT_CHECK = 1;

        private int transactionId;
        private String userName;
        private int fundId;
        private String executeDate;
        private long shares;
        private String transactionType;
        private long amount;

        public int getCustomerId() {
                return customerId;
        }

        public void setCustomerId(int customer_id) {
                this.customerId= customer_id;
        }


		public void setTransactionId(int v) {
                transactionId = v;
        }

        public void setUserName(String v) {
                userName = v;
        }

        public void setFundId(int v) {
                fundId = v;
        }

        public void setExecuteDate(String today) {
                executeDate = today;
        }

        public void setShares(long v) {
                shares = v;
        }

        public void setTransactionType(String v) {
                transactionType = v;
        }

        public void setAmount(long v) {
                amount = v;
        }

        public int getTransactionId() {
                return transactionId;
        }

        public String getUserName() {
                return userName;
        }

        public int getFundId() {
                return fundId;
        }

        public String getExecuteDate() {
                return executeDate;
        }

        public long getShares() {
                return shares;
        }

        public String getTransactionType() {
                return transactionType;
        }

        public long getAmount() {
                return amount;
        }
        public int compareTo(TransactionBean tb) {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	dateFormat.setLenient(false);
    		try {
    			return dateFormat.parse(this.executeDate).compareTo(dateFormat.parse(tb.executeDate));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		return 0;
    	}
}
