package com.billyluisneedham.foodapp.presentation.mappers

import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.presentation.models.FoodUi
import com.billyluisneedham.foodapp.presentation.util.expirationmessage.ExpirationMessage
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
            expirationMessage.getMessageForTimeStamp(
                timeStampInMilliSeconds = expirationDate
            )

        return FoodUi(
            id = id ?: throw IllegalStateException("$TAG: food.id is null and must not be"),
            name = name,
            quantity = quantity,
            expirationDate = expirationDate,
            expirationMessage = expirationMessage
        )
    }

    fun FoodUi.toFood() = Food(
        id = id,
        name = name,
        quantity = quantity,
        expirationDate = expirationDate
    )
}
