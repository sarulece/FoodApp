# MYSQL_DATABASE should be updated with name of the MySQL schema you have created
export MYSQL_DATABASE="foodie_db"
# MYSQL_USER and MYSQL_PASSWORD should be updated with username and password of the MySQL user who can access the schema created
export MYSQL_USER="root"
export MYSQL_PASSWORD="root"
if [[ -z "${MYSQL_CI_URL}" ]]; then
export MYSQL_CI_URL="jdbc:mysql://localhost:3306/foodie_db"
fi
