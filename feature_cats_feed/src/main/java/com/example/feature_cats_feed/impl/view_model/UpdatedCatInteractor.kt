package com.example.feature_cats_feed.impl.view_model

import com.example.data.domain.model.CatEntity
import io.reactivex.subjects.PublishSubject

/**
 * Интерактор для обмена обновлёнными моделями котов
 */
object UpdatedCatInteractor {

    val updatedCatsList = PublishSubject.create<CatEntity>()
}