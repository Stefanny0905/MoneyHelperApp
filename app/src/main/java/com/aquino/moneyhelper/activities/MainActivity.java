package com.aquino.moneyhelper.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aquino.moneyhelper.models.Operacion;
import com.aquino.moneyhelper.R;
import com.aquino.moneyhelper.repository.OperacionRepository;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView saldo_ahorro;
    private TextView saldo_credito;
    private TextView saldo_efectivo;
    private ProgressBar progressbar1;
    private static final int REGISTER_FORM_REQUEST = 100;
    private String[] xData={"Ingreso","Egreso"};
    PieChart piechart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saldo_ahorro = (TextView)findViewById(R.id.saldo_ahorro);
        saldo_efectivo = (TextView)findViewById(R.id.saldo_efectivo);
        saldo_credito = (TextView)findViewById(R.id.saldo_credito);
        progressbar1 = (ProgressBar)findViewById(R.id.progressbar1);
        piechart = (PieChart) findViewById(R.id.piechart);


    }

    public void nuevo(View view){
        startActivityForResult(new Intent(this, NewOperationActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        double suma_ahorro = 0;
        double suma_credito = 0;
        double suma_efectivo = 0;
        double suma_total;
        double suma_ingreso=0;
        double suma_egreso =0;

        OperacionRepository operacionRepository = OperacionRepository.getInstance();

        List<Operacion> operaciones = operacionRepository.getOperaciones();
        for (Operacion operacion : operaciones) {

         String tipo = operacion.getTipo();
         double monto = operacion.getMonto();
         String tipo_cuenta = operacion.getTipo_cuenta();


            if (tipo.equals("Ingreso")) {

                if (tipo_cuenta.equals("Ahorro")) {
                    suma_ahorro = suma_ahorro + monto;
                    saldo_ahorro.setText("S/."+suma_ahorro);

                }else if(tipo_cuenta.equals("Credito")) {
                    suma_credito = suma_credito + monto;
                    saldo_credito.setText("S/."+suma_credito);
                } else if (tipo_cuenta.equals("Efectivo")) {
                    suma_efectivo = suma_efectivo + monto;
                    saldo_efectivo.setText("S/." +suma_efectivo);
                }

                suma_ingreso = suma_ahorro+suma_credito+suma_efectivo;

            } else if (tipo.equals("Egreso")) {

                if (tipo_cuenta.equals("Ahorro") && monto<=suma_ahorro) {
                    suma_ahorro = suma_ahorro - monto;
                    saldo_ahorro.setText("S/."+suma_ahorro);
                }else if(tipo_cuenta.equals("Credito") && monto<=suma_credito) {
                    suma_credito = suma_credito - monto;
                    saldo_credito.setText("S/."+suma_credito);
                } else if (tipo_cuenta.equals("Efectivo")&& monto<=suma_efectivo) {
                    suma_efectivo = suma_efectivo - monto;
                    saldo_efectivo.setText("S/."+suma_efectivo);
                }else {
                    monto = 0;
                }
                suma_egreso = suma_egreso + monto;
            }


            suma_total = suma_ingreso + suma_egreso;
            double porcentaje = (suma_ingreso*100)/suma_total;
            progressbar1.setProgress((int)porcentaje);

            float[] yData={(int)porcentaje,(int)(100-porcentaje)};
            addset(yData);

                }

    }

    private void addset(float[] yData){
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , xData[i]));
        }

        for(int i = 0; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys,null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.LTGRAY);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        piechart.getDescription().setEnabled(false);
        piechart.setData(pieData);
        piechart.invalidate();
    }
}


