package trs;

import java.time.LocalTime;

import java.util.Scanner;

import trsservice.TableReservationSystemServiceImpl;
import trsservice.TrsService;

public class TableReservationSystem {

	public static void main(String[] args) throws Exception {
		TrsService obj = new TableReservationSystemServiceImpl();

		TableReservationSystemServiceImpl implement = new TableReservationSystemServiceImpl();
		Thread myThread = new Thread(implement);

		System.out.println("\t\t\t<<<<--**** TABLE RESERVATION SYSTEM ****-->>>>");
		System.out.println("\t\t\t-----------------------------------------------");

		int option = 0;
		boolean valid;

		LocalTime target = LocalTime.now();
		if (target.isAfter(LocalTime.of(7, 00, 00)) && target.isBefore(LocalTime.of(23, 59, 00)) == false) {
			System.out.println("\n!!! App is operational only between 7AM to 10PM. Please try later !!!.");
			System.exit(0);
		}

		menu: while (valid = true) {

			System.out.println("*** Main Menu ***\n");
			System.out.println("1. Create Reservation");
			System.out.println("2. View Reservation");
			System.out.println("3. View Reservation By ID");
			System.out.println("4. Sort Reservation");
			System.out.println("5. Delete Reservation By ID");
			System.out.println("6. Cancel Reservation By ID");
			System.out.println("7. Confirm Reservation By ID");
			System.out.println("8. Generate Report");
			System.out.println("9. Exit");
			System.out.println();

			Scanner scan = new Scanner(System.in);

			System.out.println("Enter your choice");
			boolean flag;
			outer: while (flag = true) {
				try {
					option = scan.nextInt();
					if (option > 10 || option < 1) {
						System.out.println("Enter valid option !!");
						continue outer;
					} else
						break outer;
				} catch (Exception e) {
					scan.next();
					System.out.println("Enter valid option !!");
					continue outer;
				}
			}

			switch (option) {
			case 1:
				implement.CreateReservation();
				continue menu;

			case 2:
				implement.ViewReservation();
				continue menu;

			case 3:
				implement.ViewByID();
				continue menu;

			case 4:
				implement.Sort();
				continue menu;

			case 5:
				implement.Delete();
				continue menu;

			case 6:
				implement.Cancel();
				continue menu;

			case 7:
				implement.Confirm();
				continue menu;

			case 8:
				implement.run();
				continue menu;

			case 9:
				System.out.println("Thank You. See you next time!");
				break menu;

			default:
				System.out.println("Enter a valid option no !");
				break;
			}
		}

	}

}
