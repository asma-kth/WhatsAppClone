package com.kthiri.whatsapptest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class MainActivity : AppCompatActivity() {
var mAuth: FirebaseAuth?=null
    var currentUser :FirebaseUser?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("messages").push()
mAuth= FirebaseAuth.getInstance()
//sign existing users

        (mAuth as FirebaseAuth)!!.signInWithEmailAndPassword("asma.kthiri@gmail.com","147asma").addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful){
                Toast.makeText(this,"login is succeful",Toast.LENGTH_LONG).show()
            }
                    else
                Toast.makeText(this,"login is unsecceful",Toast.LENGTH_LONG).show()
        }

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


    override fun onStart() {
        super.onStart()
        currentUser= mAuth!!.currentUser
    }

    data class Employee (var name:String, var position:String, var adress:String )
}
