package com.khiemle.nab.deps

import com.khiemle.nab.presentation.MainActivity

interface InjectionProvider {
    fun inject(mainActivity: MainActivity)
}