package com.cs.platform.framework.persistence;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * 自定义混合生成策略，可以指定Id的值，也可以用UID默认生成
 */
public class AssignedUIDGenerator extends UIDGenerator {

    /**
     * FIXME
     */
    private String entityName;


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        entityName = params.getProperty(ENTITY_NAME);
        if (entityName == null) {
            throw new MappingException("no entity name");
        }
        super.configure(type, params, serviceRegistry);
    }


    /**
     * @param session session
     * @param object  object
     * @return Serializable
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Serializable id = session.getEntityPersister(entityName, object).getIdentifier(object, session);
        if (StringUtils.isEmpty((String) id)) {
            id = super.generate(session, object);
        }
        return id;
    }

}
