package boardgame;

public class Board {

	//um taboleiro tem:
	private Integer rows; //linhas
	private Integer columns; //colunas
	private Piece[][] pieces; //matriz de peças
	
	public Board() {
	}

	public Board(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //instanciação da matriz de peças
	}
	
	
}
