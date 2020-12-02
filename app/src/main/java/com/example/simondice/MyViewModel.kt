package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MyViewModel : ViewModel() {
    //Etiqueta del log.
    private val TAG_LOG: String = "Mensaje ViewModel"

    //Lista aleatoria que el jugador tiene que imitar.
    val listaReto = mutableListOf<Int>()
    //Lista donde se almacenan los botones presionados por el usuario.
    val secUsuario = mutableListOf<Int>()

    //LiveData donde vamos a almacenar listaReto para el observer.
    val listaRetoRonda = MutableLiveData<MutableList<Int>>()
    //LiveData donde vamos a almacenar secUsuario para el observer.
    val secUsuarioRonda = MutableLiveData<MutableList<Int>>()

    //Contador de interacciones del usuario.
    var contIntera = 0

    val msjBoton = MutableLiveData<String>()

    //Con init inicializamos las variables al instanciarlas.
    init {
        listaRetoRonda
        msjBoton.value = "Start"
    }

    //Con esta funcion creamos la lista que el usuario tiene que imitar
    fun crearListaReto(){
        //Nos aseguramos de que la listaReto está vacía haciendo un removeAll
        listaReto.removeAll(listaReto)
        //Añadimos un primer valor a listaReto.
        añadirValorReto()
        listaRetoRonda.value = listaReto
    }

    //Con esta función añadimos un valor aleatorio a listaReto
    fun añadirValorReto(){
        listaReto.add(Random.nextInt(0,4))
        listaRetoRonda.postValue(listaReto)
        /* Reiniciamos el contador de interacciones del usuario porque se da por hecho que el
        juego acaba de empezar o la secuencia ha sido acertada. */
        contIntera = 0
        //Mostramos en el Logcat la listaReto
        Log.d(TAG_LOG, "Lista del Reto: " + listaReto.toString())
    }

    //Con esta función añadimos el color presionado por el usuario a secUsuario.
    fun guardarSecUsuario(color: Int){
        secUsuario.add(color)
        secUsuarioRonda.postValue(secUsuario)
        //Sumamos uno al contador de interacciones del usuario.
        contIntera++
    }

    /* Ahora vamos a comparar las listas, si las listas coinciden añadimos un nuevo valor a listaReto,
     devolvemos un true para que se utilice en un condicional que hay en el MainActivity.kt y nos
     diga que continua jugando.
     Si falla reiniciamos la secuencia y devolvemos un false que se usará en el condicional del que
     hablábamos antes y le dirá al jugador que ha fallado. */
    fun compararListas(): Boolean {
        if (listaReto == secUsuario) {
            añadirValorReto()
            //Reiniciamos la secUsuario para que repita todo desde el inicio de listaReto,
            secUsuario.removeAll(secUsuario)
            return true
        }
        else{
            //Reiniciamos la secUsuario para que repita todo desde el inicio de listaReto,
            secUsuario.removeAll(secUsuario)
            return false
        }

    }

    //Aquí vamos a cambiar el mensaje con la función que está debajo de está, creada en MisCorutinas.kt
    fun salidaLog(){
        CoroutineScope(Dispatchers.Main).launch {
            suspendFun("Start")
            //Con delay hacemos que espere 2s (2000ms) y luego se cambie el mensaje
            delay(2000)
            suspendFun("Stop")
        }
    }

    //Función que añadimos desde MisCorutinas.kt
    private fun suspendFun(msg: String){
        msjBoton.value = msg
        Log.d(TAG_LOG, msg)
    }
}