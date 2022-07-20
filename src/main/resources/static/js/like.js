const likeBtn=document.querySelector(".heart-btn");



async function like(ev, deckTitle){
    ev.preventDefault();

    const options={
      method: "post",
      deckTitle: deckTitle
    };

    await fetch("localhost:8000/api/users/like", options);
}