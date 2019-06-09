var express = require('express');
var User = require('../schemas/user');
var router = express.Router();

/* GET "/users" => get listing of all users. */
router.get('/', function(req, res, next) {
  User.find({})
    .then((users) => {
      res.json(users);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* POST "/users" => add new user */
router.post('/', function(req, res, next) {
  const user = new User({ // using constructor for "user" schema => if data type is wrong, Mongoose throws an error
    name: req.body.name,
    age: req.body.age,
    married: req.body.married,
  });
  user.save()
    .then((result) => {
      console.log(result);
      res.status(201).json(result);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

module.exports = router;
