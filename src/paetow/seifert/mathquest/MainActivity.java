package paetow.seifert.mathquest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText test;
	private EditText test2;
	private TextView Ausgabe;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

		 test = (EditText) findViewById(R.id.EditText_Feld1);
		 test2 = (EditText) findViewById(R.id.EditText_Feld2);
		 Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);

		if ((test.length() == 0) | (test2.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = test.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = test2.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 + summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}

	public void subtrahieren(View Buttonclick) {
		
		 test = (EditText) findViewById(R.id.EditText_Feld1);
		 test2 = (EditText) findViewById(R.id.EditText_Feld2);
		 Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);

		if ((test.length() == 0) | (test2.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = test.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = test2.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 - summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}
	
	
	public void dividieren (View Buttonclick) {
		
		 test = (EditText) findViewById(R.id.EditText_Feld1);
		 test2 = (EditText) findViewById(R.id.EditText_Feld2);
		 Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);

		if ((test.length() == 0) | (test2.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = test.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = test2.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 / summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}
	
	public void multiplizieren (View Buttonclick) {
		
		 test = (EditText) findViewById(R.id.EditText_Feld1);
		 test2 = (EditText) findViewById(R.id.EditText_Feld2);
		 Ausgabe = (TextView) findViewById(R.id.Ergebnisanzeige);

		if ((test.length() == 0) | (test2.length() == 0)) {
			String Fehler = "Fehler!!";
			Ausgabe.setText(Fehler);
		}

		else {
			String temp = test.getText().toString();
			int summand1 = Integer.parseInt(temp);

			temp = test2.getText().toString();
			int summand2 = Integer.parseInt(temp);

			int ergebnis = summand1 * summand2;

			Integer ausgeben = ergebnis;
			String ergebnisausgeben = ausgeben.toString();

			Ausgabe.setText(ergebnisausgeben);
		}
	}

}
