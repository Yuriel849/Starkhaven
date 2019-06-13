const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');
const router = express.Router();

router.get('/profile', isLoggedIn, (req, res) => { // isLoggedIn middleware is used before preceeding
    res.render('profile', { title: 'My Page', user: req.user });
});

router.get('/join', isNotLoggedIn, (req, res) => { // isNotLoggedIn middleware is used before preceeding
    res.render('join', {
        title: 'Join',
        user: req.user,
        joinError: req.flash('joinError'),
    });
});

router.get('/', (req, res, next) => {
    res.render('main', {
        title: 'Main',
        twits: [],
        user: req.user,
        loginError: req.flash('loginError'),
    });
});

module.exports = router;