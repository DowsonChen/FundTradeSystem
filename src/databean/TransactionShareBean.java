package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionShareBean /* implements Comparable<TransactionBean> */ {
        private int customerId;
        private int transactionId;
        private int fundId;
        private String fundName;
        private String executeDate;
        private double shares;
        private double sharePrice;
        private String transactionType;
        private double amount;

        public int getCustomeId() {
                return customerId;
        }

        public void setCustomeId(int customeId) {
                this.customerId = customeId;
        }

        public int getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(int transactionId) {
                this.transactionId = transactionId;
        }

        public int getFundId() {
                return fundId;
        }

        public void setFundId(int fundId) {
                this.fundId = fundId;
        }

        public String getFundName() {
                return fundName;
        }

        public void setFundName(String fundName) {
                this.fundName = fundName;
        }

        public String getExecuteDate() {
                return executeDate;
        }

        public void setExecuteDate(String executeDate) {
                this.executeDate = executeDate;
        }

        public double getShares() {
                return shares;
        }

        public void setShares(double shares) {
                this.shares = shares;
        }

        public double getSharePrice() {
                return sharePrice;
        }

        public void setSharePrice(double sharePrice) {
                this.sharePrice = sharePrice;
        }

        public String getTransactionType() {
                return transactionType;
        }

        public void setTransactionType(String transactionType) {
                this.transactionType = transactionType;
        }

        public double getAmount() {
                return amount;
        }

        public void setAmount(double amount) {
                this.amount = amount;
        }
}
