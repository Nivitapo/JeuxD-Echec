public class Cavalier extends Piece {
	
	public Cavalier(){
		super();
	}
	
	public Cavalier(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Cavalier";
		    unicode="\u2658";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Cavalier";
			unicode="\u265e";
		}			
	}

}
