import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class EnterprisePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create('enterprise', EnterpriseExtension)
        
        project.task('validateEnterprise') {
            doLast {
                println "Validating enterprise configuration..."
                // Simulate complex enterprise validation
            }
        }
        
        project.afterEvaluate {
            // Update to allow Java 17 compatibility
            project.tasks.withType(JavaCompile) {
                options.compilerArgs += ['-Xlint:deprecation']
            }
        }
    }
}

class EnterpriseExtension {
    String environment = 'production'
    boolean strictMode = true
}