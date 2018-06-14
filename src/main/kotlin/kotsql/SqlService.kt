package kotsql

import kotsql.util.buildQueryMap

class SqlService(sqlFilesPath: String) {

    private var queryMap: Map<String, String> = mapOf()
    private val sqlExecutorService: SqlExecutorService = SqlExecutorService()

    init {
        queryMap = buildQueryMap(filePath = sqlFilesPath)
    }

    fun executeSql(queryName: String, argsMap: Map<String, Any>? = null): ArrayList<HashMap<String, Any?>?> {
        val queryStmt = queryMap[queryName] ?: throw Exception("Query --> $queryName Not Found")
        return sqlExecutorService.executeQuery(queryStmt, argsMap)
    }
}