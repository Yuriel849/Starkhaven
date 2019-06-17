const KakaoStrategy = require('passport-kakao').Strategy;

const { User } = require('../models');

module.exports = (passport) => {
    passport.use(new KakaoStrategy({
        clientID: process.env.KAKAO_ID, // "clientID" is unique to service & provided by Kakao, saved in .env for greater security
        callbackURL: '/auth/kakao/callback',
            /* "callbackURL" designates router address where this server will receive result of authentication from Kakao
                    Kakao sends "accessToken", "refreshToken", and "profile" to the designated "callbackURL"
             */
    }, async (accessToken, refreshToken, profile, done) => {
        try {
            const exUser = await User.findOne({ where: { snsId: profile.id, provider: 'kakao' } });
            if(exUser) {
                done(null, exUser);
            } else {
                const newUser = await User.create({
                    email: profile._json && profile._json.kaccount_email,
                    nick: profile.displayName,
                    snsId: profile.id,
                    provider: 'kakao'
                });
                done(null, newUser);
            }
        } catch(error) {
            console.error(error);
            done(error);
        }
    }));
};