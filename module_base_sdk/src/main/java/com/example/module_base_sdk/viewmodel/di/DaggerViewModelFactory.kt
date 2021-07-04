package com.example.module_base_sdk.viewmodel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Фабрика, нужная для создания ViewModelProvider.Factory любой ViewModel, у которой @Inject constructor
 *
 */
class DaggerViewModelFactory @Inject constructor(
        private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

/**
 * Использовать для всех ViewModel у которых @Inject constructor
 *
 * Пример использования:
 *  @Module
 *  abstract class BankViewModelModule {
 *      @Binds
 *      @IntoMap
 *      @ViewModelKey(SomeViewModel::class)
 *      abstract fun bindSomeViewModel(viewModel: SomeViewModel): ViewModel
 *   }
 *
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)