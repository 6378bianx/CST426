/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-
* Author: Bianca Jaramillo
* Class: CST 426
* Assignment: Lab 01
* Due Date: 20 - September - 2018
* Comments: Lab 1 consists of Flyweight and Memento
* Hopefully number 1 is putting the code to one file and not having a one Dragon Class
* All the classes had to be static because they are in the same file
* 
=-=-=-=-=-=-=-=-=-=-=-=-=-=- */

import java.util.ArrayList;

public class lab1 
{
	
	
	public static class Mesh
	{
		//fake mesh class to pretend java takes a mesh
		private String mesh;
		
		//constructor that set the mesh to defualt
		public Mesh()
		{
			this.mesh = "default";
		}
		
		//function that set the mesh to default
		public void settype()
		{
			mesh = "default";
		}
		
		//an override of the previous function that set the mesh given a string input
		public void settype(String type)
		{
			if (type == "1")
			{
				mesh = "type1";
			}
			if (type == "2")
			{
				mesh = "type2";
			}
			if (type == "3")
			{
				mesh = "type3";
			}

		}
	}
	
	
	//where Flyweight begins 
	
	//abstract dragon needs to implemented by all 3 different types of dragons
	public static abstract class Dragon
	{
		//all share a mesh because all the dragons look the same
		Mesh mesh;
		//set mesh function
		abstract void setMesh();
		
	}
	//first type of dragon and extends dragon
	public static class Type1Dragon extends Dragon
	{
		//individual charactriztics of type1 dragon
		private int size;
		private String color;
		private int health;
		private int exp;
		//constructor
		public Type1Dragon()
		{
			this.size = 0;
			this.color = "black";
			this.health = 100;
			this.exp = 100;
		}
		
		
		public void setDragon()
		{
			this.size = 1;
			this.color = "black";
			this.health = 100;
			this.exp = 100;
		}
		//set the common mesh to the type1 dragon mesh
		@Override
		void setMesh() {
			
			mesh.settype("type1");
		}
		
		
		
	}
	
	public static class Type2Dragon extends Dragon
	{
		//individual charactriztics of type2 dragon
		private int size;
		private String color;
		private int health;
		private int exp;
		
		//constructor
		public Type2Dragon()
		{
			this.size = 100;
			this.color = "red";
			this.health = 10000;
			this.exp = 100000;
		}
		
		//set the common mesh to the type2 dragon mesh
		@Override
		void setMesh() {
			mesh.settype("type2");
			
		}
		
	}
	
	public static class Type3Dragon extends Dragon
	{
		//individual charactriztics of type3 dragon
		private int size;
		private String color;
		private int health;
		private int exp;
		//constructor
		public Type3Dragon()
		{
			this.size = 10;
			this.color = "green";
			this.health = 10;
			this.exp = 10;
		}
		
		//set the common mesh to the type3 dragon mesh
		@Override
		void setMesh() {
			mesh.settype("type3");
			
		}
		
	}
	
	//where memento begins and flyweight ends
	
	//holds the information
	public static class Mememto
	{
		//this will store all the dragons given that all the types of dragons must be 
		//added to the arraylist of dragons 
		private final ArrayList<Dragon> Dragons; 
				
		//constructor creates a the arraylist of dragons given a arraylist of dragons
		public Mememto(ArrayList<Dragon> dragon)
		{
			Dragons = new ArrayList<Dragon>(dragon);				
		}
		
		//returns the array list of dragons the current saved state of the dragons
		public ArrayList<Dragon> getSavedState()
		{
			return Dragons;
		}
		
	}
	//originator class uses mememto to save the information
	public static class Originator
	{
		public ArrayList<Dragon> Dragons;
		
		//constructor or else java will complain
		public Originator()
		{
			
		}
		
		//sets the current state of dragons
		public void setState(ArrayList<Dragon> dragons )
		{
			this.Dragons = new ArrayList<Dragon>(dragons);
		}
		
		//saves the dragons using the memento class 
		public Mememto save()
		{
			return new Mememto(Dragons);
		}
		
		//get the saved state from the memento
		public ArrayList<Dragon> restoreToState(Mememto memento)
		{
			Dragons = new ArrayList<Dragon>(memento.getSavedState());
			
			return Dragons;
		}
	}
	
	
	//the main will act like the caretaker in which it cannot talk to the memento class
	public static void main (String[] args)
	{
		//create an originator class 
		Originator savethedata = new Originator();
		
		//create a dragon of type 1
		Type1Dragon dragon1 = new Type1Dragon();
		
		//create a dragon of type 2 
		Type2Dragon dragon2 = new Type2Dragon();
		
		//create an arraylist of dragons
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		//add the type1 dragon to the dragons array
	    dragons.add(dragon1);
	    System.out.println("Dragons after encountering type 1: "+ dragons.size());
	    //save the arraylist of the dragons
	    savethedata.setState(dragons);
	    
	    //add the type2 dragon to the array list of dragons
	    dragons.add(dragon2);
	    System.out.println("Dragons after encountering type 2: "+ dragons.size());
	    //but the player doesnt want to encounter the type2 dragon
	    
	    //resets the save	    
	    //restore the save data from the save and send it back to the dragons array
	    dragons = new ArrayList<Dragon>(savethedata.restoreToState(savethedata.save()));
	    
		System.out.println("Dragons after restoring the save: "+ dragons.size());
	}

	
	
}


