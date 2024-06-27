const flags = [
    { country: "Albania", src: "../flags/albania.svg" },
    { country: "Andorra", src: "../flags/andorra.svg" },
    { country: "Austria", src: "../flags/austria.svg" },
    { country: "Belarus", src: "../flags/belarus.svg" },
    { country: "Belgium", src: "../flags/belgium.svg" },
    { country: "Bosnia and Herzegovina", src: "../flags/bosnia-and-herzegovina.svg" },
    { country: "Bulgaria", src: "../flags/bulgaria.svg" },
    { country: "Croatia", src: "../flags/croatia.svg" },
    { country: "Cyprus", src: "../flags/cyprus.svg" },
    { country: "Czechia", src: "../flags/czechia.svg" },
    { country: "Denmark", src: "../flags/denmark.svg" },
    { country: "Estonia", src: "../flags/estonia.svg" },
    { country: "Finland", src: "../flags/finland.svg" },
    { country: "France", src: "../flags/france.svg" },
    { country: "Germany", src: "../flags/germany.svg" },
    { country: "Greece", src: "../flags/greece.svg" },
    { country: "Hungary", src: "../flags/hungary.svg" },
    { country: "Iceland", src: "../flags/iceland.svg" },
    { country: "Ireland", src: "../flags/ireland.svg" },
    { country: "Italy", src: "../flags/italy.svg" },
    { country: "Kosovo", src: "../flags/kosovo.svg" },
    { country: "Latvia", src: "../flags/latvia.svg" },
    { country: "Liechtenstein", src: "../flags/liechtenstein.svg" },
    { country: "Lithuania", src: "../flags/lithuania.svg" },
    { country: "Luxembourg", src: "../flags/luxembourg.svg" },
    { country: "Malta", src: "../flags/malta.svg" },
    { country: "Moldova", src: "../flags/moldova.svg" },
    { country: "Monaco", src: "../flags/monaco.svg" },
    { country: "Montenegro", src: "../flags/montenegro.svg" },
    { country: "Netherlands", src: "../flags/netherlands.svg" },
    { country: "North Macedonia", src: "../flags/north-macedonia.svg" },
    { country: "Norway", src: "../flags/norway.svg" },
    { country: "Poland", src: "../flags/poland.svg" },
    { country: "Portugal", src: "../flags/portugal.svg" },
    { country: "Romania", src: "../flags/romania.svg" },
    { country: "San Marino", src: "../flags/san-marino.svg" },
    { country: "Serbia", src: "../flags/serbia.svg" },
    { country: "Slovakia", src: "../flags/slovakia.svg" },
    { country: "Slovenia", src: "../flags/slovenia.svg" },
    { country: "Spain", src: "../flags/spain.svg" },
    { country: "Sweden", src: "../flags/sweden.svg" },
    { country: "Switzerland", src: "../flags/switzerland.svg" },
    { country: "Ukraine", src: "../flags/ukraine.svg" },
    { country: "United Kingdom", src: "../flags/united-kingdom.svg" },
    { country: "Vatican City", src: "../flags/vatican-city.svg" }
];

const europeMap = document.getElementById("europe-map");
const flagElement = document.getElementById("flag");
const timerElement = document.getElementById("timer");
const correctGuessesElement = document.getElementById("correct-guesses");
const confirmButton = document.getElementById("confirm-button");
const stopButton = document.getElementById("stop-button");
const popup = document.createElement('div');
popup.id = 'popup';
document.body.appendChild(popup);

let timer = -1;
let timerInterval = null;
let correctGuesses = 0;
let currentCountry = "";
let selectedCountry = null;
const guessedCountries = new Set();

function startGame() {
    timer = -1;
    correctGuesses = 0;
    updateTimer();
    updateCorrectGuesses(0);
    loadNextFlag();
    popup.style.display = 'none';
    timerInterval = setInterval(updateTimer, 1000);
}

function updateTimer() {
    timer++;
    if (timer < 60) {
        timerElement.innerText = `${timer} s`;
    } else {
        const minutes = Math.floor(timer / 60);
        const seconds = timer % 60;
        timerElement.innerText = `${minutes} m ${seconds} s`;
    }
}

function loadNextFlag() {
    const availableCountries = flags.filter(flag => !guessedCountries.has(flag.country));
    if (availableCountries.length === 0) {
        endGame();
        return;
    }
    const randomFlag = availableCountries[Math.floor(Math.random() * availableCountries.length)];
    currentCountry = randomFlag.country;
    flagElement.src = randomFlag.src;
    flagElement.alt = `${currentCountry} Flag`;
}

function updateCorrectGuesses(count) {
    correctGuesses += count;
    correctGuessesElement.innerText = `${correctGuesses}/45`;
}

function saveTime(time) {
    fetch('http://localhost:8080/setBestTime', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(time)
    }).then(response => {
        if (response.ok) {
            return response;
        } else if (response.status >= 400 && response.status < 500) {
            return response;
        } else {
            throw new Error('Errore');
        }
    }).then(d => {
        console.log('Form submitted successfully');
    }).catch(error => {
        console.error('Form submission error:', error);
    });
}

function endGame() {
    clearInterval(timerInterval);
    popup.innerHTML = `
        <h2>Game Over</h2>
        <p>Your time is: ${timerElement.innerText}</p>
        <p>You guessed ${correctGuesses} countries correctly.</p>
        <button onclick="startGame()">Play Again</button>
    `;
    popup.style.display = 'block';
    saveTime(timer);
}

function handleCountryClick(event) {
    const clickedCountry = event.target.getAttribute('country');
    if (clickedCountry && !guessedCountries.has(clickedCountry)) {
        if (selectedCountry) {
            selectedCountry.classList.remove('selected');
        }
        selectedCountry = event.target;
        selectedCountry.classList.add('selected');
        confirmButton.classList.add('active');
        confirmButton.disabled = false;
    }
}

function handleConfirmClick() {
    if (selectedCountry) {
        const clickedCountry = selectedCountry.getAttribute('country');
        if (clickedCountry === currentCountry) {
            updateCorrectGuesses(1);
            guessedCountries.add(clickedCountry);
            selectedCountry.classList.add('guessed');
            selectedCountry.removeEventListener('click', handleCountryClick);
        }
        selectedCountry.classList.remove('selected');
        selectedCountry = null;
        confirmButton.classList.remove('active');
        confirmButton.disabled = true;

        if (guessedCountries.size >= flags.length) {
            endGame();
        } else {
            loadNextFlag();
        }
    }
}

function handleStopClick() {
    endGame();
}

europeMap.addEventListener('click', handleCountryClick);
confirmButton.addEventListener('click', handleConfirmClick);
stopButton.addEventListener('click', handleStopClick);

window.onload = startGame;
