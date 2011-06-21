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
package seam.example.confbuzz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;
import org.jboss.seam.solder.core.Veto;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@Entity
@Veto
public class Identity implements Serializable {
    private static final long serialVersionUID = -8136797293873610623L;

    private Long id;
    private Long version;
    private String name;
    private String givenFirst;
    private String giveLast;
    private String credential;
    private String email;
    private String credentialType;
    private IdentityType type;

    @Id
    @GeneratedValue
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @NotNull
    @Size(min = 1, max = 32)
    @Column(unique = true, nullable = false)
    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenFirst() {
        return givenFirst;
    }

    public void setGivenFirst(String givenFirst) {
        this.givenFirst = givenFirst;
    }

    public String getGiveLast() {
        return giveLast;
    }

    public void setGiveLast(String giveLast) {
        this.giveLast = giveLast;
    }

    @IdentityProperty(PropertyType.CREDENTIAL)
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @IdentityProperty(PropertyType.CREDENTIAL_TYPE)
    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    @Email
    @NotNull
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "IDENTITY_OBJECT_TYPE_ID")
    public IdentityType getType() {
        return type;
    }

    public void setType(IdentityType type) {
        this.type = type;
    }
}
