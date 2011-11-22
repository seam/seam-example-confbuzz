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

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.jboss.seam.security.annotations.permission.PermissionProperty;
import org.jboss.seam.security.annotations.permission.PermissionPropertyType;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
//@Entity
public class IdentityPermission implements Serializable {
    private static final long serialVersionUID = -5366058398015495583L;

    private Long id;
    private Identity identityObject;
    private IdentityRelationshipType relationshipType;
    private String relationshipName;
    private String resource;
    private String permission;

    /**
     * Surrogate primary key value for the permission.
     *
     * @return
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Either the specific identity object for which this permission is granted,
     * or in the case of a permission granted against a group, this property
     * then represents the "to" side of the group relationship.  Required field.
     *
     * @return
     */
    @NotNull
    @ManyToOne
    @PermissionProperty(PermissionPropertyType.IDENTITY)
    public Identity getIdentity() {
        return identityObject;
    }

    public void setIdentity(Identity identityObject) {
        this.identityObject = identityObject;
    }

    /**
     * If this permission is granted to a group of identities, then this property may
     * be used to indicate the relationship type of the group membership.  For example,
     * a group or role relationship.  It is possible that the permission may also be
     * granted to identities that have *any* sort of membership within a group, in
     * which case this property would be null.
     *
     * @return
     */
    @ManyToOne
    @PermissionProperty(PermissionPropertyType.RELATIONSHIP_TYPE)
    public IdentityRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(IdentityRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    /**
     * If this permission is granted to a group of identities, then this property
     * may be used to indicate the name for named relationships, such as role
     * memberships.
     *
     * @return
     */
    @PermissionProperty(PermissionPropertyType.RELATIONSHIP_NAME)
    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * The unique identifier for the resource for which permission is granted
     *
     * @return
     */
    @PermissionProperty(PermissionPropertyType.RESOURCE)
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * The permission(s) granted for the resource.  May either be a comma-separated
     * list of permission names (such as create, delete, etc) or a bit-masked
     * integer value, in which each bit represents a different permission.
     *
     * @return
     */
    @PermissionProperty(PermissionPropertyType.PERMISSION)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
