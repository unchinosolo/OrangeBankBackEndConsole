package repositorios

import entidades.Compra
import entidades.Cuenta
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.time.format.DateTimeFormatter

class CompraRepositorio() {
    val compra = mutableListOf<Compra>()
    var calcularBitcoinsComprados = 0;
    fun agregar(compra: Compra) {
        this.compra.add(compra)
    }

    fun eliminar(compra: Compra) {
        this.compra.remove(compra)
    }


    fun obtenerPorCodigo(codigoCompra: Int): Compra {
        var encontrada: Compra = Compra(0, 0, LocalDate.now(), LocalTime.now(), "null", 0.00, 0.00)
        for (c in compra) {
            if (c.codigoCompra == codigoCompra) {
                encontrada = c
            }
        }
        return encontrada
    }

    fun historialDeCompra(codigoCuenta: Int) {
        for (c in compra) {
            if (c.codigoCuenta == codigoCuenta) {
                println(


                    """
 *********************************************************************************** Ticket **********************************************************************************************************                       
      
      
       Cuenta nro: ${c.codigoCuenta}                                                          Fecha:${c.fechaCompra} Hora: ${c.horaCompra.hour}:${c.horaCompra.minute}
       Codigo compra: ${c.codigoCompra}
       
       Criptomoneda: ${c.criptomoneda}
       Valor adquirido: ${c.valorAdquirido}
       Valor pagado: ${c.valorPagado}
       
       
  ****************************************************************************************************************************************************************************************************
       
       
               """.trimIndent()
                )
            }
        }
    }

    //------------------- FUNCIONES AGREGADAS ------------------------
    fun beneficio(c: Cuenta, compra: Compra) {
        var cuenta: CuentaRepositorio = CuentaRepositorio();
        val fechaActual = LocalDate.now()
        val tiempoDeSocio = Period.between(CreateLocalDate(c.fechaAlta), fechaActual)
        val resultado = when (tiempoDeSocio.months) {
            in 1..3 -> (compra.valorPagado.times(0.05))
            in 3..12 -> (compra.valorPagado.times(0.03))
            else -> (compra.valorPagado.times(0.00))
        }
        c.dineroEnCuenta += resultado;
    }

    private fun CreateLocalDate(fechaAlta: String): LocalDate {
        return LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("yyyy-mm-dd"))
    }


}







