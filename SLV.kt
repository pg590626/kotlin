/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/* elabora il file Sigma .slv e lo riduce ad 1/5                         */
/*                                                                       */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.io.File

data class PLS(val pls: Int, val data: String, val ora: String)

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Manca il nome del file !")
        return
    }
    val filename = args[0]
    println("File: $filename")
    println("-----------   --")
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* Dichiarazione delle variabili                                         */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    // nome dei file di input
    val fileInp = File(filename)
    // nome dei file di output
    val (nam, ext) = filename.split(".")
    val fileOut = File("$nam.Mod.$ext")
    if (fileOut.exists()) {
        fileOut.delete()
    }
    // liste dei valori
    val tmp1 = mutableListOf<PLS>()            // lista di TUTTE le misure HRV
    var tmp2 = mutableListOf<String>()         // lista di una SINGOLA misura
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* apre il file di input se esiste e lo elabora                          */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    if (fileInp.exists()) {
        var cnt = 1
        // ogni riga diventa una lista di 4 item
        fileInp.forEachLine {
            if ((!it.startsWith("    <Entry")) ||
                (it.startsWith("    <Entry") && cnt == 5)
            ) {
                fileOut.appendText(it)
            }
            if (it.startsWith("    <Entry")) {
                if (cnt < 5) {
                    cnt += 1
                } else {
                    cnt = 1
                }
            }
        }
    } else {
        println("Il file non esiste")
    }
}