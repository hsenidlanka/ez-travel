package com.example.hsenid.taxiapp.util.vehicleType;

/**
 * Created by hsenid on 12/27/17.
 */

import android.app.Activity;

public class CustomSpinner extends Activity {


   /* public ArrayList<SpinnerModel> CustomListViewValueArr= new ArrayList<>();
    TextView output = null;
    CustomAdapter adapter;
    CustomSpinner activity= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        Spinner SpinnerExample=(Spinner)findViewById(R.id.spinner);
        output =(TextView)findViewById(R.id.output);

        setListData();

        Resources res=getResources();
        adapter = new CustomAdapter(activity, R.layout.spinner_rows,CustomListViewValueArr,res);
        SpinnerExample.setAdapter(adapter);

        SpinnerExample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String Company= ((TextView) view.findViewById(R.id.company)).getText().toString();
                String OutputMsg= "Selected Company :\n\n"+Company+"\n";
                output.setText(OutputMsg);

                Toast.makeText(getApplicationContext(),OutputMsg,Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }

    public  void setListData(){
        String coy[]= {"ask", "apple", "google", "cocacola","microsoft","ibm","toyota","realmadrid"};
        for (int i=0; i< coy.length;i++){
            final SpinnerModel sched= new SpinnerModel();

            *//*********** Firstly take data**************//*
            sched.setCompanyName(coy[i]);
            sched.setImage("image" +i);

            *//***********Take Model Object in ArrayList**********//*
            CustomListViewValueArr.add(sched);
        }
    }*/
}

