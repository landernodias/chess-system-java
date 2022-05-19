//################# CLASSE PARTIDA DE XADREZ##############

package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // quem deve saber a dimensão de um tabuleiro de xadrez pe a classe chessMatch
	
	private Integer turn;
	private Color currentPlayer;

	private Board board; // Na partida de chadrez precisa de um tabuleiro

	private List<Piece> pieceOnTheBoard = new ArrayList<>();// aceita todo tipo de peça
	private List<Piece> capturedPicies = new ArrayList<>();
	
	public ChessMatch() {//PARTIDA DE XADREZ//
		board = new Board(8, 8);// define o tamanho do tabuleiro de xadrez
		turn = 1; // turno no inicio da partida vale um
		currentPlayer = Color.WHITE; // quem começa são as peça branca
		initialSetup(); // Coloca as peças no tabuleiro
	}

	public int getTurn() {
		return this.turn;
	}
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
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
	
	// Essa função permite que seja impressa as posições possivel apartir de uma posição de origem
	public boolean[][] possibleMoves(ChessPosition sourcePossition){
		Position position = sourcePossition.toPosition();// converte um aposição de xadrez para um aposição de matrix normal
		validateSourcePosition(position);// valida a possição já depois que o usuario entra com a possisão
		return board.piece(position).possibleMoves();
	}
	
	// executa uma jogada
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();//muda de turno
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);// remove a peça da posição de origem
		Piece capturedPiece = board.removePiece(target); // remove a peça da posição de destino
		board.placePiece(p, target); // coloca a peça de origem no lugar da peça de destino
		if (capturedPiece != null) { //verifica se uma peça foi capturada no jogo 
			pieceOnTheBoard.remove(capturedPiece);// remove a peça da lista de peça no tabueiro de xadrez
			capturedPicies.add(capturedPiece);
		}
		return capturedPiece;
		
	}
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {// se não existir uma peça nessa posição
			throw new ChessException("There is no piece on source position");
		}
		// verificar se quem pode jogar é o jogador valido
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {// pega a peça na possição faz um downcasting e verifica a cor delase a peça do jogador atual for diferente da peça pega é uma peça do adversario assim não posso movela
			throw new ChessException("The chosen piece is not yours");// a peça escolhida não é sua
		}
		//verifica se existe movimentos pociveis para a peça
		if (!board.piece(position).isThereAnyPossibleMove()) {//se não tiver nenhum movimento possivel
			throw new ChessException("There is no possible moves for the chosen piece");//não existe movimentos possivel para a peça escolhida
		}
	}

	// valida a posição de destino
	private void validateTargetPosition(Position source, Position target) {
		//verifica se a peça que está no tabuleiro na posição de origem pode mover para a posição de destino
		if (!board.piece(source).possibleMove(target)) {//se para peça de origem a posição de destino não é um movimento possivel:
			throw new ChessException("The chosen piece can't move to target position");// a peça escolhida não pode se mover para a posição de destino
		}
	}
	
	private void nextTurn() { //executa quando ocorre uma jogada
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;// troca de turno e mudança de jogador
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {// passa a posição da peça e a peça
		// chama o tabuleiro
		// add a peça com o placePiece
		// instancia uma posição do xadrez
		// passando uma peça nas posiçoes do xadrez a1 a h8
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//sempre que for instanciar uma nova peça no jogo de xadrez coloca na lista de peça do tabuleiro
		pieceOnTheBoard.add(piece);
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
