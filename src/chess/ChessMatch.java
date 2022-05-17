//################# CLASSE PARTIDA DE XADREZ##############

package chess;

import javax.swing.border.Border;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // quem deve saber a dimensão de um tabuleiro de xadrez pe a classe chessMatch

	private Board board; //Na partida de chadrez precisa de um tabuleiro
	
	public ChessMatch() {
		board = new Board(8, 8);// define o tamanho do tabuleiro de xadrez
		initialSetup(); //Coloca as peças no tabuleiro
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {// passa a posição da peça e a peça
		//chama o tabuleiro
		// add a peça com o placePiece
		// instancia uma posição do xadrez
		// passando uma peça nas posiçoes do xadrez a1 a h8
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	private void initialSetup() {
//		board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e' ,8 , new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
}
