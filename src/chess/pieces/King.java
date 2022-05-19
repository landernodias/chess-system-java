package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

	// pode mover
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBorad().piece(position);// peg apeça p que está na posição
		return p == null || p.getColor() != getColor(); // move se a casa de destino esta vazia/null ou a cor for diferente
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColumn());// move rei para cima
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode
															// se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());// move rei para baixo
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);// move rei para esquerda
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);// move rei para direita
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonais
		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);// move rei para noroeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);// move rei para nordeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);// move rei para sudoeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);// move rei para sudeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}
}
