package com.joh.ahms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.joh.ahms.domain.model.NotificationD;
import com.joh.ahms.domain.model.NotificationD.NotificationType;
import com.joh.ahms.domain.model.RevenueD;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findPharmacyNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em
				.createNativeQuery("SELECT SUM(QUANTITY-SOLD_QUANTITY) AS EXPIRATE\n" + "FROM phms.PRODUCT_STEPUPS\n"
						+ "WHERE EXPIRATION_DATE<=CURDATE()+INTERVAL 90 DAY\n" + "AND QUANTITY-SOLD_QUANTITY>0");

		Object totalExpirationResult = query.getSingleResult();

		int totalExpiration = 0;
		if (totalExpirationResult != null)
			totalExpiration = Integer.parseInt("" + totalExpirationResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Product Expiration");
		not1.setEtc("" + totalExpiration);
		not1.setMessage("Number of Product is About to be expired in stock in next 90 days ");

		not1.setNotificationType(NotificationType.DANGER);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT ROUND(IFNULL(SUM(TOTAL_PRICE),0),3) FROM phms.CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerPriceResult = query.getSingleResult();

		double totalTodayCustomerPrice = 0;

		if (totalTodayCustomerPriceResult != null)
			totalTodayCustomerPrice = Double.parseDouble("" + totalTodayCustomerPriceResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total Customer Price");
		not2.setEtc("" + totalTodayCustomerPrice);
		not2.setMessage("Total customer price without discount");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		// Notification-3

		query = em.createNativeQuery(
				"SELECT ROUND(SUM(TOTAL_PRICE)-IFNULL(SUM(TOTAL_PRICE*DISCOUNT_AMOUNT),0),3) FROM phms.CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerPriceResultWithDiscount = query.getSingleResult();

		double totalTodayCustomerPriceWithDiscount = 0;

		if (totalTodayCustomerPriceResultWithDiscount != null)
			totalTodayCustomerPriceWithDiscount = Double.parseDouble("" + totalTodayCustomerPriceResultWithDiscount);

		NotificationD not3 = new NotificationD();
		not3.setTitle("Today Total Customer Order Income ");
		not3.setEtc("" + totalTodayCustomerPriceWithDiscount);
		not3.setMessage("Total customer price after make discount");

		not3.setNotificationType(NotificationType.INFO);

		notificationDs.add(not3);

		// Notification-4

		query = em.createNativeQuery(
				"SELECT ROUND(SUM(TOTAL_PRICE*DISCOUNT_AMOUNT),3) FROM phms.CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerDiscountResult = query.getSingleResult();

		double totalTodayCustomerDiscount = 0;

		if (totalTodayCustomerDiscountResult != null)
			totalTodayCustomerDiscount = Double.parseDouble("" + totalTodayCustomerDiscountResult);

		NotificationD not4 = new NotificationD();
		not4.setTitle("Today Total Customer Discount ");
		not4.setEtc("" + totalTodayCustomerDiscount);
		not4.setMessage("Total discount made to customer");

		not4.setNotificationType(NotificationType.INFO);

		notificationDs.add(not4);

		// Notification-5

		query = em.createNativeQuery(
				"SELECT  ROUND(IFNULL(SUM(TOTAL_PAYMENT_AMOUNT),0)) FROM phms.ORDER_PRODUCT_STEPUPS WHERE DATE(ORDER_TIME)=CURDATE()");

		Object totalProductStepUpPaymentamountResult = query.getSingleResult();

		double totalProductStepUpPaymentamount = 0;

		if (totalProductStepUpPaymentamountResult != null)
			totalProductStepUpPaymentamount = Double.parseDouble("" + totalProductStepUpPaymentamountResult);

		NotificationD not5 = new NotificationD();
		not5.setTitle("Today total Stockup Payment Amount");
		not5.setEtc("" + totalProductStepUpPaymentamount);
		not5.setMessage("The total today order amount payment");

		not5.setNotificationType(NotificationType.INFO);

		notificationDs.add(not5);

		return notificationDs;

	}

	public List<NotificationD> findStockNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em
				.createNativeQuery("SELECT IFNULL(SUM(IFNULL(QUANTITY,0)-IFNULL(SOLD_AMOUNT,0)),0) AS EXPIRATE\n"
						+ "FROM shms.ORDER_DETAILS WHERE EXPIRATION_DATE<=CURDATE()+INTERVAL 90 DAY AND QUANTITY-SOLD_AMOUNT>0;");

		Object totalExpirationResult = query.getSingleResult();

		int totalExpiration = 0;
		if (totalExpirationResult != null)
			totalExpiration = Integer.parseInt("" + totalExpirationResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Product Expiration");
		not1.setEtc("" + totalExpiration);
		not1.setMessage("Number of Product is About to be expired in stock in next 90 days ");

		not1.setNotificationType(NotificationType.DANGER);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery("SELECT  ROUND(IFNULL(SUM(TOTAL_PAYMENT),0),3)\n"
				+ "FROM shms.ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayOrderPaymentResult = query.getSingleResult();

		double totalTodayOrderPayment = 0;

		if (totalTodayOrderPaymentResult != null)
			totalTodayOrderPayment = Double.parseDouble("" + totalTodayOrderPaymentResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total Order Payment");
		not2.setEtc("" + totalTodayOrderPayment);
		not2.setMessage("Total payment amount paied to buy goods");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		return notificationDs;

	}

	public List<RevenueD> findAllRevenue(Date from, Date to) {
		List<RevenueD> revenueDs = new ArrayList<>();
		Query query = em
				.createNativeQuery("SELECT JDATE,IFNULL(SUM(EXPENSE_AMOUNT),0) EXPENSE ,IFNULL(SUM(INCOME_AMOUNT),0) \n"
						+ "INCOME FROM (SELECT EXPENSE_AMOUNT,DATE(EXPENSE_TIME) JDATE  FROM EXPENSES WHERE EXPENSE_TIME BETWEEN ? AND ? ) E\n"
						+ "RIGHT OUTER JOIN (SELECT INCOME_AMOUNT,DATE(INCOME_TIME) JDATE  FROM INCOMES  WHERE INCOME_TIME BETWEEN ? AND ? ) I USING (JDATE)\n"
						+ "GROUP BY JDATE ORDER BY JDATE DESC;");

		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		query.setParameter(3, from, TemporalType.DATE);
		query.setParameter(4, to, TemporalType.DATE);

		List<Object[]> resultList = query.getResultList();

		for (Object columns[] : resultList) {

			RevenueD revenueD = new RevenueD();

			revenueD.setDate((Date) columns[0]);
			revenueD.setTotalExpense(Double.parseDouble("" + columns[1]));
			revenueD.setTotalIncome(Double.parseDouble("" + columns[2]));

			revenueDs.add(revenueD);
		}
		return revenueDs;
	}

}
