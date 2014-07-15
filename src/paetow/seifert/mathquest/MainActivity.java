<<<<<<< HEAD
package paetow.seifert.mathquest;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText startZahl;
	private EditText zielZahl;
	private TextView Ausgabe;
	private RadioButton turn;
	private Button Plusbutton;
	private Button Minusbutton;
	private Button Malbutton;
	private Button Teilbutton;
	
	private int ans;
	private int Start;
	private int Goal;
	private int zugAnzahl;
	private int zugCounter;
	
	int plusZahl; 
	int minusZahl;
	int malZahl;
	int teilZahl;
	
	int buttonA;
	int buttonB;
	int buttonC;
	int buttonD;
	
	Rechenoperation anton; 
	Rechenoperation berta;
	Rechenoperation chris;
	Rechenoperation doofie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);   //Mit dieser Methode wird das Benutzerinterface dargestellt
		
		
		zugAnzahl = 4;                           //benoetigte Zuege vorgeben
		zugCounter = 0;
		
		
		//Eingabefelder initialisieren
		startZahl = (EditText) findViewById(R.id.Startzahl);
		zielZahl = (EditText) findViewById(R.id.Goal);
		Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);
		Plusbutton = (Button) findViewById(R.id.addieren);
		Minusbutton = (Button) findViewById(R.id.subtrahieren);
		Malbutton = (Button) findViewById(R.id.multiplizieren);
		Teilbutton = (Button) findViewById(R.id.dividieren);
		
		
		// Zufahlszahlen zuweisen
		Random Zufall = new Random();
		Start = Zufall.nextInt(20);
		Goal = Zufall.nextInt(100);
	   
		plusZahl = Zufall.nextInt(10);
		minusZahl = Zufall.nextInt(10);
		malZahl = Zufall.nextInt(10);
		teilZahl = Zufall.nextInt(10);
		
		
		// Button Enums
		buttonA= Zufall.nextInt(3);
		buttonB= Zufall.nextInt(3);
		buttonC= Zufall.nextInt(3);
		buttonD= Zufall.nextInt(3);
		
		anton = Rechenoperation.getEnumByValue(buttonA);
		berta = Rechenoperation.getEnumByValue(buttonB);
		chris = Rechenoperation.getEnumByValue(buttonC);
		doofie = Rechenoperation.getEnumByValue(buttonD);
		
		
		// Textfelder zuweisen
		ans = Start;
		String Startzahl = String.valueOf(Start);
		String zuErreichen = String.valueOf(Goal);
		String zwischenErgebnis = String.valueOf(ans);
		
		String plus = anton.getRechenzeichen()+String.valueOf(plusZahl);
		String minus = berta.getRechenzeichen()+String.valueOf(minusZahl);
		String mal = chris.getRechenzeichen()+String.valueOf(malZahl);
		String teil = doofie.getRechenzeichen()+String.valueOf(teilZahl);
		
		
		startZahl.setText(Startzahl);
		zielZahl.setText(zuErreichen);
	    Ausgabe.setText(Startzahl); 
	    Plusbutton.setText(plus);
	    Minusbutton.setText(minus);
	    Malbutton.setText(mal);
	    Teilbutton.setText(teil);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	public void addieren(View Buttonclick) {
		
		if (anton.toString()=="PLUS") 	ans = ans + plusZahl;
		if (anton.toString()=="MINUS") ans = ans - plusZahl;
		if (anton.toString()=="MAL") 	ans = ans * plusZahl;
		if (anton.toString()=="TEIL") ans = ans / plusZahl;
		
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();
		
	}

	public void subtrahieren(View Buttonclick) {

		if (berta.toString()=="PLUS") 	ans = ans + minusZahl;
		if (berta.toString()=="MINUS") ans = ans - minusZahl;
		if (berta.toString()=="MAL") 	ans = ans * minusZahl;
		if (berta.toString()=="TEIL") ans = ans / minusZahl;
		
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();
	}
	
	public void multiplizieren (View Buttonclick) {
		
		if (chris.toString()=="PLUS") 	ans = ans + malZahl;
		if (chris.toString()=="MINUS") ans = ans - malZahl;
		if (chris.toString()=="MAL") 	ans = ans * malZahl;
		if (chris.toString()=="TEIL") ans = ans / malZahl;
		
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();
	}
	
	public void dividieren (View Buttonclick) {
		 
		if (doofie.toString()=="PLUS") 	ans = ans + teilZahl;
		if (doofie.toString()=="MINUS") ans = ans - teilZahl;
		if (doofie.toString()=="MAL") 	ans = ans * teilZahl;
		if (doofie.toString()=="TEIL") ans = ans / teilZahl;
		
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();
	}
	
	
	
	
	private void ziehen (){
	
		zugCounter++;
		
		if (zugCounter == zugAnzahl && ans == Goal){Ausgabe.setText("Gewonnen!");}
		if (zugCounter == zugAnzahl && ans != Goal){Ausgabe.setText("Verloren!");}
		
	}
	
	
	public void reset(){
		zugCounter = 0;
		ans = Start;
		
		//Todo: Radiobuttons zuruecksetzen
		
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
				
			}
		}
	}
		 
	
	
	

}
=======
package paetow.seifert.mathquest;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText startZahl;
	private EditText zielZahl;
	private TextView Ausgabe;
	private RadioButton turn1;
	private RadioButton turn2;
	private RadioButton turn3;
	private RadioButton turn4;
	private RadioButton [] turnDisplay;
	private Button Plusbutton;
	private Button Minusbutton;
	private Button Malbutton;
	private Button Teilbutton;
	
	private int ans;
	private int Start;
	private int Goal;
	private int zugAnzahl;
	private int zugCounter;
	
	int plusZahl; 
	int minusZahl;
	int malZahl;
	int teilZahl;
	
	private Boolean gameEnded;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);   //Mit dieser Methode wird das Benutzerinterface dargestellt
		
		
		zugAnzahl = 4;                           //benoetigte Zuege vorgeben
		zugCounter = 0;
		
		
		//Eingabefelder initialisieren
		startZahl = (EditText)findViewById(R.id.Startzahl);
		zielZahl = (EditText)findViewById(R.id.Goal);
		Ausgabe = (TextView)findViewById(R.id.Ergebnisanzeige);
		Plusbutton = (Button)findViewById(R.id.addieren);
		Minusbutton = (Button)findViewById(R.id.subtrahieren);
		Malbutton = (Button) findViewById(R.id.multiplizieren);
		Teilbutton = (Button)findViewById(R.id.dividieren);
		turn1 = (RadioButton)findViewById(R.id.ersterZug);
		turn2 = (RadioButton)findViewById(R.id.zweiterZug);
		turn3 = (RadioButton)findViewById(R.id.dritterZug);
		turn4 = (RadioButton)findViewById(R.id.vierterZug);
		turnDisplay = new RadioButton[zugAnzahl];
	     
		//Array befuellen
		
		turnDisplay[0] = turn1;
		turnDisplay[1] = turn2;
		turnDisplay[2] = turn3;
		turnDisplay[3] = turn4;
		
		
		gameEnded = false;
		Random Zufall = new Random();
		Start = Zufall.nextInt(20);
		Goal = Zufall.nextInt(100);
	   
		plusZahl = Zufall.nextInt(10);
		minusZahl = Zufall.nextInt(10);
		malZahl = Zufall.nextInt(10);
		teilZahl = Zufall.nextInt(10);
	   
		
		ans = Start;
		String Startzahl = String.valueOf(Start);
		String zuErreichen = String.valueOf(Goal);
		String zwischenErgebnis = String.valueOf(ans);
		String plus = "+"+String.valueOf(plusZahl);
		String minus = "-"+String.valueOf(minusZahl);
		String mal = "*"+String.valueOf(malZahl);
		String teil = ":"+String.valueOf(teilZahl);
	
		
		
		//Ausgabe in View
		startZahl.setText(Startzahl);
		zielZahl.setText(zuErreichen);
	    Ausgabe.setText(Startzahl); 
	    Plusbutton.setText(plus);
	    Minusbutton.setText(minus);
	    Malbutton.setText(mal);
	    Teilbutton.setText(teil);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void addieren(View Buttonclick) {

		if (gameEnded == true){}
		else{
		ans = ans + plusZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}

	public void subtrahieren(View Buttonclick) {

		if (gameEnded == true){}
		else {
		ans = ans - minusZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}
	
	
	public void dividieren (View Buttonclick) {
		
		if (gameEnded == true){}
		else {
		ans = ans / teilZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}
	
	public void multiplizieren (View Buttonclick) {
		
        if (gameEnded == true){}
        else {
		ans = ans * malZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		ziehen();}
	}
	
	
	private void ziehen (){
	
		zugCounter++;
		
		turnDisplay[zugCounter-1].setChecked(true);
		
		if (zugCounter == zugAnzahl && ans == Goal){Ausgabe.setText("Gewonnen!");gameEnded = true;}
		if (zugCounter == zugAnzahl && ans != Goal){Ausgabe.setText("Verloren!");gameEnded = true;}
		
	}
	
	
	public void reset(View Buttonclick){
		gameEnded = false;
		zugCounter = 0;
		ans = Start;
		String zwischenErgebnis = String.valueOf(ans);
		 Ausgabe.setText(zwischenErgebnis); 
		
		for (int i= 0; i< turnDisplay.length;i++){turnDisplay[i].setChecked(false);}
		
		
	}
	
	
	

}
>>>>>>> master
