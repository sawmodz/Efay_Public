const hidepassword = document.getElementById("togglePasswordVisibility")
const password = document.getElementById("password")

hidepassword.addEventListener('click', ()=>{
    if (password.type === "password") {
        password.type = "text";
        hidepassword.style.background = 'url("/img/view.png") no-repeat 0 0';
        hidepassword.style.backgroundSize = "28px 29px";
        hidepassword.style.backgroundPosition = "center";
    } else {
        password.type = "password";
        hidepassword.style.background = 'url("/img/hide.png") no-repeat 0 0';
        hidepassword.style.backgroundSize = "28px 29px";
        hidepassword.style.backgroundPosition = "center";
    }
})