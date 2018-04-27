package test.p00.data.repository.onboarding.datasource

import test.p00.data.repository.onboarding.datasource.impl.RealmOnBoardingDataSource

object OnBoardingDataSourceFactory {

    /**
     * Возвращает источник данных о приветственных экранах.
     */
    fun create(): OnBoardingDataSource = RealmOnBoardingDataSource()

}