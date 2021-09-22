package com.example.sqldelightprototype.data.repositories

import android.util.Log
import com.example.sqldelightprototype.data.localdatasource.UserLocalDataSource
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    companion object {
        private const val TAG = "UserRepositoryImpl"
    }

    override suspend fun addUser(user: User): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                userLocalDataSource.add(user = user)
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within addUser: $e")
                ResultOf.Error(exception = e)
            }
        }
}