package com.example.blde.simpleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by BLDE on 7/29/2018.
 */

public class Studentdb {
        private static String MYDATABASE_NAME = "student_db" ;
        public static final String MYDATABASE_TABLE = "STUDENT_LOGIN";

        public static final String  COLUMN_ID ="student_id";
        public static final String  KEY_STUDENT ="STUDENT";
        public static final String  KEY_DOB ="DOB";


        static String SCRIPT_CREATE = "CREATE TABLE IF NOT EXISTS "
                +MYDATABASE_TABLE+ " " +"( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  , "
                +KEY_STUDENT+"	TEXT  ," +
                KEY_DOB+"	TEXT  ); ";


        private SQLiteDatabase sqLiteDatabase;
        private Context context;
        private Helper helper ;



        public Studentdb(Context context) {
            this.context = context;
        }

        /**
         * @return
         * @throws android.database.SQLException
         */
        public Studentdb openToRead() throws android.database.SQLException {
            helper = new Helper(context, MYDATABASE_NAME, null,  1);
            sqLiteDatabase = helper.getReadableDatabase();
            return this;
        }

        /**
         * @return
         * @throws android.database.SQLException
         */
        public Studentdb openToWrite() throws android.database.SQLException {
            helper = new Helper(context, MYDATABASE_NAME, null, 1);
            sqLiteDatabase = helper.getWritableDatabase();
            return this;
        }

        public void close() {
            helper.close();
        }


        public boolean isOpen() {
            return sqLiteDatabase!=null && sqLiteDatabase.isOpen();
        }


        public long insert(student student){
            try {
                ContentValues values  = new ContentValues();
                values.put(KEY_STUDENT, student.getName());
                values.put(KEY_DOB, student.getDOB());

                return sqLiteDatabase.insert(MYDATABASE_TABLE,null,values);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

        /**
         * UPDATE COMPANY SET PASSWORD = 'newPw' WHERE USER = user;
         * @param student
         * @param newDOB
         * @return
         */
        public long update(student student ,String newDOB) {
            try {

                ContentValues contentValues = new ContentValues();
                contentValues.put(KEY_DOB, newDOB);



                String where = KEY_STUDENT + "=  ?";  // NAME + " = ? AND " + LASTNAME + " = ?"
                String[] whereArgs = new String[]{student.getName()};


                return sqLiteDatabase.update(MYDATABASE_TABLE, contentValues, where, whereArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }


        public List<student> getStudentList(){
            List<student> studentList = new ArrayList<>();

            try{
                Cursor mCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MYDATABASE_TABLE ,null);

                if(mCursor!= null){
                    if(mCursor.moveToFirst()){

                        do{

                            student student = new student(R.mipmap.ic_launcher,
                                    mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT)),
                                    mCursor.getString(mCursor.getColumnIndex(KEY_DOB))

                            );

                            studentList.add(student) ;

                        }while (mCursor.moveToNext());

                    }
                }

            }catch (Exception e){

                e.printStackTrace();
            }


            return  studentList;


        }

        class Helper extends SQLiteOpenHelper{

            public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                // TODO Auto-generated method stub
                db.execSQL(SCRIPT_CREATE);
                System.out.println("******************************");

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }


    }
