import java.awt.Color;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*---------------------Initialisation variable------------------------*/
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Echequier extends JFrame implements ActionListener {
   private static final long serialVersionUID = 1L;
   private JButton[][] tab = new JButton[8][8];//Declaration de tableau de bouton vide a remplir plus tard
   private Piece[][] tabP = new Piece[8][8];//Declaration de tableau de piece a remplir plus tard
   private Case o = new Case();//Variable pour recuperer le tableau de piece contenu dans "Case"
   private Piece t1 =new Piece();//Variable utile pour echanger des cases dans le tableau de piece
   private int x;//Variable pour garder en memoire la ligne du premier bouton clique
   private int y;//Variable pour garder en memoire la colonne du premier bouton clique
   private int b;//Variable pour garder en memoire la ligne du deuxieme bouton clique
   private int e;//Variable pour garder en memoire la colonne du deuxieme bouton clique
   private boolean mange;//Variable pour savoir si la piece est mangeable
   private int tourJoueur = 1;//Variable pour alterner joueueur noir et joueur blanc
  
   
   
 
 /*---------------------Constructeur Echequier------------------------*/	  
     public Echequier(){
		this.initialise();//Initialiser l'echequier
		affichage();//Afficher a l'ecran
	  }
     
     
/*----------------------Fonction realiser l'affichage----------------*/  
	  public void affichage(){
  	    for(int j=0;j<8;j++){//	    	
	    	for(int i=0;i<8;i++){
			    this.add(tab[j][i]);//Ajouter chaque bouton au panel par defaut
	    	}
	   	}
	    this.setTitle("Echequier");
	    this.setSize(500, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    GridLayout c = new GridLayout(8,8);//Creer un tableau pour afficher les 64 boutons
	    this.setLayout(c);
	    this.setVisible(true);
	    
	  }
/*----------------------Fonction initialiser echequier--------------*/
	  
	  public void initialise(){
        tabP=o.getCase();//Prendre le tableau de piece contenu dans Case() et le mettre dans tabP[][]
	    boolean coulCase=true;//Variable pour alterner les couleurs blanc/noir
	    for(int j=0;j<8;j++){	//Parcourir le tableau    	
	    	for(int i=0;i<8;i++){
	    		if(coulCase == true){
    				tab[j][i]=new JButton(tabP[j][i].unicode);//Creer un bouton tab[j][i] avec comme Texte l'unicode 
    				tab[j][i].setBackground(Color.WHITE);	  //contenu dans tabP[j][i](comme ca l'unicode de la 1ere
    				coulCase=false;							  //piece sera sur le 1er bouton. Le 2eme unicode sera 
	    		}											  //sur le 2eme bouton et ainsi de suite...)
	    		else{
    				tab[j][i]=new JButton(tabP[j][i].unicode);	
    				tab[j][i].setBackground(Color.GRAY);
    				coulCase=true;
	    		}
	    		tab[j][i].addActionListener(this); 
	    		
	    		
	    	}
	    	
	    	
	    	if(coulCase==true)
	    		coulCase=false;
	    	else
	    		coulCase=true;	    	
	    }
	    
	  } 
	  public void couleurEchequier(){//Reinitialiser les couleurs de l'echequier
		  boolean coulC=true;
		    for(int f=0;f<8;f++){	    	
		    	for(int d=0;d<8;d++){
		    		if(coulC == true){
	    				tab[f][d].setBackground(Color.WHITE);
	    				coulC=false;
		    		}
		    		else{;	
	    				tab[f][d].setBackground(Color.GRAY);
	    				coulC=true;
		    		}  		
		    	}
		    	if(coulC==true)
		    		coulC=false;
		    	else
		    		coulC=true;	    	
		    }
	  }
/*----------------------Action sur un bouton------------------------*/
	  public void actionPerformed(ActionEvent clic) {
  	
		if(((JButton)clic.getSource()).getBackground()==Color.green){//Si le bouton clique est vert
			
			for(int p=0;p<8;p++){
				for(int q=0;q<8;q++){
					if(tab[p][q]==clic.getSource()){//Je garde en memoire les coordonnees du bouton clique
						this.b=p;					//(Ce bouton clique est le 2eme bouton clique qui correspond
						this.e=q;					//au bouton pour se deplacer)
					}
				}
			}

			if(((b==7) || (b==0)) && (this.tabP[y][x].getNom()=="Pion")){
				if(this.tabP[y][x].getCouleur()==1){
					
				fenetrePromo(1,b,e);
				
				}
				else{
					
					fenetrePromo(0,b,e);
				}
				
			}
			if(tabP[b][e].nom != " "){//Si la 2eme case clique contient une piece
				tabP[b][e] = new CaseVide(1,1,'V');//La supprimer en la remplacant par une case vide
			}
			
			t1=tabP[b][e]; // Echanger la 1ere case clique avec la 2eme dans le tableau de piece
			tabP[b][e]=tabP[y][x]; //Les variables x et y stockent la 1ere case clique
			tabP[y][x]=t1;
			tab[b][e].setText(tabP[b][e].unicode);//L'afficher sur les bouton
			tab[y][x].setText(tabP[y][x].unicode);
			

		
			
			if(tourJoueur==1){//Lorsque la piece est deplace, changer la variable du tour
				tourJoueur=0;
			}
			else if(tourJoueur==0){
				tourJoueur=1;
			}
			if(tabP[b][e].nom == "Pion"){
				tabP[b][e].PTour ++;
			}
			
			couleurEchequier();//Reinitialiser les couleur de l'echequier
   		}//Fin du si
   		else{//Sinon
   			for(int j=0;j<8;j++){//Parcourir le tableau
   				for(int i=0;i<8;i++){
   					
   					if(tab[j][i]==clic.getSource()){//Si le bouton clique correspond a une case du tableau
   						if(tabP[j][i].couleur == 0){//Si la piece est noir
   							this.mange=false;//Elle peut manger les blanc
   						}
   						else{
   							this.mange=true;//Sinon elle est blanche elle peut manger les noir
   						}
   						
/*--------------------*/if(tabP[j][i].nom=="Pion"){/*------------------DEPLACEMENT DU PION--------------------*/
   							couleurEchequier();
   						  if(tabP[j][i].couleur == 0 && tourJoueur == 0){//Si la couleur est noir est c'est au tour des noir
   							if(j-1>=0){// On verifie qu'on n'essaye pas d'acceder a une case qui n'existe pas
   								if(tab[j-1][i].getText()==" ")// Si la case est vide
   									tab[j-1][i].setBackground(Color.green);//Mettre la case du dessu sur vert
   								
   							}
   							if(tabP[j][i].PTour == 0){//Si le pion bouge pour la premiere fois
   								if(tab[j-1][i].getText()== " "){//si la case du dessus est vide
   									if(tab[j-2][i].getText()==" ")//si la 2eme case du dessus est vide
   									tab[j-2][i].setBackground(Color.green);//mettre la 2eme case du dessus sur vert
   									
   								}
   							}
   							if(j-1>=0 && i+1<8){//On verifie qu'on n'essaye pas d'acceder a une case qui n'existe pas
   							  if(tabP[j-1][i+1].nom != " "){//Si la case en diagonale droite n'est pas vide
   						        if(tabP[j-1][i+1].couleur == 1 && mange == false){//Si sa couleur est blanche et si "mange" est faux
									tab[j-1][i+1].setBackground(Color.green);//Colorier cette case en vert (pour pouvoir la manger)
									if(estEchec(tabP[j-1][i+1]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
									
								}
   							  }
   							}
   							if(j-1>=0 && i-1>=0){//On verifie qu'on n'essaye pas d'acceder a une case qui n'existe pas
   							  if(tabP[j-1][i-1].nom != " "){//Si la case en diagonale gauche n'est pas vide
   						        if(tabP[j-1][i-1].couleur == 1 && mange == false){//Si sa couleur est blanche et si "mange" est faux
									tab[j-1][i-1].setBackground(Color.green);//Colorier cette case en vert (pour pouvoir la manger)
									if(estEchec(tabP[j-1][i-1]) == true)
										FenetreEchec(this.tabP[j][i].getCouleur());
									
								}
   							  }
   							} 							
   						  }
   						  else if(tabP[j][i].couleur == 1 && tourJoueur == 1){// Meme principe mais pour les blanc
   							if(j+1<8){
   								if(tab[j+1][i].getText()==" ")
   									tab[j+1][i].setBackground(Color.green);
   							
   							}
   							if(tabP[j][i].PTour == 0){
   								if(tab[j+1][i].getText()== " "){
   									if(tab[j+2][i].getText()==" ")
   										tab[j+2][i].setBackground(Color.green);
   									
   								}
   							}
   							if(j+1<8 && i+1<8){
   								if(tabP[j+1][i+1].nom != " "){
   									if(tabP[j+1][i+1].couleur == 0 && mange == true){
   										tab[j+1][i+1].setBackground(Color.green);
   										if(estEchec(tabP[j+1][i+1]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   										
   									}
   								}	
   							}
   							if(j+1<8 && i-1>=0){
   								if(tabP[j+1][i-1].nom != " "){
   									if(tabP[j+1][i-1].couleur == 0 && mange == true){
   										tab[j+1][i-1].setBackground(Color.green);	
   										if(estEchec(tabP[j+1][i-1]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   										
   									}
   								}
   							}
   	   						
   	   					  }	
   						 
   						  this.x=i;//Stocker les coordonnes du bouton clique
   						  this.y=j;
   						 
   						 
   						}
/*--------------------*/else if(tabP[j][i].nom=="Tour" && tabP[j][i].couleur==tourJoueur){/*----DEPLACEMENT DE LA TOUR----*/
   						 couleurEchequier();
   							for(int z=1;z<8;z++){//Parcourir toute les cases du dessus
   							 if(j-z>=0){
   								if(tab[j-z][i].getText()==" "){//Si les cases sont vides
   									tab[j-z][i].setBackground(Color.green);//Les mettres en vert
   								} 
   								if(tab[j-z][i].getText()!=" "){//Si elles ne sont pas vide
   									if(tabP[j-z][i].couleur == 1 && mange == false){//Si la case est blanche et que la premiere case
   										tab[j-z][i].setBackground(Color.green);	    //clique est de couleur noir alors la case est mangeable
   										if(estEchec(tabP[j-z][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());//voir ligne 127 pour comprendre "mange"
   									}
   									if(tabP[j-z][i].couleur == 0 && mange == true){//Si la case est noir et que la premiere case
   										tab[j-z][i].setBackground(Color.green);	   //clique est de couleur blanche alors la case est mangeable
   										if(estEchec(tabP[j-z][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());//voir ligne 127 pour comprendre "mange"
   									}
   									break;
   								}
   							 }
   							}
   							for(int t=1;t<8;t++){//Meme principe pour les cases de droite
							  if(i+t<8){	
   								if(tab[j][i+t].getText()==" "){
   										tab[j][i+t].setBackground(Color.green);
   								} 
   								if(tab[j][i+t].getText()!=" "){
   									if(tabP[j][i+t].couleur == 1 && mange == false){//
   										tab[j][i+t].setBackground(Color.green);
   										if(estEchec(tabP[j][i+t]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									if(tabP[j][i+t].couleur == 0 && mange == true){
   										tab[j][i+t].setBackground(Color.green);
   										if(estEchec(tabP[j][i+t]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									break;
   								}
							  }	
   							}
   							
   							for(int t=1;t<8;t++){//Meme principe pour les cases du dessous
  							  if(j+t<8){	
     								if(tab[j+t][i].getText()==" "){
     										tab[j+t][i].setBackground(Color.green);
     								} 
     								if(tab[j+t][i].getText()!=" "){
     								if(tabP[j+t][i].couleur == 0 && mange == true){
   										tab[j+t][i].setBackground(Color.green);
   										if(estEchec(tabP[j+t][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
     								if(tabP[j+t][i].couleur == 1 && mange == false){
   										tab[j+t][i].setBackground(Color.green);
   										if(estEchec(tabP[j+t][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
     									break;
     								}
  							  }	
     						}
   							for(int t=1;t<8;t++){//Meme principe pour les cases de gauche
    							  if(i-t>=0){	
       								if(tab[j][i-t].getText()==" "){
       										tab[j][i-t].setBackground(Color.green);
       								} 
       								if(tab[j][i-t].getText()!=" "){
       									if(tabP[j][i-t].couleur == 1 && mange == false){
       										tab[j][i-t].setBackground(Color.green);	
       										if(estEchec(tabP[j][i-t]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j][i-t].couleur ==0 && mange == true){
       										tab[j][i-t].setBackground(Color.green);
       										if(estEchec(tabP[j][i-t]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}       									
       									break;
       								}
    							  }	
       						}
   							this.x=i;
							this.y=j;
							
							
   						  }   						
/*--------------------*/else if(tabP[j][i].nom=="Fou" && tabP[j][i].couleur==tourJoueur){/*----DEPLACEMENT DU FOU----*/
	//									MEME PRINCIPE QUE POUR LA TOUR MAIS POUR LES DIAGONALES
   							couleurEchequier();
   							for(int z=1;z<8;z++){
   							  if(i-z>=0 && j-z>=0){
   								if(tab[j-z][i-z].getText()==" "){
   									tab[j-z][i-z].setBackground(Color.green);
   								} 
   								if(tab[j-z][i-z].getText()!=" "){
   									if(tabP[j-z][i-z].couleur == 1 && mange == false){
   										tab[j-z][i-z].setBackground(Color.green);
   										if(estEchec(tabP[j-z][i-z]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									if(tabP[j-z][i-z].couleur == 0 && mange == true){
   										tab[j-z][i-z].setBackground(Color.green);
   										if(estEchec(tabP[j-z][i-z]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									break;
   								}
   							  }
   							}
   							for(int z=1;z<8;z++){
     							  if(i+z<8 && j-z>=0){
     								if(tab[j-z][i+z].getText()==" "){
     									tab[j-z][i+z].setBackground(Color.green);
     								} 
     							    if(tab[j-z][i+z].getText()!=" "){
     							    	if(tabP[j-z][i+z].couleur == 1 && mange == false){
       										tab[j-z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j-z][i+z].couleur == 0 && mange == true){
       										tab[j-z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
     									break;
     								}
     							  }
     						}
   							for(int z=1;z<8;z++){
   							  if(i+z<8 && j+z<8){
   								if(tab[j+z][i+z].getText()==" "){
   									tab[j+z][i+z].setBackground(Color.green);
   								} 
   								if(tab[j+z][i+z].getText()!=" "){
   									if(tabP[j+z][i+z].couleur == 1 && mange == false){
   										tab[j+z][i+z].setBackground(Color.green);
   										if(estEchec(tabP[j+z][i+z]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									if(tabP[j+z][i+z].couleur == 0 && mange == true){
   										tab[j+z][i+z].setBackground(Color.green);
   										if(estEchec(tabP[j+z][i+z]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									break;
   								}
   							  }
   							}
   							for(int z=1;z<8;z++){
     							  if(i-z>=0 && j+z<8){
     								if(tab[j+z][i-z].getText()==" "){
     									tab[j+z][i-z].setBackground(Color.green);
     								} 
     								if(tab[j+z][i-z].getText()!=" "){
     									if(tabP[j+z][i-z].couleur == 1 && mange == false){
       										tab[j+z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j+z][i-z].couleur == 0 && mange == true){
       										tab[j+z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
     									break;
     								}
     							  }
     						}  							
   							this.x=i;
							this.y=j;
							 
   						}
/*--------------------*/else if(tabP[j][i].nom=="Dame" && tabP[j][i].couleur==tourJoueur){/*----DEPLACEMENT DE LA DAME----*/
	//									MEME PRINCIPE QUE POUR LA TOUR ET LE FOU (diagonales pour lignes)	
   							couleurEchequier();
   							for(int z=1;z<8;z++){
   							 if(j-z>=0){
   								if(tab[j-z][i].getText()==" "){
   									tab[j-z][i].setBackground(Color.green);
   								} 
   								if(tab[j-z][i].getText()!=" "){
   									if(tabP[j-z][i].couleur == 1 && mange == false){
   										tab[j-z][i].setBackground(Color.green);
   										if(estEchec(tabP[j-z][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									if(tabP[j-z][i].couleur == 0 && mange == true){
   										tab[j-z][i].setBackground(Color.green);
   										if(estEchec(tabP[j-z][i]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									break;
   								}
   							 }
   							}
   							for(int t=1;t<8;t++){
							  if(i+t<8){	
   								if(tab[j][i+t].getText()==" "){
   										tab[j][i+t].setBackground(Color.green);
   								} 
   								if(tab[j][i+t].getText()!=" "){
   									if(tabP[j][i+t].couleur == 1 && mange == false){
   										tab[j][i+t].setBackground(Color.green);
   										if(estEchec(tabP[j][i+t]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									if(tabP[j][i+t].couleur == 0 && mange == true){
   										tab[j][i+t].setBackground(Color.green);
   										if(estEchec(tabP[j][i+t]) == true)
											FenetreEchec(this.tabP[j][i].getCouleur());
   									}
   									break;
   								}
							  }	
   							}
   							
   							for(int t=1;t<8;t++){
  							  if(j+t<8){	
     								if(tab[j+t][i].getText()==" "){
     										tab[j+t][i].setBackground(Color.green);
     								} 
     								if(tab[j+t][i].getText()!=" "){
     									if(tabP[j+t][i].couleur == 1 && mange == false){
       										tab[j+t][i].setBackground(Color.green);
       										if(estEchec(tabP[j+t][i]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j+t][i].couleur == 0 && mange == true){
       										tab[j+t][i].setBackground(Color.green);
       										if(estEchec(tabP[j+t][i]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
     									break;
     								}
  							  }	
     						}
   							for(int t=1;t<8;t++){
    							  if(i-t>=0){	
       								if(tab[j][i-t].getText()==" "){
       										tab[j][i-t].setBackground(Color.green);
       								} 
       								if(tab[j][i-t].getText()!=" "){
       									if(tabP[j][i-t].couleur == 1 && mange == false){
       										tab[j][i-t].setBackground(Color.green);
       										if(estEchec(tabP[j][i-t]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j][i-t].couleur == 0 && mange == true){
       										tab[j][i-t].setBackground(Color.green);
       										if(estEchec(tabP[j][i-t]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									break;
       								}
    							  }	
       						}
   							for(int z=1;z<8;z++){
     							  if(i-z>=0 && j-z>=0){
     								if(tab[j-z][i-z].getText()==" "){
     									tab[j-z][i-z].setBackground(Color.green);
     								} 
     								if(tab[j-z][i-z].getText()!=" "){
     									if(tabP[j-z][i-z].couleur == 1 && mange == false){
       										tab[j-z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j-z][i-z].couleur == 0 && mange == true){
       										tab[j-z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
     									break;
     								}
     							  }
     						}
 							for(int z=1;z<8;z++){
       							  if(i+z<8 && j-z>=0){
       								if(tab[j-z][i+z].getText()==" "){
       									tab[j-z][i+z].setBackground(Color.green);
       								} 
       							    if(tab[j-z][i+z].getText()!=" "){
       							    	if(tabP[j-z][i+z].couleur == 1 && mange == false){
       										tab[j-z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j-z][i+z].couleur == 0 && mange == true){
       										tab[j-z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j-z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									break;
       								}
       							  }
       						}
 							for(int z=1;z<8;z++){
     							  if(i+z<8 && j+z<8){
     								if(tab[j+z][i+z].getText()==" "){
     									tab[j+z][i+z].setBackground(Color.green);
     								} 
     								if(tab[j+z][i+z].getText()!=" "){
     									if(tabP[j+z][i+z].couleur == 1 && mange == false){
       										tab[j+z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j+z][i+z].couleur == 0 && mange == true){
       										tab[j+z][i+z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i+z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
     									break;
     								}
     							  }
 							}
 							for(int z=1;z<8;z++){
       							  if(i-z>=0 && j+z<8){
       								if(tab[j+z][i-z].getText()==" "){
       									tab[j+z][i-z].setBackground(Color.green);
       								} 
       								if(tab[j+z][i-z].getText()!=" "){
       									if(tabP[j+z][i-z].couleur == 1 && mange == false){
       										tab[j+z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									if(tabP[j+z][i-z].couleur == 0 && mange == true){
       										tab[j+z][i-z].setBackground(Color.green);
       										if(estEchec(tabP[j+z][i-z]) == true)
    											FenetreEchec(this.tabP[j][i].getCouleur());
       									}
       									break;
       								}
       							  }
 							} 
     						this.x=i;
     						this.y=j;  
     						
						}
/*--------------------*/else if(tabP[j][i].nom=="Roi" && tabP[j][i].couleur==tourJoueur){
	/*----DEPLACEMENT DU ROI----*/	
	//									MEME PRINCIPE QUE POUR LE PION MAIS POUR LES CASES AUTOUR
   							couleurEchequier();
   						 if(j+1<8 && i+1<8){	//verifier si on est toujours dans le plateau
   							if(tab[j+1][i+1].getText()==" "){ //Si la case est vide
   								tab[j+1][i+1].setBackground(Color.green);//mettre en vert
   							}
   						  if(tab[j+1][i+1].getText()!=" ") {//si la case n'est pas vide
							if(tabP[j+1][i+1].couleur == 1 && mange == false){// et si la couleur est blanche et qu'on peut manger la piece
								tab[j+1][i+1].setBackground(Color.green);	//mettre en vert
								if(estEchec(tabP[j+1][i+1]) == true)
									FenetreEchec(this.tabP[j][i].getCouleur());
							}
							if(tabP[j+1][i+1].couleur == 0 && mange == true){//si la piece est noir et quon peut manger la piece
								tab[j+1][i+1].setBackground(Color.green);	//mettre en vert
								if(estEchec(tabP[j+1][i+1]) == true)
									FenetreEchec(this.tabP[j][i].getCouleur());
							}
   						  }
   						 }
   						  if(i+1<8){
   							if(tab[j][i+1].getText()==" "){
   								tab[j][i+1].setBackground(Color.green);
   							}
   							if(tab[j][i+1].getText()!=" ") {
   								if(tabP[j][i+1].couleur == 1 && mange == false){
   									tab[j][i+1].setBackground(Color.green);
   									if(estEchec(tabP[j][i+1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j][i+1].couleur == 0 && mange == true){
   									tab[j][i+1].setBackground(Color.green);
   									if(estEchec(tabP[j][i+1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(j+1<8){
   							if(tab[j+1][i].getText()==" "){
   								tab[j+1][i].setBackground(Color.green);
   							}
   							if(tab[j+1][i].getText()!=" ") {
   								if(tabP[j+1][i].couleur == 1 && mange == false){
   									tab[j+1][i].setBackground(Color.green);
   									if(estEchec(tabP[j+1][i]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j+1][i].couleur == 0 && mange == true){
   									tab[j+1][i].setBackground(Color.green);
   									if(estEchec(tabP[j+1][i]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(j-1>=0){
   							if(tab[j-1][i].getText()==" "){
   								tab[j-1][i].setBackground(Color.green);
   							}	
   							if(tab[j-1][i].getText()!=" ") {
   								if(tabP[j-1][i].couleur == 1 && mange == false){
   									tab[j-1][i].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j-1][i].couleur == 0 && mange == true){
   									tab[j-1][i].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(j-1>=0 && i+1<8){
   							if(tab[j-1][i+1].getText()==" "){
   								tab[j-1][i+1].setBackground(Color.green);
   							}
   							if(tab[j-1][i+1].getText()!=" ") {
   								if(tabP[j-1][i+1].couleur == 1 && mange == false){
   									tab[j-1][i+1].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i+1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j-1][i+1].couleur == 0 && mange == true){
   									tab[j-1][i+1].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i+1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(j+1<8 && i-1>=0){
   							if(tab[j+1][i-1].getText()==" "){
   								tab[j+1][i-1].setBackground(Color.green);
   							}
   							if(tab[j+1][i-1].getText()!=" ") {
   								if(tabP[j+1][i-1].couleur == 1 && mange == false){
   									tab[j+1][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j+1][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j+1][i-1].couleur == 0 && mange == true){
   									tab[j+1][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j+1][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(j-1>=0 && i-1>=0){
   							if(tab[j-1][i-1].getText()==" "){
   								tab[j-1][i-1].setBackground(Color.green);
   							}
   							if(tab[j-1][i-1].getText()!=" ") {
   								if(tabP[j-1][i-1].couleur == 1 && mange == false){
   									tab[j-1][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j-1][i-1].couleur == 0 && mange == true){
   									tab[j-1][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j-1][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  if(i-1>=0){
   							if(tab[j][i-1].getText()==" "){
   								tab[j][i-1].setBackground(Color.green);
   							}
   							if(tab[j][i-1].getText()!=" ") {
   								if(tabP[j][i-1].couleur == 1 && mange == false){
   									tab[j][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   								if(tabP[j][i-1].couleur == 0 && mange == true){
   									tab[j][i-1].setBackground(Color.green);
   									if(estEchec(tabP[j][i-1]) == true)
   										FenetreEchec(this.tabP[j][i].getCouleur());
   								}
   	   						  }
   						  }
   						  this.x=i;
 						  this.y=j;
 						
   					    }
/*--------------------*/else if(tabP[j][i].nom=="Cavalier" && tabP[j][i].couleur==tourJoueur){/*----DEPLACEMENT DU CAVALIER---*/		
	//									MEME PRINCIPE QUE POUR LA TOUR MAIS POUR LES CASES EN "L"
   							couleurEchequier();  
   							if(j+2<8 && i-1>=0){	
     							if(tab[j+2][i-1].getText()==" "){
     	     							tab[j+2][i-1].setBackground(Color.green);  
     							}
     							if(tab[j+2][i-1].getText()!=" ") {
     								if(tabP[j+2][i-1].couleur == 1 && mange == false){
     									tab[j+2][i-1].setBackground(Color.green);
     									if(estEchec(tabP[j+2][i-1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j+2][i-1].couleur == 0 && mange == true){
     									tab[j+2][i-1].setBackground(Color.green);
     									if(estEchec(tabP[j+2][i-1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j+2<8 && i+1<8){
     							if(tab[j+2][i+1].getText()==" "){
     									tab[j+2][i+1].setBackground(Color.green);
     									if(estEchec(tabP[j+2][i+1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     							}
     							if(tab[j+2][i+1].getText()!=" ") {
     								if(tabP[j+2][i+1].couleur == 1 && mange == false){
     									tab[j+2][i+1].setBackground(Color.green);
     									if(estEchec(tabP[j+2][i+1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j+2][i+1].couleur == 0 && mange == true){
     									tab[j+2][i+1].setBackground(Color.green);
     									if(estEchec(tabP[j+2][i+1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j+1<8 && i-2>=0){
     							if(tab[j+1][i-2].getText()==" "){
     									tab[j+1][i-2].setBackground(Color.green);      								
     							}
     							if(tab[j+1][i-2].getText()!=" ") {
     								if(tabP[j+1][i-2].couleur == 1 && mange == false){
     									tab[j+1][i-2].setBackground(Color.green);
     									if(estEchec(tabP[j+1][i-2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j+1][i-2].couleur == 0 && mange == true){
     									tab[j+1][i-2].setBackground(Color.green);
     									if(estEchec(tabP[j+1][i-2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j+1<8 && i+2<8){
     							if(tab[j+1][i+2].getText()==" "){
     									tab[j+1][i+2].setBackground(Color.green);
     							}	
     							if(tab[j+1][i+2].getText()!=" ") {
     								if(tabP[j+1][i+2].couleur == 1 && mange == false){
     									tab[j+1][i+2].setBackground(Color.green);
     									if(estEchec(tabP[j+1][i+2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j+1][i+2].couleur == 0 && mange == true){
     									tab[j+1][i+2].setBackground(Color.green);
     									if(estEchec(tabP[j+1][i+2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j-1>=0 && i-2>=0){
     							if(tab[j-1][i-2].getText()==" "){
     									tab[j-1][i-2].setBackground(Color.green);
     							}
     							if(tab[j-1][i-2].getText()!=" ") {
     								if(tabP[j-1][i-2].couleur == 1 && mange == false){
     									tab[j-1][i-2].setBackground(Color.green);
     									if(estEchec(tabP[j-1][i-2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j-1][i-2].couleur == 0 && mange == true){
     									tab[j-1][i-2].setBackground(Color.green);
     									if(estEchec(tabP[j-1][i-2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j-2>=0 && i-1>=0){
     							if(tab[j-2][i-1].getText()==" "){
     									tab[j-2][i-1].setBackground(Color.green);
     							}
     							if(tab[j-2][i-1].getText()!=" ") {
     								if(tabP[j-2][i-1].couleur == 1 && mange == false){
     									tab[j-2][i-1].setBackground(Color.green);
     									if(estEchec(tabP[j-2][i-1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j-2][i-1].couleur == 0 && mange == true){
     									tab[j-2][i-1].setBackground(Color.green);
     									if(estEchec(tabP[j-2][i-1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j-2>=0 && i+1<8){
     							if(tab[j-2][i+1].getText()==" "){
     									tab[j-2][i+1].setBackground(Color.green);
     							}
     							if(tab[j-2][i+1].getText()!=" ") {
     								if(tabP[j-2][i+1].couleur == 1 && mange == false){
     									tab[j-2][i+1].setBackground(Color.green);
     									if(estEchec(tabP[j-2][i+1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j-2][i+1].couleur == 0 && mange == true){
     									tab[j-2][i+1].setBackground(Color.green);
     									if(estEchec(tabP[j-2][i+1]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     	   						}
     						  }
     						  if(j-1>=0 && i+2<8){
     							if(tab[j-1][i+2].getText()==" "){
     									tab[j-1][i+2].setBackground(Color.green);
     							}
     							if(tab[j-1][i+2].getText()!=" ") {
     								if(tabP[j-1][i+2].couleur == 1 && mange == false){
     									tab[j-2][i+2].setBackground(Color.green);
     									if(estEchec(tabP[j-1][i+2]) == true)
       										FenetreEchec(this.tabP[j][i].getCouleur());
     								}
     								if(tabP[j-2][i+2].couleur == 0 && mange == true){
     									tab[j-2][i+2].setBackground(Color.green);	
     								}
     	   						}
     						  }
     						
     						  this.x=i;
   					     	  this.y=j;
   					     	  
   					     	  
     					 }  

   					}
   					
   				}
   				
   			}
   		}
}
	//  ------------------------------------------------------ECHEC------------------------------------------------------------------------
	  public boolean estEchec(Piece v){
			for(int j = 0; j < 8; j++){
				for(int i = 0; i < 8; i++){
					if (v.getNom() == "Roi"){
						
						return true;
					}
				}					
			}
			return false;
		}
	  
	  public void FenetreEchec(int couleur){
		 JFrame z=new JFrame();
		  z.setTitle("PROMOTION");
		    z.setSize(350, 120);
		    z.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    z.setLocationRelativeTo(null);
		    JPanel b2 = new JPanel();
		   
		   
		    b2.setLayout(new GridLayout());
		    if(couleur==1){
		    	JLabel e = new JLabel("     Victoire des Blancs");
		    	b2.add(e);
		    }
		    else{
		    	JLabel a = new JLabel("    Victoire des Noirs");
		    	b2.add(a);
		    }
		    JButton Echec = new JButton("Quittez");
		    Echec.addActionListener(new ActionListener(){

				
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
		    	
		    	
		    });
		    
		   b2.add(Echec);
		   z.getContentPane().add(b2);
		    z.setVisible(true);
		   
		  
		  
	  }
	//  -----------------------------------------------------------PROMOTION-------------------------------------------------------------
	 
	
		 
	  public void changementButton(int couleur,int j,int i,int o){
		  if(o==1){
			 Dame c = new Dame(couleur,j,i);
			 this.tabP[j][i]=c;
		  }
		  else if(o==2){
				 Tour c = new Tour(couleur,j,i); 
				 this.tabP[j][i]=c;
			  }
		  else if(o==3){
				 Fou c = new Fou(couleur,j,i); 
				 this.tabP[j][i]=c;
			  }
		  else{
				 Cavalier c = new Cavalier(couleur,j,i);
				 this.tabP[j][i]=c;
			  }
		  tab[j][i].setText(tabP[j][i].unicode);
		  tab[j][i].addActionListener(this); 
		 
		  
		  
	  }
	
	  
	 public void fenetrePromo(int couleur,int j,int i){
		JFrame  r=new JFrame();
		  r.setTitle("PROMOTION");
		    r.setSize(350, 120);
		    r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    r.setLocationRelativeTo(null);

		    JPanel b1 = new JPanel();
		   
		    b1.setLayout(new GridLayout());
		    JButton Dame = new JButton("Dame");
		    JButton Fou = new JButton("Fou");
		    JButton Cavalier = new JButton("Cavalier");
		    JButton Tour = new JButton("Tour");
		    Dame.addActionListener(new ActionListener(){

				
				public void actionPerformed(ActionEvent e) {
					changementButton(couleur,j,i,1);
					r.dispose();
					
				}
		    	
		    	
		    });
		    Fou.addActionListener(new ActionListener(){

				
				public void actionPerformed(ActionEvent e) {
					changementButton(couleur,j,i,3);
					r.dispose();
					
				}
		    	
		    	
		    });
  Tour.addActionListener(new ActionListener(){

		
		public void actionPerformed(ActionEvent e) {
			changementButton(couleur,j,i,2);
			r.dispose();
			
		}
  	
  	
  });
  Cavalier.addActionListener(new ActionListener(){

		
		public void actionPerformed(ActionEvent e) {
			changementButton(couleur,j,i,4);
				r.dispose();
			
		}
  	
  	
  });
	 
		    b1.add(Dame);
		    b1.add(Tour);
		    b1.add(Cavalier);
		    b1.add(Fou);
		    r.getContentPane().add(b1);
		    r.setVisible(true);
		 
		  }
	 
	 
	/*----------------------------------            MAT             ------------------------------------------- */
		 
				
			public void estMat(int j,int i){
				
				
				 if(j+1<8 && i+1<8){	//verifier si on est toujours dans le plateau
						if(tab[j+1][i+1].getText()==" "){ //Si la case est vide
							tab[j+1][i+1].setBackground(Color.green);//mettre en vert
						}
					  if(tab[j+1][i+1].getText()!=" ") {//si la case n'est pas vide
						if(tabP[j+1][i+1].couleur == 1 && mange == false){// et si la couleur est blanche et qu'on peut manger la piece
							tab[j+1][i+1].setBackground(Color.green);	//mettre en vert
						
						}
						if(tabP[j+1][i+1].couleur == 0 && mange == true){//si la piece est noir et quon peut manger la piece
							tab[j+1][i+1].setBackground(Color.green);	//mettre en vert
							
						}
					  }
					 }
					  if(i+1<8){
						if(tab[j][i+1].getText()==" "){
							tab[j][i+1].setBackground(Color.green);
						}
						if(tab[j][i+1].getText()!=" ") {
							if(tabP[j][i+1].couleur == 1 && mange == false){
								tab[j][i+1].setBackground(Color.green);
								
							}
							if(tabP[j][i+1].couleur == 0 && mange == true){
								tab[j][i+1].setBackground(Color.green);
							
								
							}
   						  }
					  }
					  if(j+1<8){
						if(tab[j+1][i].getText()==" "){
							tab[j+1][i].setBackground(Color.green);
						}
						if(tab[j+1][i].getText()!=" ") {
							if(tabP[j+1][i].couleur == 1 && mange == false){
								tab[j+1][i].setBackground(Color.green);
								
							}
							if(tabP[j+1][i].couleur == 0 && mange == true){
								tab[j+1][i].setBackground(Color.green);
							
							}
   						  }
					  }
					  if(j-1>=0){
						if(tab[j-1][i].getText()==" "){
							tab[j-1][i].setBackground(Color.green);
						}	
						if(tab[j-1][i].getText()!=" ") {
							if(tabP[j-1][i].couleur == 1 && mange == false){
								tab[j-1][i].setBackground(Color.green);
								
							}
							if(tabP[j-1][i].couleur == 0 && mange == true){
								tab[j-1][i].setBackground(Color.green);
								
							}
   						  }
					  }
					  if(j-1>=0 && i+1<8){
						if(tab[j-1][i+1].getText()==" "){
							tab[j-1][i+1].setBackground(Color.green);
						}
						if(tab[j-1][i+1].getText()!=" ") {
							if(tabP[j-1][i+1].couleur == 1 && mange == false){
								tab[j-1][i+1].setBackground(Color.green);
							
							}
							if(tabP[j-1][i+1].couleur == 0 && mange == true){
								tab[j-1][i+1].setBackground(Color.green);
								
							}
   						  }
					  }
					  if(j+1<8 && i-1>=0){
						if(tab[j+1][i-1].getText()==" "){
							tab[j+1][i-1].setBackground(Color.green);
						}
						if(tab[j+1][i-1].getText()!=" ") {
							if(tabP[j+1][i-1].couleur == 1 && mange == false){
								tab[j+1][i-1].setBackground(Color.green);
								
							}
							if(tabP[j+1][i-1].couleur == 0 && mange == true){
								tab[j+1][i-1].setBackground(Color.green);
								
							}
   						  }
					  }
					  if(j-1>=0 && i-1>=0){
						if(tab[j-1][i-1].getText()==" "){
							tab[j-1][i-1].setBackground(Color.green);
						}
						if(tab[j-1][i-1].getText()!=" ") {
							if(tabP[j-1][i-1].couleur == 1 && mange == false){
								tab[j-1][i-1].setBackground(Color.green);
								
							}
							if(tabP[j-1][i-1].couleur == 0 && mange == true){
								tab[j-1][i-1].setBackground(Color.green);
							
							}
   						  }
					  }
					  if(i-1>=0){
						if(tab[j][i-1].getText()==" "){
							tab[j][i-1].setBackground(Color.green);
						}
						if(tab[j][i-1].getText()!=" ") {
							if(tabP[j][i-1].couleur == 1 && mange == false){
								tab[j][i-1].setBackground(Color.green);
								
							}
							if(tabP[j][i-1].couleur == 0 && mange == true){
								tab[j][i-1].setBackground(Color.green);
								
							}
				}
					  }}
			
	  
		  
	
	  
	 
	  
	  
/*-----------------------Execution du programme---------------------*/	  
	  public static void main (String[] args){
			new Echequier();
	  }		
}
