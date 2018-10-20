package com.joh.ahms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.ahms.domain.model.NotificationD;
import com.joh.ahms.service.ReportService;

@Controller()
@RequestMapping(path = "/notifications")
public class NotificationController {

	private static final Logger logger = Logger.getLogger(NotificationController.class);

	@Autowired
	private ReportService reportService;

	@GetMapping(path = "/pharmacy")
	public String getPharmacyNotification(Model model) {
		logger.info("getAllNotification->fired");

		List<NotificationD> notificationDs = reportService.findPharmacyNotifications();

		model.addAttribute("notificationDs", notificationDs);

		return "pharmacyNotificatons";
	}

	@GetMapping(path = "/stock")
	public String getStockNotification(Model model) {
		logger.info("getStockNotification->fired");

		List<NotificationD> notificationDs = reportService.findStockNotifications();

		model.addAttribute("notificationDs", notificationDs);

		return "stockNotificatons";
	}

	@GetMapping(path = "/revenue")
	public String getAllRevenue(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllRevenue->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);
		model.addAttribute("revenues", reportService.findAllRevenue(from, to));

		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "adminAllRevenue";
	}

}
