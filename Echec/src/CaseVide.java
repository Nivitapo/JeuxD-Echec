public class CaseVide extends Piece{
	
	public CaseVide(){
		super();
	}
	
	public CaseVide(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom=" ";
		    unicode=" ";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom=" ";
			unicode=" ";
		}
	}

}