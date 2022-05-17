package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board borad, Color color) {
		super(borad, color);
	}
	
	@Override
	public String toString() {
		return "R";//R de rook - torre
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];
		return mat;
	}

}
