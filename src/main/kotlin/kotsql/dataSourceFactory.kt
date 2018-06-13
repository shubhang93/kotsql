package kotsql

import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.io.FileInputStream
import java.util.*
import javax.sql.DataSource

fun dataSourceFactory(propertiesFilePath: String): DataSource? {
    var ds: DataSource? = null
    val dataSourceProps = Properties()
    val fis = FileInputStream(propertiesFilePath)
    dataSourceProps.load(fis)


    try {
        ds = DriverManagerDataSource()
        ds.setDriverClassName(dataSourceProps.getProperty("MYSQL_DB_DRIVER_CLASS"))
        ds.url = dataSourceProps.getProperty("MYSQL_DB_URL")
        ds.username = dataSourceProps.getProperty("MYSQL_DB_USERNAME")
        ds.password = dataSourceProps.getProperty("MYSQL_DB_PASSWORD")

    } catch (ex: Exception) {
        println(ex)
    }

    return ds

}