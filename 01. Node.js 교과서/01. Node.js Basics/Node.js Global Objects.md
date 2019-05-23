## Node.js Global Objects (= Built-in Objects)

### 1. "global"
> Global object same as a browser's "window" object
>> "window" & "document" objects CANNOT be used in Node.js (there's no DOM or BOM) <br>

> 사용할 때 “global.”은 생략해도 된다.
>> ex) global.require() => require() <br>
ex) global.console.log() => console.log()

### 2. "console"
> Unlike the browser's "console" object, which is contained in the "window" object, Node.js' "console" object is in the "global" object

> console methods
>> console.time(레이블) & console.timeEnd(레이블) => 같은 레이블을 가진 time & timeEnd 사이의 시간을 측정한다. <br>
>> Console.log(내용) => 평범한 로그를 콘솔(==터미널)에 출력한다. <br>
>> Console.error(내용) => 에러 내용을 콘솔에 출력한다. <br>
>> Console.trace(레이블) => 에러가 어디서 발생했는지 추적할 수 있게 해준다. 레이블의 위치까지 가는 trace를 출력한다. <br>
>> Console.dir(객체, 옵션) => 객체를 콘솔에 표시할 때 사용; 1st 인자는 객체, 2nd 인자는 옵션 <br>
>>> 옵션이 "colors: true"하면 콘솔 문자에 색을 더해서 가독성을 향상시킨다. <br>
>>> 옵션이 "depth: 숫자"이면 객체 안의 몇번째 단계까지 보여줄지를 결정한다 (기본값 = 2) <br>

### 3. "setTimeout()" / "setInterval()" / "setIntermediate()"
> setTimeout(callback, millisecond) => Calls the given callback after the given milliseconds have passed <br>
clearTimeout(ID) => Cancels the setTimeout() with the given ID <br>
setInterval(callback, millisecond) => Calls the given callback repeatedly every time the given milliseconds have passed <br>
clearInterval(아이디) => Cancels the setInterval() with the given ID <br>
setImmediate(callback) => Immediately calls the given callback <br>
clearImmediate(아이디) => Cancels the setImmediate with the given ID <br>
setImmediate !== setTimeout(callback, 0) <br>
>> 상황에 따라 어느 하나가 다른 것보다 빨리 실행될 수 있다 <br>
그냥 setTimeout(callback, 0)은 안 쓰는게 좋다

### 4. "__filename" & "__dirname"
> __filename => 현재 파일명 <br>
__dirname => 현재 경로

### 5. "module.exports" & "exports"
> 같은 객체를 참조한다! => module.exports 사용하는 대신 그냥 exports 사용해도 된다!
>> 정확히는 exports는 module.exports 참조하고 module.exports는 다른 것을 참조 => 같은 모듈에서 둘을 섞어 사용하는 것은 안 좋다 <br>
module.exports는 무엇이든 받을 수 있지만 exports는 속성명 & 속성값만 넣을 수 있다 (함수를 대입할 수 없다!)

### 6. "process"
> process => 현재 실행되고 있는 Node 프로세스에 대한 정보를 담고 있다
>> process.version => 설치된 노드 버전 <br>
process.arch => 프로세서 아키텍처 정보 <br>
process.platform => 운영체제 플랫폼 정보 <br>
process.pid => 현재 프로세스 아이디 <br>
process.uptime() => 프로세스 시작 후 흐른 시간 (단위: 초) <br>
process.execPath => 노드의 경로 <br>
process.cwd() => 현재 프로세스가 실행되는 위치 <br>
process.cpuUsage() => 현재 cpu 사용량 <br>
process.env => 시스템 환경 변수와 값을 출력; 서비스의 중요한 키를 저장하는 공간으로도 사용 (ex) 비밀번호) <br>
process.exit() => 현재 실행 중인 Node 프로세스 종료 (서버에는 사용 X) <br>
process.nextTick(callback) => Immediately calls the given callback function
>>> process.nextTick() & Promise => microtask라고 불리며 setImmediate이나 setTimeout보다 우선시 된다 (먼저 실행된다) <br>
