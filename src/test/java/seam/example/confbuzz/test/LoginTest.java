/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package seam.example.confbuzz.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.picketlink.idm.impl.api.PasswordCredential;
import seam.example.confbuzz.model.Conference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@RunWith(Arquillian.class)
public class LoginTest {
    @Inject
    Identity identity;

    @Inject
    Credentials credentials;

    @Deployment
    public Archive<?> createLoginDeployment() {
        return ShrinkWrap.create(WebArchive.class, "LoginTest.jar").addPackage(Conference.class.getPackage())
                .addAsManifestResource("target/test-classes/META-INF/persistence.xml")
                .addAsManifestResource("target/test-classes/META-INF/seam-beans.xml")
                // Have to setup the jboss repository for Aether
                .addAsLibraries(DependencyResolvers.use(MavenDependencyResolver.class)
                        .configureFrom("target/test-classes/profiles/settings.xml")
                        .artifacts("org.jboss.seam.security:seam-security:3.0.0.Final",
                                "org.jboss.seam.persistence:seam-persistence:3.0.0.Final",
                                "org.jboss.seam.config:seam-config-xml:3.0.0.Final").resolveAs(JavaArchive.class));
    }

    @Test
    public void assertUserCanAuthenticate() {
        credentials.setUsername("test");
        credentials.setCredential(new PasswordCredential("password"));
        assertThat(identity.login(), is("loggedIn"));
    }
}
