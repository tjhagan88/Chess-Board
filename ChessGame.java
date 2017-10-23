import java.util.Scanner;

public class ChessGame{
	public static String chessBoardArry[][] = new String[8][8];

	public static void main(String[] args) { 
		String curLoc ="";
		String newLoc ="";
		String userInput = "";
		boolean exception;

		ChessGame game = new ChessGame(); 
		game.newGame();

		Scanner sc = new Scanner(System.in);


		while (!userInput.equals("quit")) {
			//CURRENT LOCATION
			do {
				System.out.print("Enter the CURRENT location of the piece you would like to move: ");
				curLoc = sc.nextLine().trim();

				exception = positionThrowException(curLoc, "CURRENT");
			} while(exception == true);

			//NEW LOCATION
			do {
				System.out.print("Enter the NEW location of the piece you would like to move: ");
				newLoc = sc.nextLine().trim();

				exception = positionThrowException(newLoc, "NEW");
			} while(exception == true);

			movePiece(curLoc, newLoc);
			paintBoard();
		}
	}

	public static boolean positionThrowException(String userInput, String strType) {
		String errorMessage = "";

		if (!userInput.equals("")) {

			if (userInput.equals("quit") || userInput.equals("q")) {
				System.out.println("\r\nThanks for playing! Goodbye.\r\n");
            	System.exit(0);
			}

			userInput = userInput.toUpperCase();
			String strCol = userInput.substring(0,1);
			String strRow = userInput.substring(1,2);

			if (userInput.length() != 2) {
				System.out.println("Valid locations should consist of two characters.");
				return true;
			} else if ( (int) strCol.charAt(0) < 65 || (int) strCol.charAt(0) > 72 ) {
				System.out.println("Please enter a valid column on the board.");
				return true;
			} else if ( (int) strRow.charAt(0) < 49 || (int) strRow.charAt(0) > 56 ) {
				System.out.println("Please enter a valid row on the board.");
				return true;
			} else if (strType.equals("CURRENT")) {
				if (chessBoardArry[getColArrayElement(strCol)][Integer.valueOf(strRow)-1] == null) {
					System.out.println("Please pick a location that currently has an active piece.");
					return true;
				}
			} else if (strType.equals("NEW")) {
				if (chessBoardArry[getColArrayElement(strCol)][Integer.valueOf(strRow)-1] != null) {
					System.out.println("Please pick an open location to move your piece.");
					return true;
				}
			}

		} else {
			System.out.println("Please enter a value for the " + strType + " location.");
			return true;
		}

		return false;
	}

	public static void movePiece(String curPos, String newPos) {
		int intCurCol;
		int intCurRow;
		int intNewCol;
		int intNewRow;
		int tmp;

		intCurCol = getColArrayElement(curPos);
		intCurRow = Integer.valueOf(curPos.substring(1,2))-1;

		intNewCol = getColArrayElement(newPos);
		intNewRow = Integer.valueOf(newPos.substring(1,2))-1;

		chessBoardArry[intNewCol][intNewRow] = chessBoardArry[intCurCol][intCurRow];
		chessBoardArry[intCurCol][intCurRow] = null;
	}

	public static void newGame(){
		System.out.println("\r\n\r\nWELCOME Guest!\r\n\r\n** Instructions **\r\nType \'quit\' at any time to exit the game.");

		//Init values
		chessBoardArry[0][0] = "WR";
		chessBoardArry[1][0] = "WN";
		chessBoardArry[2][0] = "WB";
		chessBoardArry[3][0] = "WQ";
		chessBoardArry[4][0] = "WK";
		chessBoardArry[5][0] = "WB";
		chessBoardArry[6][0] = "WN";
		chessBoardArry[7][0] = "WR";
		chessBoardArry[0][1] = "WP";
		chessBoardArry[1][1] = "WP";
		chessBoardArry[2][1] = "WP";
		chessBoardArry[3][1] = "WP";
		chessBoardArry[4][1] = "WP";
		chessBoardArry[5][1] = "WP";
		chessBoardArry[6][1] = "WP";
		chessBoardArry[7][1] = "WP";

		chessBoardArry[0][7] = "BR";
		chessBoardArry[1][7] = "BN";
		chessBoardArry[2][7] = "BB";
		chessBoardArry[3][7] = "BQ";
		chessBoardArry[4][7] = "BK";
		chessBoardArry[5][7] = "BB";
		chessBoardArry[6][7] = "BN";
		chessBoardArry[7][7] = "BR";
		chessBoardArry[0][6] = "BP";
		chessBoardArry[1][6] = "BP";
		chessBoardArry[2][6] = "BP";
		chessBoardArry[3][6] = "BP";
		chessBoardArry[4][6] = "BP";
		chessBoardArry[5][6] = "BP";
		chessBoardArry[6][6] = "BP";
		chessBoardArry[7][6] = "BP";

		paintBoard();
	}

	public static void paintBoard(){
		String curCol;
		String strBuf;
		String strRow = "\r\n   | A| B| C| D| E| F| G| H|";

		for (int row = chessBoardArry.length; row > 0; row--) {
			strBuf = "\r\n  " + String.valueOf(row); 

			for (int col = 0; col < chessBoardArry.length; col++) {
				// Gets current column
				curCol = Character.toString(getColLetter(col));

				if (chessBoardArry[col][row-1] != null)  {
					strBuf = strBuf + "|" + chessBoardArry[col][row-1]; 
				} else {
					strBuf = strBuf + "|  "; 
				}
			}
			strRow = strRow + strBuf + "|";
		}
		strRow = strRow + "\r\n";
		System.out.println (strRow);
	}

	private static char getColLetter(int numCol) {
		// 65 is A in Ascii
		return (char)(65 + numCol);
	}

	private static int getColArrayElement(String strLoc) {
		char c = strLoc.toUpperCase().charAt(0);
		int intCol = (int) c;
		
		intCol = intCol - 65;

		return intCol;
	}


}