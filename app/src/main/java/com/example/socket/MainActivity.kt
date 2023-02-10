package com.example.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socket.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    private var data = mutableListOf<Chatting>()
    private val adapter = MessageAdapter(data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("message/text")

        binding.recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerview.adapter = adapter

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    val getData = i.getValue(Chatting::class.java)
                    data.add(getData!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //애러 떳을때
                Log.d("상태", error.message)
                Toast.makeText(this@MainActivity, error.message,Toast.LENGTH_SHORT).show()
            }
        })

        binding.message.setOnClickListener{
            val layoutManager = binding.recyclerview.layoutManager as LinearLayoutManager
            layoutManager.scrollToPosition(binding.recyclerview.adapter!!.itemCount -1)
        }

        binding.button.setOnClickListener {
            data.add(Chatting(binding.message.text.toString()))
            ref.setValue(data)
            binding.message.text = null
            adapter.notifyDataSetChanged()
        }
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("상태","onChildAdded()")
                val newdata = snapshot.getValue(Chatting::class.java)
                Log.d("상태상태","${newdata?.text}")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("상태","onChildChanged()")
//                val newdata = snapshot.getValue(Chatting::class.java)
//                data.add(newdata!!)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("상태","onChildRemoved()")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("상태","onChildMoved()")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("상태", error.message)
                Toast.makeText(this@MainActivity, error.message,Toast.LENGTH_SHORT).show()
            }

        })
    }
}