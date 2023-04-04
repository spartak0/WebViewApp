package com.example.webviewapp.domain.mapper

import com.example.webviewapp.utils.Mapper
import com.example.webviewapp.data.database.entity.LeaderboardEntity
import com.example.webviewapp.domain.model.LeaderboardData

class LeaderboardMapper : Mapper<LeaderboardData, LeaderboardEntity> {
    override fun entityToDomain(entity: LeaderboardEntity): LeaderboardData {
        return LeaderboardData(entity.id, entity.name, entity.score)
    }

    override fun domainToEntity(domain: LeaderboardData): LeaderboardEntity {
        return LeaderboardEntity(domain.id, domain.name, domain.score)
    }
}