#!/bin/bash

echo "Preparing for deploying new version of ork..."

#-------------------------------------Preparing data-----------------------------------#

# Time is 1 hour behind current time in Poland!
#---new names for old front directy and old .war file-----#
oldFrontName="$(date +%d-%b-%H_%M)"
dataVarBack=$(date +%d-%b-%H_%M)
extensionEnd=".war"
oldBackName="$dataVarBack$extensionEnd"
#echo $oldFrontName
#echo $oldBackName

echo "---------- Preparing done ---------"
#echo "Getting the newest version of code..."
#--------------------------------------------Git---------------------------------------#

#1. Get into PRI directory
 #cd /home/janietakiork/projekt/PRI


#2. Git pull new version of code
# git status
# git checkout -- .
# git pull origin develop

#echo "---------- Done ---------"

echo "~~~~~~ Begin Frontend deploying process~~~~~~"
echo "."
echo "."
echo "."

echo "Saving old frontend build directory..."
#-------------------------------------------Fronted------------------------------------#

#1. Copy build directory into old-front folder

 cd /home/janietakiork/projekt/PRI/Frontend
 cp -r  build /home/janietakiork/old-front

#2 Rename old directory
 cd /home/janietakiork/old-front
 mv build $oldFrontName

#echo "----------Done--------"

#echo "Building a new frontend and deploing..."
#2. Build a new one
 cd /home/janietakiork/projekt/PRI/Frontend
 yarn install --ignore-engines
 yarn build

#3. Restart nginx
 sudo systemctl reload nginx

echo "----------Frontend deploying done-----------"


echo "~~~~~~ Begin Backend deploying process~~~~~~"
echo "."
echo "."
echo "."

echo "Saving old backend .war file..."
#-------------------------------------------Backend------------------------------------#

#1. Copy old directory to old-back folder and rename it
 cp /opt/tomcat/apache-tomcat-9.0.34/webapps/PRI-0.0.1-SNAPSHOT.war /home/janietakiork/old-back
 cd /home/janietakiork/old-back
 mv PRI-0.0.1-SNAPSHOT.war $oldBackName

echo "------------ Done -------------"

echo "Generating new .war file and deploying..."
#2. Generate a new .war file
 cd /home/janietakiork/projekt/PRI/Backend
 mvn clean install
 mvn package

#3. Stop tomcat
 cd /opt/tomcat/apache-tomcat-9.0.34/bin
 ./catalina.sh stop

#4 Copy new .war into /webapps
 cp /home/janietakiork/projekt/PRI/Backend/target/PRI-0.0.1-SNAPSHOT.war /opt/tomcat/apache-tomcat-9.0.34/webapps

#5 Start tomcat again
 cd /opt/tomcat/apache-tomcat-9.0.34/bin
 ./catalina.sh start


echo "---------- Backend deploing done---------------"