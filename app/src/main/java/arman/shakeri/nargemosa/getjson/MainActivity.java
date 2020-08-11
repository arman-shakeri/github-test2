package arman.shakeri.nargemosa.getjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<model> list;
    RecyclerView recyclerView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);

        new GetData(MainActivity.this).execute();

        //thisIsTest();
    }
    
    public void thisIsTest(){

        Toast.makeText(this, "r u sure this is a test", Toast.LENGTH_SHORT).show();
    }

    private class GetData extends AsyncTask<Void,Void,String>{

        ProgressDialog progressDialog ;
        Context context;

        public GetData(Context context){

            this.context = context;
            //progressDialog = new ProgressDialog(MainActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("در حال دریافت اطلاعات");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String url = "http://192.168.1.3/json/Sample-employee-JSON-data.php";

            return JsonClass.getJson(url);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            super.onPostExecute(s);
            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    model model = new model();
                    model.setJobTitle(object.getString("jobTitle"));
                    model.setName(object.getString("name"));
                    model.setEmailAddress(object.getString("emailAddress"));

                    list.add(model);
                }
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
