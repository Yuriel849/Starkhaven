var express = require('express');
var User = require('../schemas/user');
var router = express.Router();

/* GET "/" => read all documents in "users" collection in MongoDB then render mongoose.pug using data read */
router.get('/', function(req, res, next) {
  User.find({}) // equivalent to db.users.find({}) => reads all documents in "users" collection
    .then((users) => {
      res.render('mongoose', { users });
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* use async/await instead of Promise
router.get('/', async (req, res, next) => {
  try {
    const users = await User.find();
    res.render('mongoose', { users });
  } catch (err) {
    console.error(err);
    next(err);
  }
})
 */

module.exports = router;
