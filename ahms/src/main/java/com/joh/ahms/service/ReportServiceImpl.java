package com.joh.ahms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.ahms.dao.ReportDAO;
import com.joh.ahms.domain.model.NotificationD;
import com.joh.ahms.domain.model.RevenueD;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;

	@Override
	public List<NotificationD> findPharmacyNotifications() {
		return reportDAO.findPharmacyNotifications();
	}

	@Override
	public List<NotificationD> findStockNotifications() {
		return reportDAO.findStockNotifications();
	}

	@Override
	public List<RevenueD> findAllRevenue(Date from, Date to) {
		return reportDAO.findAllRevenue(from, to);
	}

}
