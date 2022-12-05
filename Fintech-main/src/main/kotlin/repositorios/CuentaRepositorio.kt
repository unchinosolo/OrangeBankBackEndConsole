package repositorios

import Exceptions.CuentaInexistente
import entidades.Cuenta
import entidades.Usuario
import java.time.LocalDate
import java.time.Period

open class CuentaRepositorio {
    val cuentas = mutableListOf<Cuenta>()

    init {
        val cuenta1: Cuenta = Cuenta(1, "Luciano", "Jaime", 600.00, "2022-06-05", 0.00);
        val cuenta2: Cuenta = Cuenta(2, "Maria", "Iglesias", 80.00, "2021-06-15", 0.00);
        val cuenta3: Cuenta = Cuenta(3, "Ezequiel", "Torres", 30.00, "2022-09-18", 0.00);
        cuentas.add(cuenta1)
        cuentas.add(cuenta2)
        cuentas.add(cuenta3)
    }


    fun agregar(cuenta: Cuenta) = cuentas.add(cuenta)


    fun eliminar(cuenta: Cuenta) = cuentas.remove(cuenta)


    fun obtenerDatosDeCuenta(codigoCuenta: Int): String {
        var encontrada: Cuenta = Cuenta(0, "null", "null", 00.00, "null", 0.00);
        for (cuenta in cuentas) {
            if (cuenta.codigoCuenta == codigoCuenta) {
                encontrada = cuenta;
                break;
            }
        }

        return """
            Código de cuenta:${encontrada.codigoCuenta}
            Nombre y apellido: ${encontrada.nombre} ${encontrada.apellido}
            Saldo en pesos:${encontrada.dineroEnCuenta}
            Saldo en Bitcoins:${encontrada.cantidadBitcoins}
            Fecha de alta:${encontrada.fechaAlta}
            """.trimIndent();
    }

    fun obtenerPorCodigo(codigoCuenta: Int): Cuenta {
        var encontrada: Cuenta = Cuenta(0, "null", "null", 00.00, "null", 0.00);
        for (cuenta in cuentas) {
            if (cuenta.codigoCuenta == codigoCuenta) {
                encontrada = cuenta;
                break;
            }
        }
        return encontrada
    }


    fun buscar(nombre: String, apellido: String): List<Cuenta> {
        val filtrada = mutableListOf<Cuenta>()
        for (i in cuentas) {
            if (i.nombre.equals(nombre) && i.apellido.equals(apellido)) {
                filtrada.add(i)
                println("El usuario es: ${i.nombre} ${i.apellido} ")
                break
            }
        }
        return filtrada

    }
}

