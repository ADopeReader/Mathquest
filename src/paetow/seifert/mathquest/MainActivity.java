package paetow.seifert.mathquest;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText startZahl;
	private EditText zielZahl;
	private TextView Ausgabe;
	private RadioButton turn;
	
	private int ans;
	private int Start;
	private int Goal;
	private int zugAnzahl;
	private int zugCounter;
	
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
	
		
	   Random Zufall = new Random();
	   Start = Zufall.nextInt(20);
	   Goal = Zufall.nextInt(100);
		
		
		
		ans = Start;
		String Startzahl = String.valueOf(Start);
		String zuErreichen = String.valueOf(Goal);
		String zwischenErgebnis = String.valueOf(ans);
	
		
		startZahl.setText(Startzahl);
		zielZahl.setText(zuErreichen);
	    Ausgabe.setText(Startzahl); 
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

		if ((startZahl.length() == 0) | (zielZahl.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = startZahl.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = zielZahl.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 + summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}

	public void subtrahieren(View Buttonclick) {


		if ((startZahl.length() == 0) | (zielZahl.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = startZahl.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = zielZahl.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 - summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}
	
	
	public void dividieren (View Buttonclick) {
		
		 
		if ((startZahl.length() == 0) | (zielZahl.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = startZahl.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = zielZahl.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 / summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}
	
	public void multiplizieren (View Buttonclick) {
		

		if ((startZahl.length() == 0) | (zielZahl.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = startZahl.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = zielZahl.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 * summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
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
