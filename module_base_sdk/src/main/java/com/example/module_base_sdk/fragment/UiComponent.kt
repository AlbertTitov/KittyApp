package com.example.module_base_sdk.fragment

/**
 * Интерфейс UI компонентов приложения. Задает общие базовые методы для структуризации кода внутри реализаций
 */
interface UiComponent {

    /**
     * В этом методе инициализируем view компоненты и устанавливаем их конфигурации
     */
    fun initView() = Unit

    /**
     * В этом методе подвязываем ui события к ViewModel
     */
    fun listenUiUpdates() = Unit

    /**
     * В этом методе привязываемся к обновлениям состояний из ViewModel
     */
    fun bindView() = Unit

}