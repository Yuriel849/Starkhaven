Important ES2015+ Changes to JavaScript

### Replace "var" with "const" & "let"
> "var" VS "const" & "let"
>> var's scope is a function, if it is inside a function, it works anywhere
>> const's scope is a block, meaning a const declared within an if block will only work within that block
> "const" VS "let"
>> const is a constant, the value cannot be changed and a value must be entered at initialization
>> let is a variable with a block scope, but unlike const it's value can be changed

### Template string
> Uses ` (button above 'tab' and to left of '1') instead of ' or "
> Can include variables as ${variable} and use ' and " without escape char, instead of using concatenation
>> ex) `The name is ${name} and the age is ${age}`

### Changes to the object literal
const obj = {
	sayJS() { console.log(‘JS’); }, // 객체 내 메서드에 함수 연결할 때 “:”와 “function()” 불필요
	sayNode, // 속성명과 변수명 일치할 때 생략 가능
	[es+6] : ‘Fantastic’, // 속성명 동적으로 생성 (예전에는 객체 밖에서 만들어야 했다)
};

### Arrow function
> function() 선언부 대신 => 만 사용하면된다
>> Following four functions are identical
>>> function add1(x, y) { return x + y; }
>>> const add2 = (x, y) => { return x + y; }
>>> const add3 = (x, y) => x + y; // return 없이 return할 식을 그대로 적어도 된다
>>> const add4 = (x, y) => (x + y); // 가독성을 위해 괄호 사용
> 인수가 하나라면 괄호 생략 가능
> 인수가 없을 경우 빈 괄호 사용
>> () => { statements }
> this 바인딩 방식 변화
>> 상위 스코프의 this를 물려받음. ex) forEach() 쓰더라도 this 의미 바뀌지 않는다.

### 비구조화 할당
> const { getCandy, status: {count} } = candyMachine; // 초기화되는 2개 변수는 getCandy와 count

### Promise
> callback 대신 promise 사용
>> 프로미스 사용하려면 new Promise로 객체 생성하고 resolve & reject를 매개변수로 갖는 함수를 안에 넣는다
>> 프로미스에는 추가로 then() & catch() 붙여서, resolve(data)하면 then() 실행되고, reject(data)하면 catch() 실행된다. then() & catch()는 resolve & reject했을 때 반환되는 값(data)을 매개변수로 받을 수 있다.
>> then & catch를 여러 개 사용할 수 있으며, 순차적으로 실행된다.
>> Promise.all() 사용하면 복수의 프로미스를 실행할 수 있다
>>> Promise.all 속의 then은 모든 프로미스가 resolve되야지 실행되고 (그때까지 대기한다) 하나라도 resolve되지 않는다면 catch가 실행된다.
> callback을 promise로 바꾸려면 해당 메서드가 promise 방식을 지원해야 한다!

### async/await
> promise도 길어서, 더 간결하게 바꿔서 비동기 프로그래밍을 하게 해준다.
>> 함수 선언부에 "async"를 붙이고 promise 앞에다가 "await"를 붙인다. async & await 사용하면 해당 promise가 resolve될 때까지 기다린 후 넘어간다.
>> try-catch문을 별도로 사용해야 한다 (promise의 then & catch를 사용하지 않기 때문에)

화살표함수에 async/await 사용하기
const findAndSaveUsers = async (Users) => {
	try {
		let user = await Users.findOne({});
		user.name = ‘zero’;
		user = await user.save();
		user = await Users.findOne({ gender : ‘m’ });
	} catch(error) {
		console.error(error);
	}
}
promise.all 대신 “for await of” 사용
(async () => {
	for await (promise of [promise1, promise2]) {
		console.log(promise);
	}
})(); // 익명함수를 선언과 동시에 호출

### encodeURIComponent & decodeURIComponent
> URL에 한글을 넣을 경우 encodeURIComponent(‘XXX’) 사용한다
> 받을 때는 decodeURIComponent(‘XXX’) 사용해야 한다
> 둘 다 브라우저 & Node.js에서 사용가능하다

### Data attribute
> HTML5에서 HTML관련 데이터를 저장하는 공식적인 방법
> HTML 태그 속성을 “data-”로 시작하도록 이름 짓는다
> 앞에 “data-”가 붙은 속성은 셀렉터로 찾을 때 parent에다가 “.dataset”을 붙이면 쉽게 모두 가져올 수 있다. 반대로 “dataset.속성명”해도 HTML 태그에 반영된다 (“data-속성명”으로)