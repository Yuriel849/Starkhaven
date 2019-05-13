/* Node's file handling
        Node provides functions for (1) managing files and (2) managing directories,
            and (3) managing Blocking IO and managing (4) Non-Blocking IO
                (Blocking IO methods add 'Sync' to the front of their names)
*/

var fs = require('fs');

// Blocking IO
var data = fs.readFileSync('./package.json', 'utf8');
console.log('Blocking IO');
console.log(data);

// Non-Blocking IO
fs.readFile('./package.json', 'utf8', function(err, data) { // because it's non-blocking, requires callback function
    if(err == null) { // if there was no error in reading the designated file, err will be null
        console.log('Non-Blocking IO');
        console.log(data);
    }
})
console.log('프로젝트 폴더 안의 package.json 파일을 읽도록 요청했습니다.');