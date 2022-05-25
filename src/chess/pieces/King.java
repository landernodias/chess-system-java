package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;//associação com a partida
	
	public King(Board borad, Color color, ChessMatch chessMatch) {
		super(borad, color);
		this.chessMatch = chessMatch;
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
	
	//condição de roque
	private boolean testRookCastling(Position position) {//obj: nessa posição que foi informada existe uma torre e ela esta habita para roque
		ChessPiece p = (ChessPiece)getBorad().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;// testa se a peça é diferente de nulo testa se a peça é uma torre testa se ela é da cor do rei e testa se ela não foi movimentada
		//se a condição for satisfeita existe uma torre naquela possição habita para roque
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
		
		//#Specialmove Castling movimeto especial rei torre
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {// verifica se o rei não se moveu e se ele não está em check
			// # Special move castling kingside rook: roque pequeno
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);//posição onde deve estar a torre do rei
			if (testRookCastling(posT1)) {// testa se existe uma torre na posição
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBorad().piece(p1) == null && getBorad().piece(p2) == null) { //verifica as posições estão vazia para o rock
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// # Special move castling kingside rook: roque grande
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);//posição onde deve estar a torre do rei
			if (testRookCastling(posT2)) {// testa se existe uma torre na posição
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBorad().piece(p1) == null && getBorad().piece(p2) == null && getBorad().piece(p3) == null) { //verifica as posições estão vazia para o rock
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
			
		}
		
		return mat;
	}
}
