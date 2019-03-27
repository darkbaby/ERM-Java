package com.esynergy.erm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 





















import com.esynergy.erm.dao.BankOfRateDAO;
import com.esynergy.erm.dao.ExchangeRateAutoDAO;
import com.esynergy.erm.dao.ExchangeRateDAO;
import com.esynergy.erm.dao.ExchangeRateDetailDAO;
import com.esynergy.erm.dao.ExchangeRateManualDAO;
import com.esynergy.erm.dao.jdbc.ExchangeRateDashboardJdbc;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.BankOfRate;
import com.esynergy.erm.model.ob.CurrencyPairs;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.ExchangeRateDetailVeiwView;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.model.ob.ExchangeRateVeiwView;
import com.esynergy.erm.web.action.IPageContains;

@Service("exchangeRateService")
public class ExchangeRateServiceImpl implements ExchangeRateService {
	@Autowired private ExchangeRateDAO exchangeRateDAO;
	@Autowired private ExchangeRateDetailDAO exchangeRateDetailDAO;
	@Autowired private ExchangeRateDetailDAO exchangeRateManualDetailDAO;
	@Autowired private ExchangeRateAutoDAO exchangeRateAutoDAO;
	@Autowired private ExchangeRateManualDAO exchangeRateManualDAO;
	@Autowired private BankOfRateDAO bankOfRateDAO;
	@Autowired private ExchangeRateDashboardJdbc exchangeRateDashboardJdbc;
	@Override
	public List<ExchangeRateVeiwView> getByDate(Date date) {
		List<ExchangeRateVeiwView> exchangeRateVeiwViewList = new ArrayList<ExchangeRateVeiwView>();
		Map<String,Integer> hdrIdChkCurrencyMap = new HashMap<String,Integer>();
		List<ExchangeRateDetail>  exchangeRateDetails= exchangeRateDetailDAO.listByRateDate(date);
		pupulateExchangeRateVeiwViewList(exchangeRateVeiwViewList,exchangeRateDetails,hdrIdChkCurrencyMap);
 		
		return exchangeRateVeiwViewList;
	}
    @SuppressWarnings("unused")
	private void pupulateExchangeRateVeiwViewList(List<ExchangeRateVeiwView> exchangeRateVeiwViewList
    		,List<ExchangeRateDetail> exchangeRateDetails,Map<String,Integer> hdrIdChkCurrencyMap){
     
				for(ExchangeRateDetail dtl:exchangeRateDetails){
					ExchangeRate exchangeRate = dtl.getExchangeRate();
					String currency = dtl.getPairCurrency().getId() + " " + dtl.getFirstCurrency().getId();
//					String currency = dtl.getFirstCurrency().getCode() + dtl.getPairCurrency().getCode();
					ExchangeRateDetailVeiwView viewDtl = new ExchangeRateDetailVeiwView();
					if(!hdrIdChkCurrencyMap.containsKey(currency)){
						hdrIdChkCurrencyMap.put(currency, hdrIdChkCurrencyMap.size());
						ExchangeRateVeiwView exchangeRateVeiwView = new ExchangeRateVeiwView();
						exchangeRateVeiwViewList.add(exchangeRateVeiwView);
						exchangeRateVeiwView.setPairCurrency(dtl.getPairCurrency());
						exchangeRateVeiwView.setFirstCurrency(dtl.getFirstCurrency());
						exchangeRateVeiwView.setRateDate(exchangeRate.getRateDate());
						exchangeRateVeiwView.setPairCurrencyType(exchangeRate.getPariCurrencyType());
						exchangeRateVeiwView.addExchangeRateDetails(viewDtl);
					}else{
						int indexHdr = (Integer)hdrIdChkCurrencyMap.get(currency);
						ExchangeRateVeiwView viewHdr = exchangeRateVeiwViewList.get(indexHdr);
						viewHdr.addExchangeRateDetails(viewDtl);
					}
					 
					if(exchangeRate instanceof ExchangeRateAuto){
						viewDtl.setType(IPageContains.ER_ORIGIN_AUTO);
						try {
							ExchangeRateAuto auto = (ExchangeRateAuto) exchangeRate.clone();
							viewDtl.setBank(auto.getBankOfRate().iterator().next().getBank());
						/*	viewDtl.setBank(bankOfRateDAO.getByExchangeRateHdrId(dtl.getExchangeRate().getId()).getBank());*/
							
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						viewDtl.setType(IPageContains.ER_ORIGIN_MANUAL);
					}
					viewDtl.setBuyingRate(dtl.getBuyingRate());
					viewDtl.setSellingRate(dtl.getSellingRate());
					viewDtl.setValue(dtl.getValue());
					viewDtl.setAvgRate(dtl.getBuyingRate()==0||dtl.getSellingRate()==0?0:(dtl.getBuyingRate()+dtl.getSellingRate())/2);
					 
				} 
		 
    }
	@Override
	public IExchangeRate getById(long id) {
		IExchangeRate exchangeRate = exchangeRateDAO.getbyId(id);
		return exchangeRate;
	}

	@Override
	public List<ExchangeRateVeiwView> search(long pairCurrencyId, long baseCurrencyId,
			long countryOfBankId, String type, Date date) {
			List<ExchangeRateVeiwView> exchangeRateVeiwViewList = new ArrayList<ExchangeRateVeiwView>();
			Map<String,Integer> hdrIdChkCurrencyMap = new HashMap<String,Integer>();
			List<ExchangeRateDetail>  exchangeRateDetails= exchangeRateDetailDAO.search(pairCurrencyId, baseCurrencyId, countryOfBankId, type, date);
			pupulateExchangeRateVeiwViewList(exchangeRateVeiwViewList,exchangeRateDetails,hdrIdChkCurrencyMap);
		return exchangeRateVeiwViewList;
		 
	}
	@Override
	public List<CurrencyPairs> getRemainByDate(Date date) {
		Object[] parm = new Object[3]; 
		parm[0] = IPageContains.DATE_FORMAT.format(date);
		parm[1] = IPageContains.DATE_FORMAT.format(date);
		parm[2] = IPageContains.DATE_FORMAT.format(date);
		return exchangeRateDashboardJdbc.getRemainingExchangeRateManualByAddDate(parm);
	}
	 

}
