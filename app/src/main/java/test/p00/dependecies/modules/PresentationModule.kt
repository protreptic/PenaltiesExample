package test.p00.dependecies.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import test.p00.presentation.impl.DefaultContainer
import test.p00.presentation.conversation.impl.ConversationFragment
import test.p00.presentation.conversation.member.impl.MemberFragment
import test.p00.presentation.conversation.members.impl.MembersFragment
import test.p00.presentation.conversations.impl.ConversationsFragment
import test.p00.presentation.countries.impl.CountriesFragment
import test.p00.presentation.home.impl.HomeFragment
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.onboarding.impl.OnBoardingFragment
import test.p00.presentation.onboarding.wizard.impl.OnBoardingWizardFragment
import test.p00.presentation.onboarding.wizard.introductory.impl.OnBoardingWizardIntroductoryFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddDriverStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleLicenseStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleStepFragment
import test.p00.presentation.signup.impl.SignUpFragment
import test.p00.presentation.signup.verification.impl.SignUpVerificationFragment

@Suppress("unused")
@Module(includes = [ DomainModule::class ])
abstract class PresentationModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): DefaultContainer

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragmentInjector(): SignUpFragment

    @ContributesAndroidInjector
    abstract fun contributeCountriesFragmentInjector(): CountriesFragment

    @ContributesAndroidInjector
    abstract fun contributeSignUpVerificationFragmentInjector(): SignUpVerificationFragment

    @ContributesAndroidInjector
    abstract fun contributeLauncherFragmentInjector(): LauncherFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingWizardFragmentInjector(): OnBoardingWizardFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingWizardIntroductoryFragmentInjector(): OnBoardingWizardIntroductoryFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingWizardAddVehicleStepFragmentInjector(): OnBoardingWizardAddVehicleStepFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingWizardAddVehicleLicenseStepFragmentInjector(): OnBoardingWizardAddVehicleLicenseStepFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingWizardAddDriverStepFragmentInjector(): OnBoardingWizardAddDriverStepFragment

    @ContributesAndroidInjector
    abstract fun contributeOnBoardingFragmentInjector(): OnBoardingFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeConversationsFragmentInjector(): ConversationsFragment

    @ContributesAndroidInjector
    abstract fun contributeConversationFragmentInjector(): ConversationFragment

    @ContributesAndroidInjector
    abstract fun contributeMembersFragmentInjector(): MembersFragment

    @ContributesAndroidInjector
    abstract fun contributeMemberFragmentInjector(): MemberFragment

}