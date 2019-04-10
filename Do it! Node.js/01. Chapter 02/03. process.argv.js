/* process.agrv() shows the parameters (contained within an array) being passed to the process as it is executed */
console.log('argv 속성의 파라미터 수 : ' + process.argv.length);
console.dir(process.argv);

if(process.argv.length > 2) {
    console.log('세버째 파라미터 값 : %s', process.argv[2]);
}

/* A function must be provided as a parameter for forEach(); 
        this function may receive two parameters,
            1st parameter is each element in the array.
            2nd parameter is the index value of each element
*/
process.argv.forEach((element, index) => {
    console.log(index + ':', element);
});