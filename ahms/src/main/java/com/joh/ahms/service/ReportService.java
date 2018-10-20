package com.joh.ahms.service;

import java.util.Date;
import java.util.List;

import com.joh.ahms.domain.model.NotificationD;
import com.joh.ahms.domain.model.RevenueD;

public interface ReportService {

	List<NotificationD> findPharmacyNotifications();

	List<NotificationD> findStockNotifications();

	List<RevenueD> findAllRevenue(Date from, Date to);

}
