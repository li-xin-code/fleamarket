package com.lixin.common.utils.dbutils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author lixin
 */
public class GenerousBeanHandler<T> extends BeanHandler<T> {

    public GenerousBeanHandler(Class<? extends T> type) {
        super(type, new BasicRowProcessor(new GenerousBeanProcessor()));
    }
}
