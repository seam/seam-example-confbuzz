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
package seam.example.confbuzz;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import seam.example.confbuzz.model.Conference;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@Named
@ConversationScoped
public class ConferenceInstance implements Serializable {

    private Conference instance;

    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Inject
    private EntityManager em;
    
    public void load() {
        instance = em.find(Conference.class, id);
        if (instance == null) {
            throw new EntityNotFoundException("No conference with id " + id);
        }
    }
    
    @Inject
    public void init() {
        this.instance = new Conference();
    }

    @Produces
    @Named
    public Conference getConference() {
        return this.instance;
    }

    public void set(@Observes Conference instance) {
        this.instance = instance;
    }
}
