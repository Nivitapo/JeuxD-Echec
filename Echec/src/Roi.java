public class Roi extends Piece{
	
	public Roi(){
		super();
	}
	
	public Roi(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Roi";
		    unicode="\u2654";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Roi";
			unicode="\u265a";
		}
	}

}
