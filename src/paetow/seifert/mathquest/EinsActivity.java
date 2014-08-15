package paetow.seifert.mathquest;

import java.util.Random;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class EinsActivity extends Activity {
	
	private EditText startZahl;
	private EditText zielZahl;
	private TextView Ausgabe;
	private TextView bubbleText;
	
	private Button Plusbutton;
	private Button Minusbutton;
	private Button Malbutton;
	private Button Teilbutton;
	private int levelCounter;

	private int ans;
	private int Start;
	private int Goal;
	private int zugCounter;
	
	
	//Animation des Fortschrittsbalken
	private ImageView fortschrittsBalken;
	private Handler pHandler; 
	private ClipDrawable fortschrittsFuellung;
	private int fuellZustand;

	

	int plusZahl; 
	int minusZahl;
	int malZahl;
	int teilZahl;

	int buttonA;
	int buttonB;
	int buttonC;
	int buttonD;
	private Boolean gameEnded;

	Rechenoperation anton; 
	Rechenoperation berta;
	Rechenoperation chris;
	Rechenoperation doofie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		levelCounter = 1;              //Beim Starten der Aktivity wird mit Level 1 gestartet
		

		zugCounter = 0;                          //Zugzaehler auf Null setzen
		fuellZustand = 0;                      //Fortschrittsbalken auf Null setzen
		gameEnded = false;

		
		//Fortschrittsbalken, dessen Fuellung und Handler initialisieren
		fortschrittsBalken = (ImageView) findViewById(R.id.progress);    
		fortschrittsFuellung = (ClipDrawable) fortschrittsBalken.getDrawable();
		pHandler = new Handler();

		fortschrittsFuellung.setLevel(0);    //Setzt Fuellung auf Anfang


		//Buttons initialisieren
		startZahl = (EditText) findViewById(R.id.Startzahl);
		zielZahl = (EditText) findViewById(R.id.Goal);
		Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);
		Plusbutton = (Button) findViewById(R.id.spielStarten);
		Minusbutton = (Button) findViewById(R.id.subtrahieren);
		Malbutton = (Button) findViewById(R.id.multiplizieren);
		Teilbutton = (Button) findViewById(R.id.dividieren);
		bubbleText = (TextView) findViewById(R.id.bubble);

		
		loadLevel();    //generiert das Interface abhaengig vom Spiellevel
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.eins, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	   
	    switch (item.getItemId()) {
	    
	    case R.id.hauptMenu:
	    	Intent in = new Intent(this, MenuActivity.class);
	    	startActivity(in);
	    	 return true;
        case R.id.menueins:
        	levelEins_starten();
        	 return true;
        case R.id.menuzwei:
            levelZwei_starten();
            return true;
        case R.id.menudrei:
        	levelDrei_starten();
        	 return true;
        case R.id.menuvier:
        	levelVier_starten();
        	 return true;
        
        case R.id.menufuenf:
            levelFuenf_starten();
            return true;
            
        
        default:
            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	
	public void loadLevel ()    //Generieren der Spielvariablen und laden des Interface abhaengig vom Level
	{
		

		// Zufahlszahlen zuweisen
		Random Zufall = new Random();
		Start = Zufall.nextInt(20);
		Goal = Start;
		Zufall.setSeed(System.currentTimeMillis());

		plusZahl = Zufall.nextInt(8)+1;
		minusZahl = Zufall.nextInt(8)+1;
		malZahl = Zufall.nextInt(8)+1;
		teilZahl = Zufall.nextInt(14)+1;;


		// Button Enums
		buttonA= Zufall.nextInt(4);
		buttonB= Zufall.nextInt(4);
		buttonC= Zufall.nextInt(4);
		buttonD= Zufall.nextInt(4);

		anton = Rechenoperation.getEnumByValue(buttonA);
		berta = Rechenoperation.getEnumByValue(buttonB);
		chris = Rechenoperation.getEnumByValue(buttonC);
		doofie = Rechenoperation.getEnumByValue(buttonD);
		
	    
	   	//Zielzahl berechnen
		do {
		Goal = zielen();
		}
		while(Goal == 0);
		

		// Textfelder zuweisen
		
		ans = Start;
		String Startzahl = String.valueOf(Start);
		String zuErreichen = String.valueOf(Goal);
		String zwischenErgebnis = String.valueOf(ans);

		String plus = String.valueOf(plusZahl);
		String minus = String.valueOf(minusZahl);
		String mal = String.valueOf(malZahl);
		String teil = String.valueOf(teilZahl);
		

						
		//Buttons das jeweilige Drawable zuordnen
		if (anton.toString()=="PLUS") Plusbutton.setBackgroundResource(R.drawable.plus);
		if (anton.toString()=="MINUS") Plusbutton.setBackgroundResource(R.drawable.minus);
		if (anton.toString()=="MAL") 	Plusbutton.setBackgroundResource(R.drawable.mal);
		if (anton.toString()=="TEIL") Plusbutton.setBackgroundResource(R.drawable.geteilt);
				
		if (berta.toString()=="PLUS") Minusbutton.setBackgroundResource(R.drawable.plus);
		if (berta.toString()=="MINUS") Minusbutton.setBackgroundResource(R.drawable.minus);
		if (berta.toString()=="MAL") 	Minusbutton.setBackgroundResource(R.drawable.mal);
		if (berta.toString()=="TEIL") Minusbutton.setBackgroundResource(R.drawable.geteilt);
			
		if (chris.toString()=="PLUS") Malbutton.setBackgroundResource(R.drawable.plus);
		if (chris.toString()=="MINUS") Malbutton.setBackgroundResource(R.drawable.minus);
		if (chris.toString()=="MAL") 	Malbutton.setBackgroundResource(R.drawable.mal);
		if (chris.toString()=="TEIL") Malbutton.setBackgroundResource(R.drawable.geteilt);
				
		if (doofie.toString()=="PLUS") Teilbutton.setBackgroundResource(R.drawable.plus);
		if (doofie.toString()=="MINUS") Teilbutton.setBackgroundResource(R.drawable.minus);
		if (doofie.toString()=="MAL") 	Teilbutton.setBackgroundResource(R.drawable.mal);
		if (doofie.toString()=="TEIL") Teilbutton.setBackgroundResource(R.drawable.geteilt);
	

		//Texte in Grafik laden
		startZahl.setText(Startzahl);
		zielZahl.setText(zuErreichen);
	    Ausgabe.setText(zwischenErgebnis); 
	    Plusbutton.setText(plus);
	    Minusbutton.setText(minus);
	    Malbutton.setText(mal);
	    Teilbutton.setText(teil);	
	   setBubbleText();
		
	}
	
	
	public void nextLevel(){	
		 
		 levelCounter ++;
		 gameEnded = false;
		 zugCounter = 0;
		 step(true);
		 loadLevel();
		
	}
	
	
	public void addieren(View Buttonclick) {

		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}
		else if (gameEnded == true && ans != Goal) {}
		else if(gameEnded == true && ans == Goal){
			nextLevel();
		}
		else{
		if (anton.toString()=="PLUS") 	ans = ans + plusZahl;
		if (anton.toString()=="MINUS") ans = ans - plusZahl;
		if (anton.toString()=="MAL") 	ans = ans * plusZahl;
		if (anton.toString()=="TEIL") ans = ans / plusZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();
		}
	}

	public void subtrahieren(View Buttonclick) {
		
		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}
		else if(gameEnded == true && ans == Goal){
			nextLevel();		}
		else{
		if (berta.toString()=="PLUS") 	ans = ans + minusZahl;
		if (berta.toString()=="MINUS") ans = ans - minusZahl;
		if (berta.toString()=="MAL") 	ans = ans * minusZahl;
		if (berta.toString()=="TEIL") ans = ans / minusZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}

	public void multiplizieren (View Buttonclick) {
		
		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}
		else if(gameEnded == true && ans == Goal){
			nextLevel();
		}
		else{
		if (chris.toString()=="PLUS") 	ans = ans + malZahl;
		if (chris.toString()=="MINUS") ans = ans - malZahl;
		if (chris.toString()=="MAL") 	ans = ans * malZahl;
		if (chris.toString()=="TEIL") ans = ans / malZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}

	public void dividieren (View Buttonclick) {

		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}
		else if(gameEnded == true && ans == Goal){
			nextLevel();
		}
		else{
		if (doofie.toString()=="PLUS") 	ans = ans + teilZahl;
		if (doofie.toString()=="MINUS") ans = ans - teilZahl;
		if (doofie.toString()=="MAL") 	ans = ans * teilZahl;
		if (doofie.toString()=="TEIL") ans = ans / teilZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}



	private void ziehen (){

		zugCounter++;
		step(false);
		setBubbleText();
		
		if (zugCounter == levelCounter && ans == Goal){
			Ausgabe.setText("Gewonnen!");gameEnded = true;
			Plusbutton.setText("N");
		    Minusbutton.setText("E");
		    Malbutton.setText("X");
		    Teilbutton.setText("T");
			}
		
		if (zugCounter == levelCounter && ans != Goal){
			Ausgabe.setText("Verloren!");gameEnded = true;
			}
        
	}


	public void reset(View Buttonclick){
		if (Plusbutton.getText()!= "N"){
		gameEnded = false;
		zugCounter = 0;
		ans = Start;
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis); 

		step(true);   //sezt den Fortschritsbalken auf Ausgangsposition zurueck
		}

	}

	public enum Rechenoperation {
		PLUS,
		MINUS,
		MAL,
		TEIL;

		public static Rechenoperation getEnumByValue(int value) {
			switch (value) {
				case 0:   	return PLUS;
				case 1:   	return MINUS;
				case 2:   	return MAL;
				case 3:   	return TEIL;
				default:  	return null;
			}
		}
		
		public String getRechenzeichen (){
			switch (this){
				case PLUS: 	return "+";
				case MINUS: return "-";
				case MAL: 	return "x";
				case TEIL : return ":";
				default: 	return null;
				//domi
			}
		}
	}
	
	
	//Zufallszahlen berechnen
	//
	//
	public int zielen(){
		
		Random Zufall = new Random();

		for( int i=0; i<levelCounter; i++){
			int dynamik = Zufall.nextInt(4);
		
			if (dynamik==0){
				plus();
			}
			if (dynamik==1){
				minus();
			}
			if (dynamik==2){
				malen();
			}
			if (dynamik==3){
				teilen();
			}
		}
		return Goal;
	}
	
	public void plus(){
		
		if (anton.toString()=="PLUS") 	Goal = Goal + plusZahl;
		if (anton.toString()=="MINUS") Goal = Goal - plusZahl;
		if (anton.toString()=="MAL") 	Goal = Goal * plusZahl;
		if (anton.toString()=="TEIL") Goal = Goal / plusZahl;
		
	}
	
	public void minus (){
		
		if (berta.toString()=="PLUS") 	Goal = Goal + minusZahl;
		if (berta.toString()=="MINUS") Goal = Goal - minusZahl;
		if (berta.toString()=="MAL") 	Goal = Goal * minusZahl;
		if (berta.toString()=="TEIL") Goal = Goal / minusZahl;
		
	}
	
	public void malen(){
		
		if (chris.toString()=="PLUS") 	Goal = Goal + malZahl;
		if (chris.toString()=="MINUS") Goal = Goal - malZahl;
		if (chris.toString()=="MAL") 	Goal = Goal * malZahl;
		if (chris.toString()=="TEIL") Goal = Goal / malZahl;
		
	}
	
	public void teilen (){
		
		if (doofie.toString()=="PLUS") 	Goal = Goal + teilZahl;
		if (doofie.toString()=="MINUS") Goal = Goal - teilZahl;
		if (doofie.toString()=="MAL") 	Goal = Goal * teilZahl;
		if (doofie.toString()=="TEIL") Goal = Goal / teilZahl;
		
	}
	//
	//
	// Ende Zufallszahlen berechnen
	
	
	
	public void levelEins_starten ()
	{
		levelCounter = 1;
        step(true);
		loadLevel();
	}
	
	public void levelZwei_starten ()
	{
		levelCounter = 2;
		 step(true);
		loadLevel();
	}
	
	public void levelDrei_starten ()
	{
		levelCounter = 3;
		 step(true);
		loadLevel();
	}
	
	public void levelVier_starten ()
	{
		levelCounter = 4;
		 step(true);
		loadLevel();
		
	}
	
	public void levelFuenf_starten()
	{
		levelCounter = 5;
		 step(true);
		loadLevel();	
	}	
	
	
	
	

	private Runnable animateImage = new Runnable() {

        @Override
        public void run() {
            doTheAnimation();
        }
    };
    
    
    
    private void doTheAnimation() {
    	
        fortschrittsFuellung.setLevel(fuellZustand);
        if (fuellZustand <= 10000) {
            pHandler.postDelayed(animateImage, 50);
        } else {
            pHandler.removeCallbacks(animateImage);
        }
        
        
    }

private void step(boolean resetter)
{
	if (resetter == true){fuellZustand = 0;}
	else {
	fuellZustand += (10000 / levelCounter);}
	pHandler.post(animateImage);

}
	

private void setBubbleText (){
	String bubble = zugCounter + " / " + levelCounter;
	bubbleText.setText(bubble);
}
	
}
