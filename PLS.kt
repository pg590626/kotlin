/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/* elabora il file delle pulsazioni estratto da Notify e ricava il valore*/
/* medio per ogni giorno da mezzanotte alle 6                            */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.io.File

data class PLS(val pls: Int, val data: String, val ora: String)

fun main() {
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* Dichiarazione delle variabili                                         */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    // nome dei file di input
    val filename = "\\D:\\Dati\\Allenamenti\\Salute\\CuoreAF.csv"
    val fileInp = File(filename)
    // nome dei file di output
    val (nam, ext) = filename.split(".")
    val fileOut = File("$nam.Mod.$ext")
    // liste dei valori
    val tmp1 = mutableListOf<PLS>()            // lista di TUTTE le misure HRV
    var tmp2 = mutableListOf<String>()         // lista di una SINGOLA misura
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* apre il file di input se esiste e lo elabora                          */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    if (fileInp.exists()) {
        // ogni riga diventa una lista di 4 item
        fileInp.forEachLine {
            tmp2 = it.split(";").toMutableList()
            // normalizza le date dei 1° 10 giorni aggiungendo uno 0 all'inizio
            if (tmp2[2][1] == ' ') {
                tmp2[2] = "0" + tmp2[2]
            }
            // salta la 1° riga (nome colonne)
            if (tmp2[0] != "Frequenza cardiaca") {
                // copia n° battiti, data e ora nella data class
                val tmp3 = PLS(tmp2[0].toInt(), tmp2[2], tmp2[3])
                // aggiunge la misura alla lista delle misure
                tmp1.add(tmp3)
            }
        }
    }
    else {
        println("Il file non esiste")
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* calcola la lista unica di tutte le date nel file                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    val days = mutableListOf<String>()
    var i = ""
    for (item in tmp1) {
        if (item.data != i) {
            days.add(item.data)
            i = item.data
        }
    }
    // for (item in days) println(item)

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* calcola l'HRV medio di ogni giorno                                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    for (day in days) {
        // selez. tutte le misurazioni del singolo giorno
        val giorno1 = tmp1.filter { it.data == day }
        // selez. tutte le misurazioni da 00:00 a 06:00
        val giorno2 = giorno1.filter { it.ora >= "00:00:00" && it.ora <= "05:59:00" }
        // estrapola il valore di PLS
        val giorno3 = giorno2.map {it.pls}
        // lo ordina, elimina i 3 min e max e calcola la media
        val giorno4 = giorno3.sorted()
        val giorno5 = giorno4.slice(3..giorno3.size -4)
        val giorno6 = giorno5.average().toInt()
        // visualizza il risultato
        println("$day - $giorno6")
        // salva il risultato nel file di output
        fileOut.appendText("$day - $giorno6\n")
    }
}