//################# CLASSE PARTIDA DE XADREZ##############

package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.border.Border;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch { // quem deve saber a dimensão de um tabuleiro de xadrez pe a classe chessMatch
	
	private Integer turn;
	private Color currentPlayer;// jogador atual
	private Board board; // Na partida de chadrez precisa de um tabuleiro
	private boolean check;
	private boolean checkMate;
	private ChessPiece emPassantvulnerable;// por padrão ela já inicia null
	
	private List<Piece> pieceOnTheBoard = new ArrayList<>();// aceita todo tipo de peça
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {//PARTIDA DE XADREZ//
		board = new Board(8, 8);// define o tamanho do tabuleiro de xadrez
		turn = 1; // turno no inicio da partida vale um
		currentPlayer = Color.WHITE; // quem começa são as peça branca
		//check = false;// opcional
		initialSetup(); // Coloca as peças no tabuleiro
	}

	public int getTurn() {
		return this.turn;
	}
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCkeckMate() {
		return checkMate;
	}
	
	public ChessPiece getEmPassantVulnerable() {
		return emPassantvulnerable;
	}
	
	// retorna uma matriz de peças de xadrez correspondente a essa partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);// uma peça de chadrez dentro da partida de chadrez
			}
		}
		return mat;// retorna a matriz de peça da partida de xadrez
	}
	
	// Essa função permite que seja impressa as posições possivel apartir de uma posição de origem
	public boolean[][] possibleMoves(ChessPosition sourcePossition){
		Position position = sourcePossition.toPosition();// converte um aposição de xadrez para um aposição de matrix normal
		validateSourcePosition(position);// valida a possição já depois que o usuario entra com a possisão
		return board.piece(position).possibleMoves();
	}
	
	// executa uma jogada
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);// executa o movimento
		
		// verifica se o movimento deixou o proprio jogador em xeque.
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("you can't put yourself in check");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);// pega uma referencia da peça que moveu
		
		// verifica se o oponente ficou em xeque
		check = (testCheck(opponent(currentPlayer))) ? true : false;
			
		//testa se o jogo acabou se deixou o oponentent em check mate
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();//muda de turno			
		}
		
		if(movedPiece instanceof Pawn && (target.getRow() == source.getRow() -2 || target.getRow() == source.getRow() + 2)) { //verifica se a peça movida é um peão e se o movimento foi dois para mais ou menos ou sej amoveu duas casa
			// movimento de peão pela primeira vez e ele esta vuneravel a tomar um empassantvulnerable
			emPassantvulnerable = movedPiece;
		} else {
			emPassantvulnerable = null;
		}
		
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);// remove a peça da posição de origem
		p.increseMoveCount();
		Piece capturedPiece = board.removePiece(target); // remove a peça da posição de destino
		board.placePiece(p, target); // coloca a peça de origem no lugar da peça de destino
		if (capturedPiece != null) { //verifica se uma peça foi capturada no jogo 
			pieceOnTheBoard.remove(capturedPiece);// remove a peça da lista de peça no tabueiro de xadrez
			capturedPieces.add(capturedPiece);// lista de peça capturada
		}
		
		// #Specialmove castling kingside rook : movimento de roque lado menor
		if(p instanceof King && target.getColumn() == source.getColumn() + 2) { //verifica se p é um rei e se a coluna de destino é igual a coluna de origem +2 (direita) é um roque pequeno
			//movimentando a torre para sua posição no roque
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);// posição da torre: mesma linha do rei a três colunas do rei
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);// posição de destino da torre
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);//retira a torre da posição que ela está
			board.placePiece(rook, targetT);// move para a posição de destino
			rook.increseMoveCount();// incrementa a quantidade de movimento da torre
		}
		
		// #Specialmove castling Queenside rook : movimento de roque lado da rainha
		if(p instanceof King && target.getColumn() == source.getColumn() - 2) { //verifica se p é um rei e se a coluna de destino é igual a coluna de origem -2 (esquerda) é um roque pequeno
			//movimentando a torre para sua posição no roque
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);// posição da torre: mesma linha do rei a três colunas do rei
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);// posição de destino da torre
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);//retira a torre da posição que ela está
			board.placePiece(rook, targetT);// move para a posição de destino
			rook.increseMoveCount();// incrementa a quantidade de movimento da torre
		}
		
		// #Specialmove en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {// meu peão andou na diagonal e não capturou peça foi um empassant
				Position pawnPosition;
				// verifica se o peão que moveu é um peão branco
				if (p.getColor() == Color.WHITE) {// significa que a peça capturada esta embaixo do peão branco
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());//é na linha de baixo a peça capturada
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);// remove a peça na posição 
				capturedPieces.add(capturedPiece);// adciona ela na lista de peça capturadas
				pieceOnTheBoard.remove(capturedPiece);// remove ela das peças do tabuleiro
				}
		}
		
		return capturedPiece;
		
	}
	// desfazer o movimento
	private void undoMove(Position source, Position target, Piece capturedPiece ) {
		ChessPiece p = (ChessPiece)board.removePiece(target);// cria um apeça que recebe e tira a peça que movi para o destino
		p.decreaseMoveCount();
		board.placePiece(p, source);// devolve a peça do destino na origem
		
		if(capturedPiece != null) {// verifica se uma peça foi capturada
			board.placePiece(capturedPiece, target);// volta a peça captura para o posição de destino 
			capturedPieces.remove(capturedPiece); //remove a peça capturada da lista de peças capturadas
			pieceOnTheBoard.add(capturedPiece);//adciona novamente a peça capturada na lista de peça do tabuleiro
			
		}
		//DESFAZENDO MOVIMENTOS ROQUE 
		// #Specialmove castling kingside rook : movimento de roque lado menor
				if(p instanceof King && target.getColumn() == source.getColumn() + 2) { //verifica se p é um rei e se a coluna de destino é igual a coluna de origem +2 (direita) é um roque pequeno
					//movimentando a torre para sua posição no roque
					Position sourceT = new Position(source.getRow(), source.getColumn() + 3);// posição da torre: mesma linha do rei a três colunas do rei
					Position targetT = new Position(source.getRow(), source.getColumn() + 1);// posição de destino da torre
					ChessPiece rook = (ChessPiece)board.removePiece(target);//retira a torre da posição que ela está
					board.placePiece(rook, sourceT);// move para a posição de origem novamente
					rook.decreaseMoveCount();// decrementa a quantidade de movimento da torre
				}
				
				// #Specialmove castling Queenside rook : movimento de roque lado da rainha
				if(p instanceof King && target.getColumn() == source.getColumn() - 2) { //verifica se p é um rei e se a coluna de destino é igual a coluna de origem -2 (esquerda) é um roque pequeno
					//movimentando a torre para sua posição no roque
					Position sourceT = new Position(source.getRow(), source.getColumn() - 4);// posição da torre: mesma linha do rei a três colunas do rei
					Position targetT = new Position(source.getRow(), source.getColumn() - 1);// posição de destino da torre
					ChessPiece rook = (ChessPiece)board.removePiece(target);//retira a torre da posição que ela está
					board.placePiece(rook, sourceT);// move para a posição de origem novamente
					rook.decreaseMoveCount();// decrementa a quantidade de movimento da torre
				}
				
				// #Specialmove en passant
				if (p instanceof Pawn) {
					if (source.getColumn() != target.getColumn() && capturedPiece == emPassantvulnerable) {// meu peão andou na diagonal e não capturou peça foi um empassant
						ChessPiece pawn = (ChessPiece)board.removePiece(target);// retira ela do lugar errado que ela voltou
						Position pawnPosition;
						// verifica se o peão que moveu é um peão branco
						if (p.getColor() == Color.WHITE) {// significa que a peça capturada esta embaixo do peão branco
							pawnPosition = new Position(3, target.getColumn());//é na linha de baixo a peça capturada
						} else {
							pawnPosition = new Position(4, target.getColumn());
						}
						board.placePiece(pawn, pawnPosition);
					}
				}
		
	}
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {// se não existir uma peça nessa posição
			throw new ChessException("There is no piece on source position");
		}
		// verificar se quem pode jogar é o jogador valido
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {// pega a peça na possição faz um downcasting e verifica a cor delase a peça do jogador atual for diferente da peça pega é uma peça do adversario assim não posso movela
			throw new ChessException("The chosen piece is not yours");// a peça escolhida não é sua
		}
		//verifica se existe movimentos pociveis para a peça
		if (!board.piece(position).isThereAnyPossibleMove()) {//se não tiver nenhum movimento possivel
			throw new ChessException("There is no possible moves for the chosen piece");//não existe movimentos possivel para a peça escolhida
		}
	}

	// valida a posição de destino
	private void validateTargetPosition(Position source, Position target) {
		//verifica se a peça que está no tabuleiro na posição de origem pode mover para a posição de destino
		if (!board.piece(source).possibleMove(target)) {//se para peça de origem a posição de destino não é um movimento possivel:
			throw new ChessException("The chosen piece can't move to target position");// a peça escolhida não pode se mover para a posição de destino
		}
	}
	
	private void nextTurn() { //executa quando ocorre uma jogada
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;// troca de turno e mudança de jogador
	}
	
	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE; //verifica a cor do oponent e retor a cor do oponente
	}
	
	private ChessPiece king(Color color) {
		//filtrando as peças do jogo procurnado o rei
		List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());// filtrando um alista
		for(Piece p : list) {
			if (p instanceof King) {// verifica se a peça é uma instancia de rei
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");// não existe a cor do rei no tabuleiro
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();// pega a posição do rei
		List<Piece> opponentPieces = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());// filtra as peças no tabuleiro do opponente e joga numa lista
		for (Piece p : opponentPieces) {//verifica todas as peças do oponente
			boolean[][] mat = p.possibleMoves();//cria uma matrix de boleano com as peças do oponente 
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {//verifica se alguma delas é possivel movimentar para a posição do rei
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());// filtra todas as peças do tabuleiro dessa cor
		// verifica se existe alguma peça da cor que retire o rei de check
		for (Piece p: list) {
			boolean[][] mat = p.possibleMoves();//verifica todos movimentos possiveis da peça p
			//percorrendo as linhas e as colunas da matrix
			for(int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]) {// testa  se é um movimento possivel
						//testa se esse movimento possivel tira o rei do check?]
						//posição de origem
						Position source = ((ChessPiece)p).getChessPosition().toPosition();// pega a posição inicial da peça p fazendo um downcasting para chessPiece pois o atributo position é privado
						// posição de destino
						Position target = new Position(i,j);// posição de movimento possivel
						Piece capturedPiece = makeMove(source, target);//movimenta da origem para o destino
						boolean testCheck = testCheck(color);//verifica se ainda está em check
						undoMove(source, target, capturedPiece);// desfaz o movimento feito
						if(!testCheck) {//verifica se tirou o rei de check
							return false;
						}
					}
				}
			}
		}
		return true;
		
	}
	private void placeNewPiece(char column, int row, ChessPiece piece) {// passa a posição da peça e a peça
		// chama o tabuleiro
		// add a peça com o placePiece
		// instancia uma posição do xadrez
		// passando uma peça nas posiçoes do xadrez a1 a h8
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//sempre que for instanciar uma nova peça no jogo de xadrez coloca na lista de peça do tabuleiro
		pieceOnTheBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));// this: passa a auto referencia da partida
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));// this: passa a auto referencia da partida 
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
		
	}
}
