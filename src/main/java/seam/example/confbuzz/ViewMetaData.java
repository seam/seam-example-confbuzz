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

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.rewrite.UrlMapping;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
@org.jboss.seam.faces.view.config.ViewConfig
public interface ViewMetaData {
    static enum Pages {
        @UrlMapping(pattern = "/home")
        @ViewPattern("/home.xhtml")
        HOME,

        @FacesRedirect
        @ViewPattern("/*")
        @AccessDeniedView("/home.xhtml")
        @LoginView("/home.xhtml")
        ALL
    }
}
