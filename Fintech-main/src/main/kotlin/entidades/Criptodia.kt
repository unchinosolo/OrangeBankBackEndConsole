package entidades

import java.time.LocalTime

class Criptodia(nombre: String) : Exchange(nombre) {


    //---------- COMISION -------------
    override fun calcularComision(monto: Double): Double {
        val hora1 = LocalTime.of(20, 0)
        val hora2 = LocalTime.of(23, 59)
        val horaActual = LocalTime.now();
        if (horaActual in hora1..hora2) {
            return monto * 1.01
        }
        return monto * 1.03

    }


}



