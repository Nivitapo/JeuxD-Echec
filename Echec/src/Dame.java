public class Dame extends Piece{
	
	public Dame(){
		super();
	}
	
	public Dame(int coul, int rang, int col)
	{
		if(coul == 1){
			couleur=1;
		    colonne=col;
		    rangee=rang;
		    nom="Dame";
		    unicode="\u2655";
		}
		else{
			couleur=0;
			colonne=col;
			rangee=rang;
			nom="Dame";
			unicode="\u265b";
		}
	}

}
