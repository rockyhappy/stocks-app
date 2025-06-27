package com.devrachit.groww.utility.constants

class Constants {
    companion object {
        const val HOME_ROUTE = "home"
        const val DETAIL_ROUTE = "detail"
        const val DISPLAY_ROUTE = "display"
        const val WAITLIST_ROUTE = "waitlist"
        const val BOTTOM_BAR_ROUTE = "bottom_bar"
        const val MAIN_ANIMATION_DURATION = 300
        const val START_DESTINATION = BOTTOM_BAR_ROUTE
        const val START_DESTINATION_INNER_NAV = HOME_ROUTE
        const val ANIMATION_DURATION = 300
        const val  APP_TITLE = "Stocks App"
//        const val API_KEY = "CWM5EGLE634DF0JM"
        const val API_KEY = "demo"
//        const val API_KEY = "QZ9P7NPXFKJUR04F"
//        const val API_KEY = "NJKPXW44YH4UZTVC"
        const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
        const val BASE_URL = "https://www.alphavantage.co/"
        const val TOP_GAINERS="Top Gainers"
        const val TOP_LOSERS="Top Losers"
        const val FAVOURITES="Favourites"
        const val MOSTLY_TRADED="Mostly Traded"
        const val API_KEY_FAILURE="No Data Found pull to refresh to fetch data if the problem persists then the rate limit for today is full try again tomorrow or rebuild with the new API key"
    }
}