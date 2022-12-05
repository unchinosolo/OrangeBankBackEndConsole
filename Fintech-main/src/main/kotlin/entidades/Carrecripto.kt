package entidades

import java.time.LocalDate

class Carrecripto(nombre: String) : Exchange(nombre) {


    //---------- COMISION -------------
    override fun calcularComision(monto: Double): Double {
        val diaSabado = "SATURDAY"
        val diaDomingo = "SUNDAY"
        val fechaActual = LocalDate.now()
        if (fechaActual.dayOfWeek.name == diaSabado || fechaActual.dayOfWeek.name == diaDomingo) {
            return monto * 1.03
        } else {
            return monto * 1.075
        }
    }
}

