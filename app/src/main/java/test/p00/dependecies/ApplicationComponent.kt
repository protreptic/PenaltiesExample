package test.p00.dependecies

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import test.p00.PenaltiesApplication
import test.p00.auxiliary.reactivex.bus.RxBus
import test.p00.dependecies.modules.PresentationModule
import test.p00.auxiliary.reactivex.schedulers.Schedulers


@Component(modules = [ AndroidSupportInjectionModule::class, PresentationModule::class ])
interface ApplicationComponent : AndroidInjector<PenaltiesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        @BindsInstance
        fun withBus(bus: RxBus): Builder
        
        fun build(): ApplicationComponent
    }

}