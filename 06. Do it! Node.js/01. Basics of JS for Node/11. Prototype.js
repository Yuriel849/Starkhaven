/* Prototype -> Create a base object ('prototype') from which new instances are created */

function Person(name, age) { // constructor for prototype object
    this.name = name;
    this.age = age;
}

// Person.prototype.walk == Person.walk -> Same, but adding 'prototype' is more efficient
Person.prototype.walk = function(speed) {
    console.log(speed + 'km 속도로 걸어갑니다.');
}

var person01 = new Person('John', 15);
var person02 = new Person('Alice', 20);

console.log(person01.name + ' 객체의 walk(10)을 호출합니다.');
person01.walk(10);