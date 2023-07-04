# script pour framework
cd frameWork
javac -d ./bin ./src/*.java
cd bin
jar cvf ../FrameWork.jar etu1820
cd ../
cp FrameWork.jar /opt/tomcat/webapps/framework/WEB-INF/lib/
cd ../

# script pour test_framework
cd test_framework
javac -d ./bin ./src/*.java
cd bin
cp -r util /opt/tomcat/webapps/framework/WEB-INF/classes/
cd ../../

pwd