package gr.magdamaria.meterconverter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {
	Button btn1;
	EditText edt;
	TextView txt;
	String[] items;
	Spinner spn1,spn2;
	String option1,option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setTitle("Μετατροπέας Μέτρων"); //Αλλαγή του τίτλου της μπάρας
       
        //Δημιουργία των αντικειμένων της εφαρμογής μας 
        btn1 = (Button) findViewById(R.id.button1);
        edt =(EditText) findViewById(R.id.editText1);
        txt=(TextView) findViewById(R.id.textView5);
        spn1 = (Spinner) findViewById(R.id.spinner1);
        spn2 = (Spinner) findViewById(R.id.spinner2);
        items = getResources().getStringArray(R.array.items1);
         
        //Δημιουργία της λειτουργίας του πρώτου Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); //Εμφάνιση της λίστας με radio buttons
        spn1.setAdapter(adapter);
        spn1.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) { //η μεθοδος ενεργοποιείται κάθε φορά που επιλέγεται ενα στοιχείο της λίστας-
        		int index = arg0.getSelectedItemPosition();
        		Toast.makeText(getBaseContext(), "Convert from:  "+items[index],Toast.LENGTH_LONG).show();
        	}
        	@Override
        	public void onNothingSelected(AdapterView<?> arg0) {}
        });
        
        //Δημιουργία της λειτουργίας του δεύτερου Spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, items);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn2.setAdapter(adapter2);
        spn2.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        		int index = arg0.getSelectedItemPosition();
        		Toast.makeText(getBaseContext(), "To:  "+items[index],Toast.LENGTH_LONG).show();
        	}
        	@Override
        	public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }
    
    //Δημιουργία της μεθόδου convert που θα ενεργοποιείται με το πάτημα του πλήκτρου "Convert"
    public void convert(View v){
    	txt.setTextSize(20);
		txt.setTextColor(Color.BLUE);
    	String x = edt.getText().toString();
    	
    	if (TextUtils.isEmpty(x) /*|| x.matches("[a-zA-Z]+")*/){ //Έλεγχος αν το πεδίο εισαγωγής κειμένου είναι κενό /*η περιέχει μόνο χαρακτήρες (αν ενεργοπιηθεί αυτή η εντολή τότε η εντολή " android:inputType="numberDecimal"στo activity_main είναι περιττή)*/
    		edt.setError("Πληκτρολογήστε έναν αριθμό για μετατροπή");
    		return;
    	}
    	
    	Double y = Double.parseDouble(x);
    	option1= spn1.getSelectedItem().toString();//Η μεταβλητή option1 παίρνει την επιλεγμένη τιμή απο την λίστα
    	option2= spn2.getSelectedItem().toString();//Η μεταβλητή option2 παίρνει την επιλεγμένη τιμή απο την λίστα
    	if(option1.equals("kilometer") && option2.equals("meter")){ //Μετατροπή χιλιομέτρων σε μέτρα
    		double k=y*1000;
    		String z= new Double(k).toString();
    		txt.setText(z);
    	}
    	else
    		if (option1.equals("kilometer") && option2.equals("centimeter")){//Μετατροπή χιλιομέτρων σε εκατοστά
    			double k=y*100000;
    			String z= new Double(k).toString();
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("kilometer") && option2.equals("inch")){//Μετατροπή χιλιομέτρων σε ιντσες
    			double k=y*39370.0787;
    			String z=String.format("%.2f", k); //Συναρτηση που μας επιστρέφει το αποτέλεσμα μόνο με 2 δεκαδικά ψηφία
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("kilometer") && option2.equals("mile")){//Μετατροπή χιλιομέτρων σε μίλια
        		double k=y*0.621371192;
        		String z=String.format("%.2f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("meter") && option2.equals("kilometer")){//Μετατροπή μέτρων σε χιλιόμετρα
        		double k=y/1000;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
    	else
    		if (option1.equals("meter") && option2.equals("centimeter")){//Μετατροπή μέτρων σε εκατοστά
    			double k=y*100;
    			String z= new Double(k).toString();
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("meter") && option2.equals("inch")){//Μετατροπή μέτρων σε ίντσες
    			double k=y*39.3700787;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("meter") && option2.equals("mile")){//Μετατροπή μέτρων σε μίλια
        		double k=y*0.000621371192;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
        else
        	if(option1.equals("centimeter") && option2.equals("kilometer")){//Μετατροπή εκατοστών σε χιλιόμετρα
        		double k=y/100000;
        		String z=String.format("%.8f", k);//Συναρτηση που μας επιστρέφει το αποτέλεσμα με 8 δεκαδικά ψηφία
        		txt.setText(z);
        	}
    	else
    		if (option1.equals("centimeter") && option2.equals("meter")){//Μετατροπή εκατοστών σε μέτρα
    			double k=y*0.01;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("centimeter") && option2.equals("inch")){//Μετατροπή εκατοστών σε ίντσες
    			double k=y*0.393700787;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("centimeter") && option2.equals("mile")){//Μετατροπή εκατοστών σε μίλια
        		double a=6.21371192/1000000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("inch") && option2.equals("kilometer")){//Μετατροπή ιντσών σε χιλιόμετρα
        		double a=2.54/100000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
    	}
    	else
    		if (option1.equals("inch") && option2.equals("meter")){//Μετατροπή ιντσών σε μέτρα
    			double k=y*0.0254;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("inch") && option2.equals("centimeter")){//Μετατροπή ιντσών σε εκατοστά
    			double k=y*2.54;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("inch") && option2.equals("mile")){//Μετατροπή ιντσών σε μίλια
        		double a=1.57828283/100000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("mile") && option2.equals("kilometer")){//Μετατροπή μιλίων σε χιλιόμετρα
        		double k=y*1.609344 ;
        		String z=String.format("%.2f", k);
        		txt.setText(z);
    	}
    	else
    		if (option1.equals("mile") && option2.equals("meter")){//Μετατροπή μιλίων σε μέτρα
    			double k=y*1609.344;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("mile") && option2.equals("centimeter")){//Μετατροπή μιλίων σε εκατοστά
    			double k=y*160934.4;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("mile") && option2.equals("inch")){//Μετατροπή μιλίων σε ίντσες
        		double k=y*63360;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
        else
        	if(option1.equals(option2)){ //Αν και οι 2 λίστες έχουν επιλεγμένη την ίδια μονάδα μέτρησης
        		txt.setTextSize(10);
        		txt.setTextColor(Color.RED);
        		txt.setText("Δεν γίνεται μετατροπή στην ίδια μονάδα μέτρησης");
    	}
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
}

