package test.p00.dependecies

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import test.p00.PenaltiesApplication
import test.p00.dependecies.activity.ActivityModule
import test.p00.util.reactivex.schedulers.Schedulers


@Component(modules = [ AndroidSupportInjectionModule::class, ActivityModule::class, DataModule::class ])
interface ApplicationComponent : AndroidInjector<PenaltiesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent
    }

}