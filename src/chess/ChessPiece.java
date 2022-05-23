package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

	private Color color;
	private int moveCount;

	public ChessPiece(Board borad, Color color) {
		super(borad);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public void increseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public int getMoveCount() {
		return moveCount;
	}

	//verifica se tem uma peça advrsaria em uma dada casa
	protected boolean isThereOpponentPiece(Position position) {
		// variavel que pega a peça que está na posição no tabuleiro
		ChessPiece p = (ChessPiece)getBorad().piece(position);
		//verificar se é um a peça adversaria
		return p != null && p.getColor() != color;//verifica se a peça é diferente de nulo e se ela é diferente da peça da cor que eu estou utilizando
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
}
