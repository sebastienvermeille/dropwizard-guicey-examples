package ru.vyarus.dropwizard.guice.examples.support;

import de.spinscale.dropwizard.jobs.Job;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.module.installer.FeatureInstaller;
import ru.vyarus.dropwizard.guice.module.installer.feature.ManagedInstaller;
import ru.vyarus.dropwizard.guice.module.installer.install.TypeInstaller;
import ru.vyarus.dropwizard.guice.module.installer.util.FeatureUtils;
import ru.vyarus.dropwizard.guice.module.installer.util.Reporter;

/**
 * Installer performs utility tasks:
 *  - searches for jobs and bind them to guice context (so {@link JobsManager} could install them
 *  - print registered jobs to console
 *
 * @author Vyacheslav Rusakov
 * @since 11.03.2018
 */
public class JobsInstaller implements FeatureInstaller<Job>, TypeInstaller<Job> {

    private final Reporter reporter = new Reporter(JobsInstaller.class, "jobs =");

    @Override
    public boolean matches(Class<?> type) {
        return FeatureUtils.is(type, Job.class);
    }

    @Override
    public void install(Environment environment, Class<Job> type) {
        // here we can also look for class annotations and show more info in console
        // (omitted for simplicity)
        reporter.line("(%s)", type.getName());
    }

    @Override
    public void report() {
        reporter.report();
    }
}
