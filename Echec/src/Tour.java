public class Tour extends Piece {
	
	public Tour(){
		super();
	}
	
	public Tour(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Tour";
		    unicode="\u2656";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Tour";
			unicode="\u265c";
		}
	}

}
