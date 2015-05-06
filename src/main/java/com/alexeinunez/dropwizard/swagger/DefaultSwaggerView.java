/*
 * Copyright (C) 2015 Alexei Nunez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alexeinunez.dropwizard.swagger;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A {@link com.alexeinunez.dropwizard.swagger.SwaggerView} which renders a
 * <a href="https://github.com/swagger-api/swagger-spec">Swagger Spec</a> using the
 * <a href="https://github.com/swagger-api/swagger-ui">Swagger UI</a> project in the
 * default path "/assets/default-swagger-ui/" resource directory.
 *
 * @author Alexei Nunez
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultSwaggerView extends SwaggerView {

    private boolean displayApiSelector = true;
    private String title = "Swagger UI";

    /**
     * By default, this view will use the Swagger Spec exposed by the host application via the "/api-docs" path.
     */
    public DefaultSwaggerView() {
        this("/api-docs");
    }

    /**
     * Define a Swagger Spec to render.
     *
     * @param swaggerSpecUrl a fully qualified URL of a Swagger Spec to be displayed
     */
    public DefaultSwaggerView(final String swaggerSpecUrl) {
        super("/templates/index.mustache", "default-swagger-ui", swaggerSpecUrl);
    }

}
