This project adds hot reloading (save refresh in case of browser apps) to any java maven app.  The core of it is basically spring-loaded https://github.com/SpringSource/spring-loaded.

I added two things:


1. A package class watcher to watch *.java files for changes (every second by default) and run an ant build for juicy incremental builds.
2. A shell script that can be used in place of mvn tomcat:run during testing to start the app for hot relaod capabilities


Installation:
1.  Dump the contents of bin into the root of maven web project that you typically start using mvn  tomcat:run or eq.
2.  sh run.sh

Enjoy!

Released under MIT license @http://opensource.org/licenses/MIT




