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
	//retorna a posição da peça 
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

}
