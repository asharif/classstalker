export MAVEN_OPTS="-noverify -javaagent:springloaded-1.1.1-dev.jar"

java -jar classstalker-1.0.jar &

PID=$!

echo "\n*** Class Stalker started [PID $PID]...***"


trap control_c INT

control_c()
{
	echo  "\n*** Stopping Class Stalker [PID $PID ]... ***\n"
	kill -9 $PID
  echo  "*** Stopping App... ***\n"
  exit $?
}

mvn tomcat:run
