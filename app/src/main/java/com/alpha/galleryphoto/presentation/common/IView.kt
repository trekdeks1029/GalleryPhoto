package com.alpha.galleryphoto.presentation.common

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IView : MvpView{

    @StateStrategyType(SkipStrategy::class)
    fun showLoadingProgress(show: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showToast(message: String?)

    @StateStrategyType(SkipStrategy::class)
    fun showToast(messageId: Int)
}