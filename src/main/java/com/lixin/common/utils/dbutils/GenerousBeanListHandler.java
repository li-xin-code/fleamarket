package com.lixin.common.utils.dbutils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * @author lixin
 */
public class GenerousBeanListHandler<T> extends BeanListHandler<T> {

    public GenerousBeanListHandler(Class<? extends T> type) {
        super(type, new BasicRowProcessor(new GenerousBeanProcessor()));
    }

}
