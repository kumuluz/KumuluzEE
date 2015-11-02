package com.kumuluz.ee.cdi;

import com.kumuluz.ee.common.Component;
import com.kumuluz.ee.common.KumuluzServer;
import com.kumuluz.ee.common.config.EeConfig;
import org.kohsuke.MetaInfServices;

import java.util.logging.Logger;

/**
 * @author Tilen
 */
@MetaInfServices
public class CdiComponent implements Component {

    private Logger log = Logger.getLogger(CdiComponent.class.getSimpleName());

    @Override
    public void init(KumuluzServer server, EeConfig eeConfig) {
    }

    @Override
    public void load() {

        log.info("Initiating Weld");
    }

    @Override
    public String getComponentName() {

        return "CDI";
    }

    @Override
    public String getImplementationName() {

        return "Weld";
    }
}
