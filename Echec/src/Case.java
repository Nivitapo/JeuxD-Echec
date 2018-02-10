public class Case{
	
	public Case(){
	}
	public Piece[][] getCase(){
				
		  		Piece tabP[][];
		  		tabP = new Piece[8][8];
			
				tabP[0][0] = new Tour(1,0,1);
				tabP[0][1] = new Cavalier(1,0,2);
				tabP[0][2] = new Fou(1,0,3);
				tabP[0][3] = new Dame(1,0,4);
				tabP[0][4] = new Roi(1,0,5);
				tabP[0][5] = new Fou(1,0,6);
				tabP[0][6] = new Cavalier(1,0,7);
				tabP[0][7] = new Tour(1,0,8);
				
				tabP[1][0] = new Pion(1,1,1);
				tabP[1][1] = new Pion(1,1,2);
				tabP[1][2] = new Pion(1,1,3);
				tabP[1][3] = new Pion(1,1,4);
				tabP[1][4] = new Pion(1,1,5);
				tabP[1][5] = new Pion(1,1,6);
				tabP[1][6] = new Pion(1,1,7);
				tabP[1][7] = new Pion(1,1,8);
				
				for (int j=2; j<6; j++){
					for(int i=0; i<8;i++){
						tabP[j][i]= new CaseVide(1,1,5);
					}
				}

				
				tabP[6][0] = new Pion(0,1,1);
				tabP[6][1] = new Pion(0,1,2);
				tabP[6][2] = new Pion(0,1,3);
				tabP[6][3] = new Pion(0,1,4);
				tabP[6][4] = new Pion(0,1,5);
				tabP[6][5] = new Pion(0,1,6);
				tabP[6][6] = new Pion(0,1,7);
				tabP[6][7] = new Pion(0,1,8);
				
				tabP[7][0] = new Tour(0,0,1);
				tabP[7][1] = new Cavalier(0,0,2);
				tabP[7][2] = new Fou(0,0,3);
				tabP[7][3] = new Dame(0,0,4);
				tabP[7][4] = new Roi(0,0,5);
				tabP[7][5] = new Fou(0,0,6);
				tabP[7][6] = new Cavalier(0,0,7);
				tabP[7][7] = new Tour(0,0,8);

			return tabP;
	  }
	
}