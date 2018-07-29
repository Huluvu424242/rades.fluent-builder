package com.github.funthomas424242.rades.fluentbuilder.statechart.domain;

/*-
 * #%L
 * rades.fluent-builder
 * %%
 * Copyright (C) 2018 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.github.funthomas424242.rades.fluentbuilder.statechart.generators.AbstractFluentBuilderGenerator;
import com.github.funthomas424242.rades.fluentbuilder.statechart.modelling.ParameterSignatur;
import com.github.funthomas424242.rades.fluentbuilder.statechart.modelling.ParameterSignaturParameterizedType;
import com.github.funthomas424242.rades.fluentbuilder.statechart.modelling.ParameterSignaturType;
import com.github.funthomas424242.rades.fluentbuilder.statechart.modelling.ParameterSignaturTypeVariable;
import com.github.funthomas424242.rades.fluentbuilder.statechart.modelling.ParameterSignaturVararg;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;


public class QueueTest {

    @Test
    public void createQueueStatechart() throws IOException {
        final String id = "com.github.funthomas424242.rades.fluentbuilder.test.QueueStatechart";
        final StatechartAccessor statechart = StatechartFluentBuilder.newStatechart()
            .withQualifiedClassName(id)
            .addState("Empty")
            .addState("Not Empty")
            .withStartState("Empty")
            .addTransition("Empty", "Not Empty", "enqueue")
            .addEmission("Empty", "isEmpty", ParameterSignaturType.of(boolean.class))

            .addTransition("Not Empty", "Not Empty", "enqueue")
            .addEmission("Not Empty", "isEmpty", ParameterSignaturType.of(boolean.class))
            .addTransition("Not Empty", "Not Empty", "dequeue")

            // Nichtdeterminismus nicht möglich mit FluentBuilder, da nur 1 Returntyp supported
            //.addTransition("Not Empty", "Empty", "dequeue")

            .build(StatechartAccessor.class);

        assertEquals(2, statechart.states().count());
        assertSame(statechart.getState("Empty"), statechart.getStartState());
        assertEquals(statechart.getState("Not Empty"), State.of("Not Empty"));
        assertNotSame(statechart.getState("Not Empty"), State.of("Not Empty"));
        assertNotEquals(statechart.getState("Not Empty"), statechart.getState("Empty"));

        final AbstractFluentBuilderGenerator generator = new AbstractFluentBuilderGenerator(statechart);
        generator.generate("target/generated-test-sources/test-annotations/");
    }

}