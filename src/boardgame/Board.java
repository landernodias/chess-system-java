package boardgame;

public class Board {

	// um taboleiro tem:
	private Integer rows; // linhas
	private Integer columns; // colunas
	private Piece[][] pieces; // matriz de peças

	public Board() {
	}

	public Board(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // instanciação da matriz de peças
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	// retorna a matriz piceis na linha row coluna column.
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	//retorna a peça pela posição 
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;//pegar a matriz na posição dada e atribuir a ela uma peça que foi informado
		piece.position = position;// fala que a posição da peça não é mais nula elá rece a position informada.
	}

}
