javac -d ./bin ./src/*.java

cd bin
jar cvf ../FrameWork.jar etu1820

cd ../
cp FrameWork.jar /opt/tomcat/webapps/framework/WEB-INF/lib/