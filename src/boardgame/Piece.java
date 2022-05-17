package boardgame;

public class Piece {

	protected Position position;//posição da peça
	private Board borad;//relação com tabuleiro
	
	public Piece() {
	}

	public Piece(Board borad) {
		this.borad = borad;
		position = null;
	}

	//somente classes do mesmo pacote pode acessar o tabuleiro de um apeça: restringe somente as peças pode acessar o tabuleiro mais nenhuma outra classe pode
	protected Board getBorad() {//não tem set porque o tabuleiro não será alterado
		return borad;
	}
	
}
