package com.devrachit.groww.di.qualifiers

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class WithChucker

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class WithoutChucker