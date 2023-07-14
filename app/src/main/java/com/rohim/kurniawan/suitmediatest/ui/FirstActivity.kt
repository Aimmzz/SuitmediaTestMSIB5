package com.rohim.kurniawan.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.rohim.kurniawan.suitmediatest.R
import com.rohim.kurniawan.suitmediatest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }
    private fun setupAction() {
        val palindrome = binding.edtPalindrome.text
        val name = binding.edtName.text

        binding.btnCheck.setOnClickListener {
            var isPalindrome = true
            if(palindrome.isEmpty()){
                binding.edtPalindrome.error ="Must be filled!"
                isPalindrome = false
            }

            val textLength = palindrome.length
            var i = 0
            while(i<textLength/2){
                i++
                if(palindrome[i] != palindrome[textLength-1-i]){
                    isPalindrome = false
                    break
                }
            }
            if(isPalindrome){
                Toast.makeText(this, "Palindrome", Toast.LENGTH_SHORT).show()
            }else if(!isPalindrome && palindrome.isNotEmpty()) {
                Toast.makeText(this, "Not Palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val moveToSecond = Intent(this, SecondActivity::class.java)
            moveToSecond.putExtra(SecondActivity.EXTRA_NAME,name.toString())
//            val coba = intent.putExtra(SecondActivity.EXTRA_NAME,name.toString())
//            Log.d(TAG, "$coba")
            if(name.isEmpty()){
                binding.edtName.error = "Must be filled!"
            }else startActivity(moveToSecond)
        }
    }
    companion object {
        private var TAG = "MainActivity"
    }
}