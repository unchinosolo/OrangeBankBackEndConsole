import Exceptions.CuentaInexistente
import Exceptions.DineroInsuficienteException
import entidades.*
import repositorios.CompraRepositorio
import repositorios.CuentaRepositorio
import repositorios.UsuarioRepositorio
import java.time.LocalDate
import java.time.LocalTime

val fecha: LocalDate = LocalDate.now();
val horaActual = LocalTime.now();
var opcion: Int = 0;
fun main() {
    // ------------ FECHA Y HORA ACTUAL ---------------
    val fecha: LocalDate = LocalDate.now();
    val horaActual = LocalTime.now();
    // ---------- INSTANCIA DE OBJETOS Y VARIABLES  -------------------
    val cuentas: CuentaRepositorio = CuentaRepositorio();
    val usuarios: UsuarioRepositorio = UsuarioRepositorio();
    val compras: CompraRepositorio = CompraRepositorio();
    val criptodia: Criptodia = Criptodia("Criptodia");
    val criptomas: Criptomas = Criptomas("Criptomas");
    val carrecripto: Carrecripto = Carrecripto("Carrecripto");
    var usuarioEncontrado: Usuario = Usuario("null", "null", 0);
    var cuentaEncontrada: Cuenta = Cuenta(1, "null", "null", 0.00, "null", 0.00);

    var nickname: String;
    var password: String;
    var codCuenta: Int;
    var cuentaDatos: String = "";




    do { //-------------- WHILE 1  (INICIO DEL PROGRAMA) -------------------
        println("  ============        Bienvenido a Orange        ============== ")



        println("Ingrese Usuario")
        nickname = readLine()!!.toString();
        println("Ingrese Contraseña")
        password = readLine()!!.toString();
        try {
            do {//----------- WHILE 2 --------------------


                usuarioEncontrado = usuarios.iniciar(nickname, password);
                codCuenta = usuarioEncontrado.codigoCuenta;
                cuentaEncontrada = cuentas.obtenerPorCodigo(codCuenta);
                println(
                    """

Bienvenido/a ${cuentaEncontrada.nombre}                    Fecha: ${fecha}  Hora:${horaActual.hour}:${horaActual.minute}

Seleccionar operacion a realizar: 
1)Datos de cuenta
2)Comprar Bitcoin
3)Historial de compras
4)Cerrar Sesion               
                """.trimMargin()
                )
//----------------------------------- SELECCION DE OPERACION ----------------------------------------------------------
                opcion = readLine()!!.toInt();
                while (opcion !in 1..4) {
                    println(
                        """
Por favor ingrese una opcion válida:
1)Datos de cuenta
2)Comprar Bitcoin
3)Historial de compras
4)Cerrar Sesion 
                    """.trimIndent()
                    )
                    opcion = readLine()!!.toInt()
                }
                when (opcion) {// ---------(WHEN PADRE)
                    1 -> {
                        println(cuentas.obtenerDatosDeCuenta(codCuenta));
                    }
//---------------------------------- SELECCION DE EXCHANGE -------------------------------------------------------------
                    2 -> {
                        do {


                            println(
                                """
ELEGIR EXCHANGE PARA OPERAR:
1)Criptomas   
2)Criptodia   
3)Carrecripto                        
                    """.trimIndent()
                            )

                            opcion = readLine()!!.toInt()
                            when (opcion) {

                                1 -> {
                                    operacionExchange(criptomas, compras, cuentaEncontrada)

                                }
                                2 -> {
                                    operacionExchange(criptodia, compras, cuentaEncontrada)
                                }
                                3 -> {
                                    operacionExchange(carrecripto, compras, cuentaEncontrada)
                                }
                            }
                            println("Desea operar nuevamente?\n1)Seleccion de exchange\n2)Menu principal")
                            opcion = readLine()!!.toInt()
                            while (opcion != 1 && opcion != 2) {
                                println("Por favor ingrese una opcion correcta:\n1)Seleccion de exchange\n2)Menu principal")
                                opcion = readLine()!!.toInt()
                            }
                        } while (opcion != 2)
                    }//-----FIN DE CASO 2 (seleccion de exchange)
                    //--------------HISTORIAL DE CUENTA ------------
                    3->{compras.historialDeCompra(codCuenta)}
                    //----------------------------------------------
                }//------FIN DEL WHEN (PADRE)
            } while (opcion != 4)//-----------FIN DEL WHILE 2
        } catch (c: CuentaInexistente) {
            println("Cuenta Inexistente")
            opcion = 2;
        }
        println("Desea Ingresar con otro usuario?\n1)Iniciar Sesion   2)Salir de Orange")
        opcion = readLine()!!.toInt();
    } while (opcion != 2) // -------------------FIN DEL WHILE 1


}


//------------------------------------------- FUNCIONES DEL MAIN ---------------------------------------------------------


fun operacionExchange(e: Exchange, c: CompraRepositorio, cuenta: Cuenta) {
    //------- ATRIBUTOS DEL OBJETO COMPRA -------------
    var codCuenta = cuenta.codigoCuenta;
    var codCompra = 0;
    var fechaCompra = LocalDate.now()
    var horaCompra = LocalTime.now();
    var criptomoneda = "Bitcoin";
    var valorPagado = e.calcularComision(e.PRECIO);
    var valorAdquirido = 1.00;
    var bitcoins =0
    //------------------------------------------------
    do {
        println(menuExchange(e, cuenta, c))
        opcion = readLine()!!.toInt();
        try {
            if (opcion == 1 && validarSaldo(e, cuenta)) {
                c.agregar(
                    Compra(
                        codCuenta,
                        codCompra++,
                        fechaCompra,
                        horaCompra,
                        criptomoneda,
                        valorAdquirido,
                        valorPagado,
                    )
                )
                bitcoins++
                cuenta.dineroEnCuenta -= valorPagado
                cuenta.cantidadBitcoins = bitcoins.toDouble();
                // c.beneficio(cuenta, c.obtenerPorCodigo(codCompra))  !! Este metodo lo comentamos porque nos tiraba error en tiempo de ejecucion y no pudimos solucionarlo

                println("Operacion Realizada\nCodigo de compra:${codCompra}\n${valorAdquirido} Bitcoin")
            }
            while (opcion != 1 && opcion != 2) {
                println("Por favor ingresar una opcion correcta:\n1)Comprar\n2)Atras")
                opcion = readLine()!!.toInt()
            }
        } catch (d: DineroInsuficienteException) {
            println("Operacion rechazada---> Dinero Insuficiente")
            opcion = 2;
        }
    } while (opcion != 2)
}


fun menuExchange(objeto: Exchange, cuenta: Cuenta, compraRepositorio: CompraRepositorio): String {

    val str = """
------------------------------------------------------------------------------------------------------------------------
===================================== ${objeto.nombre.toUpperCase()} ===================================================================
 Fecha: ${fecha}  Hora:${horaActual.hour}:${horaActual.minute}    
 Cripto: Bitcoins
 Valor:${objeto.PRECIO}
 1)Comprar
 2)Atras
    """.trimIndent()

    return str;
}

//--------------- VALIDACIONES Y OTROS ------------------
fun validarSaldo(e: Exchange, c: Cuenta): Boolean {
    if (c.dineroEnCuenta >= e.PRECIO) {
        return true
    }
    throw DineroInsuficienteException()

}

