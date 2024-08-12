package com.schooltools.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schooltools.retrofit.dataClasses.News
import com.schooltools.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Api.retrofitService.fetchData()
                withContext(Dispatchers.Main) {
                    binding.loading.visibility = GONE
                    binding.recyclerView.adapter = Adapter(response)
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception) {
                Log.d("MainActivity", "Error: ${e.message}")
                withContext(Dispatchers.Main) {
                    binding.loading.visibility = GONE
                    binding.errorMessage.text = "Error: ${e.message}"
                    binding.errorMessage.visibility = VISIBLE
                }
            }
        }
    }
}