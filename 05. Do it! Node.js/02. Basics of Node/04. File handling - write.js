var fs = require('fs');

fs.writeFile('./output.txt', 'Hello World! Brought to you by Node.js', function(err) {
                    // if there was no error in writing to the designated file, err is null
    if(err) {
        console.log('Error : ' + err);
    }

    console.log('output.txt 파일에 데이터 쓰기 완료.');
})