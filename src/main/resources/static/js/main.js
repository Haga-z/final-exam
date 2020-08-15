'use strict';

function search() {
    let value = document.getElementById("search-input").value;
    if(value.length === 0){
        return;
    }else{
        window.location.href = '/search/'+value;
    }

};