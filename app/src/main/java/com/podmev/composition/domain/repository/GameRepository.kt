package com.podmev.composition.domain.repository

import com.podmev.composition.domain.entity.GameSettings
import com.podmev.composition.domain.entity.Level
import com.podmev.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}
