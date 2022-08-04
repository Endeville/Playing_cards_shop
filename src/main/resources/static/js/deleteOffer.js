const deleteOfferBtns=document.querySelectorAll(".deleteBtn");
const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

const closeBtn=document.getElementById("closeConsentModalBtn");
const continueBtn=document.getElementById("continueBtn");

for (const deleteOfferBtn of deleteOfferBtns) {
    deleteOfferBtn.addEventListener("click", (ev)=>{
        ev.preventDefault();
        const id=Number(ev.currentTarget.id.substring(7));
        showConsentModal(id);
    })
}

function showConsentModal(id){
    continueBtn.addEventListener("click", (ev)=>{
        deleteOffer(ev, id)
    })
}

async function deleteOffer(ev, id){
    closeBtn.click();

    const options={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            id
        })
    };

    await fetch("http://localhost:8000/api/offers/delete", options);
    window.location="/offers/all";
}