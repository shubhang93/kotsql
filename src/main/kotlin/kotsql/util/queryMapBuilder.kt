package kotsql.util

import java.io.File

private fun getFiles(path: String): List<File> {
    return File(path).walk().map { it }.toList()

}

private fun getRawQueryList(filePath: String): List<List<String>> {
    val fileList = getFiles(filePath).drop(1)
    return fileList.map { it -> it.bufferedReader().readLines() }
}

private fun getQueriesAsString(filePath: String): String {
    val rawQueries = getRawQueryList(filePath)

    return rawQueries.flatMap { it -> it.map { it } }.joinToString(" ")
}

private fun getDuplicateQueries(queryList: List<List<String>>): List<String> {
    val queryNames = queryList.map { it.first() }
    return queryNames
            .groupBy { it: String -> it }
            .filter { it -> it.value.size > 1 }
            .keys
            .toList()
}


fun buildQueryMap(filePath: String): Map<String, String> {
    val queryList = getQueriesAsString(filePath)
            .split(Regex("-- name: *"))
            .filter { it -> it != "" }
            .map { it -> it.trim().split(" ") }

    val duplicateQueries = getDuplicateQueries(queryList)
    if (duplicateQueries.size > 1) {
        throw Exception("Duplicate Queries found ---> \n $duplicateQueries")
    }

    return queryList.map { it.first() to it.drop(1).joinToString(" ") }.toMap()
}