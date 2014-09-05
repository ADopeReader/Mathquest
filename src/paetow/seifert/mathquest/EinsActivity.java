package paetow.seifert.mathquest;

import java.util.Random;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EinsActivity extends Activity implements OnClickListener{
	
	//Pausedialoge
	
	private Dialog gewonnenDialog, verlorenDialog;
	private Button dialogReset, dialogNextLevel;
	
	
	
	private EditText startZahl,zielZahl;
	
	private TextView Ausgabe,bubbleText;
	
	private Button Plusbutton, Minusbutton,Malbutton,Teilbutton;

	private int levelCounter, ans,Start,Goal,zugCounter;


	//Animation des Fortschrittsbalken
	private ImageView fortschrittsBalken;
	private Handler pHandler; 
	private ClipDrawable fortschrittsFuellung;
	private int fuellZustand;

	
	int plusZahl, minusZahl,malZahl,teilZahl;

	int buttonA, buttonB,buttonC,buttonD;
	private Boolean gameEnded;

	Rechenoperation anton,berta,chris,doofie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//Dialogfenster und deren Buttons initialisieren
		gewonnenDialog = new Dialog (this,android.R.style.Theme_Translucent);   //Dialogfenster initialisieren
		gewonnenDialog.setContentView(R.layout.geschafft);
		gewonnenDialog.hide();
		dialogNextLevel = (Button) gewonnenDialog.findViewById(R.id.dialogNextLevel);
		dialogNextLevel.setOnClickListener(this);
		
		verlorenDialog = new Dialog (this,android.R.style.Theme_Translucent); 
		verlorenDialog.setContentView(R.layout.verloren);
		verlorenDialog.hide();
		dialogReset = (Button)verlorenDialog.findViewById(R.id.dialogReset);
		dialogReset.setOnClickListener(this);
		
		
		levelCounter = 1;              //Beim Starten der Aktivity wird mit Level 1 gestartet
		

		zugCounter = 0;                          //Zugzaehler auf Null setzen
		fuellZustand = 0;                      //Fortschrittsbalken auf Null setzen
		gameEnded = false;

		
		//Fortschrittsbalken, dessen Fuellung und Handler initialisieren
		fortschrittsBalken = (ImageView) findViewById(R.id.progress);    
		fortschrittsFuellung = (ClipDrawable) fortschrittsBalken.getDrawable();
		pHandler = new Handler();

		fortschrittsFuellung.setLevel(0);    //Setzt Fuellung auf Anfang


		//Buttons und Felder initialisieren
		startZahl = (EditText) findViewById(R.id.Startzahl);
		zielZahl = (EditText) findViewById(R.id.Goal);
		Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);
		Plusbutton = (Button) findViewById(R.id.addieren); Plusbutton.setOnClickListener(this);
		Minusbutton = (Button) findViewById(R.id.subtrahieren);Minusbutton.setOnClickListener(this);
		Malbutton = (Button) findViewById(R.id.multiplizieren);Malbutton.setOnClickListener(this);
		Teilbutton = (Button) findViewById(R.id.dividieren); Teilbutton.setOnClickListener(this);
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
	    	gewonnenDialog.dismiss();
	    	verlorenDialog.dismiss();
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
	
	
	public void onClick(View v)
	{
		switch (v.getId()){
		case R.id.dialogNextLevel: gewonnenDialog.hide(); nextLevel();break;
		case R.id.dialogReset: verlorenDialog.hide(); reset(); break;
		case R.id.reset: reset(); break;
		case R.id.addieren: addieren(); break;
		case R.id.subtrahieren: subtrahieren(); break;
		case R.id.multiplizieren: multiplizieren(); break;
		case R.id.dividieren: dividieren(); break;

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
	
	
	public void addieren() {

		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}
		else if (gameEnded == true && ans != Goal) {}

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

	public void subtrahieren() {
		
		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}

		else{
		if (berta.toString()=="PLUS") 	ans = ans + minusZahl;
		if (berta.toString()=="MINUS") ans = ans - minusZahl;
		if (berta.toString()=="MAL") 	ans = ans * minusZahl;
		if (berta.toString()=="TEIL") ans = ans / minusZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}

	public void multiplizieren (){
		
		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}

		
		else{
		if (chris.toString()=="PLUS") 	ans = ans + malZahl;
		if (chris.toString()=="MINUS") ans = ans - malZahl;
		if (chris.toString()=="MAL") 	ans = ans * malZahl;
		if (chris.toString()=="TEIL") ans = ans / malZahl;

		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}

	public void dividieren (){

		if (gameEnded == true && levelCounter == 5){
			Intent in = new Intent(this, MenuActivity.class);
			startActivity(in);
		}		else if (gameEnded == true && ans != Goal) {}

		
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
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    gewonnenDialog.show();
			}
		
		if (zugCounter == levelCounter && ans != Goal){
			Ausgabe.setText("Verloren!");gameEnded = true;
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    verlorenDialog.show();
			}
        
	}


	public void reset(){

		gameEnded = false;
		zugCounter = 0;
		ans = Start;
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis); 

		step(true);   //sezt den Fortschritsbalken auf Ausgangsposition zurueck
		

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
