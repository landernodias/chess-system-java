//################# CLASSE PARTIDA DE XADREZ##############

package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // quem deve saber a dimensão de um tabuleiro de xadrez pe a classe chessMatch

	private Board board; // Na partida de chadrez precisa de um tabuleiro

	public ChessMatch() {
		board = new Board(8, 8);// define o tamanho do tabuleiro de xadrez
		initialSetup(); // Coloca as peças no tabuleiro
	}

	// retorna uma matriz de peças de xadrez correspondente a essa partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);// uma peça de chadrez dentro da partida de chadrez
			}
		}
		return mat;// retorna a matriz de peça da partida de xadrez
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);// remove a peça da posição de origem
		Piece capturedPiece = board.removePiece(target); // remove a peça da posição de destino
		board.placePiece(p, target); // coloca a peça de origem no lugar da peça de destino
		return capturedPiece;
		
	}
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {// se não existir uma peça nessa posição
			throw new ChessException("There is no piece on source position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {// passa a posição da peça e a peça
		// chama o tabuleiro
		// add a peça com o placePiece
		// instancia uma posição do xadrez
		// passando uma peça nas posiçoes do xadrez a1 a h8
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
