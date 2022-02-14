package com.billyluisneedham.foodapp.data.repositories

import android.util.Log
import com.billyluisneedham.foodapp.data.localdatasource.UserLocalDataSource
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.domain.repositories.UserRepository
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    companion object {
        private const val TAG = "UserRepositoryImpl"
    }

    override fun getAllUsers(): Flow<ResultOf<List<User>>> =
        try {
            userLocalDataSource.getAllUsers().mapNotNull {
                ResultOf.Success(it)
            }
        } catch (e: Exception) {
            Log.e(TAG, "exception within getAllUsers: $e")
            flow { emit(ResultOf.Error(exception = e)) }
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

    override suspend fun deleteUser(user: User): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                userLocalDataSource.delete(user = user)
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteUser: $e")
                ResultOf.Error(exception = e)
            }
        }
}
