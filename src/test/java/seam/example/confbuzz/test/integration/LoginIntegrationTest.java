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
package seam.example.confbuzz.test.integration;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.api.maven.filter.ScopeFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.picketlink.idm.api.AttributesManager;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.PersistenceManager;
import org.picketlink.idm.api.RelationshipManager;
import org.picketlink.idm.api.User;
import org.picketlink.idm.common.exception.IdentityException;
import org.picketlink.idm.impl.api.PasswordCredential;
import seam.example.confbuzz.PersistenceConfiguration;
import seam.example.confbuzz.model.Conference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@RunWith(Arquillian.class)
public class LoginIntegrationTest {
    @Inject
    private IdentitySession identitySession;

    @Inject
    private Identity identity;

    @Inject
    @DefaultTransaction
    SeamTransaction tx;

    @Deployment(name = "authentication")
    public static Archive<?> createLoginDeployment() {
        // This is the simplest way to test the full archive as you will be deploying it
        final Collection<JavaArchive> libraries = DependencyResolvers.use(MavenDependencyResolver.class)
                .loadReposFromPom("pom.xml")
                .loadDependenciesFromPom("pom.xml")
                .resolveAs(JavaArchive.class, new ScopeFilter("compile", "runtime"));

        return ShrinkWrap.create(WebArchive.class, "LoginIntegrationTest.war").addPackage(Conference.class.getPackage())
                .addClass(PersistenceConfiguration.class)
                .addPackage(seam.example.confbuzz.model.Identity.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
//                .addAsResource("META-INF/seam-beans.xml")
                .addAsResource("auth-import.sql", "import.sql") // HUGE GOTCHA!!! IDENTITY TYPES MUST BE IN THE DB BEFORE CREATING IDENTITIES
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(libraries);
    }

    @Before
    public void setupTestUser() throws IdentityException, SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        if (!tx.isActive())
            tx.begin();

        final PersistenceManager pm = identitySession.getPersistenceManager();
        final AttributesManager am = identitySession.getAttributesManager();
        final RelationshipManager rm = identitySession.getRelationshipManager();

        // Setup the group we want our user to belong to
        final Group memberGroup = pm.createGroup("member", "GROUP");
        final User user = pm.createUser("test");

        am.updatePassword(user, "password");

        rm.associateUser(memberGroup, user);

        tx.commit();
    }

    @Test
    public void assertUserCanAuthenticate(Credentials credentials) {
        credentials.setUsername("test");
        credentials.setCredential(new PasswordCredential("password"));
        assertThat(identity.login(), is(Identity.RESPONSE_LOGIN_SUCCESS));
    }
}

