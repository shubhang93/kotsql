package kotsql

fun main(args: Array<String>) {
    val sqlService: SqlService = SqlService("/home/shubhang/IdeaProjects/kotsql/sql")
    val res = sqlService.executeSql(queryName = "product", argsMap = mapOf(Pair("code", "p233")))
    println(res)
}