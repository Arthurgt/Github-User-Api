-- Drop user first if they exist
DROP USER if exists 'githubuserapi'@'%' ;

-- Now create user with prop privileges
CREATE USER 'githubuserapi'@'%' IDENTIFIED BY 'githubuserapi';

GRANT ALL PRIVILEGES ON * . * TO 'githubuserapi'@'%';