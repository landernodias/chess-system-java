package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;// depedencia para a partida
	
	public Pawn(Board borad, Color color, ChessMatch chessMatch) {
		super(borad, color);
		this.chessMatch = chessMatch;

	}
	@Override
	public String toString() {
		return "P"; // P : Pawn - Peão
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBorad().getRows()][getBorad().getColumns()];
		Position p = new Position(0, 0);
		
		//MOVIMENTAÇÂO PEÂO BRANCO
		// regra geral do peão branco
		if (getColor() == Color.WHITE) { // verifica se a peça é da cor branca
			p.setValues(position.getRow() - 1, position.getColumn());// movimenta uma casa para frente/acima
			if (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {// verifica se o pião pode se mover para a possição
				mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
			}
			// teste para duas linhas a frente
			p.setValues(position.getRow() - 2, position.getColumn());// movimenta duas casa para frente/acima
			Position p2 = new Position(position.getRow() - 1, position.getColumn());// pega a primeira posição afrentedo peão
			if (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p) && getBorad().positionExists(p2) && !getBorad().thereIsAPiece(p2) && getMoveCount() == 0) {// verifica se o pião pode se mover para a possição e se não existeça na primeira posição
				mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
			}
			// teste captura de peça na diagonal
			p.setValues(position.getRow() - 1, position.getColumn() - 1);// movimenta uma casa para diagonal esquerda
			if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// verifica se o movimento é possivel e se tem uma peça adversaria no local
				mat[p.getRow()][p.getColumn()] = true; // pode ir para a posição uma linha para frente
			}
			// teste captura de peça na outra diagonal
			p.setValues(position.getRow() - 1, position.getColumn() + 1);// movimenta uma casa para diagonal direita
			if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// verifica se o movimento é possivel e se tem uma peça adversaria no local
				mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
			}
			
			// #Specialmove en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() -1);
				//:verifica se existe uma posição left, verifica se na posição right existe uma peça do oponente, verifica se a peça na posição right do tabuleiro e esta vuneravel a tomar um em passant
				if (getBorad().positionExists(left) && isThereOpponentPiece(left) && getBorad().piece(left) == chessMatch.getEmPassantVulnerable()) {
					// se sim posso captura a peça na posição right
					mat[left.getRow() -1][left.getColumn()] = true;// posição possivel para o pião mover
					
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				//:verifica se existe uma posição right, verifica se na posição right existe uma peça do oponente, verifica se a peça na posição rignt do tabuleiro e esta vuneravel a tomar um em passant
				if (getBorad().positionExists(right) && isThereOpponentPiece(right) && getBorad().piece(right) == chessMatch.getEmPassantVulnerable()) {
					// se sim posso captura a peça na posição left
					mat[right.getRow() -1][right.getColumn()] = true;// posição possivel para o pião mover
					
				}
			}
		} else {
			// MOVIMENTO PEÇAS PRETA
			// regra geral peão preto
			if (getColor() == Color.BLACK) { // verifica se a peça é da cor branca
				p.setValues(position.getRow() + 1, position.getColumn());// movimenta uma casa para frente/acima
				if (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p)) {// verifica se o pião pode se moverpara a possição
					mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
				}
				// teste para duas linhas a frente
				p.setValues(position.getRow() + 2, position.getColumn());// movimenta duas casa para frente/acima
				Position p2 = new Position(position.getRow() + 1, position.getColumn());// pega a primeira posição afrente do peão
				if (getBorad().positionExists(p) && !getBorad().thereIsAPiece(p) && getBorad().positionExists(p2)
						&& !getBorad().thereIsAPiece(p2) && getMoveCount() == 0) {// verifica se o pião pode se mover para a possição e se não existeça na primeira posição
					mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
				}
				// teste captura de peça na diagonal
				p.setValues(position.getRow() + 1, position.getColumn() - 1);// movimenta uma casa para diagonal esquerda
				if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// verifica se o movimento é possivel e se tem uma peça adversaria no local
					mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
				}
				// teste captura de peça na outra diagonal
				p.setValues(position.getRow() + 1, position.getColumn() + 1);// movimenta uma casa para diagonal direita
				if (getBorad().positionExists(p) && isThereOpponentPiece(p)) {// verifica se o movimento é possivel e se tem uma peça adversaria no local
					mat[p.getRow()][p.getColumn()] = true;// pode ir para a posição uma linha para frente
				}
				// #Specialmove en passant black
				if (position.getRow() == 4) {
					Position left = new Position(position.getRow(), position.getColumn() -1);
					//:verifica se existe uma posição left, verifica se na posição right existe uma peça do oponente, verifica se a peça na posição right do tabuleiro e esta vuneravel a tomar um em passant
					if (getBorad().positionExists(left) && isThereOpponentPiece(left) && getBorad().piece(left) == chessMatch.getEmPassantVulnerable()) {
						// se sim posso captura a peça na posição right
						mat[left.getRow() + 1][left.getColumn()] = true;// posição possivel para o pião mover
						
					}
					Position right = new Position(position.getRow(), position.getColumn() + 1);
					//:verifica se existe uma posição right, verifica se na posição right existe uma peça do oponente, verifica se a peça na posição rignt do tabuleiro e esta vuneravel a tomar um em passant
					if (getBorad().positionExists(right) && isThereOpponentPiece(right) && getBorad().piece(right) == chessMatch.getEmPassantVulnerable()) {
						// se sim posso captura a peça na posição left
						mat[right.getRow() + 1][right.getColumn()] = true;// posição possivel para o pião mover
						
					}
				}
			}
		}

		return mat;
	}

}
