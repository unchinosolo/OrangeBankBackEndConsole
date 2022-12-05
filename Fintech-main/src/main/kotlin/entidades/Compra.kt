package entidades

import java.time.LocalDate
import java.time.LocalTime

data class Compra(
    val codigoCuenta: Int,
    val codigoCompra: Int,
    val fechaCompra: LocalDate,
    val horaCompra: LocalTime,
    val criptomoneda: String,
    val valorAdquirido: Double,
    val valorPagado: Double
    )


