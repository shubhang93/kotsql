package kotsql

interface SqlServiceRunner {
    fun runQuery(queryName: String, argsMap: Map<String, Any>? = null): ArrayList<HashMap<String?, Any?>>
}