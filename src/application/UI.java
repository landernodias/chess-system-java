package application;

import chess.ChessPiece;

// imprime o tabuleiro
public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(8 - i + " ");
			for (int j = 0; j < pieces.length; j++ ) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	//Imprime um apeça
	private static void printPiece(ChessPiece piece) {
		// verifica se não tem peça numa determinada posição do tabuleiro.
		if(piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
