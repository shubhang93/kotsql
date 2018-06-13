package kotsql

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.Connection
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource


class MySqlConnector(propertiesFilePath: String = "/home/shubhang/IdeaProjects/sqlService/src/db.properties") {

    private var dsConnection: Connection? = null
    private val namedParameterJdbcTemplate:NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(dataSourceFactory(propertiesFilePath))

    init {
        val ds: DataSource? = dataSourceFactory(propertiesFilePath)
        dsConnection = ds?.connection

    }


    fun executeStatement(sqlStatement: String): ArrayList<HashMap<String?, Any?>> {
        var resultSet: ResultSet? = null
        var resultSetMetaData: ResultSetMetaData? = null
        val resultList: ArrayList<HashMap<String?, Any?>> = arrayListOf()
        var columnCount: Int? = 0
        try {
            val stmt = dsConnection?.createStatement()
            resultSet = stmt?.executeQuery(sqlStatement)
            resultSetMetaData = resultSet?.metaData
            columnCount = resultSetMetaData?.columnCount

        } catch (ex: SQLException) {
            println(ex)
        }


        if (resultSet !== null) {
            while (resultSet.next()) {
                val rowMap = hashMapOf<String?, Any?>()
                if (columnCount !== null) {
                    for (i in 1..columnCount) {
                        rowMap[resultSetMetaData?.getColumnName(i)] = resultSet.getObject(i)
                    }
                }
                resultList.add(rowMap)
            }
        }
        return resultList
    }

}