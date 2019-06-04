var express = require('express');
var { User, Comment } = require('../models');
var router = express.Router();

/* GET "/comments/:id" => get list of comments */
router.get('/:id', function(req, res, next) {
    Comment.findAll({
        include: { // "include" option only useable if using "hasMany" OR "belongsTo"
            model: User,
            where: { id: req.params.id },
        },
    })
        .then((comments) => {
            console.log(comments);
            res.json(comments);
        })
        .catch((err) => {
            console.error(err);
            next(err);
        });
});

/* POST "/comments" => add new comment */
router.post('/', function(req, res, next) {
    Comment.create({
        commenter: req.body.id,
        comment: req.body.comment,
    })
        .then((result) => {
            console.log(result);
            res.status(201).json(result);
        })
        .catch((err) => {
            console.error(err);
            next(err);
        });
});

/* PATCH "/comments/:id" => update existing comment */
router.patch('/:id', function(req, res, next) {
    Comment.update({ comment: req.body.comment }, { where: { id: req.params.id } })
                // 1st argument is which column, 2nd argument is the codition for finding which row
        .then((result) => {
            res.json(result);
        })
        .catch((err) => {
            console.error(err);
            next(err);
        });
});

/* DELETE "/comments/:id" => delete existing comment */
router.delete('/:id', function(req, res, next) {
    Comment.destroy({ where: { id: req.params.id } })
        .then((result) => {
            res.json(result);
        })
        .catch((err) => {
            console.error(err);
            next(err);
        });
});

module.exports = router;