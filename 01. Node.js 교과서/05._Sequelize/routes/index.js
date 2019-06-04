var express = require('express');
var User = require('../models').User;
var router = express.Router();

/* GET "/" => find all data in table "users" and then render sequelize.pug, provide "users" as arguments */
router.get('/', function(req, res, next) {
  User.findAll()
    .then((users) => { // Sequelize supports Promise, so this is possible
      res.render('sequelize', { users });
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* async/await instead of Promise */
// router.get('/', async(req, res, next) => {
//   try {
//     const users = await User.findAll();
//     res.render('sequelize', { users });
//   } catch(error) {
//     console.error(error);
//     next(error);
//   }
// });


module.exports = router;
