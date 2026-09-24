package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

        val initialIsRead = intent.getBooleanExtra("INITIAL_IS_READ", false)
        isReadChecked = savedInstanceState?.getBoolean("isReadChecked", initialIsRead) ?: initialIsRead

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkboxRead.isChecked = isReadChecked
        binding.checkboxRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
        }

        binding.imageViewArticle.load("https://n1s1.hsmedia.ru/50/39/04/503904078771671b626989ce70f33e5e/656x438_1:5523_b44511afbeeb27a1957da52867e6333b@2000x1335_0xqSBgTrfx_9440343981717064141.jpg.webp")

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i(TAG, "handleOnBackPressed")
                val resultIntent = Intent()
                resultIntent.putExtra("isRead", isReadChecked)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isReadChecked", isReadChecked)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.i(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}