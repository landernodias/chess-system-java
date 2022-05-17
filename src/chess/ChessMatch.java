package chess;

import boardgame.Board;

public class ChessMatch { // quem deve saber a dimensão de um tabuleiro de xadrez pe a classe chessMatch

	private Board board; //Na partida de chadrez precisa de um tabuleiro
	
	public ChessMatch() {
		board = new Board(8, 8);// define o tamanho do tabuleiro de xadrez
	}
	
	//retorna uma matriz de peças de xadrez correspondente a essa partida
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);// uma peça de chadrez dentro da partida de chadrez
			}
		}
		return mat;//retorna a matriz de peça da partida de xadrez
	}
}
