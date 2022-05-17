package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board borad, Color color) {
		super(borad, color);
	}

	@Override
	public String toString() {
		return "K"; // k : king - rei
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];
		return mat;
	}
}
