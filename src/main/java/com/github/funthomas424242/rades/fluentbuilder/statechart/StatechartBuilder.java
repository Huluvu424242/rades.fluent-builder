package com.github.funthomas424242.rades.fluentbuilder.statechart;

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

import com.github.funthomas424242.rades.annotations.accessors.InvalidAccessorException;

import javax.annotation.Generated;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StatechartBuilder {

    private Statechart statechart;

    public StatechartBuilder(){
        this(new Statechart());
    }

    public StatechartBuilder(final Statechart statechart ){
        this.statechart = statechart;
    }

    public Statechart build() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final java.util.Set<ConstraintViolation<Statechart>> constraintViolations = validator.validate(this.statechart);

        if (constraintViolations.size() > 0) {
            java.util.Set<String> violationMessages = new java.util.HashSet<String>();

            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }

            final StringBuffer buf = new StringBuffer();
            buf.append("Statechart is not valid:\n");
            violationMessages.forEach(message -> buf.append(message + "\n"));
            throw new ValidationException(buf.toString());
        }
        final Statechart value = this.statechart;
        this.statechart = null;
        return value;
    }

    public <A> A build(Class<A> accessorClass) {
        final Statechart statechart = this.build();
        this.statechart=statechart;
        try{
            final Constructor<A> constructor=accessorClass.getDeclaredConstructor(Statechart.class);
            final A accessor = constructor.newInstance(statechart);
            this.statechart=null;
            return accessor;
        }catch(NoSuchMethodException | IllegalAccessException|  InstantiationException|  InvocationTargetException ex){
            throw new InvalidAccessorException("ungültige Accessorklasse übergeben",ex);
        }
    }

    public StatechartBuilder withStartState( final State startState ) {
        this.statechart.startState = startState;
        return this;
    }

    public  StatechartBuilder addState(final State state){
        this.statechart.states.add(state);
        return this;
    }

    public StatechartBuilder addTransition( final Transition transition){
        this.statechart.transitions.add(transition);
        return this;
    }

//    public StatechartBuilder addTransition( final State startState, final State targetState, final ParameterSignatur parameters){
//        this.statechart.transitions.add(Transition.of(startState,targetState,parameters));
//        return this;
//    }
//
//    public StatechartBuilder addTransition( final String startStateName, final String targetStateName, final String parameterSignatur){
//        this.addTransition(State.of(startStateName),State.of(targetStateName),ParameterSignatur.of(parameterSignatur));
//        return this;
//    }
}
