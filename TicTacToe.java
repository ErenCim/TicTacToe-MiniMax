/**
 * @(#)TicTacToe.java
 * Class for the GUI (game class)
 * It holds the GUI that the user will interact with, currently there are no player objects that this class can inherit as an instance variable, but it may be added in the future. 
 * Eren Cimentepe
 * Start Date: 11/03/2020
 * End Date: 16/03/2020
 */

	import javax.swing.*;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.event.*;

public class TicTacToe implements ActionListener 
{
	String testing; 
	
	//A boolean for the game state (if there is a winner or not).
	boolean gameState; 
	//A byte to keep track of the turn of the players.
	byte blnTurn; 
	int comScore; 
	int playScore;
	
	//The frame of the GUI. 
	JFrame myFrame;
	
	//All the buttons in the GUI are declared as instance variables. 
	JButton[][] buttons;
	JButton newButton;
	JButton reset; 
	JButton exit;
	
	//All the labels on the GUI, declared as instance variables. 
	JLabel instructions;
	JLabel computer; 
	JLabel player; 
	JLabel computerScore; 
	JLabel playerScore; 
	
	
	//Default constructor, creates the game. 
	public TicTacToe () 
	{
		this.gameState = false; 
		this.blnTurn = (byte)0;
		this.buttons = new JButton [3][3];  
		this.createGUI();
		this.playScore = 0; 
		this.comScore = 0; 
	}
	
	//This method is used to create the GUI. 
	public void createGUI()
	{
		//Creates the frame and sets its properties like size, layout etc. 
		this.myFrame = new JFrame("Welcome to TicTacToe");
		this.myFrame.setSize(620,600);
		this.myFrame.setLayout(null); 
		this.myFrame.setVisible(true);
		//Color.getHSBColor(204, 200, 225)
		this.myFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
    	this.myFrame.setBackground(Color.cyan);
    	//this.myFrame
    	this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//Creates and sets the properties of the the label for instructions. 
    	this.instructions = new JLabel();
    	this.instructions.setText("<html><div style='text-align: center;'> This is a game of TicTacToe that is played against the computer. You are X and the computer is O (all the time) and X goes first.\nYou take your turn clicking boxes you want to place an X on and the computer will play afterwards. You can win by getting three lined up diagonally, horizontally, or vertically.<html>");
    	this.instructions.setSize(555,100);
        this.instructions.setMaximumSize(new Dimension (300, 100));
    	this.instructions.setBackground(Color.getHSBColor(204, 200, 225));
    	this.instructions.setOpaque(true);
    	this.instructions.setBorder(BorderFactory.createLoweredBevelBorder());
    	this.instructions.setLocation(30,30);
    	this.myFrame.add(instructions);
		
		//System.out.println(count);
    	//Method is called to fill the array. 
    	this.fillArray();
    	
    	//Creates and sets the properties of the reset button. 
		this.reset = new JButton("Reset");
		this.reset.setSize(160, 50);
		this.reset.setLocation(425, 375);
		this.myFrame.add(this.reset);
		this.reset.setBackground(Color.getHSBColor(204, 200, 225));
		this.reset.setOpaque(true);
		this.reset.setBorder(BorderFactory.createLoweredBevelBorder());
		this.reset.setBorderPainted(true);
		this.reset.addActionListener(this);
		
		//Creates and sets the properties of the exit button.
		this.exit = new JButton("Exit");
		this.exit.setSize(160, 50);
		this.exit.setLocation(425, 450);
		this.myFrame.add(this.exit);
		this.exit.setBackground(Color.getHSBColor(204, 200, 225));
		this.exit.setOpaque(true);
		this.exit.setBorder(BorderFactory.createLoweredBevelBorder());
		this.exit.setBorderPainted(true);
		this.exit.addActionListener(this);
		
		//Creates and sets the properties of the computer label (which is the title for the score of the computer). 
		this.computer = new JLabel("Computer", SwingConstants.CENTER);
		this.computer.setLocation(425, 153);
		this.computer.setSize(70, 35);
		this.computer.setOpaque(true);
		this.computer.setBackground(Color.white);
		this.computer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computer.setFont(this.computer.getFont().deriveFont(Font.BOLD, 12.0f));
		this.computer.setForeground(Color.black);
		this.myFrame.add(this.computer);
		
		//Creates and sets the properties of the computer score label. 
		//It keeps track of the score of the computer. 
		this.computerScore = new JLabel ("" + this.comScore, SwingConstants.CENTER);
		this.computerScore.setLocation(425, 193);
		this.computerScore.setSize(70, 45);
		this.computerScore.setOpaque(true);
		this.computerScore.setBackground(Color.white);
		this.computerScore.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computerScore.setFont(this.computerScore.getFont().deriveFont(Font.BOLD, 12.0f));
		this.computerScore.setForeground(Color.black);
		this.myFrame.add(this.computerScore);
		
		//Creates and sets the properties of the player label, which is just the title for the score label of the player. 
		this.player = new JLabel("Player", SwingConstants.CENTER);
		this.player.setLocation(515, 153);
		this.player.setSize(70, 35);
		this.player.setOpaque(true);
		this.player.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.setBackground(Color.white);
		this.player.setFont(this.player.getFont().deriveFont(Font.BOLD, 12.0f));
		this.player.setForeground(Color.black);
		this.myFrame.add(this.player);
		
		//Creates and sets the properties of the player score label. 
		//It keeps track of the score of the player. 
		this.playerScore = new JLabel ("" + this.playScore, SwingConstants.CENTER);
		this.playerScore.setLocation(515, 193);
		this.playerScore.setSize(70, 45);
		this.playerScore.setOpaque(true);
		this.playerScore.setBackground(Color.white);
		this.playerScore.setBorder(BorderFactory.createLineBorder(Color.black));
		this.playerScore.setFont(this.playerScore.getFont().deriveFont(Font.BOLD, 12.0f));
		this.playerScore.setForeground(Color.black);
		this.myFrame.add(this.playerScore);		
		
		this.myFrame.repaint();
	}
	
	public void fillArray()
	{
		//Loop to go through the array of buttons and create a button at each spot, also adds them to the frame since they are a big part of the game board. 
		for (int i = 0; i < 3; i ++)
		 {
			for (int x = 0; x < 3; x ++)
			{
				//System.out.println(count);
				this.buttons[i][x] = new JButton(" ");
				this.buttons[i][x].setSize(100, 100);
				this.buttons[i][x].setLocation(30 + (x * 125), 150 + (i * 125));
				this.myFrame.add(this.buttons[i][x]);
				this.buttons[i][x].setBackground(Color.white);
				this.buttons[i][x].setOpaque(true);
				this.buttons[i][x].setBorder(BorderFactory.createLoweredBevelBorder());
				this.buttons[i][x].addActionListener(this);
			}	 
		}
	}
	
	//Action performed, calls the method to change the text of the button that was clicked (which in return calls a chain of methods). 
	public void actionPerformed(ActionEvent e) 
	{
		//If the reset button is clicked, it would make all the buttons text empty, because the game would be restarting. 
		//Then, it would also enable all the buttons. 
		//Also, it would reset the score. 
		if (e.getActionCommand().equalsIgnoreCase("reset"))
		{
			this.gameState = false; 
			this.blnTurn = (byte)0;
			//An int for the decision of the user. Since a confirm dialog will give 0, 1, or 2. 
			int scoreDecision; 
			//A confirm dialog is used to ask the user if they want to reset the score alongside the board, or not. 
			//It is in a do while loop because the user is not allowed to press cancel, and the confirm dialog will pop up as long as the user doesn't press yes or no. 
			do 
			{
				scoreDecision = JOptionPane.showConfirmDialog(null, "Would you also like to reset the score, if so click yes. If you only want to reset the game board, then click no."); 
			
			} while(scoreDecision != 0 && scoreDecision != 1);
			
			
			//If the user said yes to reseting the score boards, it executes the following code.  
			if (scoreDecision == 0)
			{
				this.playScore = 0; 
				this.comScore = 0; 
				this.computerScore.setText("" + this.comScore);
				this.playerScore.setText("" + this.playScore);
			}
			
			//this.buttons[0][0].setEnabled(true);
			//this.buttons[0][0].setText(" ");;
			
			//this.fillArray();
			//System.out.println("reset");
			
			for (int i = 0; i < 3; i ++)
	 		{
				for (int x = 0; x < 3; x ++)
				{
					this.buttons[i][x].setText(" ");
					//System.out.println("reset");
					this.buttons[i][x].setEnabled(true);
					//this.setDisable(this.buttons[i][x], false);
				}	 
	 		}
		}
		//If the exit button is clicked, it just closes the program. 
		else if (e.getActionCommand().equalsIgnoreCase("exit"))
		{
			System.exit(0);
		}
		//Anything else would mean that a button on the game board was clicked, so it calls the method for the response. 
		else 
		{
			this.setText((JButton)e.getSource());
		}
		
		//System.out.println(this.checkText(this.buttons[2][0].getText()));
		//System.out.println(this.checkText(this.buttons[2][1].getText()));
		//System.out.println(this.checkText(this.buttons[2][2].getText()));
		//System.out.println(this.checkText(this.buttons[1][1].getText()));
	}
	
	//Method to set the text of the buttons when they are clicked. 
	public void setText(JButton b)
	{
		for (int i = 0; i < 3; i ++)
 		{
			for (int x = 0; x < 3; x ++)
			{
				if (this.buttons[i][x] == b && this.blnTurn % 2 == 0)
				{
					this.buttons[i][x].setText("X");
					this.buttons[i][x].setForeground(Color.black);
					this.buttons[i][x].setFont(this.buttons[i][x].getFont().deriveFont(Font.BOLD, 24.0f));
					blnTurn++; 
					this.setDisable(b); 
					this.actWinner();
					//Put the method for the AI here. 
					if (!this.gameState)
					{
						this.nextBestMove(this.buttons);
					}
					break; 
				}
			}	 
 		}
	}
	
	//A method to disable the buttons that have been clicked.
	public void setDisable(JButton b)
	{		
		//Nested loop to go through the array of buttons. 
		for (int i = 0; i < 3; i ++)
 		{
			for (int x = 0; x < 3; x ++)
			{
				//If the buttons that is clicked (which is passed as a parameter) is the same button as the array, it sets the enable property to false. 
				if(this.buttons[i][x] == b)
				{
					this.buttons[i][x].setEnabled(false);
				}
			}	 
 		}
	}
	
	public void nextBestMove(JButton[][] gameBoard)
	{
		//+1 represents O winning, 0 represents a draw, -1 represents the X winning. 
		//X is trying to minimize its loses, so it would pick the smallest number -1, the one that is the most disadvantageous to O. 
		//On the other hand O is going to pick +1. 
		
		byte bestScore = -2; 
		byte j = 0;
		byte y = 0; 
		
		for (int i = 0; i < 3; i ++)
 		{
			for (int x = 0; x < 3; x ++)
			{
				if (gameBoard[i][x].getText().equals(" "))
				{
					gameBoard[i][x].setText("O");
					byte currentScore = this.miniMax(gameBoard, (byte)(this.blnTurn + 1), false); 
					gameBoard[i][x].setText(" ");
					if (currentScore > bestScore)
					{
						bestScore = currentScore; 
						j = (byte)i; 
						y = (byte)x; 
					}
				}
			}
 		}
		
		gameBoard[j][y].setText("O");
		gameBoard[j][y].setEnabled(false);
		gameBoard[j][y].setForeground(Color.black);
		gameBoard[j][y].setFont(gameBoard[j][y].getFont().deriveFont(Font.BOLD, 24.0f));
		blnTurn++;  
		this.actWinner();
		
	}
	
	//Call the winning method here. 
	//This is the minimax method, that will check to see things. 
	public byte miniMax(JButton[][] board, byte turn, boolean isMaximizing)
	{
		//The method to check for the winner is called because if the winner was already determined by the past move.
		//Then there is no point of continuing, so it would just return the best score. 
		byte result = this.checkWinner();
		if (result != -5)
		{
			return result; 
		}
		
		//However, otherwise, minimax would continue. 
		if (isMaximizing)
		{
			byte bestScore = -2; 
			for (int i = 0; i < 3; i ++)
	 		{
				for (int x = 0; x < 3; x ++)
				{
					if (board[i][x].getText().equals(" "))
					{
						board[i][x].setText("O");
						byte newTurn = (byte) (this.blnTurn + 1);  
						byte score = this.miniMax(board, newTurn, false); 
						board[i][x].setText(" ");
						if (score > bestScore)
						{
							bestScore = score; 
						}
						
					}
				}
	 		}
			
			return bestScore; 
		}
		//Else means that it is blacks turn and thus, black would pick the lowest possible option for white, since it is trying to minimize its loses. 
		else
		{
			//The same method as the maximizing one, however, this time the best score is set the largest possible (2). 
			byte bestScore = 2; 
			for (int i = 0; i < 3; i ++)
	 		{
				for (int x = 0; x < 3; x ++)
				{
					if (board[i][x].getText().equals(" "))
					{
						board[i][x].setText("X");
						byte newTurn = (byte) (this.blnTurn + 1); 
						byte score = this.miniMax(board, newTurn , true); 
						board[i][x].setText(" ");
						//And the best score is changed if the minimax returns a score that is smaller. 
						if (score < bestScore)
						{
							bestScore = score;
						}
						
					}
				}
	 		}
			//Returns the best score for this scenario.
			return bestScore; 
		}
	}
	
	public byte checkWinner()
	{
		boolean win = false; 
		//5 will represent the winner not being determined. 
		byte winPlay = -5;  
		String compare = this.buttons[1][1].getText();
		
		//This if statement is not included with the other one (coming up) because it is better to just check this beforehand, no need to check if everytime throughout the loop. 
		//Checks the two diagonals and if they are 
		if (!compare.equals(" ") && compare.equals(this.buttons[0][0].getText()) && compare.equals(this.buttons[2][2].getText()))
		{
			win = true; 
			return this.checkText(compare);
		}
		else if (!compare.equals(" ") && compare.equals(this.buttons[0][2].getText()) && compare.equals(this.buttons[2][0].getText()))
		{
			win = true; 
			return this.checkText(compare);
		}
		 
		for (int i = 0; i < 3; i ++)
 		{ 
			for (int x = 0; x < 3; x ++)
			{ 
				//This doesn't work (potentially due to the first if statement). 
				//count ++;
				
				//These two are declared for efficiency purposes since they are going to be used in multiple conditions. 
				String middleNum = this.buttons[1][x].getText();
				String secondMiddle = this.buttons[i][0].getText();
				
				//Checking rows, which should theoretically work. 
				//Add the check clause for the emptiness here (incorporate). 
				if (!secondMiddle.equals(" ") && this.buttons[i][1].getText().equals(secondMiddle) && this.buttons[i][2].getText().equals(secondMiddle))
				{
					win = true;
					
					return this.checkText(secondMiddle); 
				}
				//
				else if (!middleNum.equals(" ") && this.buttons[0][x].getText().equals(middleNum) && this.buttons[2][x].getText().equals(middleNum) )
				{
					
					win = true;  
					return this.checkText(middleNum);
				}
			}	 
 		}
		//If the user hasn't won yet, it means that it is a tie, so it would return a zero. 
		if (win != true && this.checkTie())
		{
			return 0;
		}
		
		//This would return -5 meaning the game hasn't been concluded yet. 
		return winPlay;
	}
	
	//A method that checks to see if the game is a tie or not. 
	public boolean checkTie()
	{
		//Goes through the loop and if any of the positions are empty then returns false. 
		for (int i = 0; i < 3; i ++)
 		{
			for (int x = 0; x < 3; x ++)
			{
				if (this.buttons[i][x].getText().equals(" "))
					return false; 
			}
 		}
		//At the end of the loop if nothing was returned it means the array is full, so, it would return true, meaning it is a tie. 
		return true; 
	}
	
	//This method is done to increase the efficiency of this code. When passed a string of a button on the board, it will return the corresponding number. 
	//X is -1, O is +1, and null would just be -5. 
	public byte checkText(String n) 
	{
		if (n.equals("X"))
			return -1; 
		else if (n.equals("O"))
			return +1; 
		else 
			return -5; 
	}
	
	//This method is created to 
	public void actWinner()
	{
		byte result = this.checkWinner();
		if (result == -1)
		{
			//Debugging code:
			//System.out.println(this.checkWinner());
			//System.out.println(this.blnTurn);
			JOptionPane.showMessageDialog(null, "Congradulations, you won! Click to play again if you want.");
			//Also setting the state of the game to true to indicate that the game has been concluded. 
			this.playerScore.setText("" + (++this.playScore));
			this.gameEnd();
			this.gameState = true; 
		}
		else if (result == 0)
		{
			JOptionPane.showMessageDialog(null, "It was a tie! Click to play again.");
			//Also setting the state of the game to true to indicate that the game has been concluded. 
			this.gameState = true; 
			this.gameEnd();
			this.computerScore.setText("" + (++this.comScore));
			this.playerScore.setText("" + (++this.playScore));
		}
		else if (result == 1)
		{
			JOptionPane.showMessageDialog(null, "Unfortunately, you lost. Click to try again.");
			//Also setting the state of the game to true to indicate that the game has been concluded. 
			this.gameState = true; 
			this.gameEnd();
			this.computerScore.setText("" + (++this.comScore));
		}
	}
	
	//Disables all the buttons once the game is over. 
	public void gameEnd()
	{
		for (int i = 0; i < 3; i ++)
 		{
			for (int x = 0; x < 3; x ++)
			{
				this.buttons[i][x].setEnabled(false);
			}
 		}
	}
	
	//Main class to run the program. 
	public static void main(String[] args)
	{
		TicTacToe TTT = new TicTacToe();	
	}
}
