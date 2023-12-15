package com.example.iot_aes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.iot_aes.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference1: DatabaseReference
    private lateinit var databaseReference2: DatabaseReference
    private lateinit var temperatureTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference1 = FirebaseDatabase.getInstance().reference.child("DHT").child("temperature")

        databaseReference1.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperature = snapshot.getValue(Double::class.java)
                temperature?.let {
                    binding.tempval.text = "$it°C"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error fetching data", Toast.LENGTH_SHORT).show()
            }

        })
        databaseReference2 = FirebaseDatabase.getInstance().reference.child("DHT").child("humidity")

        databaseReference2.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperature = snapshot.getValue(Double::class.java)
                temperature?.let {
                    binding.humidval.text = "$it%"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error fetching data", Toast.LENGTH_SHORT).show()
            }

        })



    }
}