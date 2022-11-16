package es.viu.actividad1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var textViewResultado: TextView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView = findViewById(R.id.textView)
        textView.text = getString(R.string.result)

        textViewResultado = findViewById(R.id.textViewResultado)
        var respuesta: String = intent.getStringExtra("sumaAlg").toString()
        textViewResultado.setText(respuesta)

        var cadena: String = getString(R.string.result) + " " + respuesta
        val buttonCompartir: Button = findViewById(R.id.buttonCompartir)
        buttonCompartir.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, cadena)
            startActivity(Intent.createChooser(intent, getString(R.string.share)))
        }
    }
}