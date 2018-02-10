public class Fou extends Piece {
	
	public Fou(){
		super();
	}

	public Fou(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Fou";
		    unicode="\u2657";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Fou";
			unicode="\u265d";
		}
	}
}
