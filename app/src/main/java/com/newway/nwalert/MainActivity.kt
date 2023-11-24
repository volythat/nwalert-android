//package com.newway.nwalert
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.newway.nwalert.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnShow.setOnClickListener {
//            val dialog = NWAlertFragment.newInstance(listOf(
//                NWButton(title = "OK", isHighLight = true),
//                NWButton("Cancel")
//            ),
//                title = "Logout",
//                message = "Are you sure you want to logout from the application?")
//            dialog.show(supportFragmentManager)
//        }
//    }
//}