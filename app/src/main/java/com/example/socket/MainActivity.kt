package com.example.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socket.databinding.ActivityMainBinding
import io.socket.client.Socket
import io.socket.emitter.Emitter

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    lateinit var socket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        socket = SocketApplication.get()
        socket.connect()
    }
}