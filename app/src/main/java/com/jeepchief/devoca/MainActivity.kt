package com.jeepchief.devoca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jeepchief.devoca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            navController = findNavController(R.id.nav_host_fragment)
        }
    }

//    private var time : Long = 0
//    override fun onBackPressed() { //뒤로가기 클릭 시 종료 메소드
//        supportFragmentManager.fragments.forEach { fragment ->
//            if((fragment is MainFragment).not())
//                return
//        }
//        if(System.currentTimeMillis() - time >= 2000) {
//            time = System.currentTimeMillis()
//            Toast.makeText(this@MainActivity, "한번 더 누르면 종료합니다", Toast.LENGTH_SHORT).show()
//        }
//        else if(System.currentTimeMillis() - time < 2000) {
//            this.finishAffinity()
//        }
//    }
}