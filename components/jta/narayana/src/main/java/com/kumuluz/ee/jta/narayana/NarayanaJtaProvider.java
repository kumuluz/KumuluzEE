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
package com.kumuluz.ee.jta.narayana;

import com.arjuna.ats.jta.common.JTAEnvironmentBean;
import com.arjuna.ats.jta.common.jtaPropertyManager;
import com.kumuluz.ee.jta.common.JtaProvider;
import io.agroal.api.transaction.TransactionIntegration;
import io.agroal.narayana.NarayanaTransactionIntegration;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.TransactionSynchronizationRegistry;
import jakarta.transaction.UserTransaction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcos Koch Salvador
 * @since 2.3.0
 */
public class NarayanaJtaProvider extends JtaProvider {

    private final JTAEnvironmentBean jtaEnvironment;
    private final Map<String, NarayanaTransactionIntegration> transactionIntegrations;

    public NarayanaJtaProvider() {
        jtaEnvironment = jtaPropertyManager.getJTAEnvironmentBean();
        transactionIntegrations = new HashMap<>();
    }

    @Override
    public UserTransaction getUserTransaction() {
        return jtaEnvironment.getUserTransaction();
    }

    @Override
    public TransactionManager getTransactionManager() {
        return jtaEnvironment.getTransactionManager();
    }

    @Override
    public TransactionSynchronizationRegistry getTransactionSynchronizationRegistry() {
        return jtaEnvironment.getTransactionSynchronizationRegistry();
    }

    @Override
    public TransactionIntegration getTransactionIntegration(String jndiName) {

        if (!transactionIntegrations.containsKey(jndiName)) {
            transactionIntegrations.put(jndiName, new NarayanaTransactionIntegration(getTransactionManager(),
                    getTransactionSynchronizationRegistry(), jndiName));
        }

        return transactionIntegrations.get(jndiName);
    }

}
