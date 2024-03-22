document.addEventListener('DOMContentLoaded', () => {
    let bip = 0;
    const myEasterEgg = document.getElementsByClassName("categoryTitle")
    myEasterEgg[0].addEventListener('click', () => {
        bip++;
        if (bip === 10) {
            document.getElementsByClassName("titlea")[0].innerText = "🦀🦀🦀🦀🦀🦀";
            bip = 0;
        }
    })
})