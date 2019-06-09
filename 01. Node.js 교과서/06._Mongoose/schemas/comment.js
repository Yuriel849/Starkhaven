// Schema => similar to "models" in Sequelize!

const mongoose = require('mongoose');

const { Schema } = mongoose;
const { Types : { ObjectId } } = Schema;
const commentSchema = new Schema({
    commenter: {
        type: ObjectId,
        require: true,
        ref: 'User', // the value of "commenter" is the ObjectId of the "User" schema => Used for "populate" method
    },
    comment: {
        type: String,
        required: true,
    },
    createdAt: {
        type: Date,
        default: Date.now,
    },
});

module.exports = mongoose.model('Comment', commentSchema);