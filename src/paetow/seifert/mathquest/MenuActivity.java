package paetow.seifert.mathquest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}


	
	public void loslegen (View Buttonclick){
		
		Intent in = new Intent(this, EinsActivity.class);
		startActivity(in);
		
		
	}
}
