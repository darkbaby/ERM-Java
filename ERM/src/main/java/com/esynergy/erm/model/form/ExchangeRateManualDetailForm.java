package com.esynergy.erm.model.form;


public class ExchangeRateManualDetailForm {
	private long id;
	private String firstCurrency;
	private String pairCurrency;
	private String firstCurrencyStatic;
	private String pairCurrencyStatic; 
	private String value;
	//Rate
	private String buyingRate;
	private String sellingRate;
	private String chk;
	
	
    public String getBuyingRate() {
		return buyingRate;
	}

	public void setBuyingRate(String buyingRate) {
		this.buyingRate = buyingRate;
	}

	public String getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(String sellingRate) {
		this.sellingRate = sellingRate;
	}

	/*	private Set<RateManual> rateList;
	
	public ExchangeRateManualDetail(){
		this.rateList = new HashSet<RateManual>();
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPairCurrency() {
		return pairCurrency;
	}

	public void setPairCurrency(String pairCurrency) {
		this.pairCurrency = pairCurrency;
	}

 

	public String getFirstCurrency() {
		return firstCurrency;
	}

	public void setFirstCurrency(String firstCurrency) {
		this.firstCurrency = firstCurrency;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getPairCurrencyStatic() {
		return pairCurrencyStatic;
	}

	public void setPairCurrencyStatic(String pairCurrencyStatic) {
		this.pairCurrencyStatic = pairCurrencyStatic;
	}

	public String getFirstCurrencyStatic() {
		return firstCurrencyStatic;
	}

	public void setFirstCurrencyStatic(String firstCurrencyStatic) {
		this.firstCurrencyStatic = firstCurrencyStatic;
	}

 

	 
	
}
