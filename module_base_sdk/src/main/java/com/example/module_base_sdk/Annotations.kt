package com.example.module_base_sdk

import javax.inject.Scope

/**
 * Корневая аннотация для компонентов, вынесенных в модуль
 */
@Scope
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerFeature