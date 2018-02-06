package com.esynergy.erm.service;

import java.util.Date;

import com.esynergy.erm.model.ob.ExchangeRateDetail;

public interface ExchangeRateDetailService {
	public void delete(ExchangeRateDetail o);
	public long getCountByDate(Date date);
}
