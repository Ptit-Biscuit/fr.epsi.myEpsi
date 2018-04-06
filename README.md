# LeBonKoinKoin

This JEE project is a '[leboncoin](https://www.leboncoin.fr/)-like' website to create / modify / sold an ad.

## Run application

1. Clone project
1. Deploy `.war` file on Tomcat (see below for more explanations)
1. Go under `libs/Project server/`
1. Run `_startProjectServer.bat` to start HSQL database
1. Open browser
1. Go to `http://localhost:8080/fr.epsi.myEpsi_war/`

## Deploy

To deploy the `.war` file on Tomcat:
Copy / paste `leBonKoinKoin.war` file under `webapps/` folder of your Tomcat installation.
Finally you start Tomcat and voilà !

(You can always do it with Tomcat manager if you want)

## Logging

All application logs are under `$CATALINA_HOME` or `$CATALINA_BASE` within `leBonKoinKoin.log` file

You can also use `jconsole` to monitor the application's:
* CPU usage
* Memory usage
* See all ads in database
* See and set logs' levels
* etc

## Authors
* Vincent Brebion - [_Ptit-Biscuit_](https://github.com/Ptit-Biscuit)
* Benjamin Guillon - [_benguillon_](https://github.com/benguillon)
* Maxime Orain - [_Maxor_](https://github.com/Maxor)
* Isaak Sailly - [_Larmis_}(https://github.com/Larmis)
