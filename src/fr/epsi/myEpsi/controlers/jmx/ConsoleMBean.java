package fr.epsi.myEpsi.controlers.jmx;

import java.util.List;
import java.util.Map;

public interface ConsoleMBean {

    /**
     * liste tous les loggers
     */
    public void getLogger();

    /**
     * Modifie le niveau de log d'un element
     */
    public void setLogLevel(String logger, String level);

}
