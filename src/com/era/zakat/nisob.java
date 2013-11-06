package com.era.zakat;

import java.text.DecimalFormat;

import com.era.zakatDB.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class nisob extends Activity {
	final DBAdapter db = new DBAdapter(this);
	DecimalFormat df = new DecimalFormat("###.##");	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nisab);
		
		final TextView Nisob = (TextView)findViewById(R.id.setNisob);
		
		db.open();
		Cursor x = db.getRecord(1);
		Double dx = x.getDouble(1);
		Nisob.setText(df.format(dx));
		db.close();

		Button hitung = (Button) findViewById(R.id.hitung);
		hitung.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					EditText a = (EditText) findViewById(R.id.editText1);
					String ha = a.getText().toString();
			        Double da = Double.parseDouble(ha);
				
			        Double hNisob = 85*da;
			        
					Nisob.setText(df.format(hNisob));
			      
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(nisob.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		Button setNisob = (Button) findViewById(R.id.sNisob);
		setNisob.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
			        TextView Nisob = (TextView)findViewById(R.id.setNisob);
			        String nisob = Nisob.getText().toString();
			        final Double hNisob = Double.valueOf(nisob).doubleValue();
			        db.open();
					db.updateRecord(1, hNisob);
					db.close();
 
					db.open();
					Cursor c = db.getAllRecords();
					if (c.moveToFirst())
					{
						do {
							DisplayRecord(c);
						}while (c.moveToNext());
					}
					db.close();
					
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(nisob.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
	}
	
	public void DisplayRecord(Cursor c)
	{
		Toast.makeText(this,
				"Angka Nisob : "
				+df.format(c.getDouble(1)),
				Toast.LENGTH_SHORT).show();
	}

}
