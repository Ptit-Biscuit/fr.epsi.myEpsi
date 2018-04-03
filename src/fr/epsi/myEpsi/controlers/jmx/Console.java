package fr.epsi.myEpsi.controlers.jmx;

import org.apache.logging.log4j.*;


public class Console implements ConsoleMBean {
    public Console(){

    }

    public void getLogger() {
        LogManager.getRootLogger().getLevel();
    }


    public void setLogLevel(String logger, String level) {
//        Logger l = Logger.getLogger(logger);
//        Level lvl = new Level(Level.toLevel(level.toUpperCase()));

    }
}
