const express = require('express');
const passport = require('passport');
const bcrypt = require('bcrypt');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');
const { User } = require('../models');

const router = express.Router();

/* POST "/auth/join" */
router.post('/join', isNotLoggedIn, async (req, res, next) => {
    const { email, nick, password } = req.body;
    try {
        const exUser = await User.find({ where = { email } });
        if(exUser) {
            req.flash('joinError', '이미 가입된 이메일입니다.');
            return res.redirect('/join');
        }

        const hash = await bcrypt.hash(password, 12); // hash() => encrypts password with bcrypt module
                                              // 2nd argument is how many times to encrypt, recommend over 12, maximum is 31
        await User.create({
            email,
            nick,
            password: hash,
        });
        return res.redirect('/');
    } catch(error) {
        console.error(error);
        return next(error);
    }
});

/* POST "/auth/login" */
router.post('/login', isNotLoggedIn, (req, res, next) => {
    passport.authenticate('local', (authError, user, info) => { // passport middleware within router middleware
            /* passport.authenticate() first calls localStrategy.js in passport directory
                callback is called whether Passport's local strategy is successful or not
                    IF 1st argument exists, authenticate failed
                    IF 2nd argument exists, authenticate succeeded
             */
        if(authError) {
            console.error(authError);
            return next(authError);
        }
        if(!user) {
            req.flash('loginError', info.message);
            return res.redirect('/');
        }
        return req.login(user, (loginError) => { // Passport adds login() and logout() to req object
            if(loginError) {
                console.error(loginError);
                return next(loginError);
            }
            return res.redirect('/');
        });
    })(req, res, next); // add "(req, res, next)" for middleware within middleware
});

/* GET "/auth/logout" */
router.get('/logout', isLoggedIn, (req, res) => {
    req.logout(); // deletes req.user object
    req.session.destroy(); // deletes data in req.session object
    res.redirect('/');
});

module.exports = router;