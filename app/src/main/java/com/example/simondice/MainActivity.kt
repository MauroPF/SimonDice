package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    val miView by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Botones.
        val botonVerde: Button = findViewById(R.id.verde)
        val botonRojo: Button = findViewById(R.id.rojo)
        val botonAmarillo: Button = findViewById(R.id.amarillo)
        val botonAzul: Button = findViewById(R.id.azul)
        val botonStart: Button = findViewById(R.id.start)

        /* Esta es la función para cambiar el color de los botones cuando salen en la secuencia
        a seguir, pero no he logrado que acabe de funcionar. */
        fun cambiarColorBoton() {
            for (num in miView.listaReto) {
                if (num == 0) {
                    botonVerde.setPressed(true)
                    Thread.sleep(2000)
                    botonVerde.setPressed(false)
                }
                else if (num == 1) {
                    botonRojo.setPressed(true)
                    Thread.sleep(2000)
                    botonRojo.setPressed(false)
                }
                else if (num == 2) {
                    botonAmarillo.setPressed(true)
                    Thread.sleep(2000)
                    botonAmarillo.setPressed(false)
                }
                else if (num == 3) {
                    botonAzul.setPressed(true)
                    Thread.sleep(2000)
                    botonAzul.setPressed(false)
                }
            }
        }

        /* Función para que si la secuencia a imitar y la secuencia del usuario no son iguales
        se deshabiliten los botones y no se puedan presionar hasta que empiece de nuevo el juego. */
        fun deshabilitarBtns() {
            botonVerde.setEnabled(false)
            botonAmarillo.setEnabled(false)
            botonAzul.setEnabled(false)
            botonRojo.setEnabled(false)
        }


        //Función para que se vuelvan a habilitar los botones, la utilizamos en el listener START.
        fun habilitarBtns() {
            botonVerde.setEnabled(true)
            botonAmarillo.setEnabled(true)
            botonAzul.setEnabled(true)
            botonRojo.setEnabled(true)
        }

        //Listener de botón Start.
        botonStart.setOnClickListener {
            habilitarBtns()
            miView.crearListaReto()
            cambiarColorBoton()
        }

        //Listener de botón verde.
        botonVerde.setOnClickListener {
            val b = miView.guardarSecUsuario(0)
            //Comparamos si el nº de pulsaciones del usuario coincide con la secuencia a seguir.
            if (miView.contIntera == miView.listaReto.size) {
                if (miView.compararListas()) {
                    Toast.makeText(applicationContext,"Has acertado, continua!", Toast.LENGTH_SHORT).show()
                    cambiarColorBoton()
                } else {
                    Toast.makeText(applicationContext,"Has fallado, vuelve a intentarlo!", Toast.LENGTH_SHORT).show()
                    deshabilitarBtns()
                }
            }
        }

        //Listener de botón rojo.
        botonRojo.setOnClickListener {
            miView.guardarSecUsuario(1)
            //Comparamos si el nº de pulsaciones del usuario coincide con la secuencia a seguir.
            if (miView.contIntera == miView.listaReto.size) {
                if (miView.compararListas()) {
                    Toast.makeText(applicationContext,"Has acertado, continua!", Toast.LENGTH_SHORT).show()
                    cambiarColorBoton()
                } else {
                    Toast.makeText(applicationContext,"Has fallado, vuelve a intentarlo!", Toast.LENGTH_SHORT).show()
                    deshabilitarBtns()
                }
            }
        }

        //Listener de botón amarillo.
        botonAmarillo.setOnClickListener {
            miView.guardarSecUsuario(2)
            //Comparamos si el nº de pulsaciones del usuario coincide con la secuencia a seguir.
            if (miView.contIntera == miView.listaReto.size) {
                if (miView.compararListas()) {
                    Toast.makeText(applicationContext,"Has acertado, continua!", Toast.LENGTH_SHORT).show()
                    cambiarColorBoton()
                } else {
                    Toast.makeText(applicationContext,"Has fallado, vuelve a intentarlo!", Toast.LENGTH_SHORT).show()
                    deshabilitarBtns()
                }
            }
        }

        //Listener de botón azul.
        botonAzul.setOnClickListener {
            miView.guardarSecUsuario(3)
            //Comparamos si el nº de pulsaciones del usuario coincide con la secuencia a seguir.
            if (miView.contIntera == miView.listaReto.size) {
                if (miView.compararListas()) {
                    Toast.makeText(applicationContext,"Has acertado, continua!", Toast.LENGTH_SHORT).show()
                    cambiarColorBoton()
                } else {
                    Toast.makeText(applicationContext,"Has fallado, vuelve a intentarlo!", Toast.LENGTH_SHORT).show()
                    deshabilitarBtns()
                }
            }
        }

    }

}