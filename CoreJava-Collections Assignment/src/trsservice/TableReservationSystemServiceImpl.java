package trsservice;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Scanner;

import trspojo.Reservation;

public class TableReservationSystemServiceImpl implements TrsService, Runnable {

	Scanner scan = new Scanner(System.in);

	boolean booleanb = false;
	boolean flag = true;

	ArrayList<Reservation> arrayList = new ArrayList<>();
	ListIterator<Reservation> listIterator;

	// taking file data as arrayList
	public void fileToArray() {
		try {
			String path = "C:/Assignment/Reservation.txt";
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			arrayList = (ArrayList<Reservation>) ois.readObject();
			ois.close();
		} catch (Exception e) {
		}
	}

	// writing to file
	public void writeFile() {
		try {
			File file = new File("C:\\Assignment\\Reservation.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrayList);
			oos.close();
		} catch (Exception e) {
		}
	}

	// reading from the file
	public void readFile() {
		try {
			String path = "C:\\Assignment\\Reservation.txt";
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			System.out.println(ois.readObject());
			System.out.println();
			ois.close();
		} catch (Exception e) {
		}
	}

	// Case 1
	@Override
	public void CreateReservation() {

		this.fileToArray();

		Scanner scan1 = new Scanner(System.in);
		String yesno = null;

		do {
			boolean valid = true;
			int ReservationId = 0;
			// adding reservation details
			ID: while (valid = true) {
				try {
					System.out.println("Reservation ID :");
					ReservationId = scan1.nextInt();
					listIterator = arrayList.listIterator();
					while (listIterator.hasNext()) {
						Reservation r = (Reservation) listIterator.next();
						if (r.getReservationId() == ReservationId) {
							System.out.println("ID already exists!. Enter a new ID");
							continue ID;
						}
					}

					if (String.valueOf(ReservationId).length() > 8 || String.valueOf(ReservationId).length() < 8) {
						System.out.println("Enter valid input !!");
						continue ID;
					} else
						break;

				} catch (Exception e) {
					scan1.nextLine();
					System.out.println("Enter valid input !!");
					continue ID;
				}
			}
			scan1.nextLine();

			System.out.println("Customer Name :");
			String CustomerName = scan1.nextLine();

			System.out.println("Reservation Description :");
			String ReservDescp = scan1.nextLine();

			System.out.println("Reservation Date (yyyy-MM-dd HH:mm):");

			LocalDateTime ReservDate = null;
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			date: while (booleanb = true) {
				try {
					ReservDate = LocalDateTime.parse(scan1.nextLine(), dtf);
					if (ReservDate.isBefore(LocalDateTime.now())) {
						System.out.println("Enter valid date");
						continue date;
					} else
						break;
				} catch (Exception datetime) {
					System.out.println("Enter valid date!");
					continue date;
				}
			}

			int Adults = 0;
			int Children = 0;
			booleanb = true;

			System.out.println("No of Adults :");
			adults: while (booleanb = true) {
				try {
					Adults = scan1.nextInt();
					if (Adults < 1) {
						System.out.println("Enter valid input");
						continue adults;
					} else
						break;

				} catch (Exception ad) {
					scan1.nextLine();
					System.out.println("Enter only number value");
					continue adults;
				}
			}

			System.out.println("No of Children :");
			children: while (booleanb = true) {
				try {
					Children = scan1.nextInt();
					if (Children < 0) {
						System.out.println("Enter valid input");
						continue children;
					} else
						break;

				} catch (Exception ch) {
					scan1.nextLine();
					System.out.println("Enter only number value");
					continue children;
				}
			}

			String Status = "Booked";
			System.out.println("Booking Status : " + Status + "\n");

			double SubTotAm = 500 * (Adults) + 300 * (Children);
			double DiscAm = 0;

			// if wednesday then 25% discount
			if (DayOfWeek.WEDNESDAY == ReservDate.getDayOfWeek())
				DiscAm = 0.25 * SubTotAm;
			else
				DiscAm = 0;

			double TaxAm = 0.05 * (SubTotAm - DiscAm);
			double TotalAm = (SubTotAm - DiscAm) + TaxAm;

			arrayList.add(new Reservation(ReservationId, CustomerName, ReservDescp, ReservDate, Adults, Children,
					Status, SubTotAm, DiscAm, TaxAm, TotalAm));

			this.writeFile();

			System.out.println("Reservation added successfully\n");

			System.out.println("Reservation ID :" + ReservationId + "\nSub Total Amount :" + SubTotAm
					+ "\nDiscount Amount :" + DiscAm + "\nTax Amount :" + TaxAm + "\nTotal Amount :" + TotalAm + "\n");
			System.out.println("Do you want to add more Reservations? (Y/N)");

			boolean flag = true;
			res: while (flag) {
				yesno = scan1.next();
				if (yesno.equalsIgnoreCase("Y") || yesno.equalsIgnoreCase("N")) {
					booleanb = true;
					break;
				} else {
					System.out.println("Enter valid input");
					continue res;
				}
			}
		} while (yesno.equalsIgnoreCase("Y"));
	}

	// CASE 2
	@Override
	public void ViewReservation() {
		// display all the reservation details
		this.fileToArray();
		listIterator = arrayList.listIterator();
		while (!listIterator.hasNext()) {
			System.out.println("No Record");
			break;
		}
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}
	}

	// CASE 3
	@Override
	public void ViewByID() {

		Scanner scan2 = new Scanner(System.in);
		boolean ID;
		int ReservationId = 0;
		byID: while (ID = true) {
			System.out.println("Enter the Reservation ID");
			try {
				ReservationId = scan2.nextInt();
				break;
			} catch (Exception e) {
				scan2.nextLine();
				System.out.println("Enter a valid input");
				continue byID;
			}

		}

		listIterator = arrayList.listIterator();
		boolean found = false;
		while (listIterator.hasNext()) {
			Reservation r = (Reservation) listIterator.next();
			if (r.getReservationId() == ReservationId) {
				System.out.println(
						"------------------------------------------------------------------------------------------");
				System.out.println(r);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Record not found !!\n");
		}

	}

	// CASE 4
	@Override
	public void Sort()

	{
		Scanner scan3 = new Scanner(System.in);
		int choose = 0;

		loop: do {
			System.out.println("1. Sort by Reservation ID");
			System.out.println("2. Sort by Reservation description");
			System.out.println("3. Sort by Reservation Date");
			System.out.println("4. Sort by Total Amount");
			System.out.println("5. Go Back");
			System.out.println();
			System.out.println("Enter Option no");

			try {
				choose = scan3.nextInt();
			} catch (Exception e) {
				scan3.nextLine();
				System.out.println("Enter a valid input");
				continue loop;
			}

			loop1: switch (choose) {

			// sort by Reservation ID
			case 1:
				System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
				String type = scan.next();
				while (flag) {
					if (type.equalsIgnoreCase("A") || type.equalsIgnoreCase("B")) {
						flag = true;
						break;
					} else {
						System.out.println("Incorrect input. Try again\n");
						continue loop;
					}
				}

				this.fileToArray();
				Collections.sort(arrayList, new Comparator<Reservation>() {

					@Override
					public int compare(Reservation r1, Reservation r2) {
						if (type.equalsIgnoreCase("A")) {
							return r1.getReservationId() - r2.getReservationId();
						} else
							return r2.getReservationId() - r1.getReservationId();
					}
				});

				listIterator = arrayList.listIterator();
				while (listIterator.hasNext()) {
					System.out.println(listIterator.next());
				}
				System.out.println();
				continue loop;

			// sort by reservation description
			case 2:
				System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
				this.fileToArray();
				String type1 = scan.next();
				while (flag) {
					if (type1.equalsIgnoreCase("A") || type1.equalsIgnoreCase("B")) {
						flag = true;
						break;
					} else {
						System.out.println("Incorrect input. Try again\n");
						continue loop;
					}
				}

				Collections.sort(arrayList, new Comparator<Reservation>() {

					@Override
					public int compare(Reservation r1, Reservation r2) {
						if (type1.equalsIgnoreCase("A"))
							return r1.getReservDescp().compareTo(r2.getReservDescp());
						else
							return r2.getReservDescp().compareTo(r1.getReservDescp());
					}
				});
				listIterator = arrayList.listIterator();
				while (listIterator.hasNext()) {
					System.out.println(listIterator.next());
				}
				System.out.println();
				continue loop;

			// sort by Reservation Date
			case 3:
				System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
				this.fileToArray();
				String type2 = scan.next();
				while (flag) {
					if (type2.equalsIgnoreCase("A") || type2.equalsIgnoreCase("B")) {
						flag = true;
						break;
					} else {
						System.out.println("Incorrect input. Try again\n");
						continue loop;
					}
				}
				Collections.sort(arrayList, new Comparator<Reservation>() {

					@Override
					public int compare(Reservation r1, Reservation r2) {
						if (type2.equalsIgnoreCase("A")) {
							return r1.getReservDate().compareTo(r2.getReservDate());
						} else {
							return r2.getReservDate().compareTo(r1.getReservDate());
						}

					}
				});
				listIterator = arrayList.listIterator();
				while (listIterator.hasNext()) {
					System.out.println(listIterator.next());
				}
				System.out.println();
				continue loop;

			// sort by total amount
			case 4:
				System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
				this.fileToArray();
				String type3 = scan.next();
				while (flag) {
					if (type3.equalsIgnoreCase("A") || type3.equalsIgnoreCase("B")) {
						break;
					} else {
						System.out.println("Incorrect input. Try again\n");
						continue loop;
					}
				}
				Collections.sort(arrayList, new Comparator<Reservation>() {

					@Override
					public int compare(Reservation r1, Reservation r2) {
						if (type3.equalsIgnoreCase("A")) {
							return (int) (r1.getTotalAm() - r2.getTotalAm());

						} else {
							return (int) (r2.getTotalAm() - r1.getTotalAm());
						}
					}
				});

				listIterator = arrayList.listIterator();
				while (listIterator.hasNext()) {
					System.out.println(listIterator.next());
				}
				System.out.println();
				continue loop;
			case 5:
				break loop;

			default:
				System.out.println("Enter a valid Option no !");
				continue loop;
			}
		} while (choose != 5);
	}

	@Override
	public void Delete() {
		// delete a reservation
		Scanner scan4 = new Scanner(System.in);
		String del = null;

		do {
			boolean ID;
			int ReservationId = 0;

			byID: while (ID = true) {
				System.out.println("Enter the Reservation ID to delete");
				try {
					ReservationId = scan4.nextInt();
					break;
				}

				catch (Exception e) {
					scan4.nextLine();
					System.out.println("Enter a valid input");
					continue byID;
				}
			}
			// check if ID matches the input value
			listIterator = arrayList.listIterator();
			boolean found = false;

			while (listIterator.hasNext()) {
				Reservation r = (Reservation) listIterator.next();
				if (r.getReservationId() == ReservationId) {
					listIterator.remove();
					System.out.println("Reservation " + r.getReservationId() + " deleted successfully");
					found = true;
					break;
				}
			}
			while (!found) {
				System.out.println("No record found");
				break;
			}
			this.writeFile();

			System.out.println("Do you want to DELETE more reservation? (Y/N)");
			del: while (flag = true) {

				del = scan4.next();
				if (del.equalsIgnoreCase("Y") || del.equalsIgnoreCase("N")) {
					booleanb = true;
					break;
				} else {
					System.out.println("Enter valid input");
					continue del;
				}
			}
		} while (del.equalsIgnoreCase("Y"));
	}

	@Override
	public void Cancel() {

		this.fileToArray();
		Scanner scan5 = new Scanner(System.in);
		String can = null;

		do {
			System.out.println("Enter Reservation ID to cancel");
			int ReservationId = 0;
			del: while (flag = true) {
				try {
					ReservationId = scan5.nextInt();
					break;
				} catch (Exception ex) {
					scan5.nextLine();
					System.out.println("Enter valid input");
					continue del;
				}
			}

			listIterator = arrayList.listIterator();
			Reservation r = null;
			boolean found = false;

			while (listIterator.hasNext()) {
				r = (Reservation) listIterator.next();
				while (r.getReservationId() == ReservationId) {
					found = true;
					if (r.getStatus().equalsIgnoreCase("cancelled")) {
						System.out.println("Reservation already cancelled!");
						break;
					} else {
						r.setStatus("Cancelled");
						this.writeFile();
						System.out.println("Reservation " + r.getReservationId() + " cancelled successfully");
						System.out.println("Amount " + r.getTotalAm() + " will be refunded within 24 hours");
						break;
					}
				}
			}
			while (!found) {
				System.out.println("No record found");
				break;
			}

			System.out.println("Do you want to CANCEL more reservation? (Y/N)");
			can: while (flag = true) {
				try {
					can = scan5.next();
					break;
				} catch (Exception c) {
					scan5.nextLine();
					System.out.println("Enter valid input");
					continue can;
				}
			}
		} while (can.equalsIgnoreCase("Y"));

	}

	@Override
	public void Confirm() {

		this.fileToArray();
		Scanner scan6 = new Scanner(System.in);
		String conf = null;
		do {
			System.out.println("Enter Reservation ID to confirm");
			int ReservationId = 0;
			del: while (flag = true) {
				try {
					ReservationId = scan6.nextInt();
					break;
				} catch (Exception ex) {
					scan6.nextLine();
					System.out.println("Enter valid input");
					continue del;
				}
			}

			listIterator = arrayList.listIterator();
			Reservation re = null;
			boolean found = false;

			while (listIterator.hasNext()) {
				re = (Reservation) listIterator.next();
				if (re.getReservationId() == ReservationId) {
					found = true;
					if (re.getStatus().equalsIgnoreCase("confirmed")) {
						System.out.println("Reservation already confirmed!");
						break;
					} else {
						re.setStatus("Confirmed");
						this.writeFile();
						System.out.println("Reservation " + re.getReservationId() + " confirmed !");
						break;
					}
				}
			}

			while (!found) {
				System.out.println("No record found");
				break;
			}

			System.out.println("Do you want to CONFIRM more reservation? (Y/N)");
			con: while (flag = true) {
				try {
					conf = scan6.next();
					break;
				} catch (Exception con) {
					scan6.nextLine();
					System.out.println("Enter valid input");
					continue con;
				}
			}
		} while (conf.equalsIgnoreCase("Y"));
	}

	@Override
	public void Report() {

		TableReservationSystemServiceImpl trsServiceImpl = new TableReservationSystemServiceImpl();
		Thread reportGenerateThread = new Thread(trsServiceImpl);

		reportGenerateThread.start();

	}

	@Override
	public void run() {

		ArrayList<Reservation> arrayList = new ArrayList<>();
		String path = "C:\\Assignment\\Reservation.txt";
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			arrayList = (ArrayList<Reservation>) ois.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate date = LocalDate.now();
		File newfile = new File("C:\\Assignment\\Reservation_Report_" + date + ".txt");
		Scanner scan7 = new Scanner(System.in);
		int select = 0;

		Export: while (flag = true) {
			System.out.println("1. Export all");
			System.out.println("2. Export by status");
			System.out.println("3. Go Back");
			expo: while (flag = true) {
				try {
					select = scan7.nextInt();
					break;
				} catch (Exception ex) {
					scan7.nextLine();
					System.out.println("Enter a valid number");
					continue expo;
				}
			}
			switch (select) {

			case 1:
				FileWriter writer;
				try {
					this.fileToArray();
					writer = new FileWriter(newfile);
					for (Reservation resv : arrayList) {
						writer.write(resv + System.lineSeparator());
					}
					System.out.println("Report generated successfully.\n");
					writer.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case 2:
				System.out.println("Enter the option no");
				System.out.println("\t1. Booked");
				System.out.println("\t2. Cancelled");
				System.out.println("\t3. Confirmed");
				System.out.println("\t4. Go Back");

				try {
					int sel = 0;
					st: while (flag = true) {
						try {
							sel = scan7.nextInt();
							break;
						} catch (Exception e) {
							scan7.nextLine();
							System.out.println("Enter only number values");
							continue st;
						}
					}

					boolean found = false;
					exp: switch (sel) {
					case 1:
						FileWriter write = new FileWriter(newfile);

						this.fileToArray();
						for (Reservation re : arrayList) {
							while (re.getStatus().equalsIgnoreCase("booked")) {
								write.write(re + System.lineSeparator());
								found = true;
								break;
							}
						}
						if (!found) {
							System.out.println("No record found!");
							break;
						}

						write.close();
						System.out.println("Report generated successfully.\n");
						break exp;

					case 2:
						FileWriter writer1 = new FileWriter(newfile);

						this.fileToArray();
						for (Reservation re : arrayList) {
							while (re.getStatus().equalsIgnoreCase("cancelled")) {
								writer1.write(re + System.lineSeparator());
								found = true;
								break;
							}
						}
						if (!found) {
							System.out.println("No record found!");
							break exp;
						}

						writer1.close();
						System.out.println("Report generated successfully.\n");
						break exp;

					case 3:
						FileWriter writer2 = new FileWriter(newfile);

						this.fileToArray();
						for (Reservation re : arrayList) {
							while (re.getStatus().equalsIgnoreCase("confirmed")) {
								writer2.write(re + System.lineSeparator());
								found = true;
								break;
							}
						}
						if (!found) {
							System.out.println("No record found!");
							break exp;
						}

						writer2.close();
						System.out.println("Report generated successfully.\n");
						break exp;

					case 4:
						break Export;

					default:
						System.out.println("Enter a valid input");
						break exp;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				break Export;
			default:
				System.out.println("Enter valid input");
				continue Export;

			}
			while (select < 0 || select > 3) {
				System.out.println("Enter valid option !!");
				break;
			}
		}
	}

}
