eclipselink project uses mysql employees sample database, here are the steps to setup and configure mysql employees sample database:

1. Download mysql employees sample database
https://launchpad.net/test-db/
Locate Downloads in the right pane
download employees-db-full-1.0.6  (https://launchpad.net/test-db/employees-db-1/1.0.6/+download/employees_db-full-1.0.6.tar.bz2)

2. Install mysql employees sample database
shell> tar -xjf $HOME/Downloads/employees_db-full-1.0.6.tar.bz2
shell> cd employees_db/

mysql -u root -p -v -v -v -t < employees.sql
enter your mysql root password at prompt

3. Create user account
shell> mysql -u root -p

create user dcfg and grant full access to dcfg
mysql> GRANT ALL PRIVILEGES ON *.* TO 'dcfg'@'localhost' IDENTIFIED BY 'dconfig' WITH GRANT OPTION;


