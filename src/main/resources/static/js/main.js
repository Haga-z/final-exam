'use strict';

function search() {
    let value = document.getElementById("search-input").value;
    if(value.length === 0){
        return;
    }else{
        window.location.href = '/search/'+value;
    }

};

function scrollToBottom() {
    window.scrollTo({ left: 0, top: document.body.scrollHeight, behavior: "smooth" });
}