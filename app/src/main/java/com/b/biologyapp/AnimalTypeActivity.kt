package com.b.biologyapp

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException


class AnimalTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //Bundle является важным инструментом в архитектуре Android-приложений на Kotlin, который позволяет передавать данные между компонентами.
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // устанавливаем полноэкраннный режим
        setContentView(R.layout.activity_animal_type)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())//Экземпляр Insets содержит четыре целых смещения, которые описывают изменения четырех краев прямоугольника.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val animalType: AnimalType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("animalType", AnimalType::class.java) as AnimalType
        } else {
            intent.getSerializableExtra("animalType") as AnimalType
        }

        val tvName: TextView = findViewById(R.id.tvName)
        val imv: ImageView = findViewById(R.id.imv)
        val tvInterestingFact: TextView = findViewById(R.id.tvInterestingFact)

        tvName.text = animalType.name
        tvInterestingFact.text = animalType.interestingFact

        try {
            val ims = assets.open(animalType.image)
            val d = Drawable.createFromStream(ims, null)
            imv.setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            return
        }
    }
}