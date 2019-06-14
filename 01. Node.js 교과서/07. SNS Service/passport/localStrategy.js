const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');

const { User } = require('../models');

module.exports = (passport) => {
    passport.use(new LocalStrategy({
        usernameField: 'email', // "usernameField" & "passwordField" are variables in passport-local =>
        passwordField: 'password', // =>  these 2 lines designate which values in req.body to use
    }, async (email, password, done) => {
           // 1st & 2nd arguments from above 2 lines, 3rd argument "done" is callback of passport.authenticate()
           // done(A, B, C) => passport.authenticate('local', (A, B, C) => {})
        try {
            const exUser = await User.find({ where: { email } });
            if(exUser) {
                const result = await bcrypt.compare(password, exUser.password);
                if(result) {
                    done(null, exUser);
                } else {
                    done(null, false, { message: '비밀번호가 일치하지 않습니다.' });
                }
            } else {
                done(null, false, { message: '가입되지 않은 회원입니다.' });
            }
        } catch(error) {
            console.error(error);
            done(error);
        }
    }));
};