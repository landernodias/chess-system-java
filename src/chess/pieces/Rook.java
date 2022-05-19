package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board borad, Color color) {
		super(borad, color);
	}

	@Override
	public String toString() {
		return "R";// R de rook - torre
	}

	// Movimentos da torre
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];// cria matriz com todas as
																						// posiçoes falsa
		// cria posição auxiliar
		Position p = new Position(0, 0);

		// Moviment above piece (cima):-- position.getRiw() -1 = linha da posição acima
		// da minha peça
		// osition.getColumn(): coluna da posição da peça
		p.setValues(position.getRow() - 1, position.getColumn()); // position é a posição que a peça está localizada
																	// dentro do tabuleiro

		// Enquanto a posição existir e não tiver uma peça lá: enquanto ela estiver vaga
		// marque que o movimento é verdadeiro
		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// indica que a peça pode mover para essa possição
			p.setRow(p.getRow() - 1);// movimento da peça uma casa para cima
		}
		// verifica se existe uma casa se nessa casa possui uma peça adversaria se sim
		// coloca ela como verdadeira
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Moviment left piece (esquerda)
		p.setValues(position.getRow(), position.getColumn() - 1);// movimento no aixo das coluna para a esquerda

		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);// coluna que movimenta para esquerda
		}
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Moviment rigth piece (direita)
		p.setValues(position.getRow(), position.getColumn() + 1);// movimento no aixo das coluna para a esquerda

		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);// coluna que movimenta para direita
		}
		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// isThereOponentPiece: verifica se a peça é uma
																		// peça do adversario
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Moviment below piece (baixo):-- position.getRiw() + 1 = linha da posição baixo
		p.setValues(position.getRow() + 1, position.getColumn()); 
		
		while (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);// linha que movimenta para baixo
		}

		if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

}
