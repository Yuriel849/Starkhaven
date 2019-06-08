## MongoDB
> MongoDB DOES NOT REQUIRE COLUMNS TO BE SPECIFIED WHEN CREATING A COLLECTION <br>
MongoDB accepts any type of data in its collections <br>
MongoDB basically uses the same data types as JavaScript, with some differences
>> USES ObjectId, Binary Data, Timestamp, Int, Long, Decimal, JavaScript, etc. <br>
DOES NOT USE Undefined, Symbol
<br>
### MongoDB & MySQL
> Collection is analagous to table <br>
Document is analagous to row <br>
Field is analagous to column <br>
<br>
### To use, open a command-line interface and start the server...
> mongod --auth
### ...then, open another command-line interface and login...
> mongo admin -u <USER NAME> -p <USER PASSWORD>
<br>
### Get started with a command-line interface; after logging in...
1. use <DATABASE NAME>
> Use the specified database; if it doesn't exist, it will be created.
2. show dbs
> Shows list of databases
3. db
> Shows the database currently being used
4. db.createCollection(<'COLLECTION>')
> Creates the designated collection in the database currently being used <br>
This is not necessary, because adding a document will automatically create the collection!
5. show collections
> Shows list of collections in this database
6. db.<COLLECTION NAME>.save(<DOCUMENT>)
> Create a new document in the designated collection; the document can be organized like a JavaScript object <br>
If successful, the response should be "WriteResult({ "nInserted": 1 })
7. db.<COLLECTION NAME>.find({ name: <NAME> }, { _id: 1 })
> Find the field with the designated name and get the _id (data type is ObjectId)
<br>
### Get started with MongoDB Compass; after logging in...
1. Click 'CREATE DATABASE', enter 'Database Name' and 'Collection Name', then create
2. Enter a database and click 'CREATE COLLECTION', enter the 'Collection Name', then create
