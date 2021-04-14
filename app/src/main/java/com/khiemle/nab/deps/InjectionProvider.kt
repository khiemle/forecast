package com.khiemle.nab.deps

import com.khiemle.nab.MainActivity

interface InjectionProvider {
    fun inject(mainActivity: MainActivity)
}