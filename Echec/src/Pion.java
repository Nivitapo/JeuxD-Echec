public class Pion extends Piece{
	public Pion(){
		super();
	}
	
	public Pion(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Pion";
		    unicode="\u2659";
		    PTour=0;
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Pion";
			unicode="\u265f";
			PTour=0;
		}
	}
}
