/* "Multer" middleware => used in router that handles multipart data */

const express = require('express');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const { Post, Hashtag, User } = require('../models');
const { isLoggedIn } = require('./middlewares');

const router = express.Router();

/* Check if the "uploads" directory exists, and if not, create one */
fs.readdir('uploads', (error) => {
    if(error) {
        console.error('uploads 폴더가 없어서 uploads 폴더를 생성합니다.');
        fs.mkdirSync('uploads');
    }
});

const upload = multer({ // add options to multer module before assigning to "upload"
    storage: multer.diskStorage({ // save image to the server disk
        destination(req, file, cb) {
            cb(null, 'uploads/'); // save image to "uploads" directory under "07. SNS Service" directory
        },
        filename(req, file, cb) {
            const ext = path.ext(file.originalname);
            cb(null, path.basename(file.originalname, ext) + new Date().valueOf() + ext); // filename is original + date
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 }, // limit image size to maximum 5MB
});

/* POST "/post/img" */
router.post('/img', isLoggedIn, upload.single('img'), (req, res) => {
    console.log(req.file);
    res.json({ url: `/img/${req.file.filename}` });
});

const upload2 = multer();

/* POST "/post" */
router.post('/', isLoggedIn, upload2.none(), async (req, res, next) => {
    try {
        const post = await Post.create({
            content: req.body.content,
            img: req.body.url,
            userId: req.user.id,
        });
        const hashtags = req.body.content.match(/#[^\s]*/g);
        if(hashtags) {
            const result = await Promise.all(hashtags.map(tag => Hashtag.findOrCreate({
                where: { title: tag.slice(1).toLowerCase() },
            })));
            await post.addHashtags(result.map(r => r[0]));
        }
        res.redirect('/');
    } catch(error) {
        console.error(error);
        next(error);
    }
});

/* POST "/post/hashtag" => searchs posts by hashtags */
router.get('/hashtag', async (req, res, next) => {
    const query = req.query.hashtag;
    if(!query) {
        return res.redirect('/');
    }
    try {
        const hashtag = await Hashtag.find({ where: { title: query } });
        let posts = [];
        if(hashtag) {
            posts = await hashtag.getPosts({ include: [{ model: User }] });
        }
        return res.render('main', {
            title: `${query} | SNS Service`,
            user: req.user,
            twits: posts,
        })
    } catch(error) {
        console.error(error);
        return next(error);
    }
});

module.exports = router;