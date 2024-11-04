const toggleBtn = document.querySelector("#trigger");
const menu = document.querySelector(".navbar-menu");
const icons = document.querySelector(".navbar-icons");
const mobileNavWorks = document.querySelector("#mobile-nav-works");
const mobileNavbarDropdownSubmenu = document.querySelector(".mobile-navbar-dropdown-submenu");

toggleBtn.addEventListener("change", () => {
    if (toggleBtn.checked) {
        menu.classList.toggle("active");
        icons.classList.toggle("active");
    } else {
        menu.classList.remove("active");
        icons.classList.remove("active");
    }
});

mobileNavWorks.addEventListener("change", () => {
    if (toggleBtn.checked) {
        mobileNavbarDropdownSubmenu.classList.toggle("active");
    } else {
        mobileNavbarDropdownSubmenu.classList.remove("active");
    }
})

function logoutHandler() {
    if(confirm("로그아웃 하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}