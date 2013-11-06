package com.era.zakat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.era.zakatDB.*;


public class MainActivity extends Activity {

	DecimalFormat df = new DecimalFormat("###.##");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		Button tahun = (Button) findViewById(R.id.bTahun);
		Button profesi = (Button) findViewById(R.id.bProfesi);
		Button usaha = (Button) findViewById(R.id.bUsaha);
		Button nisab = (Button) findViewById(R.id.bNisab);

		tahun.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
				Intent i = null;
				i = new Intent(MainActivity.this, tahunan.class);
				startActivity(i);
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(MainActivity.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		profesi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
				Intent i = null;
				i = new Intent(MainActivity.this, profesi.class);
				startActivity(i);
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(MainActivity.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		usaha.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
				Intent i = null;
				i = new Intent(MainActivity.this, usaha.class);
				startActivity(i);
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(MainActivity.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		nisab.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
				Intent i = null;
				i = new Intent(MainActivity.this, nisob.class);
				startActivity(i);
				}catch (Exception ex) {
					AlertDialog.Builder build = new
	        				AlertDialog.Builder(MainActivity.this);
	        				build.setTitle("Kesalahan")
	        				.setMessage("Terjadi Kesalahan : "+ex)
	        				.setPositiveButton("OK",null)
	        				.show();
	        				}
			}
        });
		
		try{
			String destPath ="/data/data/"+getPackageName()+"/databases/NisobDB";
			File f =new File(destPath);
			if (! f.exists()){
				CopyDB(getBaseContext().getAssets().open("mydb"),
						new FileOutputStream(destPath));
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		DBAdapter db = new DBAdapter(this);
		
		db.open();
		long id=db.insertRecord(1,"20000000");
		db.close();
		
		db.open();
		Cursor c = db.getAllRecords();
		if (c.moveToFirst())
		{
			do {
				DisplayRecord(c);
			}while (c.moveToNext());
		}
	}   
	
	public void CopyDB(InputStream inputstream, OutputStream outputsteam)
	throws IOException{
		byte[] buffer = new byte[1024];
		int lenght;
		while ((lenght = inputstream.read(buffer))>0){
			outputsteam.write(buffer,0,lenght);
		}
		inputstream.close();
		outputsteam.close();
	}
	
	public void DisplayRecord(Cursor c)
	{
		Toast.makeText(this,
				"Angka Nisob : "
				+df.format(c.getDouble(1)),
				Toast.LENGTH_SHORT).show();
	}

}
