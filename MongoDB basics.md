## MongoDB

### MongoDB & MySQL
> Collection is analagous to table (MongoDB DOES NOT REQUIRE COLUMNS TO BE SPECIFIED) <br>
Document is analagous to row <br>
Field is analagous to column <br>

### To use, open a command-line interface and start the server...
> mongod --auth
### ...then, open another command-line interface and login...
> mongo admin -u "USER NAME" -p "USER PASSWORD"

### Get started with a command-line interface; after logging in...
####  use "DATABASE NAME"
> Use the specified database; if it doesn't exist, it will be created.
#### show dbs
> Shows list of databases
#### db
> Shows the database currently being used
#### db.createCollection("COLLECTION")
> Creates the designated collection in the database currently being used <br>
This is not necessary, because adding a document will automatically create the collection!
#### show collections
> Shows list of collections in this database

### Get started with MongoDB Compass; after logging in...
#### Click 'CREATE DATABASE', enter 'Database Name' and 'Collection Name', then creates
