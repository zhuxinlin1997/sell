
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>网上宠物商店</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/sell/css/normalize.min.css">
    <link rel="stylesheet" href="/sell/css/style1.css">
</head>
<body>
<div class="form">
    <ul class="tab-group">
        <li class="tab"><a href="#signup">注册</a></li>
        <li class="tab active"><a href="#login">登录</a></li>
    </ul>
    <div class="tab-content">
        <div id="login">
            <h1>欢迎登陆!</h1>
            <form action="/sell/buyer/login" method="post">
                <div class="field-wrap">
                    <label>
                        用户名<span class="req">*</span>
                    </label>
                    <input name="username" type="text" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>
                        密码<span class="req">*</span>
                    </label>
                    <input name="password" type="password" required autocomplete="off"/>
                </div>

                <p class="forgot"><a href="#">忘记密码?</a></p>

                <button type="submit" class="button button-block"/>登录</button>

            </form>

        </div>
        <div id="signup">
            <h1>欢迎注册</h1>

            <form action="/sell/buyer/reg" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            用户名<span class="req">*</span>
                        </label>
                        <input name="username" type="text" required autocomplete="off" />
                    </div>

                    <div class="field-wrap">
                        <label>
                            密码<span class="answer">*</span>
                        </label>
                        <input name="password" type="text" required autocomplete="off"/>
                    </div>
                </div>

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            密保问题<span class="ques">*</span>
                        </label>
                        <input name="relQuestion" type="text" required autocomplete="off" />
                    </div>

                    <div class="field-wrap">
                        <label>
                            密保答案<span class="answer">*</span>
                        </label>
                        <input name="relPassword" type="text"required autocomplete="off"/>
                    </div>
                </div>
                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            真实姓名<span class="ques">*</span>
                        </label>
                        <input name="userRelName" type="text" required autocomplete="off" />
                    </div>
                </div>
                <button type="submit" class="button button-block"/>确认注册</button>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='/sell/js/jquery-1.10.0.js'></script>
<script  src="/sell/js/index.js"></script>
</body>

</html>
