package com.example.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socket.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    private lateinit var data: MutableList<Chatting>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("message/text")
        binding.recyclerview.adapter
        binding.button.setOnClickListener {
            data.add(Chatting(binding.message.text.toString()))
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(String::class.java)
                Log.d("상태","$value")
            }

            override fun onCancelled(error: DatabaseError) {
                //애러 떳을때
                Log.d("상태", error.message)
            }
        })
    }
}