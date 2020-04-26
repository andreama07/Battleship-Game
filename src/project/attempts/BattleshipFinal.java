package project.attempts;

import java.util.Scanner;
import java.util.Random;
public class BattleshipFinal {
	
//Prints out a string 2D array, displays the different game boards	
	public static void display (String[][] input) {
		System.out.println("-----------------------------");
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(j == 0) {
					System.out.print("|");
				}
				System.out.print(input[i][j] + "|");		
			}
			System.out.println("");
			System.out.println("-----------------------------");
		}
	}
	
//User makes move, determines whether user hits a ship or not
	public static int OppTurn (String[][] input2, String[][] input3, int input4) {
		System.out.println("Your turn!");
		display(input3);
		Scanner oppmove = new Scanner(System.in);
		System.out.println();
		System.out.println("What row do you choose?");
		String choice1 = oppmove.nextLine();
		System.out.println("What column do you choose?");
		String choice2 = oppmove.nextLine();
		int hitrow = Integer.parseInt(choice1);
		int hitcolumn = Integer.parseInt(choice2);
		int output = input4;
		
		if (input2[hitrow-1][hitcolumn-1] == " S ") {
			input3[hitrow-1][hitcolumn-1] = " X ";
			output = 1;
		}
		else {
			input3[hitrow-1][hitcolumn-1] = " O ";
			output = 0;
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		display(input3);
		return output;
	}
	
//computer randomly chooses a position, determines whether it hits a ship or not
	public static int CompTurn (String[][] input, int input2) {
		System.out.println("Computer Turn!");
		Random rand = new Random();
		int c = rand.nextInt(6);
		int d = rand.nextInt(6);
		int output = input2;
		if (input[c][d] == " S " || input [c][d] == " X ") {
			input[c][d] = " X ";
			output = 1;
		}
		else {
			input [c][d] = " O ";
			output = 0;
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		display(input);
		return output;
	}
	
//determining if the user or computer has hit all of the opp
	public static int Winner (String[][] input, String[][] input1, String[][] input2) {
		int output = 0;
		int counterO = 0;
		int counterC = 0;
		Opponent:
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (input[i][j] == " S " && input1[i][j] == " X ") {
					counterO += 1;
					if (counterO == 7) {
					output = 1;
					break Opponent;
					}
				}
			}
		}
		Computer:
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					if (input2[i][j] != " S ") {
						counterC += 1;
						if (counterC == 49) {
						output = 2;
						break Computer;
						}
					}
				}
			}
		return output;
	}
	
	
//START OF MAIN
	public static void main(String[] args) {
//creating a blank 2D array for the user's display method
		String [][] board = new String [7][7];
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				board [i][j] = "   ";
			}
		}		
		
//setting up computer's ship orientation
		String [][] compships = new String [7][7];
		Random rand = new Random();
		int x = rand.nextInt(4);
		int y = rand.nextInt(5) + 1;
		int z = rand.nextInt(5);
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				compships [i][j] = "   ";
			}
		}
		for (int temp = 0; temp < 2; temp++) {
			compships [x][1] = " S ";
			x += 1;
		}
		for (int temp = 3; temp < 5; temp++) {
			compships [4][y] = " S "; 
			y += 1;
		}
		for (int temp = 5; temp < 7; temp++) {
			compships [z][5] = " S "; 
			z += 1;
		}
		
//creating a blank 2D array for the computer's display method
		String [][] compboard = new String [7][7];
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				compboard [i][j] = "   ";
			}
		}
		
		System.out.println("Welcome to Battleship!\nYou will be playing against the computer on a 7x7 board.");
		System.out.println("You will now customize your ship layout.");
		display(board);	
		
//User chooses placement of first ship
		Scanner scanner = new Scanner(System.in);
		System.out.println("What column do you want to place your first ship?(1-7)");
		String column1 = scanner.nextLine();
		System.out.println("What row do you want to place your first ship?(1-5)");
		String row1 = scanner.nextLine();
		int x1 = Integer.parseInt(row1);
		int y1 = Integer.parseInt(column1);
		for (int temp0 = 0; temp0 < 3; temp0++) {
			board [x1-1][y1-1] = " S ";
			x1 += 1;
		}
		display(board);
		
//User chooses placement of second ship
		System.out.println("What column do you want to place your second ship?(1-6)");
		String column2 = scanner.nextLine();
		System.out.println("What row do you want to place your second ship?(1-7)");
		String row2 = scanner.nextLine();
		int a = Integer.parseInt(row2);
		int b = Integer.parseInt(column2);
		for (int temp1 = 0; temp1 < 2; temp1++) {
			board [a-1][b-1] = " S ";
			b += 1;
		}
		display(board);
		
//User chooses placement of third ship
		System.out.println("What column do you want to place your third ship?(1-7)");
		String column3 = scanner.nextLine();
		System.out.println("What row do you want to place your third ship?(1-6)");
		String row3 = scanner.nextLine();
		int c = Integer.parseInt(row3);
		int d = Integer.parseInt(column3);
		for (int temp2 = 0; temp2 < 2; temp2++) {
			board [c-1][d-1] = " S ";
			c += 1;
		}
		display(board);
		
//Start of the game portion
		System.out.println("You get to go first.");
		int repeatO = 1;
		int repeatC = 1;
		int result = 0;
		
		Game:
		while (true){
			repeatO = 1;
			while (repeatO == 1){
				repeatO = OppTurn(compships, compboard, repeatO);
				result = Winner(compships, compboard, board);
				if (result == 1) {
					break Game;
				}
			}
			repeatC = 1; 
			while (repeatC == 1) {
				repeatC = CompTurn(board, repeatC);
				result = Winner(compships, compboard, board);
				if (result == 2) {
					break Game;
				}
			}
		}
		switch (result) {
		case (1):
			System.out.println("You won!");
			break;
		case (2):
			System.out.println("The computer won!");
			break;
		}
	}

	private static void While(boolean b) {
		
	}
}

