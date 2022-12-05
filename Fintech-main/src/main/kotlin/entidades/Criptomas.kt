package entidades

class Criptomas(nombre: String) : Exchange(nombre) {

    //---------- COMISION -------------
    override fun calcularComision(monto: Double): Double {
        var total = 0.00;
        total=monto * 1.02
        return total;
    }

}

