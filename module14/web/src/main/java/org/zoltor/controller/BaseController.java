package org.zoltor.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zoltor.config.SpringConfig;

/**
 * Created by org.zoltor on 18/09/16.
 */
public abstract class BaseController {

    protected static final ApplicationContext CTX = new AnnotationConfigApplicationContext(SpringConfig.class);

}
