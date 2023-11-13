const val TAM_FIL = 10
const val TAM_COL = 5
const val PRICE = 70
const val OCUPADO = true
const val LIBRE = false
typealias AVION = Array<BooleanArray>


fun main(){
    val avion: AVION = Array(TAM_FIL){ BooleanArray(TAM_COL){LIBRE} }
    println("Bienvenido a Fly With Me")
    println("=========================")
    println()
    do{
        println("Para mostrar el manifiesto del avión, pulse 1")
        println("Para comprar un asiento, pulse 2")
        println("Para devolver su asiento, pulse 3")
        println("Para comprobar la recaudación, pulse 4")
        println("Para ver el listado de pasajeros, pulse 5")
        println("Para salir, pulse 6")
        println("Introduzca su opción: ")
        val input = readln().trim().toIntOrNull() ?: -1
        when(input){
            1 -> mostrarManifiesto(avion)
            2 -> comprarAsiento(avion)
            3 -> devolverAsiento(avion)
            4 -> comprobarRecaudacion()
            5 -> listadoPasajeros()
            6 -> println("Hasta luego")
            else -> println("Opción no válida")
        }
        println()
    }while(input != 6)

}

fun listadoPasajeros() {
    TODO("Not yet implemented")
}

fun comprobarRecaudacion() {
    TODO("Not yet implemented")
}

fun devolverAsiento(vuelo: AVION) {
    println("Para comprar su asiento, debemos comprobar el DNI")
    examinarDNI()
    comprobarValidedDeAsientoYAsignacionOcupada(vuelo)
}

fun comprobarDNI(dni: String): Boolean{
    val regex = Regex("[0-9]{8}[A-z]")
    if(!regex.matches(dni)){
        println("Formato de DNI no válido, introduzca el formato correcto")
        return false
    }
    return true
}

fun comprarAsiento(avion: AVION) {
    ComprobarAsientoLibre(avion)
    println("Para comprar su asiento, debemos comprobar el DNI")
    examinarDNI()
    comprobarValidedDeAsientoYAsignacionLibre(avion)
}

private fun comprobarValidedDeAsientoYAsignacionOcupada(avion: AVION) {  //TODO Posibilidad de comprar más de 1 billete a la vez
    var fil: Int = -1
    var col: Int = -1
    var isCorrect = true
    do {
        println("Elija la fila y la columna que desea (nº de fila - nº de columna, ejemplo: 2-4")
        val input = readln().trim().split("-").toTypedArray()
        if (input.size != 2) {
            println("No has introducido un asiento válido")
            isCorrect = false
        } else {
            fil = input[0].trim().toIntOrNull() ?: -1
            col = input[1].trim().toIntOrNull() ?: -1
            if (fil !in (1..TAM_FIL)) {
                println("Fila incorrecta, debe ser un valor entre 1 y $TAM_FIL")
                isCorrect = false
            }
            if (col !in (1..TAM_COL)) {
                println("Columna incorrecta, debe ser un valor entre 1 y $TAM_COL")
                isCorrect = false
            }
        }

    } while (!isCorrect)
    fil--
    col--
    if (avion[fil][col] == LIBRE) {
        println("No hay una billete asignado a este asiento")
    } else {
        avion[fil][col] = LIBRE
        println("Billete devuelto correctamente")
    }
    println()
}

private fun comprobarValidedDeAsientoYAsignacionLibre(avion: AVION) {  //TODO Posibilidad de comprar más de 1 billete a la vez
    var fil: Int = -1
    var col: Int = -1
    var isCorrect = true
    do {
        println("Elija la fila y la columna que desea (nº de fila - nº de columna, ejemplo: 2-4")
        val input = readln().trim().split("-").toTypedArray()
        if (input.size != 2) {
            println("No has introducido un asiento válido")
            isCorrect = false
        } else {
            fil = input[0].trim().toIntOrNull() ?: -1
            col = input[1].trim().toIntOrNull() ?: -1
            if (fil !in (1..TAM_FIL)) {
                println("Fila incorrecta, debe ser un valor entre 1 y $TAM_FIL")
                isCorrect = false
            }
            if (col !in (1..TAM_COL)) {
                println("Columna incorrecta, debe ser un valor entre 1 y $TAM_COL")
                isCorrect = false
            }
        }

    } while (!isCorrect)
    fil--
    col--
    avion[fil][col] = LIBRE
    println()
}

private fun examinarDNI() {
    do {
        println("Ingrese su DNI")
        val input = readln().trim()
        comprobarDNI(input)
    } while (!comprobarDNI(input))
}

private fun ComprobarAsientoLibre(avion: AVION) {
    if (!hayAsientoLibre(avion)) {
        println("El vuelo está completo, compruebe otro vuelo o mantengase a la espera de una posible cancelación de asiento.")
        return
    }
}

fun hayAsientoLibre(avion:AVION):Boolean{
    for(filas in avion.indices){
        for(columnas in avion[filas].indices){
            if(avion[filas][columnas]==LIBRE){
                return true
            }

        }
    }
    return false
}


fun mostrarManifiesto(avion: AVION) {
      for (filas in avion.indices){
          for (columnas in avion[filas].indices){
              if (avion[filas][columnas]==LIBRE){
                  print("|_|")
              }else{
                  print("|X|")
              }
          }
          println()
      }
    println()
}

