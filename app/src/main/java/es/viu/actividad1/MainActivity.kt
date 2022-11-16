package es.viu.actividad1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    var sumaAlg: Double = 0.0
    var operacion: Int = 0
    var segundo: Double = 0.0
    private lateinit var textViewNumero: TextView
    private lateinit var textViewHistorial: TextView
    private lateinit var textViewOperador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewNumero = findViewById(R.id.textViewNumero)
        textViewHistorial = findViewById(R.id.textViewHistorial)
        textViewOperador = findViewById(R.id.textViewOperador)
        val buttonC: Button = findViewById(R.id.buttonC)
        buttonC.setOnClickListener{
            limpiar()
        }
        val buttonIgual: Button = findViewById(R.id.buttonIgual)
        buttonIgual.setOnClickListener{
            if (textViewNumero.text.toString().isEmpty()){
                textViewNumero.text = "0"
            }
           segundo = textViewNumero.text.toString().toDouble()
            if (operacion != 0) {
                when (operacion) {
                    1 -> sumaAlg = sumaAlg + segundo
                    2 -> sumaAlg = sumaAlg - segundo
                }
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("sumaAlg", sumaAlg.toString())
                startActivity(intent)
                limpiar()
            }
        }
    }

    fun verNumero(view: View) {
        var cadena:String = textViewNumero.text.toString()
        when(view.id){
            R.id.button0 -> textViewNumero.setText(cadena + "0")
            R.id.button1 -> textViewNumero.setText(cadena + "1")
            R.id.button2 -> textViewNumero.setText(cadena + "2")
            R.id.button3 -> textViewNumero.setText(cadena + "3")
            R.id.button4 -> textViewNumero.setText(cadena + "4")
            R.id.button5 -> textViewNumero.setText(cadena + "5")
            R.id.button6 -> textViewNumero.setText(cadena + "6")
            R.id.button7 -> textViewNumero.setText(cadena + "7")
            R.id.button8 -> textViewNumero.setText(cadena + "8")
            R.id.button9 -> textViewNumero.setText(cadena + "9")
            R.id.buttonPunto -> {
                if (!cadena.contains(".")) {
                    if(cadena.isEmpty() or cadena.contains("0")){
                        cadena = "0"
                    }
                    textViewNumero.setText(cadena + ".")
                }
            }
        }
    }

    fun sumaAlgebraica(view: View){
        if (operacion == 0 && textViewNumero.text.toString().isEmpty()){
            textViewNumero.text = "0"
        }
        var primero_texto: String = textViewNumero.text.toString()
        textViewNumero.setText("")
        when(view.id) {
            R.id.buttonSuma -> {
                textViewHistorial.setText(primero_texto)
                textViewOperador.setText("+")
                if (operacion == 2 && textViewNumero.text.toString().isEmpty()) {
                    sumaAlg = sumaAlg - textViewHistorial.text.toString().toDouble()
                }else {
                    sumaAlg = sumaAlg + textViewHistorial.text.toString().toDouble()
                }
                operacion = 1
            }
            R.id.buttonResta -> {
                textViewHistorial.setText(primero_texto)
                var historial:String = textViewHistorial.text.toString()
                textViewOperador.setText("-")
                if (operacion == 1){
                    sumaAlg = sumaAlg + textViewHistorial.text.toString().toDouble()
                    textViewNumero.setText("")
                    textViewHistorial.text = "0"
                }
                if (operacion != 0 && textViewNumero.text.toString().isEmpty()) {
                    sumaAlg = sumaAlg - textViewHistorial.text.toString().toDouble()
                }else{
                    sumaAlg = textViewHistorial.text.toString().toDouble()
                }
                textViewHistorial.text = historial
                operacion = 2
            }
        }
    }

    fun limpiar(){
        textViewNumero.setText("")
        textViewHistorial.setText("")
        textViewOperador.setText("")
        operacion = 0
        sumaAlg = 0.0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("historial", textViewHistorial.text.toString())
        outState.putString("operador", textViewOperador.text.toString())
        outState.putString("numero", textViewNumero.text.toString())
        outState.putInt("operacion", operacion)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewHistorial.text = savedInstanceState.getString("historial")
        textViewOperador.text = savedInstanceState.getString("operador")
        textViewNumero.text = savedInstanceState.getString("numero")
        operacion = savedInstanceState.getInt("operacion")
    }
}