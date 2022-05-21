$(function () {
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
});

const wrapper = document.querySelector("#wrapper");

window.addEventListener("load", onResize);
window.addEventListener("resize", onResize);

function onResize(ev) {
    ev.preventDefault();

    wrapper.classList.remove("toggled");
}