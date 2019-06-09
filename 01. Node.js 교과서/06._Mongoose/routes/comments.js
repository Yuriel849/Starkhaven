var express = require('express');
var Comment = require('../schemas/comment');
var router = express.Router();

/* GET "/comments/:id" => get listing of all comments with the designated ID. */
router.get('/:id', function(req, res, next) {
  Comment.find({ commenter: req.params.id }).populate('commenter')
    /* finds comment with value of commenter matching provided ID, then finds document of referenced collection
        "commenter" field in schema "comment" references "User", so finds document in "users" collection with the same ID
        finally, value of commenter field becomes the document in "users" collection with same ObjectId as the provided ID
     */
    .then((comments) => {
      console.log(comments);
      res.json(comments);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* POST "/comments" => adds new comment. */
router.post('/', function (req, res, next) {
  const comment = new Comment({
    commenter: req.body.id,
    comment: req.body.comment,
  });
  comment.save()
    .then((result) => {
      return Comment.populate(result, { path: 'commenter' }); // save() then populate()
    })
    .then((result) => {
      res.status(201).json(result);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* PATCH "/comments/:id" => update a comment with the designated ID. */
router.patch('/:id', function (req, res, next) {
  Comment.update({ _id: req.params.id }, { comment: req.body.comment }) // "$set" unnecessary => only designated field changed
    .then((result) => {
      res.json(result);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

/* DELETE "/comments/:id" => deletes comment with the designated ID. */
router.delete('/:id', function (req, res, next) {
  Comment.remove({ _id: req.params.id})
    .then((result) => {
      res.json(result);
    })
    .catch((err) => {
      console.error(err);
      next(err);
    });
});

module.exports = router;
