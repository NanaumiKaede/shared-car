$().ready(() => {
    let str = window.sessionStorage.getItem("user");
    let user = JSON.parse(str);
    if (user != undefined) {
        $('#loginBox').empty();
        $('#loginBox').append("<span style='color: rgba(255, 255, 255,.75)'>欢迎你," + user.name + "</span><a href='javascript:void(0)' onclick='logout()'>注销</a>");
    }
})

function logout() {
    window.sessionStorage.removeItem("user");
    window.location.href='index.html';
}

function page(result) {
    $('#total').text(result)
    let totalpage = Math.ceil(result / 5);
    for (let i = 2; i <= totalpage; i++) {
        let text = `<li class="page-item number"><a class="page-link" href="#">${i}</a></li>`
        $('#numberList').append(text);
    }
}