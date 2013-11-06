package com.era.zakat;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.era.zakatDB.*;

public class tahunan extends Activity {
	DecimalFormat df = new DecimalFormat("###.##");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tahunan);
		
		DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.getRecord(1);
		final Double DNisob = c.getDouble(1);
		final String Nisob = df.format(DNisob);
		db.close();
		Button hitung = (Button) findViewById(R.id.hitung);
		hitung.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					EditText a = (EditText) findViewById(R.id.editText1);
					String ha = a.getText().toString();
			        Double da = Double.parseDouble(ha);
					EditText b = (EditText) findViewById(R.id.editText2);
					String hb = b.getText().toString();
			        Double db = Double.parseDouble(hb);
					EditText c = (EditText) findViewById(R.id.editText3);
					String hc = c.getText().toString();
			        Double dc = Double.parseDouble(hc);
					EditText d = (EditText) findViewById(R.id.editText4);
					String hd = d.getText().toString();
			        Double dd = Double.parseDouble(hd);
					EditText e = (EditText) findViewById(R.id.editText5);
					String he = e.getText().toString();
			        Double de = Double.parseDouble(he);
					EditText g = (EditText) findViewById(R.id.editText6);
					String hg = g.getText().toString();
			        Double dg = Double.parseDouble(hg);
				
			        Double hasil = da+db+dc+dd+de;
			        Double harta = hasil-dg;
			        if (harta > DNisob){
			        	Double zakat = 0.025 * harta;
				        
				        AlertDialog.Builder build = new
		        				AlertDialog.Builder(tahunan.this);
		        				build.setTitle("Hasil Perhitungan")
		        				.setMessage("* Jml Harta Simpanan : "+hasil+"\n"
		        						+"* Harta Kena Zakat   : "+harta+"\n"
		        						+"* Besarnya Zakat adalah 2.5% dari Harta Kena Zakat dengan asumsi harta kena zakat lebih dari nisab, sehingga \n"
		        						+"* Zakat Simpanan : "+zakat)
		        				.setPositiveButton("OK",null)
		        				.show();
			        }else{
			        	AlertDialog.Builder build = new
		        				AlertDialog.Builder(tahunan.this);
		        				build.setTitle("Hasil Perhitungan")
		        				.setMessage("* Jml Harta Simpanan : "+hasil+"\n"
		        						+"* Harta Kena Zakat   : "+harta+"\n"
		        						+"* Karena Harta Kena Zakat kurang dari Nisob yang besarnya "+Nisob+ "\n"
		        						+"Maka anda belum wajib mengeluarkan zakat")
		        				.setPositiveButton("OK",null)
		        				.show();
			        }
			        
			        
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(tahunan.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
