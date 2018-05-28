package test.p00.auxiliary.dependecies

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import test.p00.DefaultApplication
import test.p00.auxiliary.bus.Bus
import test.p00.auxiliary.dependecies.modules.PresentationModule
import test.p00.auxiliary.reactivex.schedulers.Schedulers

@Component(modules = [ AndroidSupportInjectionModule::class, PresentationModule::class ])
interface ApplicationComponent : AndroidInjector<DefaultApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        @BindsInstance
        fun withBus(bus: Bus): Builder

        fun build(): ApplicationComponent

    }

}