package com.b.biologyapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val animalTypes = arrayListOf<AnimalType>(
            AnimalType("Кабан", "kaban.png", "Кабан - всеядное животное (питается растительной и животной пищей). Основу рациона составляют корневища и луковицы растений, семена, фрукты, опавшие листья, орехи, желуди, ягоды. Может разорять гнезда и поедать яйца, также не брезгует мелкими грызунами, ест мышей, ящериц, червей и змей. Не откажется от падали. Особое лакомство кабанов – крапива, она помогает быстро восстановить запас витаминов после зимы."),
            AnimalType("Волк", "wolf.jpg", " Волки умнее собак. Исследования показывают, что уровень интеллекта у волков выше, чем у его одомашненных родственников. На это указывает и то обстоятельство, что объем мозга у волков на 30% больше, чем у собак"),
            AnimalType("Бобр", "bobr.jpg", "В бобровой струе присутствует вещество, содержащее аспирин, т.е. это вещество помогает от головных болей. Также запах этой струи очень напоминает дерево и кожу, вследствие чего востребован в производстве элитной парфюмерии."),
        )

        val rvAnimalTypes: RecyclerView = findViewById(R.id.rvAnimalTypes)
        val adapter = AnimalTypeAdapter(animalTypes)
        val layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvAnimalTypes.adapter = adapter
        rvAnimalTypes.layoutManager = layoutManager

        adapter.onItemClick = { animalType ->
            val intent = Intent(this@MainActivity, AnimalTypeActivity::class.java)
            intent.putExtra("animalType", animalType)
            startActivity(intent)
        }
    }
}