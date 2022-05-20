const backTopBtn = document.getElementById("btn-back-to-top");
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
    ) {
        backTopBtn.style.display = "block";
    } else {
        backTopBtn.style.display = "none";
    }
}

backTopBtn.addEventListener("click", backToTop);

function backToTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}