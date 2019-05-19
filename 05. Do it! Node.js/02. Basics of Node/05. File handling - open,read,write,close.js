/* Instead of using readFile(Sync) or writeFile(Sync)...
        directly open, read, write, and close files
 */
var fs = require('fs');

// open, write, & close
fs.open('./output.txt', 'w', function(err, fd) {
                     // 'w' is flag, indicating whether to read or write or append ('r', 'w', 'w+', 'a+')
    if(err) throw err;

    var buf = new Buffer('안녕!\n'); // contents of Buffer object are what is to be written in the target file
                                     // Buffer object used to read and write binary data
    fs.write(fd, buf, 0, buf.length, null, function(err, written, buffer) {
        if(err) throw err;

        console.log('writing : ', err, written, buffer);

        fs.close(fd, function() {
            console.log('파일 열고 데이터 쓰고 파일 닫기 완료.');
        });
    });
});

// open, read, & close
fs.open('./output.txt', 'r', function(err, fd) {
    if(err) throw err;

    var buf = new Buffer(10);
    console.log('버퍼 타입 : %s', Buffer.isBuffer(buf));

    fs.read(fd, buf, 0, buf.length, null, function(err, bytesRead, buffer) {
        if(err) throw err;

        var inStr = buffer.toString('utf8', 0, bytesRead);
        console.log('파일에서 읽은 데이터 : %s', inStr);

        console.log('reading : ', err, bytesRead, buffer);

        fs.close(fd, function() {
            console.log('output.txt 파일 열고 읽기 완료.');
        });
    });
});