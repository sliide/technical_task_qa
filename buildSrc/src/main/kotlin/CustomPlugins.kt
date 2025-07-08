import org.gradle.api.Plugin
import org.gradle.api.Project

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyAndroidAppPlugin()
        applyAndroidCompose(target.androidAppExtension, target)
        target.applyCommonPlugins()
        target.applyCommonAndroidConfig()
        target.applyHilt()
        target.applyAppSigningConfig()
        target.applyUiTests()
    }
}
