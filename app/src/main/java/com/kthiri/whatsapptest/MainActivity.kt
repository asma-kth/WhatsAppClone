package com.kthiri.whatsapptest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("messages")

        myRef.setValue("Hello, World!")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var value = dataSnapshot!!.value
                Log.d( "Value is: ", value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException())
            }
        })
    }
}
