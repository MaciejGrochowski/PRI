#!/bin/bash

echo "---------------------Data Check begin----------------"
cd /home/janietakiork/projekt/PRI
git status
git checkout -- .
git pull

newDate_1=$(git log -n 1 --pretty=format:%cd --date=format:"%d %m %Y %H")
oldCommit_1=""

cd /home/janietakiork

filename='lastCommit.txt'
while read line; do
    oldCommit_1=$line
done < $filename

echo "Date of last commit:           $newDate_1"
echo "Date of last change on server: $oldCommit_1"
if [[("$oldCommit_1" != "$newDate_1")]]
then
    echo $newDate_1 > /home/janietakiork/lastCommit.txt
    echo "------------ Date updated -------------"
    bash ./autoscript.sh
    exit 0
else
    echo " --------- There are no changes - scrpit ended --------------"
    exit 0
fi