package com.example.qr_biz;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class MainActivity extends AppCompatActivity {
    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable

        etInput = findViewById(R.id.et_input);
        btGenerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);

        btGenerate.setOnClickListener(view -> {

            //Get input value from edit text
            String sText = etInput.getText().toString().trim();
            //Initialize multi format writer
            MultiFormatWriter writer = new MultiFormatWriter();
            //Initialize bit matrix
            try{
                BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder encoder = new BarcodeEncoder();
                //Initialize bitmap
                Bitmap bitmap = encoder.createBitmap(matrix);
                //Set bitmap on image view
                ivOutput.setImageBitmap(bitmap);
                //Initialize input manager
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);
            }catch (WriterException e){
                e.printStackTrace();
            }
        });
    }
}