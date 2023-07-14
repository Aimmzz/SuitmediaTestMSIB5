package com.rohim.kurniawan.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rohim.kurniawan.suitmediatest.R
import com.rohim.kurniawan.suitmediatest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = activity2

        val username = intent.getStringExtra(EXTRA_NAME)
        binding.tvUsername.text = username

        setupAction()
    }

    private fun setupAction() {
        var selectedUserName = binding.tvSelectedUser.isVisible
        binding.btnChoseUser.setOnClickListener {
            val moveToThird = Intent(this,ThirdActivity::class.java)
            if (!selectedUserName){
                startActivity(moveToThird)
            }else {
                Toast.makeText(this, "Must click name!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvUsername.setOnClickListener {
            binding.tvSelectedUser.visibility = View.VISIBLE
            binding.tvSelectedUser.text = SELECTED
            binding.tvUsername.setTextColor(ContextCompat.getColor(this, R.color.cl_button))
            selectedUserName = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val SELECTED = "Selected User Name"
        private var activity2 ="Second Screen"
    }
}