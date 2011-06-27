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

import java.io.IOException;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.AuthenticationException;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.events.LoginFailedEvent;
import org.jboss.seam.solder.logging.TypedCategory;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@HandlesExceptions
public class ExceptionHandler {
    public void authenticationException(@Handles CaughtException<AuthenticationException> e,
                                        @TypedCategory(ExceptionHandler.class) ExceptionLogger logger, Credentials creds) {
        logger.authenticationError(creds, e.getException());
    }
    
    public void entityNotFoundException(@Handles CaughtException<EntityNotFoundException> e, @TypedCategory(ExceptionHandler.class) Logger logger,
            HttpServletResponse response, FacesContext ctx) {
        logger.error(e.getException().getMessage());
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            ctx.responseComplete();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void loginFailed(@Observes LoginFailedEvent event, @TypedCategory(ExceptionHandler.class) ExceptionLogger logger, Messages msg) {
        logger.loginFailed();
        msg.error("Invalid username and password combination. Please try again.");
    }
}
