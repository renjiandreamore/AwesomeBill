package deliverable2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class citySim {
	public static String[] locations = {"Hotel", "Diner", "Library", "College", "Outside City"};
	//public static String outsideCity = "Outside City";
	public static String[] onewayRoads = {"Fourth Ave", "Fifth Ave"};
	public static String[] twowayRoads = {"Bill St", "Phil St"};
	
	public static void main(String[] args){
		int seed = 0;
		citySim main = new citySim();
		if(main.valid(args)){
			seed = Integer.parseInt(args[0]);
		}else {
			System.out.println("wrong number, plz enter a digit!");
			System.exit(0);
		}
		main.run(seed);
	}
	
	public boolean valid(String[] args){
		try {
		if(Character.isDigit(args[0].charAt(0)) && args.length == 1){
			return true;
		}
		}catch (NullPointerException e){
			return false;
		}
		return false;
	}
	
	public void run(int seed){
		Random ran = new Random(seed);
		for(int i = 0; i < 5; i++){
			int loc = ran.nextInt(5);
			if(loc == 4){ // starts at outside city
				driveFromOut(i);// street
			}else{
				driveNormally(i, locations[loc]);
			}
			System.out.println("-----");
		}
	}
	
	public void driveFromOut(int i){
		Random ran = new Random();
		String location; 
		if(ran.nextInt(10) >= 5){
			location = "Hotel";
			String street = "Fourth Ave";
			System.out.println("Driver " + i + " heading from Outside City to " + location + " via " + street +".");
		}else{
			location = "College";
			String street = "Fifth Ave";
			System.out.println("Driver " + i + " heading from Outside City to " + location + " via " + street +".");
		}
		driveNormally(i, location);
	}
	public void driveNormally(int i, String location){
		Places test = new Places();
		test.move2next(i, location);
	}
	
}

class Places{
	HashMap<String, ArrayList<String>> next = new HashMap<String, ArrayList<String>>();
	int id;
	String newCity;
	String oldCity;
	
	
	public void move2next(int i, String location){
		int ran = new Random().nextInt(2);
		next.put("Hotel", new ArrayList<>(Arrays.asList("Diner", "Library","Fourth Ave", "Bill St")));
		next.put("Diner", new ArrayList<>(Arrays.asList("College","Outside City","Phil St", "Fourth Ave")));
		next.put("Library", new ArrayList<>(Arrays.asList("Hotel", "Outside City", "Bill St", "Fifth Ave")));
		next.put("College", new ArrayList<>(Arrays.asList("Diner", "Library", "Phil St", "Fifth Ave")));
		this.id = i;
		if(next.get(location) != null){
			if(location == "Hotel" && next.get(location).get(ran) == "Diner"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Fourth Ave.");
			}
			if(location == "Hotel" && next.get(location).get(ran) == "Library"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Bill St.");
			}
			if(location == "Diner" && next.get(location).get(ran) == "College"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Phil St.");
			}
			if(location == "Diner" && next.get(location).get(ran) == "Outside City"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Fourth Ave.");
			}
			if(location == "Library" && next.get(location).get(ran) == "Hotel"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Bill St.");
			}
			if(location == "Library" && next.get(location).get(ran) == "Outside City"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Fifth Ave.");
			}
			if(location == "College" && next.get(location).get(ran) == "Diner"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Phil St.");
			}
			if(location == "College" && next.get(location).get(ran) == "Library"){
				System.out.println("Driver " + id + " heading from " + 
						location + " to " + next.get(location).get(ran) + " via Fifth Ave.");
			}
			newCity = next.get(location).get(ran);
			
			oldCity = location;
			if(newCity == "Outside City"){
				if(oldCity == "Diner"){
					System.out.println("Driver "+ i +" has gone to Philadelphia.");
					return;
				}else if(oldCity == "Library"){
					System.out.println("Driver "+ i +" has gone to Cleveland.");
					return;
				}	
			}
			move2next(i, newCity);
			
		}
		return;
	}
}

//class Hotel extends Places{
//	public Hotel(int id){
//		this.id = id;
//		this.type = citySim.locations[0];
//		ArrayList<String> des = new ArrayList<String>();
//		des.add(citySim.locations[1]);
//		des.add(citySim.locations[2]);
//		this.next.put(type, des);	
//	}
//}
//
//class Diner extends Places{
//	public Diner(int id){
//		this.id = id;
//		this.type = citySim.locations[1];
//		ArrayList<String> des = new ArrayList<String>();
//		des.add(citySim.locations[3]);
//		des.add(citySim.locations[4]);
//		this.next.put(type, des);
//	}
//}
//
//class Library extends Places{
//	public Library(int id){
//		this.id = id;
//		this.type = citySim.locations[2];
//		ArrayList<String> des = new ArrayList<String>();
//		des.add(citySim.locations[0]);
//		des.add(citySim.locations[4]);
//		this.next.put(type, des);
//	}
//}
//
//class College extends Places{
//	public College(int id){
//		this.id = id;
//		this.type = citySim.locations[3];
//		ArrayList<String> des = new ArrayList<String>();
//		des.add(citySim.locations[1]);
//		des.add(citySim.locations[2]);
//		this.next.put(type, des);
//	}
//}
//
//
//
//
//
