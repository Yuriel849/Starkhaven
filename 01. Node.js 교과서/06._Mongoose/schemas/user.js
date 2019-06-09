// Schema => similar to "models" in Sequelize!

const mongoose = require('mongoose');

const { Schema } = mongoose;
const userSchema = new Schema({
    name: {
        type: String,
        require: true,
        unique: true,
    },
    age: {
        type: Number,
        required: true,
    },
    married: {
        type: Boolean,
        require: true,
    },
    comment: String,
    createdAt: {
        type: Date,
        default: Date.now,
    },
});

module.exports = mongoose.model('User', userSchema);
/* mongoose.model()
    1st argument becomes name of MongoDB collection
        if first letter is uppercase, make it lowercase, then make the word plural and use that as the name of the collection
        "User" => "users" collection is created
    ALTERNATIVELY,
        mongoose.model(A, B, C)
            Instead of modifying and using A, this makes MongoDB use C (3rd argument) as the name of the collection
 */