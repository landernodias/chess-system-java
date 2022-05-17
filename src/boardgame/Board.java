package boardgame;

public class Board {

	// um taboleiro tem:
	private Integer rows; // linhas
	private Integer columns; // colunas
	private Piece[][] pieces; // matriz de peças

	public Board() {
	}

	public Board(Integer rows, Integer columns) {
		// programação defenciva
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // instanciação da matriz de peças
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	// retorna a matriz piceis na linha row coluna column.
	public Piece piece(int row, int column) {
		// verificar se a posição da peça não existe.
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	// retorna a peça pela posição da matriz
	public Piece piece(Position position) {
		// verificar se não existe uma posição de uma peça.
		if (!positionExists(position.getRow(), position.getColumn())) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		// Antes de colocar uma peça na posição verificar se tem outra peça na posição
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;// pegar a matriz na posição dada e atribuir a ela uma
																// peça que foi informado
		piece.position = position;// fala que a posição da peça não é mais nula elá rece a position informada.
	}

	public Piece removePiece(Position position) {//remove uma peça do tabuleiro
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);//pega uma peça do tabuleiro
		aux.position = null;//remove ela setando ela para null
		pieces[position.getRow()][position.getColumn()] = null;// indica que não tem mais peça nessa posição da matrix
		return aux; //retor a peça retirada
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;// verifica se a posição esta no tabuleiro
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());// verificar se a posição existe
	}

	// testa se tem uma peça na posição informada
	public boolean thereIsAPiece(Position position) {
		// verifica se a posição existe
		if (!positionExists(position.getRow(), position.getColumn())) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;// se a peça for diferente de null significa que tem um apeça na posição
										// informada
	}

}
