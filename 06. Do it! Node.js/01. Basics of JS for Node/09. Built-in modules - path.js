/* The "path" built-in module is used for manipulating file paths */
var path = require('path');

// 디렉터리 이름 합치기
var directories = ["users", "user", "Alice", "personal", "docs"];
var docsDirectory = directories.join(path.sep); /* If "path.sep" is not provided, joins the given strings with commas */
console.log('문서 디렉터리 : %s', docsDirectory);

// 디렉터리 이름과 파일 이름 합치기
var curPath = path.join(docsDirectory, 'homework.exe');
console.log('파일 패스 : %s', curPath);

// 패스에서 디렉터리, 파일명, 확장자 구별하기
var dirname = path.dirname(curPath);
var extname = path.extname(curPath);
var basename = path.basename(curPath, '.txt');
            /* path.basename()'s 2nd parameter is OPTIONAL
                If a target extension is designated, target extension is excluded from basename
                    (only if the actual extension is the same as the target extension)
            */
console.log('디렉터리 : %s, 파일명 : %s, 확장자 : %s', dirname, basename, extname);