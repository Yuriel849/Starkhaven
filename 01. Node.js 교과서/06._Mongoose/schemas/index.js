const mongoose = require('mongoose');

module.exports = () => {
    const connect = () => {
        if(process.env.NODE_ENV !== 'production') {
            mongoose.set('debug', true); // if this is not production code, prints queries on the console
        }
        mongoose.connect('mongodb://Yuriel:Sapph1r3@localhost:27017/admin', { // attempts to connect Mongoose to MongoDB
            dbName: 'nodejs', // although connection is to "admin" DB, what is actually required is "nodejs" DB
        }, (error) => { // callback to check if the connection is successful
            if(error) {
                console.log('MongoDB connection error', error);
            } else {
                console.log('MongoDB connection successful');
            }
        });
    };
    connect();
    mongoose.connection.on('error', (error) => {
        console.error('MongoDB connection error', error);
    });
    mongoose.connection.on('disconnected', () => {
        console.error('MongoDB disconnected. Attempting to reconnect.');
        connect();
    });
    require('./user'); // connects User schema and Comment schema
    require('./comment');
};