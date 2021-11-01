package ru.sber.rdbms

import java.sql.DriverManager

class TransferPessimisticLock {
    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "123"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        connection.use { conn ->
            val autoCommit = conn.autoCommit
            try {
                conn.autoCommit = false
                val prepareStatement1 =
                    conn.prepareStatement("select * from account1 where id = $accountId1 for update")
                prepareStatement1.executeQuery().use { statement ->
                    statement.next()
                    if (statement.getInt("amount") - amount < 0)
                        throw CustomException("Недостаточно средств")
                }
                val prepareStatement2 =
                    conn.prepareStatement("update account1 set amount = amount - $amount where id = $accountId1")
                prepareStatement2.executeUpdate()

                val prepareStatement3 =
                    conn.prepareStatement("select * from account1 where id = $accountId2 for update")
                prepareStatement3.executeQuery()
                val prepareStatement4 =
                    conn.prepareStatement("update account1 set amount = amount + $amount where id = $accountId2")
                prepareStatement4.executeUpdate()
                conn.commit()
            } catch (exception: Exception) {
                println(exception.message)
                conn.rollback()
            } finally {
                conn.autoCommit = autoCommit
            }
        }
    }
}

fun main() {
    TransferOptimisticLock().transfer(2,1,100)
}