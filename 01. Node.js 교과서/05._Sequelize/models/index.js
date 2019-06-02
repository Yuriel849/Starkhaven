const path = require('path');
const Sequelize = require('sequelize');

const env = process.env.NODE_ENV || 'development';
const config = require(path.join(__dirname, '..', 'config', 'config.json'))[env];
const db = {};

const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

// connects models to models/index.js => objects added to "db" object => can access "user" & "comment" models through "db"
db.User = require('./user')(sequelize, Sequelize);
db.Comment = require('./comment')(sequelize, Sequelize);
// 1 : N relationship => connects "id" column of table "users" to "commenter" column of table "comments"
db.User.hasMany(db.Comment, { foreignKey: 'commenter', sourceKey: 'id' });
db.Comment.belongsTo(db.User, { foreignKey: 'commenter', sourceKey: 'id' });
/*
  1 : 1 relationships
    => use hasMany() & belongsTo
  N : M relationships
    => both tables use "belongsToMany(<table name>, { through: <new table name> })"
        for N : M relationships, a new table is created with the ids of both tables;
        the new table's name is designated as the "through" attribute
 */

// IF TABLES TO NOT EXIST IN THE DATABASE, THEN STARTING THE SERVER ("npm start") WILL CREATE THE TABLES!!!

module.exports = db;
