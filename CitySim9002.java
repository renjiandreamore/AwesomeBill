package deliverable2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class CitySim9002 {
	
	public static String[] visitors = 
			new String[]{"Student", "Business", "Professor", "Blogger"};
	
	public static String[] locations = 
			new String[]{"The Cathedral of Learning", "Squirrel Hill", 
						"The Point", "Downtown"};
	LinkedList<Visitor> listVisitor = new LinkedList<Visitor>();
	//Visitors' ID, from 1 t0 5 as required. 
	public static int ID = 1;
	
	public static void main(String[] args){
		int seed = 0;
		
		CitySim9002 exe = new CitySim9002();
		if(exe.commend(args)){
			seed = Integer.parseInt(args[0]);
			System.out.println("Welcome to CitySim! Your seed is " + seed + ".");
		} else {
			System.out.println("Please enter one integer argument, seed");
			return;
		}

		exe.run(seed);
	}
	
	public boolean addVisitor(Visitor visitor){
		try{
			listVisitor.add(visitor);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	
	public static boolean isInteger(String[] args) {
		if (args.length != 1) {	
			return false;
		}
		try {
			Integer.parseInt(args[0]);
		} catch (NumberFormatException e) { // check whether the seed is an integer
			return false;
		} catch (NullPointerException e) { // check whether the seed is null
			return false;
		}
		return true;
	}
	
	//*Generate a random visitor based on the input
	//0: student; 1: Businessman; 2: Professor; 3: Blogger
	 
	public Visitor generateVisitor(int type){
		switch(type){
		case 0:
			return new Student(ID);
		case 1:
			return new Business(ID);
		case 2:
			return new Professor(ID);
		case 3:
			return new Blogger(ID);
		default:
			System.out.println("error INPUT");
			return null;
		}
	}
	
	

//	Let the visitor go to one city, return ture if he can, ortherwise not

	
	public boolean visit(Visitor visitor, String City){
		return visitor.visitCity(City);
	}
	/**
	*run method
	*generating a random number
	*@return times the Loop runs
	*/
	public int run(int seed){
		Random rand = new Random(seed);
		Visitor visitor = null;
		int numberVisitor = 0;
		for(int i = 1; i <= 5; i++){
			int type = randomSelect(rand);
			visitor = generateVisitor(type);
			ID++;
			numberVisitor += 1;
			int location = rand.nextInt(4);
			while(location != 4){
				visit(visitor, locations[location]);
				location = rand.nextInt(5);
			}
			visitor.leave();
			System.out.println("***");			
		}
		return numberVisitor;
	}

	//The commend should be one integer. use the RE to check it. 
	
	public boolean commend(String[] args){
		if(args.length == 1 && args[0].matches("[0-9]+")) 
			return true;
		return false;
	}
	
	
	public int randomSelect(Random rand){
		return rand.nextInt(4);
	}
		
}

class Visitor{
	HashMap<String, Boolean> perference = new HashMap<String, Boolean>();
	int ID;
	String type;
	public boolean visitCity(String City){
		if(!perference.containsKey(City))
			return false;
		System.out.println("Visitor " + ID + " is going to " + City + ".");
		if(perference.get(City)){
			System.out.println("Visitor " + ID + " did like " + City + ".");
		} else {
			System.out.println("Visitor " + ID + " did not like " + City + ".");
		}
		return true;
		
	}
	
	public void leave(){
		System.out.println("Visitor " + ID + " has left the city.");
	}
	
	public int Id(){
		return this.ID;
	}
	public String type(){
		return type;
	}
	
	public boolean perference(String City){
		if(perference.containsKey(City))
			return perference.get(City);
		return false;
	}
}

class Student extends Visitor{
	public Student(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[0];
		System.out.println("Visitor " + id + " is a Student.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], true);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Business extends Visitor{
	public Business(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[1];
		System.out.println("Visitor " + id + " is a Business Person.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], false);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Professor extends Visitor{
	public Professor(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[2];
		System.out.println("Visitor " + id + " is a Professor.");
		this.perference.put(CitySim9002.locations[0], true);
		this.perference.put(CitySim9002.locations[1], true);
		this.perference.put(CitySim9002.locations[2], true);
		this.perference.put(CitySim9002.locations[3], true);
	}
}

class Blogger extends Visitor{
	public Blogger(int id){
		this.ID = id;
		this.type = CitySim9002.visitors[3];
		System.out.println("Visitor " + id + " is a Blogger.");
		this.perference.put(CitySim9002.locations[0], false);
		this.perference.put(CitySim9002.locations[1], false);
		this.perference.put(CitySim9002.locations[2], false);
		this.perference.put(CitySim9002.locations[3], false);
	}
}
