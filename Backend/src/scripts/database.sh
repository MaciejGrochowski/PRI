#!/usr/bin/expect -f
spawn bash ./database_run.sh
expect "Start database copy script"
expect "Enter password: "
send "[password]\r"
expect "Database copied"
expect "Wait for download"
expect "Database uploaded. Starting database copy removal"
expect "Database copy removed"
exit 0