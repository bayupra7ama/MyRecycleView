package com.example.myrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes : RecyclerView
    private val list =ArrayList<Hero>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //menetabkan fixxed size pada reyceycle view
        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)


        list.addAll(getHeroes())
        showRecycleList()
    }
    private fun getHeroes(): ArrayList<Hero>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices){
           val  hero  = Hero(dataName[i], dataDescription[i] ,dataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecycleList(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemCliced(data: Hero) {
                showSelectedData(data)
            }
        })
    }

    //untuk buat obtionMEnu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun showSelectedData(hero:Hero){
        Toast.makeText(this,"kamu memilih"+hero.name , Toast.LENGTH_LONG).show()
    }

    //agar menu bisa ditekan
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_grid->{
                rvHeroes.layoutManager = GridLayoutManager(this ,2)
            }
            R.id.action_list->{
                rvHeroes.layoutManager = LinearLayoutManager(this)
            }

        }

        return super.onOptionsItemSelected(item)
    }

}
