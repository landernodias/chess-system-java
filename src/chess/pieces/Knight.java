package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board borad, Color color) {
		super(borad, color);
	}

	@Override
	public String toString() {
		return "N"; // k : king - rei
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

		p.setValues(position.getRow() - 1, position.getColumn() - 2);// move cavalo em L para cima 1
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode
															// se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1);// move cavalo em L para baixo 2
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() + 1);// move rei para esquerda
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2);// move rei para direita
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 2);// move rei para noroeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		

		p.setValues(position.getRow() + 2, position.getColumn() + 1);// move rei para nordeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() - 1);// move rei para sudoeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() - 2);// move rei para sudeste
		if (getBorad().positionExists(p) && canMove(p)) {// verifica se existe essa posição no tabuleiro e se o rei pode se mover para ela
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}
}

