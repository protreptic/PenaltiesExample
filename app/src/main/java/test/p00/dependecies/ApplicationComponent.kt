package test.p00.dependecies

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import test.p00.PenaltiesApplication
import test.p00.dependecies.activity.ActivityModule

@Component(modules = [ AndroidSupportInjectionModule::class, ActivityModule::class ])
interface ApplicationComponent : AndroidInjector<PenaltiesApplication>