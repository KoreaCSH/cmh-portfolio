const toggleBtn = document.querySelector(".navbar-toogleBtn");
const menu = document.querySelector(".navbar-menu");
const icons = document.querySelector(".navbar-icons");

toggleBtn.addEventListener("click", () => {
    menu.classList.toggle("active");
    icons.classList.toggle("active");
});

function logoutHandler() {
    if(confirm("로그아웃 하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}