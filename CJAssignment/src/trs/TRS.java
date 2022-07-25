package trs;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

interface Reservations{
	void CreateReservation();
	void ViewReservation();
	void ViewByID();
	void Sort();
	void DeleteByID();
	void CancelByID();
	void ConfirmByID();
	void Report();
}

public class TRS implements Reservations{
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, ParseException {
		
		System.out.println("\t\t\t<<<<--**** TABLE RESERVATION SYSTEM ****-->>>>");
		System.out.println("\t\t\t-----------------------------------------------");
		int option =0;
		LocalTime target = LocalTime.now();
		if(target.isAfter(LocalTime.of(7, 00, 00))&&target.isBefore(LocalTime.of(22, 00, 00))==false){
			System.out.println("\n!!! App is operational only between 7AM to 10PM. Please try later !!!.");
			System.exit(0);
		}
		
	do {
		System.out.println();
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
		System.out.println("Enter your choice");
		Scanner s = new Scanner(System.in);
		
		int flag = 1;
			outer:	while(flag==1) {
					try {
						option = s.nextInt();
						break;
					}catch(Exception e) {
						s.nextLine();
						System.out.println("Enter valid option");
						continue outer;
					}
					
				}
		
		ArrayList<Reservation> al = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		ListIterator<Reservation> li = null;
		File file = new File("C:\\Assignment\\Reservation.txt");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;


		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Reservation>) ois.readObject();
			ois.close();
		}
		
	switch(option) {
	
		case 1: String yn;
			    do {
					boolean valid = false;
					int ReservationId;
					do{
					System.out.print("Reservation ID :");
					ReservationId = sc.nextInt();
					li = al.listIterator();
					while(li.hasNext()) {
						Reservation r = (Reservation)li.next();
						if(r.getReservationId()== ReservationId){
						System.out.println("ID already exists!. Enter a new ID");
						valid = true;
						break;
					}
					if(valid=false)
						break;
					}
					}while(valid);
						
				System.out.print("Customer Name :");
				String CustomerName = sc1.nextLine();
				
				System.out.print("Reservation Description :");
				String ReservDescp = sc1.nextLine();
				
				System.out.print("Reservation Date (dd/mm/yyyy):");
			    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			    Date date = format.parse(sc1.nextLine());
			    String ReservDate = date.toGMTString();
			    
				System.out.print("No of Adults :");
				int Adults = sc.nextInt();
				
				System.out.print("No of Children :");
				int Children = sc.nextInt();
			
				String Status = "Booked";
				System.out.print("Booking Status : "+Status+"\n");
				
				double SubTotAm = 500*(Adults)+300*(Children);
				System.out.print("Sub Total Amount : "+SubTotAm+"\n");
				
				double DiscAm=0;;
				if(date.getDay()==3)
					DiscAm = 0.25*SubTotAm;
				else
					DiscAm = 0;
				System.out.print("Discount Amount :"+DiscAm+"\n");
				
				
				double TaxAm = 0.05*(SubTotAm-DiscAm);
				System.out.print("Tax Amount : "+TaxAm+"\n");
				
				double TotalAm = (SubTotAm-DiscAm)+TaxAm;
				System.out.print("Total Amount : "+TotalAm+"\n");
			
				al.add(new Reservation(ReservationId,CustomerName,ReservDescp,ReservDate,Adults,
						Children,Status,SubTotAm,DiscAm,TaxAm,TotalAm));
				
				try {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Reservation added successfully\n");
				System.out.println("Reservation ID :"+ReservationId+"\nSub Total Amount :"+SubTotAm+"\nDiscount Amount :"
						+DiscAm+"\nTax Amount :"+TaxAm+"\nTotal Amount :"+TotalAm+"\n");
				System.out.println("Do you want to add more Reservations? (Y/N)");
				yn = sc1.next();
				}
				while(yn.equalsIgnoreCase("Y"));
				break;
				
	case 2: 	li = al.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
					System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

				}	
				break;
				
	case 3:		System.out.println("Enter the Reservation ID");
				int ReservationId = s.nextInt();
				li = al.listIterator();
				boolean found = false;
				while(li.hasNext()) {
					Reservation r = (Reservation)li.next();
					if(r.getReservationId()== ReservationId){
						System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(r);
						System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						found = true;
					}
		
				}
				if(!found) {
					System.out.println("Record not found !!!");
				}
				break;
	case 4:		int choose;
				do {
				System.out.println("1. Sort by Reservation ID");
				System.out.println("2. Sort by Reservation description");
				System.out.println("3. Sort by Reservation Date");
				System.out.println("4. Sort by Total Amount");
				System.out.println("5. Go Back");
				System.out.println();
				System.out.println("Enter Option no");
				choose = sc.nextInt();
				
				switch(choose) {
				
				case 1: System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
						String type = sc1.nextLine();
						Collections.sort(al, new Comparator<Reservation>() {
		
							@Override
							public int compare(Reservation r1, Reservation r2) {
						if(type.equalsIgnoreCase("A")) {
						return r1.getReservationId()-r2.getReservationId();
						}else {
						return r2.getReservationId()-r1.getReservationId();
						}
						
						}
						});
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
							System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						}
						System.out.println();
						break;
						
				case 2: System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
						String type1 = sc1.nextLine();
						Collections.sort(al, new Comparator<Reservation>() {
		
							@Override
							public int compare(Reservation r1, Reservation r2) {
						if(type1.equalsIgnoreCase("A")) {
						return r1.getReservDescp().compareTo(r2.getReservDescp());
						}else {
						return r2.getReservDescp().compareTo(r1.getReservDescp());
						}
						
						}
						});
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
							System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						}
						System.out.println();
						break;
						
				case 3:	System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
						String type2 = sc1.nextLine();
						Collections.sort(al, new Comparator<Reservation>() {
		
							@Override
							public int compare(Reservation r1, Reservation r2) {
						if(type2.equalsIgnoreCase("A")) {
						return r1.getReservDate().compareTo(r2.getReservDate());
						}else {
						return r2.getReservDate().compareTo(r1.getReservDate());
						}
						
						}
						});
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
							System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						}
						System.out.println();
						break;
						
				case 4:	System.out.println("Enter 'A' for ascending order OR Enter 'B' for descending order");
						String type3 = sc1.nextLine();
						Collections.sort(al, new Comparator<Reservation>() {
		
							@Override
							public int compare(Reservation r1, Reservation r2) {
						if(type3.equalsIgnoreCase("A")) {
						return (int)(r1.getTotalAm()-r2.getTotalAm());
						}else {
						return (int)(r2.getTotalAm()-r1.getTotalAm());
						}
						}
						});
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
							System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
						}
						System.out.println();
						break;
				case 5:		break;		
				default: System.out.println("Enter a valid Option no !");
							break;
						
				}
				}while(choose!=5);
				break;
	
	case 5:  	String v;
				do {
				System.out.println("Enter Reservation ID to delete");
				int res = s.nextInt();
				li = al.listIterator();
				boolean bo = false;
				while(li.hasNext()) {
					Reservation r = (Reservation)li.next();
					if(r.getReservationId()==res)
					{
						li.remove();
						bo=true;
						System.out.println("Reservation "+r.getReservationId()+" deleted successfully");

				}
				}
				if(bo!=true)
				{
					System.out.println(" Record not found !!!");
				}
				else {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				}
				System.out.println("Do you want to DELETE more reservation? (Y/N)");
				v = sc1.next();
				}while(v.equalsIgnoreCase("Y"));
				break;
					
					
	case 6:     String x;
				do {
				System.out.println("Enter Reservation ID to cancel");
				int e = s.nextInt();
				li = al.listIterator();
				Reservation r =null;
				boolean f = false;
				while(li.hasNext()) {
					 r = (Reservation)li.next();
					if(r.getReservationId()==e){
						String Status = "Cancelled";
						li.set(new Reservation(r.getReservationId(),r.getCustomerName(),r.getReservDescp(),r.getReservDate(),r.getAdults(),r.getChildren(),Status,r.getSubTotAm(),r.getDiscAm(),r.getTaxAm(),r.getTotalAm()));
						System.out.println("Reservation "+r.getReservationId()+" cancelled successfully");
						System.out.println("Amount "+r.getTotalAm()+" will be refunded within 24 hours");
						f=true;
					}
				}
				if(f!=true){
					System.out.println("Record not found !!!");
					}
				else{
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
					}
				System.out.println("Do you want to CANCEL more reservation? (Y/N)");
				x = sc1.next();
				}while(x.equalsIgnoreCase("Y"));	
				break;
		
	case 7:		String z;
				do {
				System.out.println("Enter Reservation ID to confirm");
				int c = s.nextInt();
				li = al.listIterator();
				Reservation re =null;
				boolean b = false;
				while(li.hasNext()) {
					 re = (Reservation)li.next();
					if(re.getReservationId()==c){
						String Status = "Confirmed";
						li.set(new Reservation(re.getReservationId(),re.getCustomerName(),re.getReservDescp(),re.getReservDate(),re.getAdults(),re.getChildren(),Status,re.getSubTotAm(),re.getDiscAm(),re.getTaxAm(),re.getTotalAm()));
						System.out.println("Reservation "+re.getReservationId()+" confirmed !");
						b=true;
					}
				}
				if(b!=true){
						System.out.println("Record not found !!!");
					}
				else{
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
				}	
						System.out.println("Do you want to CONFIRM more reservation? (Y/N)");
						z = sc1.next();
				}while(z.equalsIgnoreCase("Y"));
				break;
				
	case 8: 	System.out.println("1. Export all");
				System.out.println("2. Export by status");
				LocalDate date = LocalDate.now();
				File f = new File("C:\\Assignment\\Reservation_Report_"+date+".txt");
				int select = sc.nextInt();
				if(select==1) {
				
						FileWriter writer = new FileWriter(f); 
						for(Reservation resv: al) {
						  writer.write(resv + System.lineSeparator());
						}
						System.out.println("Report generated successfully.");
						writer.close();
				}
				else if(select==2){
						System.out.println("Enter the option no");
						System.out.println("\t1. Booked");
						System.out.println("\t2. Cancelled");
						System.out.println("\t3. Confirmed\n");
						FileWriter writer = new FileWriter(f);
						int sel = sc.nextInt();
						switch(sel) {
						case 1: 	for(Reservation re :al) {
												if(re.getStatus().equalsIgnoreCase("booked")){
												writer.write(re + System.lineSeparator());
												
											}
										}
									oos = new ObjectOutputStream(new FileOutputStream(f));
									oos.close();
									writer.close();
									System.out.println("Report generated successfully.");
									break;
						case 2:		for(Reservation re :al) {
												if(re.getStatus().equalsIgnoreCase("Cancelled")){
												writer.write(re + System.lineSeparator());
												
											}
										}
									oos = new ObjectOutputStream(new FileOutputStream(f));
									oos.close();
									writer.close();
									System.out.println("Report generated successfully.");
									break;
						case 3:		for(Reservation re :al) {
												if(re.getStatus().equalsIgnoreCase("Confirmed")){
												writer.write(re + System.lineSeparator());
												
											}
										}
									oos = new ObjectOutputStream(new FileOutputStream(f));
									oos.close();
									writer.close();
									System.out.println("Report generated successfully.");
									break;
						}
					
					
				}
				break;
				
	case 9:		System.out.println("Thank You. See you next time!");
				System.exit(0);
	
				
	default:	System.out.println("Enter a valid option no !");
				break;
		}
	
	}while(option!=0);
	}

	@Override
	public void CreateReservation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ViewReservation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ViewByID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteByID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CancelByID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ConfirmByID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Report() {
		// TODO Auto-generated method stub
		
	}
	
}

