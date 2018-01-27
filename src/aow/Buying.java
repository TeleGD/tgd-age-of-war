package aow;

import org.newdawn.slick.Input;

/*But de cette classe : p,o,i,  a,z,e    et achat des joueurs*/

public class Buying {
	
	
	private boolean aPress,zPress,ePress,iPress,oPress,pPress;
	
	
	public void keyReleased(int key, char c) { // donne la valeur false aux touches relachées

        if(key == Input.KEY_A){

            aPress = false;

        }else if(key == Input.KEY_Z){

            zPress = false;

        }else if(key == Input.KEY_E){

            ePress = false;

        }else if(key == Input.KEY_I)

        {

            iPress=false;

        }else if(key == Input.KEY_O)

        {

            oPress=false;

        }else if(key == Input.KEY_P)
        	
        {

            pPress = false;

        }

        }

	
	
	
	
	public void keyPressed(int key, char c) { // donne la valeur true aux touches qui etaient relachées et qui sont appuyees dernierement lance l'achat de minions 

		if(key == Input.KEY_A){ // la case a a ete appuyee 
			if(aPress==false) // ce n'etait pas une touche sur laquelle on etait reste appuye, et la case 0 est libre pour faire apparaitre un 'minion'
			{
				World1.p1.achatMinion(1);
				aPress=true;
			}
            

        }else if(key == Input.KEY_Z){
        	if(zPress==false)
        	{
        		World1.p1.achatMinion(2);
				zPress=true;
        		
        	}


        }else if(key == Input.KEY_E){
        	if(ePress==false)
        	{
        		World1.p1.achatMinion(3);
				ePress=true;
        	}

          

        }else if(key == Input.KEY_I)
        {
        	if(iPress==false)
        	{
        		World1.p1.achatMinion(1);
				iPress=true;
        	}
        }else if(key == Input.KEY_O)
        {
        	if(oPress==false)
        	{
        		World1.p1.achatMinion(2);
				oPress=true;
        	}
          

        }else if(key == Input.KEY_P)	
        {
        	if(pPress==false)
        	{
        		World1.p1.achatMinion(3);
				pPress=true;
        	}

        }
        
	
	
}
	

	
	
	
	
	
}
