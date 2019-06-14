const express = require('express');

const { isLoggedIn } = require('./middlewares');
const { User } = require('../models');

const router = express.Router();

router.post('/:id/follow', isLoggedIn, async (req, res, next) => {
    try {
        const user = await User.find({ where: { id: req.user.id } }); // search database for which user to follow
        await user.addFollowing(parseInt(req.params.id, 10)); // add follower
        res.send('success');
    } catch(error) {
        console.error(error);
        next(error);
    }
});

module.exports = router;