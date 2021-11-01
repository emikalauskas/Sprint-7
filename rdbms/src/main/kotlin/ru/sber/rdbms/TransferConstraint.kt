package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferConstraint {
    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "123"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        connection.use { conn ->
            try {
                val prepareStatement1 =
                    conn.prepareStatement("alter table account1 add constraint chk_amount check (amount>=0)")
                prepareStatement1.use { statement ->
                    statement.execute()
                }
                val prepareStatement2 =
                    conn.prepareStatement("update account1 set amount = amount - $amount where id = $accountId1")
                prepareStatement2.use { statement ->
                    statement.executeUpdate()
                }
                val prepareStatement3 =
                    conn.prepareStatement("update account1 set amount = amount + $amount where id = $accountId2")
                prepareStatement3.use { statement ->
                    statement.executeUpdate()
                }
            } catch (exception: SQLException) {
                println(exception.message)
            } finally {
                val prepareStatement4 =
                    conn.prepareStatement("alter table account1 drop constraint chk_amount")
                prepareStatement4.use { statement ->
                    statement.execute()
                }
            }
        }
    }
}

fun main() {
    TransferConstraint().transfer(1, 2, 100)
    TransferConstraint().transfer(2, 1, 100)
}