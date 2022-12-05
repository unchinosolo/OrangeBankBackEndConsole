package entidades

interface CalculoMonto {
    fun calcularMonto(precio: Double, cantidad: Int, funcion: ((Double, Int) -> Double));
}