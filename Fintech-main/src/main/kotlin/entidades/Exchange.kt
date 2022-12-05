package entidades

abstract class Exchange(var nombre: String, val PRECIO: Double=50.00) {

   abstract fun calcularComision(monto:Double):Double



}