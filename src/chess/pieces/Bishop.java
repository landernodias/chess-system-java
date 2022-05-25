package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board borad, Color color) {
		super(borad, color);
	}

	@Override
	public String toString() {
		return "B";// R de rook - torre
	}

	// Movimentos da torre
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];// cria matriz com todas as
																						// posiçoes falsa
		// cria posição auxiliar
		Position p = new Position(0, 0);

		// Moviment above piece (noroeste)

		p.setValues(position.getRow() - 1, position.getColumn() - 1);// movimenta uma posição par anoroeste 
		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// indica que a peça pode mover para essa possição
			p.setValues(p.getRow() - 1, p.getColumn() - 1);// movimento da peça uma casa para noroeste jagonal
		}
		// verifica se existe uma casa se nessa casa possui uma peça adversaria se sim
		// coloca ela como verdadeira
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Moviment left piece (nordeste)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);// movimento no aixo das coluna para a esquerda

		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);// coluna que movimenta para esquerda
		}
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Moviment rigth piece (sudeste)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);// movimento no aixo das coluna para a esquerda

		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);// coluna que movimenta para direita
		}
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Moviment below piece (sudoeste)
		p.setValues(position.getRow() + 1, position.getColumn() - 1); 
		
		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);// linha que movimenta para baixo
		}

		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

}