package kotsql.util

import java.sql.ResultSet
import java.sql.ResultSetMetaData

fun resultSetTransformer(resultSet: ResultSet, resultSetMetaData: ResultSetMetaData): ArrayList<HashMap<String, Any?>?> {
    val resultList = arrayListOf<HashMap<String, Any?>?>()
    val columnCount = resultSetMetaData.columnCount
    while (resultSet.next()) {
        val rowMap = hashMapOf<String, Any?>()
        for (i in 1..columnCount)
            rowMap[resultSetMetaData.getColumnName(i)] = resultSet.getObject(i)

        resultList.add(rowMap)
    }
    return resultList
}