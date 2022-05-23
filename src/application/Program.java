package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// Instanciando uma partida de chadrez
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		//enquanto a partida não estiver em check mate
		while (!chessMatch.getCkeckMate()) {
			try {

				UI.clearScreen();
				UI.printMatch(chessMatch, captured);// imprime a partida de xadrez
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);//imprime o tabuleiro colorindo as opções possivel

				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);// execute um movimento de xadrez
				if (capturedPiece != null) {//controle de peça capturada
					//a peça foi capturada
					captured.add(capturedPiece);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
