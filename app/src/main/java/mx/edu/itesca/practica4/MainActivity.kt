package mx.edu.itesca.practica4


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pesoK = findViewById<EditText>(R.id.weight)
        val alturaE = findViewById<EditText>(R.id.height)
        val calcular = findViewById<Button>(R.id.calcular)
        val imc = findViewById<TextView>(R.id.imc)
        val rango = findViewById<TextView>(R.id.range)

        fun calcularIMC(altura: Double, peso: Double): Double {
            return peso / (altura * altura)
        }

        calcular.setOnClickListener {
            var peso: Double
            var altura: Double

            try {
                peso = pesoK.text.toString().toDouble()
                altura = alturaE.text.toString().toDouble()
            } catch (e: Exception) {
                imc.text = "Se debe ingresar valores reales"
                rango.text = ""
                rango.setBackgroundResource(android.R.color.transparent)
                return@setOnClickListener
            }

            val resultado = calcularIMC(altura, peso)
            val formattedNumber = "%.2f".format(resultado)
            imc.text = "IMC: $formattedNumber"

            val salud: String
            val colorRes: Int

            when {
                resultado < 18.5 -> {
                    salud = "Bajo peso"
                    colorRes = R.color.colorRed
                }
                resultado in 18.5..24.9 -> {
                    salud = "Saludable"
                    colorRes = R.color.colorGreenish
                }
                resultado in 25.0..29.9 -> {
                    salud = "Sobrepeso"
                    colorRes = R.color.colorYellow
                }
                resultado in 30.0..34.9 -> {
                    salud = "Obesidad Grado 1"
                    colorRes = R.color.colorOrange
                }
                resultado in 35.0..39.9 -> {
                    salud = "Obesidad Grado 2"
                    colorRes = R.color.colorBrown
                }
                resultado >= 40 -> {
                    salud = "Obesidad Grado 3"
                    colorRes = R.color.colorRed
                }
                else -> {
                    salud = "Error"
                    colorRes = android.R.color.transparent
                }
            }

            rango.setBackgroundResource(colorRes)
            rango.text = salud
        }
    }
}
