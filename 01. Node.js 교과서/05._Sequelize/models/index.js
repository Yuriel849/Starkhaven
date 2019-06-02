const path = require('path');
const Sequelize = require('sequelize');

const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const db = {};

const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

// connects models to models/index.js => objects added to "db" object => can access "user" & "comment" models through "db"
db.User = require('./user')(sequelize, Sequelize);
db.Comment = require('./comment')(sequelize, Sequelize);
// connects "id" column of table "users" to "commenter" column of table "comments"
db.User.hasMany(db.Comment, { foreignKey: 'commenter', sourceKey: 'id' });
db.Comment.belongsTo(db.User, { foreignKey: 'commenter', sourceKey: 'id' });

module.exports = db;
