package boardgame;

public abstract class Piece {

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
	
	public abstract boolean[][] possibleMoves();
	
	//rookmetods: é quando um metodo interno usa um método abstrato: um gancho com a subclasse
	public boolean possibleMove(Position position){
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves(); // cria um amatriz de boolean
		for (int i = 0; i < mat.length; i++) {//varre a matriz: presume que a matrix é quadrada
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j]) { //verifica se existe alguma posição que seja verdadeira
					return true;
				}
			}
		}
		return false;
	}
	
}
