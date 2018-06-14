package kotsql

import kotsql.util.resultSetTransformer
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.ResultSet
import javax.sql.DataSource


internal class SqlExecutorService(propertiesFilePath: String = "/home/shubhang/IdeaProjects/sqlService/src/db.properties") {
    private val ds: DataSource = dataSourceFactory(propertiesFilePath)
            ?: throw Exception("Failed to Initialize Data Source")

    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(ds)

    fun executeQuery(query: String, params: Map<String, Any?>?): ArrayList<HashMap<String, Any?>?> {
        val resultList = namedParameterJdbcTemplate.execute(query, params, {
            val rs: ResultSet = it.executeQuery()
            val resultSetMetaData = rs.metaData
            return@execute resultSetTransformer(rs, resultSetMetaData)
        })
        return resultList
    }

}