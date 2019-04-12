/* This is a basic settings file for leaving log files
        For use with other programs, copy this file and tweak the settings
        Requires installation of 'winston', 'winston-daily-rotate-file', 'moment' modules

   ...currently results in ENOENT: no such file or directory, mkdir 'log\server.)2019-04-12.4/12\'
*/

var winston = require('winston'); // Module for handling logs
var winstonDaily = require('winston-daily-rotate-file'); // Module for handling daily logs
var moment = require('moment'); // Module for handling time & date

/* winston module's log levels (lowest to highest)
    debug > info > notice > warning > error > crit > alert > emerg
*/

function timeStampFormat() {
    return moment().format('YYYY-MM-DD HH:mm:ss.SSS ZZ');
    // ex) '2019-04-12 12:00:05.200 +0900
}

var logger = new (winston.Logger)({ // 'Logger' -> refers to the object making logs
    transports: [ // 'transports' -> attribute of 'winston' module's 'Logger' that can transport various data
        new (winstonDaily)({
            name : 'info-file'
            , filename : './log/server/'
            , datePattern : '_YYYY-MM-DD.log'
            , colorize : false
            , maxsize : 50000000 // each log file can be maximum 50MB in size
            , maxFiles : 1000 // total permissible number of log files is 1000
            , level : 'info' // only 'info' level logs are logged
            , showLevel : true
            , json : false
            , timestamp : timeStampFormat
        }),
        new (winston.transports.Console)({ // in addition leaving a log file, data is printed on the console
            name : 'debug-console'
            , colorize : true
            , level : 'debug'
            , showLevel : true
            , json : false
            , timestamp : timeStampFormat
        })
    ],
    exceptionHandlers : [
        new (winstonDaily)({
            name : 'exception-file'
            , filename : './log/exception/'
            , datePattern : '_YYYY-MM-DD.log'
            , colorize : false
            , maxsize : 50000000
            , maxFiles : 1000
            , level : 'error'
            , showLevel : true
            , json : false
            , timestamp : timeStampFormat
        }),
        new (winston.transports.Console)({
            name : 'exception-console'
            , colorize : true
            , level : 'debug'
            , showLevel : true
            , json : false
            , timestamp : timeStampFormat
        })
    ]
});