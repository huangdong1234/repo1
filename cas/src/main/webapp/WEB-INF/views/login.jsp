<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <script type="text/javascript" src="/js/md5.js"></script>
    </head>
    <body>
    <h1> 登录</h1>

        <form action="/user/login" >
           <label>账号：</label> <input type="text" name="username" /><br/>
           <label>密码：</label>  <input type="password" name="password"  id="password" /><br/>
           <button tyee="submit" onclick="toMd5()">登录</button>
        </form>

    </body>
    <script type="text/javascript">

        function toMd5(){
            var passwordNode=document.getElementById("password");
            //加密前
            var hash=hex_md5(passwordNode.value);
            passwordNode.value=hash;
        }
    </script>
</html>
