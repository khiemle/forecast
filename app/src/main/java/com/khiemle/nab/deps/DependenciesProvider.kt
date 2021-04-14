package com.khiemle.nab.deps

interface DependenciesProvider {
    fun provideInjectionProvider() : InjectionProvider
}