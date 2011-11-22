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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.EntityType;
import org.jboss.seam.security.annotations.management.IdentityEntity;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;
import org.picketlink.idm.spi.model.IdentityObject;
import org.picketlink.idm.spi.model.IdentityObjectRelationshipType;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@Entity
@IdentityEntity(EntityType.IDENTITY_RELATIONSHIP)
public class IdentityRelationship implements Serializable {

    private static final long serialVersionUID = 6561141019143035366L;
    private Long id;
    private String name;
    private IdentityRelationshipType relationshipType;
    private Identity from;
    private Identity to;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "RELATIONSHIP_TYPE_ID")
    public IdentityRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(IdentityRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_FROM)
    @JoinColumn(name = "FROM_IDENTITY_ID")
    public Identity getFrom() {
        return from;
    }

    public void setFrom(Identity from) {
        this.from = from;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_TO)
    @JoinColumn(name = "TO_IDENTITY_ID")
    public Identity getTo() {
        return to;
    }

    public void setTo(Identity to) {
        this.to = to;
    }
}
