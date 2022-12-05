package entidades

import java.time.LocalDate

data class Cuenta(
    val codigoCuenta: Int,
    val nombre: String,
    val apellido: String,
    var dineroEnCuenta: Double,
    val fechaAlta: String,
    var cantidadBitcoins: Double
)


