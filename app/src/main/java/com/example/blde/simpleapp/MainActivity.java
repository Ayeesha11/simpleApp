package com.example.blde.simpleapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    RecyclerView ryList;
    StudAdapter StudAdapter;
    List<student> studentList = new ArrayList<>();
    Studentdb studentdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ryList = (RecyclerView) findViewById(R.id.ryList);

        // studentList = getStudentList();
        Studentdb studentdb = new Studentdb(MainActivity.this);

        new MyAsyncTask().execute("https://api.androidhive.info/contacts/");

    }

    List<student> getStudentList() {
        List<student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            student student = new student(
                    R.mipmap.ic_launcher,
                    "Student" + i,
                    (i + 1) + "/06/1990");

            list.add(student);
        }

        return list;

    }


    class MyAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }


        @Override
        protected String doInBackground(String... strings) {
            //TODO https://api.androidhive.info/contacts/
            String URL = strings[0];
            // HttpC
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(URL);

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status == 200) {

                    String responseString = EntityUtils.toString(httpResponse.getEntity());

                    System.out.println(responseString);
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject subJson = jsonArray.getJSONObject(i);
                        String name = subJson.getString("name");
                        String dob = subJson.getString("email");


                        student student = new student(R.mipmap.ic_launcher, name, dob);
                        studentList.add(student);
                        System.out.println(i + "." + "Name:" + name);

                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            //showList();
            storeList(studentList);
        }
    }


    void showList() {
        //   StudAdapter = new StudAdapter(studentList);

        StudAdapter = new StudAdapter(getListFromDb());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        ryList.setLayoutManager(linearLayoutManager);
        ryList.setAdapter(StudAdapter);
        StudAdapter.notifyDataSetChanged();
    }


    void storeList(List<student> list) {
        for (int i = 0; i < list.size(); i++) {
            student student = list.get(i);

            studentdb.openToWrite();
            studentdb.insert(student);
            studentdb.close();

        }
        showList();
    }

    List<student> getListFromDb(){
        List<student> list = new ArrayList<>();
        studentdb.openToRead();
        list= studentdb.getStudentList();
        studentdb.close();
        return  list;
    }


}
