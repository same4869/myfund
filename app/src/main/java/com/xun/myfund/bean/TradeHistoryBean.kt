package com.xun.myfund.bean

data class TradeHistoryBean(
    val `data`: Data,
    val result_code: Int
)

data class Data(
    val current_page: Int,
    val items: List<Item>,
    val size: Int,
    val total_items: Int,
    val total_pages: Int
)

data class Item(
    val buy_amount: Double,
    val plan_code: String,
    val remark: String,
    val sale_type: Int,
    val status: Int,
    val trade_date: Long,
    val trade_date_cache: Long,
    val trading_elements: List<TradingElement>,
    val trading_id: String
)

data class TradingElement(
    val action: String,
    val fd_code: String,
    val fd_name: String,
    val money: Int,
    val portion: Double,
    val volume: Int
)