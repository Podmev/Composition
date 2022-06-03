package com.podmev.composition.domain.usecase

import com.podmev.composition.domain.entity.GameSettings
import com.podmev.composition.domain.entity.Level
import com.podmev.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}