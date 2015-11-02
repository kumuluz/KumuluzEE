package com.kumuluz.ee.el;

import com.kumuluz.ee.common.Component;
import com.kumuluz.ee.common.KumuluzServer;
import com.kumuluz.ee.common.config.EeConfig;
import org.kohsuke.MetaInfServices;

import java.util.logging.Logger;

/**
 * @author Tilen
 */
@MetaInfServices
public class ElComponent implements Component {

    private Logger log = Logger.getLogger(ElComponent.class.getSimpleName());

    @Override
    public void init(KumuluzServer server, EeConfig eeConfig) {
    }

    @Override
    public void load() {

        log.info("Initiating UEL");
    }

    @Override
    public String getComponentName() {

        return "EL";
    }

    @Override
    public String getImplementationName() {

        return "UEL";
    }
}
