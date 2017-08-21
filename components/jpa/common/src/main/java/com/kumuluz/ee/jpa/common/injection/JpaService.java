/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.jpa.common.injection;

import com.kumuluz.ee.jpa.common.PersistenceUnitHolder;
import com.kumuluz.ee.jpa.common.PersistenceWrapper;
import com.kumuluz.ee.jpa.common.exceptions.NoDefaultPersistenceUnit;
import org.jboss.weld.injection.spi.JpaInjectionServices;
import org.jboss.weld.injection.spi.ResourceReferenceFactory;

import javax.annotation.Priority;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author Tilen Faganel
 * @since 1.0.0
 */
@Priority(1)
public class JpaService implements JpaInjectionServices {

    @Override
    public ResourceReferenceFactory<EntityManager> registerPersistenceContextInjectionPoint
            (InjectionPoint injectionPoint) {

        PersistenceUnitHolder holder = PersistenceUnitHolder.getInstance();

        PersistenceContext pc = injectionPoint.getAnnotated().getAnnotation(PersistenceContext
                .class);
        String unitName = pc.unitName();

        if (unitName.isEmpty()) {

            unitName = holder.getDefaultUnitName();

            if (unitName.isEmpty()) {
                throw new NoDefaultPersistenceUnit();
            }
        }

        PersistenceWrapper wrapper = holder.getEntityManagerFactory(unitName);

        return new PersistenceContextResourceFactory(unitName, wrapper.getEntityManagerFactory(),
                wrapper.getTransactionType(), pc.synchronization());
    }

    @Override
    public ResourceReferenceFactory<EntityManagerFactory> registerPersistenceUnitInjectionPoint
            (InjectionPoint injectionPoint) {

        PersistenceUnitHolder holder = PersistenceUnitHolder.getInstance();

        PersistenceUnit pu = injectionPoint.getAnnotated().getAnnotation(PersistenceUnit.class);
        String unitName = pu.unitName();

        if (unitName.isEmpty()) {

            unitName = holder.getDefaultUnitName();

            if (unitName.isEmpty()) {
                throw new NoDefaultPersistenceUnit();
            }
        }

        PersistenceWrapper wrapper = holder.getEntityManagerFactory(unitName);

        return new PersistenceUnitResourceFactory(wrapper.getEntityManagerFactory());
    }

    @Override
    public EntityManager resolvePersistenceContext(InjectionPoint injectionPoint) {

        throw new UnsupportedOperationException();
    }

    @Override
    public EntityManagerFactory resolvePersistenceUnit(InjectionPoint injectionPoint) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanup() {
    }
}
