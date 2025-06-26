package com.devrachit.groww.domain.models

enum class StokeType{
    Gainer,
    Loser,
    Active,
}
sealed class StockType(val type: StokeType)
{
    class Gainer: StockType(StokeType.Gainer)
    class Loser: StockType(StokeType.Loser)
    class Active: StockType(StokeType.Active)
    fun isGainer() = type == StokeType.Gainer
    fun isLoser() = type == StokeType.Loser
    fun isActive() = type == StokeType.Active
}