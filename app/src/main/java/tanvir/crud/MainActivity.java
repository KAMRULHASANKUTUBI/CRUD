package tanvir.crud;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etxtid,etxtname,etxtphone;
    Button btnCreate,btnShow,btnUpdate,btnDelete;
    MySQliteDB Mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtid=findViewById(R.id.etxt_id);
        etxtname=findViewById(R.id.etxt_name);
        etxtphone=findViewById(R.id.etxt_phone);

        btnCreate=findViewById(R.id.btn_create);
        btnShow=findViewById(R.id.btn_show);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete=findViewById(R.id.btn_delete);

        Mydb=new MySQliteDB(MainActivity.this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etxtid.getText().toString();
                String name=etxtname.getText().toString();
                String phone=etxtphone.getText().toString();

                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Id", Toast.LENGTH_SHORT).show();
                }
                else if (name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if (phone.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your Phone", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check=Mydb.addToTable(id,name,phone);
                    if(check=true){
                        Toast.makeText(MainActivity.this, "Data Create Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Data NOT Created", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void viewData(View v){
        Cursor getData=Mydb.display();
        if (getData.getCount()==0){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuffer buffer=new StringBuffer();
            getData.moveToFirst();

            do{
                buffer.append("ID: "+getData.getString(0)+"\n");
                buffer.append("Name: "+getData.getString(1)+"\n");
                buffer.append("Phone: "+getData.getString(2)+"\n");
            }while (getData.moveToNext());
            showData("My Contacts",buffer.toString());

        }
    }
    public void showData(String title,String data){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();


    }
    public void delete(View v){
        String getId=etxtid.getText().toString();
        int check=Mydb.deleteData(getId);

        if (check>0){
            Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Not Deleted! Some Thing is Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v){
        String id=etxtid.getText().toString();
        String name=etxtname.getText().toString();
        String phone=etxtphone.getText().toString();

        if(id.isEmpty()){
            Toast.makeText(MainActivity.this, "Please Enter Your Id", Toast.LENGTH_SHORT).show();
        }
        else if (name.isEmpty()){
            Toast.makeText(MainActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }
        else if (phone.isEmpty()){
            Toast.makeText(MainActivity.this, "Enter Your Phone", Toast.LENGTH_SHORT).show();
        }else{
            boolean check=Mydb.addToTable(id,name,phone);
            if(check=true){
                Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Data NOT Update", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
