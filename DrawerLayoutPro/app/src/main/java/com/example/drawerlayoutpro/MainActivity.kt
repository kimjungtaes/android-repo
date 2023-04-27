package com.example.drawerlayoutpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.drawerlayoutpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1.액션바 대신 툴바로 대체
        setSupportActionBar(binding.toolbar)
        //2액션바 토글 버튼적용
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout, R.string.drawer_opne, R.string.drawer_close)
        //3.업버튼활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //4.토글 sync
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}