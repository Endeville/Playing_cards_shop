const header = document.querySelector("header");
const sortBtn=document.querySelector(".search .sort");
const searchbar=document.querySelector(".search");

function check() {
    if (window.scrollY === 0) {
        header.classList.add("top");
        sortBtn.value="Sort";
    } else {
        header.classList.remove("top");
        sortBtn.value=String.fromCharCode(parseInt("021F5",16));
    }

    if(window.innerWidth<1450){
        searchbar.style.display="none";
    }else{
        searchbar.style.display="flex";
    }
}

window.addEventListener("scroll", check);
window.addEventListener("load", check);