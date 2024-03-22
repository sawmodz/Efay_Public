document.getElementById("search").addEventListener("click", ()=>{
    let search = document.getElementById("searchInput").value;
    window.location.href = "/search?search=" + search;
})