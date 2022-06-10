const header = document.querySelector("header");
const sortBtn=document.querySelector("#openSortModalBtn");
const searchbar=document.querySelector(".search");

function check() {
    if (window.scrollY === 0) {
        header.classList.add("top");
        sortBtn.value="Sort";
    } else {
        header.classList.remove("top");
        sortBtn.value=String.fromCharCode(parseInt("021F5",16));
    }
}

window.addEventListener("scroll", check);
window.addEventListener("load", check);