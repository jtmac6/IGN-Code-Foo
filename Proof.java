import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Proof {
	
	// Board represents the following:
	//[[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7],[0,1,2,3,4,5,6,7]]
	//This is list of lists representing rows which contain integers to represent columns
	public static ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
	// List of Visited Spaces
	public static ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>();
	// The currently occupied square
	public static ArrayList<Integer> current = new ArrayList<Integer>();
	// 
	public static int moveCounter = 1;
		
	/**
	 * Generates a list of possible moves
	 * returns a list of squares
	 * tiles are represented as an ArrayList of Integers [x,y]
	 * @param node
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> nextConfigs( ArrayList<Integer> node ){
		
		ArrayList<ArrayList<Integer>> retList = new ArrayList<ArrayList<Integer>>();
		
		//Move two right
		if(node.get(0)+2 < board.size()){
			//Move one up
			 if(node.get(1)+1 < board.get(node.get(0)+2).size()){
				 //System.out.println("Case 1");
				 int x = node.get(0)+2;
				 int y = node.get(1)+1;
				 ArrayList<Integer> tempList = new ArrayList<Integer>();
				 tempList.add(x);
				 tempList.add(y);
				 if(!visited.contains(tempList)){
					 retList.add(tempList);
				 }
			 }
			 //move one down
			 if(node.get(1)-1 < board.get(node.get(0)+2).size()&& node.get(1)-1 >= 0){
				 //System.out.println("Case 2");
				 int x = node.get(0)+2;
				 int y = node.get(1)-1;
				 ArrayList<Integer> tempList = new ArrayList<Integer>();
				 tempList.add(x);
				 tempList.add(y);
				 if(!visited.contains(tempList)){
					 retList.add(tempList);
				 }
			 }
		}// End X+2 If Statement
		//Move two left
		if(node.get(0)-2 < board.size() && node.get(0)-2 >= 0){
			//Move one up
			if(node.get(1)+1 < board.get(node.get(0)-2).size()){
				//System.out.println("Case 3");
				int x = node.get(0)-2;
				int y = node.get(1)+1;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
			//Move one down
			if(node.get(1)-1 < board.get(node.get(0)-2).size()&& node.get(1)-1 >= 0){
				//System.out.println("Case 4");
				int x = node.get(0)-2;
				int y = node.get(1)-1;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
		}//End X-2 If Statement
		//Move one right
		if(node.get(0)+1 < board.size()){
			//Move two up
			if(node.get(1)+2 < board.get(node.get(0)+1).size()){
				//System.out.println("Case 5");
				int x = node.get(0)+1;
				int y = node.get(1)+2;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
			//Move two down
			if(node.get(1)-2 < board.get(node.get(0)+1).size() && node.get(1)-2 >= 0 ){
				//System.out.println("Case 6");
				int x = node.get(0)+1;
				int y = node.get(1)-2;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
		}//End X+1 If Statement
		//Move one left
		if(node.get(0)-1 < board.size()&& node.get(0)-1>=0){
			//Move two up
			if(node.get(1)+2 < board.get(node.get(0)-1).size()){
				//System.out.println("Case 7");
				int x = node.get(0)-1;
				int y = node.get(1)+2;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
			//Move two down
			if(node.get(1)-2 < board.get(node.get(0)-1).size()&& node.get(1)-2 >= 0){
				//System.out.println("Case 8");
				int x = node.get(0)-1;
				int y = node.get(1)-2;
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(x);
				tempList.add(y);
				if(!visited.contains(tempList)){
					 retList.add(tempList);
				}
			}
		}// End X-1 If Statement
		return retList;
	}
	
	/**
	 * Chooses the Next Move Based on Warnsdorff's Algorithm
	 * @param configs
	 * @return
	 */
	public static ArrayList<Integer> chooseMove(ArrayList<ArrayList<Integer>> configs){
		
		int lowestIndex=0;

		//Find the index of the square that has the least amount of subsequent moves
		for(int i=0; i<configs.size();i++){
			ArrayList<Integer> current = configs.get(i);
			ArrayList<Integer> lowestArray = configs.get(lowestIndex);
			if(nextConfigs(current).size()<nextConfigs(lowestArray).size()){
				lowestIndex = i;
			}	
		}
		//return the square with the least amount of subsequent moves
		return configs.get(lowestIndex);	
	}
	
	public static void traverse(ArrayList<Integer> node){
		current = node;
		System.out.println("Move: " + moveCounter);
		System.out.println("Starting at position: ("+current.get(0)+","+current.get(1)+")");
		
		while (visited.size()!=63){
			ArrayList<ArrayList<Integer>> configs = nextConfigs(current);
			
			visited.add(current);
			ArrayList<Integer> nextMove = chooseMove(configs);
			
			moveCounter++;
			System.out.println("Move: "+moveCounter);
			System.out.println("Moving from ("+current.get(0)+","+current.get(1)+")" + " to ("+nextMove.get(0)+","+nextMove.get(1)+")"); 
			current = nextMove;
		}
		visited.add(current);
		
		//Checks if all squares have been visited
		if(validate()){
			System.out.println("All Squares Visited!");
		}
		
	}
	
	/**
	 * Check if a String is an Integer
	 * @param input
	 * @return
	 */
	public static boolean isInteger( String input ){  
	   try  
	   {  
	      Integer.parseInt( input );  
	      return true;  
	   }  
	   catch( Exception e )  
	   {  
	      return false;  
	   }  
	} 
	
	//check if every square has been visited
	public static boolean validate(){
		
		for(int i=0; i<8;i++){
			
			for(int j=0; j<8;j++){
				
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(i);
				tempList.add(j);
				
				if(!visited.contains(tempList)){return false;}
			}
		}
		
		return true;
		
	}
	
	/**
	 * Main Method
	 * Prompts for User Input and Begins Board Traversal
	 * @param args
	 */
	public static void main(String[] args){
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
	
		//Create Rows
		for(int i = 0; i<8; i++ ){
			temp.add(i);
		}
		//Create Columns
		for(int i = 0; i<8; i++ ){
			board.add(temp);
		}
		//start will represent the starting square in terms of an (x,y) coordinate
		ArrayList<Integer> start = new ArrayList<Integer>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Input a Starting Position:");
		
		//Prompt for X Coordinate Input
		boolean validInput1 = false;
		do{
			System.out.println("X Coordinate(0-7): ");	
			String input1 = sc.next();
			if(isInteger(input1)){
				if(Integer.parseInt(input1)<8 &&Integer.parseInt(input1)>=0){
					int x = Integer.parseInt(input1);
					start.add(x);
					validInput1=true;
				}else{System.out.println("Invalid Input! Please enter an integer between 0 and 7.");}
			}else{System.out.println("Invalid Input! Please enter an integer between 0 and 7.");}
		}while(validInput1==false);
		
		//Prompt for Y Coordinate Input
		boolean validInput2=false;
		do{
			System.out.println("Y Coordinate(0-7): ");	
			String input2 = sc.next();
			if(isInteger(input2)){
				if(Integer.parseInt(input2)<8 &&Integer.parseInt(input2)>=0){
					int y = Integer.parseInt(input2);
					start.add(y);
					validInput2=true;
				}else{System.out.println("Invalid Input! Please enter an integer between 0 and 7.");}
			}else{System.out.println("Invalid Input! Please enter an integer between 0 and 7.");}
		}while(validInput2==false);
		
		//Traverse the board
		current = start;
		traverse(current);
		
	}

}
