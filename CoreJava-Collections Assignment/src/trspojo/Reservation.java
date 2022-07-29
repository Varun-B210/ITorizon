package trspojo;

import java.io.Serializable;

import java.time.LocalDateTime;

public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ReservationId;
	private String CustomerName;
	private String ReservDescp;
	private LocalDateTime ReservDate;
	private int Adults;
	private int Children;
	private String Status;
	private double SubTotAm;
	private double DiscAm;
	private double TaxAm;
	private double TotalAm;

	public Reservation(int reservationId, String customerName, String reservDescp, LocalDateTime reservDate, int adults,
			int children, String status, double subTotAm, double discAm, double taxAm, double totalAm) {
		ReservationId = reservationId;
		CustomerName = customerName;
		ReservDescp = reservDescp;
		ReservDate = reservDate;
		Adults = adults;
		Children = children;
		Status = status;
		SubTotAm = subTotAm;
		DiscAm = discAm;
		TaxAm = taxAm;
		TotalAm = totalAm;
	}

	public int getReservationId() {
		return ReservationId;
	}

	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getReservDescp() {
		return ReservDescp;
	}

	public void setReservDescp(String reservDescp) {
		ReservDescp = reservDescp;
	}

	public LocalDateTime getReservDate() {
		return ReservDate;
	}

	public void setReservDate(LocalDateTime reserveDate) {
		ReservDate = reserveDate;
	}

	public int getAdults() {
		return Adults;
	}

	public void setAdults(int adults) {
		Adults = adults;
	}

	public int getChildren() {
		return Children;
	}

	public void setChildren(int children) {
		Children = children;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public double getSubTotAm() {
		return SubTotAm;
	}

	public void setSubTotAm(double subTotAm) {
		SubTotAm = subTotAm;
	}

	public double getDiscAm() {
		return DiscAm;
	}

	public void setDiscAm(double discAm) {
		DiscAm = discAm;
	}

	public double getTaxAm() {
		return TaxAm;
	}

	public void setTaxAm(double taxAm) {
		TaxAm = taxAm;
	}

	public double getTotalAm() {
		return TotalAm;
	}

	public void setTotalAm(double totalAm) {
		TotalAm = totalAm;
	}

	@Override
	public String toString() {
		return "<-- Reservation Id = " + ReservationId + " -->\nCustomer Name = " + CustomerName
				+ "\nReservation Description = " + ReservDescp + "\nReservation Date = " + ReservDate
				+ "\nNo of Adults = " + Adults + "\nNo of Children = " + Children + "\nBooking Status = " + Status
				+ "\nSub Total Amount = " + SubTotAm + "\nDiscount = " + DiscAm + "\nTax Amount = " + TaxAm
				+ "\nTotal Amount = " + TotalAm
				+ "\n------------------------------------------------------------------------------------------\n";
	}

}
