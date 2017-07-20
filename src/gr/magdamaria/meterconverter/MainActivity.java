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
        getActionBar().setTitle("����������� ������"); //������ ��� ������ ��� ������
       
        //���������� ��� ������������ ��� ��������� ��� 
        btn1 = (Button) findViewById(R.id.button1);
        edt =(EditText) findViewById(R.id.editText1);
        txt=(TextView) findViewById(R.id.textView5);
        spn1 = (Spinner) findViewById(R.id.spinner1);
        spn2 = (Spinner) findViewById(R.id.spinner2);
        items = getResources().getStringArray(R.array.items1);
         
        //���������� ��� ����������� ��� ������ Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); //�������� ��� ������ �� radio buttons
        spn1.setAdapter(adapter);
        spn1.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) { //� ������� �������������� ���� ���� ��� ���������� ��� �������� ��� ������-
        		int index = arg0.getSelectedItemPosition();
        		Toast.makeText(getBaseContext(), "Convert from:  "+items[index],Toast.LENGTH_LONG).show();
        	}
        	@Override
        	public void onNothingSelected(AdapterView<?> arg0) {}
        });
        
        //���������� ��� ����������� ��� �������� Spinner
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
    
    //���������� ��� ������� convert ��� �� �������������� �� �� ������ ��� �������� "Convert"
    public void convert(View v){
    	txt.setTextSize(20);
		txt.setTextColor(Color.BLUE);
    	String x = edt.getText().toString();
    	
    	if (TextUtils.isEmpty(x) /*|| x.matches("[a-zA-Z]+")*/){ //������� �� �� ����� ��������� �������� ����� ���� /*� �������� ���� ���������� (�� ������������ ���� � ������ ���� � ������ " android:inputType="numberDecimal"��o activity_main ����� �������)*/
    		edt.setError("�������������� ���� ������ ��� ���������");
    		return;
    	}
    	
    	Double y = Double.parseDouble(x);
    	option1= spn1.getSelectedItem().toString();//� ��������� option1 ������� ��� ���������� ���� ��� ��� �����
    	option2= spn2.getSelectedItem().toString();//� ��������� option2 ������� ��� ���������� ���� ��� ��� �����
    	if(option1.equals("kilometer") && option2.equals("meter")){ //��������� ����������� �� �����
    		double k=y*1000;
    		String z= new Double(k).toString();
    		txt.setText(z);
    	}
    	else
    		if (option1.equals("kilometer") && option2.equals("centimeter")){//��������� ����������� �� ��������
    			double k=y*100000;
    			String z= new Double(k).toString();
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("kilometer") && option2.equals("inch")){//��������� ����������� �� ������
    			double k=y*39370.0787;
    			String z=String.format("%.2f", k); //��������� ��� ��� ���������� �� ���������� ���� �� 2 �������� �����
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("kilometer") && option2.equals("mile")){//��������� ����������� �� �����
        		double k=y*0.621371192;
        		String z=String.format("%.2f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("meter") && option2.equals("kilometer")){//��������� ������ �� ����������
        		double k=y/1000;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
    	else
    		if (option1.equals("meter") && option2.equals("centimeter")){//��������� ������ �� ��������
    			double k=y*100;
    			String z= new Double(k).toString();
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("meter") && option2.equals("inch")){//��������� ������ �� ������
    			double k=y*39.3700787;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("meter") && option2.equals("mile")){//��������� ������ �� �����
        		double k=y*0.000621371192;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
        else
        	if(option1.equals("centimeter") && option2.equals("kilometer")){//��������� ��������� �� ����������
        		double k=y/100000;
        		String z=String.format("%.8f", k);//��������� ��� ��� ���������� �� ���������� �� 8 �������� �����
        		txt.setText(z);
        	}
    	else
    		if (option1.equals("centimeter") && option2.equals("meter")){//��������� ��������� �� �����
    			double k=y*0.01;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("centimeter") && option2.equals("inch")){//��������� ��������� �� ������
    			double k=y*0.393700787;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("centimeter") && option2.equals("mile")){//��������� ��������� �� �����
        		double a=6.21371192/1000000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("inch") && option2.equals("kilometer")){//��������� ������ �� ����������
        		double a=2.54/100000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
    	}
    	else
    		if (option1.equals("inch") && option2.equals("meter")){//��������� ������ �� �����
    			double k=y*0.0254;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("inch") && option2.equals("centimeter")){//��������� ������ �� ��������
    			double k=y*2.54;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("inch") && option2.equals("mile")){//��������� ������ �� �����
        		double a=1.57828283/100000;
        		double k=y*a;
        		String z=String.format("%.8f", k);
        		txt.setText(z);
        	}
        else
        	if(option1.equals("mile") && option2.equals("kilometer")){//��������� ������ �� ����������
        		double k=y*1.609344 ;
        		String z=String.format("%.2f", k);
        		txt.setText(z);
    	}
    	else
    		if (option1.equals("mile") && option2.equals("meter")){//��������� ������ �� �����
    			double k=y*1609.344;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
    		if (option1.equals("mile") && option2.equals("centimeter")){//��������� ������ �� ��������
    			double k=y*160934.4;
    			String z=String.format("%.2f", k);
    			txt.setText(z);
    		}
    	else
        	if (option1.equals("mile") && option2.equals("inch")){//��������� ������ �� ������
        		double k=y*63360;
        		String z= new Double(k).toString();
        		txt.setText(z);
        	}
        else
        	if(option1.equals(option2)){ //�� ��� �� 2 ������ ����� ���������� ��� ���� ������ ��������
        		txt.setTextSize(10);
        		txt.setTextColor(Color.RED);
        		txt.setText("��� ������� ��������� ���� ���� ������ ��������");
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

