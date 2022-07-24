const minusBtns=document.querySelectorAll(".minus");
minusBtns.forEach(e=>e.addEventListener("click", (ev)=>{
    ev.preventDefault();

    const priceCont=ev.target.parentElement.parentElement.querySelector(".product-price");

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    const pricePerProduct=Number(priceCont.textContent.substring(0, priceCont.textContent.length-1))/Number(quantitySpan.textContent);

    quantitySpan.textContent=String(Number(quantitySpan.textContent)-1);

    if(Number(quantitySpan.textContent)===0){
        ev.target.disabled=true;
    }

    priceCont.textContent=String((Math.round(pricePerProduct*Number(quantitySpan.textContent) * 100) / 100).toFixed(2)) + "€";
}))

const plusBtns=document.querySelectorAll(".plus");
plusBtns.forEach(e=>e.addEventListener("click", (ev)=>{
    ev.preventDefault();

    const priceCont=ev.target.parentElement.parentElement.querySelector(".product-price");

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    const pricePerProduct=Number(priceCont.textContent.substring(0, priceCont.textContent.length-1))/Number(quantitySpan.textContent);

    quantitySpan.textContent=String(Number(quantitySpan.textContent)+1);

    if(Number(quantitySpan.textContent)===1){
        parent.querySelector(".minus").disabled=false;
    }


    priceCont.textContent=String((Math.round(pricePerProduct*Number(quantitySpan.textContent) * 100) / 100).toFixed(2)) + "€";
}))