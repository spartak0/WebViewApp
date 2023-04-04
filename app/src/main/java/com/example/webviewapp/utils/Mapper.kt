package com.example.webviewapp.utils
interface Mapper<Domain, Entity> {
    fun entityToDomain(entity: Entity):Domain
    fun domainToEntity(domain: Domain):Entity
}