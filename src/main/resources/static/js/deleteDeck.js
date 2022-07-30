const deleteDeckBtns=document.querySelectorAll(".deleteBtn");
const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

const closeBtn=document.getElementById("closeConsentModalBtn");
const continueBtn=document.getElementById("continueBtn");

for (const deleteDeckBtn of deleteDeckBtns) {
    deleteDeckBtn.addEventListener("click", (ev)=>{
        ev.preventDefault();
        const title=ev.currentTarget.id.substring(7);
        showConsentModal(title, ev.currentTarget);
    })
}

function showConsentModal(title, target){
    continueBtn.addEventListener("click", (ev)=>{
        deleteDeck(ev, title, target)
    })
}

async function deleteDeck(ev, title, initialTarget){
    closeBtn.click();

    const options={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            title: title
        })
    };


    initialTarget.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.style.display='none';
    await fetch("http://localhost:8000/api/decks/delete", options);
}