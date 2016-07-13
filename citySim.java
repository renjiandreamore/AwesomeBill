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
		if(main.valid(args)){			// check if the passed arguments is valid
			seed = Integer.parseInt(args[0]);
		}else {
			System.out.println("wrong number, plz enter a digit!");
			System.exit(0);
		}
		main.run(seed);
	}
	
	public boolean valid(String[] args){ //the input should only be digit, not others
		try {
		if(args.length == 1 && Character.isDigit(args[0].charAt(0)) ){
			return true;
		}
		}catch (NullPointerException e){
			return false;
		}
		return false;
	}
	
	public boolean run(int seed){
		Random ran = new Random(seed);
		for(int i = 0; i < 5; i++){
			int loc = ran.nextInt(5);   // gengerate 0 - 4 and then choose
			if(loc == 4){ // 4 means starts at outside city because
						  // locations[4] = "Outside City";
				driveFromOut(i);
			}else{
				driveNormally(i, locations[loc]); // 0 - 3 means hotel, diner, library and college
			}
			System.out.println("-----");
		}
		return true;
	}
	
	public boolean driveFromOut(int i){
		Random ran = new Random();
		String location; 
		if(ran.nextInt(10) >= 5){  // randomly choose which is the next stop
								   // from outside city to hotel via 4th ave
								   // or from outside to College via 5th ave
			location = "Hotel";
			String street = "Fourth Ave";
			System.out.println("Driver " + i + " heading from Outside City to " + location + " via " + street +".");
			driveNormally(i, location);  // then from the location to others
			return true;
		}else{
			location = "College";
			String street = "Fifth Ave";
			System.out.println("Driver " + i + " heading from Outside City to " + location + " via " + street +".");
			driveNormally(i, location); // then from the location to others
			return true;
		}
		
	}
	
	public boolean driveNormally(int i, String location){
		Places test = new Places();
		test.move2next(i, location);
		return true;
	}
	
}

class Places{
	HashMap<String, ArrayList<String>> next = new HashMap<String, ArrayList<String>>();
	int id;
	String newCity;  //the next place
	String oldCity; // the old place
	
	
	public boolean move2next(int i, String location){
		int ran = new Random().nextInt(2); // randomly choose 0 or 1, which
										   // could map the key to the array[0] or array[1]
										   // and according to this choose the next stop
		next.put("Hotel", new ArrayList<>(Arrays.asList("Diner", "Library","Fourth Ave", "Bill St")));
		next.put("Diner", new ArrayList<>(Arrays.asList("College","Outside City","Phil St", "Fourth Ave")));
		next.put("Library", new ArrayList<>(Arrays.asList("Hotel", "Outside City", "Bill St", "Fifth Ave")));
		next.put("College", new ArrayList<>(Arrays.asList("Diner", "Library", "Phil St", "Fifth Ave")));
										// the first twos are the places that connected to the key
										// the last twos are the street that goes through, respectively.
		this.id = i;
		/*
		 * accroding to different places, assign different streets to it.
		 */
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
			if(newCity == "Outside City"){ // quit conditions.
				if(oldCity == "Diner"){
					System.out.println("Driver "+ i +" has gone to Philadelphia.");
					return true;
				}else if(oldCity == "Library"){
					System.out.println("Driver "+ i +" has gone to Cleveland.");
					return true;
				}	
			}
			move2next(i, newCity); // recursively. 
			
		}
		return false;
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
