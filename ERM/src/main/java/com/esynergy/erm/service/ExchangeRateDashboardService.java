package com.esynergy.erm.service;

import com.esynergy.erm.model.ob.Chart;

public interface ExchangeRateDashboardService {
	public Chart getChartERManualByAddDateSystemDate();
	public Chart getChartERAutoByAddDateSystemDate();
}
