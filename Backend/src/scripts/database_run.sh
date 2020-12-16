#!/bin/bash
name="$(date +%d-%m-%Y).sql"
cd /home/janietakiork/Desktop/Database-backup
echo "Start database copy script"
mysqldump -u [username] -p  orc > $name
chmod a+rwx $name

echo "Database copied"
echo "Wait for download"

cd /home/janietakiork/Desktop/
su -c "google-drive-ocamlfuse google-drive -o allow_other" janietakiork

cp /home/janietakiork/Desktop/Database-backup/$name /home/janietakiork/Desktop/google-drive

cd /home/janietakiork/Desktop/
su -c "fusermount -u google-drive" janietakiork

echo "Database uploaded. Starting database copy removal"
cd /home/janietakiork/Desktop/Database-backup
sleep 5s
rm $name
echo "Database copy removed"
exit 0