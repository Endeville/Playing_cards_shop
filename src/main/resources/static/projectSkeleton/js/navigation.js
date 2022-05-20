const menu=document.querySelector("#menu");
const hambBtn=document.querySelector("#hamburger");

window.addEventListener("resize",onResize);
window.addEventListener("load", onResize);

function onResize(ev){
    ev.preventDefault();

    if(window.innerWidth<1450){
        hambBtn.style.display="inline";
        menu.style.display="none";
    }else{
        hambBtn.style.display="none";
        menu.style.display="inline";
    }
}