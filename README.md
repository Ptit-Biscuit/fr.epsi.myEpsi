# LeBonKoinKoin

This JEE project is a '[leboncoin](https://www.leboncoin.fr/)-like' website to create / modify / sold an ad.

## Run application

1. Go under `libs/Project server`
1. Run `_startProjectServer.bat` to start HSQL database
1. Deploy `.war` file on Tomcat (or launch in IDE to dev / debug)
1. Open browser
1. Go to `localhost:8080`

## Logging

All application logs are under `$CATALINA_HOME` or `$CATALINA_BASE` within `leBonKoinKoin.log` file

You can also use `jconsole` to monitor the application's:
* CPU usage
* Memory usage
* See all ads in database
* See and set logs' levels
* etc

## Authors
* Vincent Brebion - _Ptit-Biscuit_
* Benjamin Guillon - _benguillon_
* Maxime Orain - _Maxor_
* Isaak Sailly - _Larmis_
