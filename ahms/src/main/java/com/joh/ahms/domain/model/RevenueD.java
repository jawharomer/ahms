package com.joh.ahms.domain.model;

import java.util.Date;

public class RevenueD {
	private Date date;
	private Double totalIncome;
	private Double totalExpense;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}

	@Override
	public String toString() {
		return "RevenueD [date=" + date + ", totalIncome=" + totalIncome + ", totalExpense=" + totalExpense + "]";
	}

}
