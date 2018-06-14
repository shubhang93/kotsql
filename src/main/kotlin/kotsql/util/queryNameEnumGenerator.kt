package kotsql.util

import java.io.File
import java.io.FileWriter

private fun dedupQueryNames(queryNames: List<String>): List<String> {
    return queryNames.toSet().toList()
}

fun generateQueryNameEnum(queryNames: List<String>) {
    if (queryNames.isEmpty()) return
    val uniqueQueryNames = dedupQueryNames(queryNames)
    val file: File = File("/home/shubhang/IdeaProjects/kotsql/sql/enumtest.kt")
    file.createNewFile()
    val fw: FileWriter = FileWriter(file)
    fw.write("""
         enum class QueryNames(value:String){
            PRODUCT("product")
         }
    """.trimIndent())
    fw.close()
}