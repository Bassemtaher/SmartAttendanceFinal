package com.bassem.bassem.smartattendance;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bassem.bassem.smartattendance.Model.ModelInterface;

import com.bassem.bassem.smartattendance.Model.AddInterface;
import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Student;
import com.bassem.bassem.smartattendance.Model.Subject;




public class HelperMethods {


    public static Student currentUser = new Student();
    public static Subject currentSubject = new Subject();
    public static ArrayList <Subject> subjectArrayList=new ArrayList<>();




    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    public static void putToolTip(final Activity activity, String seatNumber) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Seat Number");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Seat Number : " + seatNumber);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void saveInFireBase(Object o, String childName) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = mDatabase.child(childName);
        mDatabase.setValue(o);
    }

//    public static void pushInFireBase(Object o, String childName, final Activity ac, String title, String msg, final boolean showToast) {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase = mDatabase.child(childName);
//        HelperMethods.showDialog(ac, title, msg);
//        mDatabase.push().setValue(o, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                HelperMethods.hideDialog(ac);
//
//                if (databaseError == null) {
//                    if (showToast) {
//                        Toast.makeText(ac, "Added successfully", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(ac, "Saving error", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }

    //    pushInFireBase( String title, String msg, ) {
    public static void pushInFireBase(String childName1, String childName2, ModelInterface currentObject, final AddInterface ac, String msg, String title) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = currentObject.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + childName1 + "/" + childName2 + "/", postValues);
        //mDatabase.updateChildren(childUpdates);
        HelperMethods.showDialog((Activity) ac, title, msg);

        mDatabase.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                HelperMethods.hideDialog((Activity) ac);
                ac.updateUI(databaseError);

            }
        });
    }

    public static void pushInFireBase(String childName1, String childName2, ModelInterface currentObject) {//, final AddInterface ac, String msg, String title, String id, final boolean showToast) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = mDatabase.child(childName1).child(childName2);
        mDatabase.setValue(currentObject);
    }

    public static void pushInFireBase(String childName, ModelInterface currentObject, final AddInterface ac, String msg, String title, String id) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child(childName).push().getKey();
        Map<String, Object> postValues = currentObject.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + childName + "/" + id, postValues);
        //mDatabase.updateChildren(childUpdates);
        HelperMethods.showDialog((Activity) ac, title, msg);

        mDatabase.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                HelperMethods.hideDialog((Activity) ac);
                ac.updateUI(databaseError);

            }
        });
    }

    public static void pushInFireBase(String childName, String childName1, ModelInterface currentObject, final AddInterface ac, String msg, String title, String id) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child(childName).child(childName1).push().getKey();
        Map<String, Object> postValues = currentObject.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + childName + "/" + id+ "/", postValues);
        //mDatabase.updateChildren(childUpdates);
        HelperMethods.showDialog((Activity) ac, title, msg);

        mDatabase.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                HelperMethods.hideDialog((Activity) ac);
                ac.updateUI(databaseError);

            }
        });
    }


    public static void pushInFireBase(String childName, String childName1, String childName2, String childName3,String childName4,ModelInterface currentObject, final AddInterface ac, String msg, String title, String id1, String id2, String id3, String id4, String id5) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child(childName).child(childName1).child(childName2).child(childName3).child(childName4).push().getKey();
        Map<String, Object> postValues = currentObject.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + childName + "/" + id1+ "/" + id2+ "/" + id3+ "/" + id4+ "/" + id5, postValues);
        //mDatabase.updateChildren(childUpdates);
        HelperMethods.showDialog((Activity) ac, title, msg);

        mDatabase.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                HelperMethods.hideDialog((Activity) ac);
                ac.updateUI(databaseError);

            }
        });
    }


    public static void getData(final GetDataInterface currentActivity, String childName, String title, String msg) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        HelperMethods.showDialog((Activity) currentActivity, title, msg);
        mDatabase.child(childName).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HelperMethods.hideDialog((Activity) currentActivity);
                        Log.i("dataa", "onDataChange: " + dataSnapshot.toString());
                        currentActivity.updateUI(dataSnapshot);

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("error", "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }



    public static void getData(final GetDataInterface currentActivity, String childName,String childName2,String childName3,String childName4, String childName5,String title, String msg) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        HelperMethods.showDialog((Activity) currentActivity, title, msg);
        mDatabase.child(childName).child(childName2).child(childName3).child(childName4).child(childName5).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HelperMethods.hideDialog((Activity) currentActivity);
                        Log.i("dataa", "onDataChange: " + dataSnapshot.toString());
                        currentActivity.updateUI(dataSnapshot);

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("error", "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }

    public static void getData(final GetDataInterface currentActivity, String childName1, String childName2, String title, String msg) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        HelperMethods.showDialog((Activity) currentActivity, title, msg);

        mDatabase.child(childName1).child(childName2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HelperMethods.hideDialog((Activity) currentActivity);
                currentActivity.updateUI(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static void geFragmentData(final GetDataInterface currentActivity, String childName, String title, String msg) {
        mDatabase.child(childName).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);
                        currentActivity.updateUI(dataSnapshot);

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("error", "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }


    private static ProgressDialog blg;

    public static void showDialog(Activity currentActivity, String title, String msg) {
        blg = new ProgressDialog(currentActivity);
        blg.setTitle(title);
        blg.setMessage(msg);
        blg.show();

    }

    public static void hideDialog(Activity currentActivity) {
        blg.cancel();
    }


}
