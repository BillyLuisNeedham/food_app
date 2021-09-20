package com.example.sqldelightprototype.presentation.mappers

import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.util.ExpirationMessage
import java.lang.IllegalStateException
import javax.inject.Inject

class FoodUiMapper @Inject constructor(
    private val expirationMessage: ExpirationMessage
) {

    companion object {
        private const val TAG = "FoodUiMapper"
    }

    fun Food.toFoodUi(): FoodUi {
        val expirationMessage =
            expirationMessage.getMessageForTimeStamp(timeStampInMilliSeconds = expirationDate)

        return FoodUi(
            id = id ?: throw IllegalStateException("$TAG: food.id is null and must not be"),
            name = name,
            quantity = quantity,
            expirationMessage = expirationMessage
        )
    }
}