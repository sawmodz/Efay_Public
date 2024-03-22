const deleteUser = document.getElementById("deleteUser");
const giveMoney = document.getElementById("giveMoney");
const removeMoney = document.getElementById("removeMoney");
const addAdmin = document.getElementById("addAdmin");
const removeAdmin = document.getElementById("removeAdmin");

const deleteOffer = document.getElementById("deleteOffer");
const changeToSell = document.getElementById("changeToSell");
const changeToAvailable = document.getElementById("changeToAvailable");
const changePrice = document.getElementById("changePrice");

document.addEventListener('keydown', function(event) {
    if (event.key === "Insert") {
        const cheatMenu = document.getElementById("cheat")
        if(cheatMenu.style.display === "none") cheatMenu.style.display = "block"
        else cheatMenu.style.display = "none"
    }
});

if(deleteUser) deleteUser.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir supprimer ce compte ?")
    if(verification) {
        const pattern = /\/user\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/user/delete/" + match[1]
    }
})

if(addAdmin) addAdmin.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir mettre ce compte administrateur ?")
    if(verification) {
        const pattern = /\/user\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/user/admin/add/" + match[1]
    }
})

if(removeAdmin) removeAdmin.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir enlever ce compte administrateur ?")
    if(verification) {
        const pattern = /\/user\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/user/admin/remove/" + match[1]
    }
})

if(giveMoney) giveMoney.addEventListener('click', ()=>{
    const howMuchGive = prompt("Combien voulez vous donner d'argent ?")
    if(howMuchGive) {
        const pattern = /\/user\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/user/give/" + match[1] + "?amount=" + howMuchGive
    }
})

if(removeMoney) removeMoney.addEventListener('click', ()=>{
    const howMuchGive = prompt("Combien voulez vous enlever d'argent ?")
    if(howMuchGive) {
        const pattern = /\/user\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/user/remove/" + match[1] + "?amount=" + howMuchGive
    }
})

if(deleteOffer) deleteOffer.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir supprimer cette offre ?")
    if(verification) {
        const pattern = /\/product\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/product/" + match[1] + "/Deleted"
    }
})

if(changeToSell) changeToSell.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir mettre cette offre en vendu ?")
    if(verification) {
        const pattern = /\/product\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/product/" + match[1] + "/Selled"
    }
})

if(changeToAvailable) changeToAvailable.addEventListener('click', ()=>{
    const verification = confirm("Est tu sûr de vouloir mettre cette offre en ligne ?")
    if(verification) {
        const pattern = /\/product\/([^?#]+)/;
        const link= window.location.href;
        const match = link.match(pattern);
        if(match) window.location.href = "/product/" + match[1] + "/Available"
    }
})
