package kotsql

import kotsql.util.buildQueryMap
import kotsql.util.doesQueryContainArgs
import kotsql.util.paramReplacer
import java.util.*

class SqlService(sqlFilesPath: String) : SqlServiceRunner {

    private var queryMap: Map<String, String> = mapOf()
    private val connection: MySqlConnector = MySqlConnector()

    init {
        queryMap = buildQueryMap(filePath = sqlFilesPath)
    }

    private fun getExecutableQueryStatement(queryStatement: String, argsMap: Map<String, Any>?): String {

        if (argsMap == null && doesQueryContainArgs(queryStatement)) {
            throw Exception("Please Pass arguments to ---> $queryStatement")
        }

        return when (argsMap) {
            is Map<String, Any> -> paramReplacer(queryStatement, argsMap)
            else -> queryStatement
        }

    }


    override fun runQuery(queryName: String, argsMap: Map<String, Any>?): ArrayList<HashMap<String?, Any?>> {

        val queryStmt: String? = queryMap[queryName]

        if (queryStmt === null) {
            throw Exception("$queryName not found")
        }

        val executableQueryStatement: String = getExecutableQueryStatement(queryStmt, argsMap)
        return connection.executeStatement(executableQueryStatement)
    }
}