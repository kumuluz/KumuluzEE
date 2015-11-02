package com.kumuluz.ee.jsonp;

import com.kumuluz.ee.common.Component;
import com.kumuluz.ee.common.KumuluzServer;
import com.kumuluz.ee.common.config.EeConfig;
import org.kohsuke.MetaInfServices;

import java.util.logging.Logger;

/**
 * @author Tilen
 */
@MetaInfServices
public class JsonPComponent implements Component {

    private Logger log = Logger.getLogger(JsonPComponent.class.getSimpleName());

    @Override
    public void init(KumuluzServer server, EeConfig eeConfig) {
    }

    @Override
    public void load() {

        log.info("Initiating JSONP");
    }

    @Override
    public String getComponentName() {

        return "JSON-P";
    }

    @Override
    public String getImplementationName() {

        return "JSONP";
    }
}
