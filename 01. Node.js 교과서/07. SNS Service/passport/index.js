/*
    "strategy" => Passport term for files that determine what to do when someone tries to log in

    LOGIN
        1. login request received by Node.js server
        2. passport.authenticate() called
        3. execute login strategy
        4. if login is successful, call req.login(user)
        5. req.login() calls passport.serializeUser(user)
        6. user.id saved in req.session
        7. login complete

    LOGGED IN
        1. request received
        2. passport.session() calls passport.deserializeUser(session.id)
        3. search database for user with id matching session.id
        4. save user data in req.user
        5. router can now access user data in req.user
 */

const local = require('./localStrategy');
const kakao = require('./kakaoStrategy');
const { User } = require('../models');

module.exports = (passport) => {
    // "serializeUser" designates which data to save in the req.session object
    passport.serializeUser((user, done) => { // receives user data in "user" argument
        done(null, user.id); // saves only user.id in session (1st argument is used for errors)
    });

    // "deserializeUser" is called upon every request by "passport.session()" middleware
    passport.deserializeUser((id, done) => { // receives user.id from session in "id" argument
        User.findOne({
            where: { id },
            include: [{
                model: User,
                attributes: ['id', 'nick'], // specify which columns to send, in case password is sent accidentally
                as: 'Followers',
            }, {
                model: User,
                attributes: ['id', 'nick'],
                as: 'Followings',
            }],
        }) // searches database for data with that id
            .then(user => done(null, user)) // saves data from database in req.user object
            .catch(err => done(err));
    });

    local(passport);

    kakao(passport);
};