package io.craigmiller160.database

import kotlinx.coroutines.CoroutineDispatcher
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.context.GlobalContext
import org.koin.core.qualifier.named

suspend fun <T> appTransaction(
    db: Database? = null,
    transactionIsolation: Int? = null,
    statement: suspend Transaction.() -> T
): T {
  val postgresPoolDispatcher = GlobalContext.get().get<CoroutineDispatcher>(named("postgresPool"))
  return newSuspendedTransaction(postgresPoolDispatcher, db, transactionIsolation, statement)
}
