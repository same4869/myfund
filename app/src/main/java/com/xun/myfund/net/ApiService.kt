package com.xun.myfund.net

import com.xun.myfund.bean.TradeHistoryBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("djapi/plan/CSI666/trade_history")
    fun requestTradeHistory(@Query("size") size: Int, @Query("page") page: Int): Observable<TradeHistoryBean>
}