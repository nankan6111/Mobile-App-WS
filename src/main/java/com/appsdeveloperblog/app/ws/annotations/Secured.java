package com.appsdeveloperblog.app.ws.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})


public @interface Secured {

}
