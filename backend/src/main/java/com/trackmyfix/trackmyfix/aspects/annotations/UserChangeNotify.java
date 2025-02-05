package com.trackmyfix.trackmyfix.aspects.annotations;

import com.trackmyfix.trackmyfix.entity.ActionUser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface UserChangeNotify {
    ActionUser actionUser();
}
