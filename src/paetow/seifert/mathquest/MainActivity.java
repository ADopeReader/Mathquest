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

		ans = ans + plusZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		
	}

	public void subtrahieren(View Buttonclick) {


		ans = ans - minusZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
		
	}
	
	
	public void dividieren (View Buttonclick) {
		
		 
		ans = ans / teilZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
	}
	
	public void multiplizieren (View Buttonclick) {
		

		ans = ans * malZahl; 
		String zwischenErgebnis = String.valueOf(ans);
		Ausgabe.setText(zwischenErgebnis);
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
	
	
	
	

}
