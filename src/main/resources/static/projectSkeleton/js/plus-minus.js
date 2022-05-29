const minusBtns=document.querySelectorAll(".minus");
minusBtns.forEach(e=>e.addEventListener("click", (ev)=>{
    ev.preventDefault();

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    quantitySpan.textContent=String(Number(quantitySpan.textContent)-1);
    if(Number(quantitySpan.textContent)===0){
        ev.target.disabled=true;
    }
}))

const plusBtns=document.querySelectorAll(".plus");
plusBtns.forEach(e=>e.addEventListener("click", (ev)=>{
    ev.preventDefault();

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    quantitySpan.textContent=String(Number(quantitySpan.textContent)+1);

    if(Number(quantitySpan.textContent)===1){
        parent.querySelector(".minus").disabled=false;
    }
}))