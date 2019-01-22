<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="/starkhaven/insertAction(EMP).jsp" method="post">
        <ul>
            <li>
                <label for="empno">사원번호 : </label>
                <input type="number" id="empno" name="EMPNO" maxlength="4" required>
            </li>
            <li>
                <label for="ename">사원이름 : </label>
                <input type="text" id="ename" name="ENAME" maxlength="10" required>
            </li>
            <li>
                <label for="job">직급 : </label>
                <input type="text" id="job" name="JOB" maxlength="9" required>
            </li>
            <li>
                <label for="mgr">메니저 : </label>
                <select name="MGR" id="mgr" required>
                    <option value="0">NONE</option>
                    <option value="7839">KING</option>
                    <option value="7698">BLAKE</option>
                    <option value="7782">CLARK</option>
                    <option value="7566">JONES</option>
                    <option value="7788">SCOTT</option>
                    <option value="7902">FORD</option>
                </select>
            </li>
            <li>
                <label for="hiredate">입사일 : </label>
                <input type="date" id="hiredate" name="HIREDATE" required>
            </li>
            <li>
                <label for="sal">급여 : </label>
                <input type="number" id="sal" name="SAL" maxlength="7" required>
            </li>
            <li>
                <label for="comm">커미션 : </label>
                <input type="number" id="comm" name="COMM" maxlength="7" required>
            </li>
            <li>
                <label for="deptno">부서번호 : </label>
                <select name="DEPTNO" id="deptno" required>
                    <option value="10">ACCOUNTING</option>
                    <option value="20">RESEARCH</option>
                    <option value="30">SALES</option>
                    <option value="40">OPERATIONS</option>
                </select>
            </li>
            <li>
                <input type="submit" value="전송">
            </li>
        </ul>
    </form>
</body>
</html>