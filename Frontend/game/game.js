const flags = [
    { country: "Afghanistan", src: "flags/afghanistan.svg" },
    { country: "Albania", src: "flags/albania.svg" },
    { country: "Algeria", src: "flags/algeria.svg" },
    { country: "Andorra", src: "flags/andorra.svg" },
    { country: "Angola", src: "flags/angola.svg" },
    { country: "Antigua and Barbuda", src: "flags/antigua-and-barbuda.svg" },
    { country: "Argentina", src: "flags/argentina.svg" },
    { country: "Armenia", src: "flags/armenia.svg" },
    { country: "Australia", src: "flags/australia.svg" },
    { country: "Austria", src: "flags/austria.svg" },
    { country: "Azerbaijan", src: "flags/azerbaijan.svg" },
    { country: "Bahamas", src: "flags/bahamas.svg" },
    { country: "Bahrain", src: "flags/bahrain.svg" },
    { country: "Bangladesh", src: "flags/bangladesh.svg" },
    { country: "Barbados", src: "flags/barbados.svg" },
    { country: "Belarus", src: "flags/belarus.svg" },
    { country: "Belgium", src: "flags/belgium.svg" },
    { country: "Belize", src: "flags/belize.svg" },
    { country: "Benin", src: "flags/benin.svg" },
    { country: "Bhutan", src: "flags/bhutan.svg" },
    { country: "Bolivia", src: "flags/bolivia.svg" },
    { country: "Bosnia and Herzegovina", src: "flags/bosnia-and-herzegovina.svg" },
    { country: "Botswana", src: "flags/botswana.svg" },
    { country: "Brazil", src: "flags/brazil.svg" },
    { country: "Brunei", src: "flags/brunei.svg" },
    { country: "Bulgaria", src: "flags/bulgaria.svg" },
    { country: "Burkina Faso", src: "flags/burkina-faso.svg" },
    { country: "Burundi", src: "flags/burundi.svg" },
    { country: "Cabo Verde", src: "flags/cabo-verde.svg" },
    { country: "Cambodia", src: "flags/cambodia.svg" },
    { country: "Cameroon", src: "flags/cameroon.svg" },
    { country: "Canada", src: "flags/canada.svg" },
    { country: "Central African Republic", src: "flags/central-african-republic.svg" },
    { country: "Chad", src: "flags/chad.svg" },
    { country: "Chile", src: "flags/chile.svg" },
    { country: "People's Republic of China", src: "flags/people's-republic-of-china.svg" },
    { country: "Colombia", src: "flags/colombia.svg" },
    { country: "Comoros", src: "flags/comoros.svg" },
    { country: "Congo", src: "flags/congo.svg" },
    { country: "Cook Islands", src: "flags/cook-islands.svg" },
    { country: "Costa Rica", src: "flags/costa-rica.svg" },
    { country: "Cote d'Ivoire", src: "flags/cote-d'ivoire.svg" },
    { country: "Croatia", src: "flags/croatia.svg" },
    { country: "Cuba", src: "flags/cuba.svg" },
    { country: "Cyprus", src: "flags/cyprus.svg" },
    { country: "Czechia", src: "flags/czechia.svg" },
    { country: "Denmark", src: "flags/denmark.svg" },
    { country: "Djibouti", src: "flags/djibouti.svg" },
    { country: "Dominica", src: "flags/dominica.svg" },
    { country: "Dominican Republic", src: "flags/dominican-republic.svg" },
    { country: "Ecuador", src: "flags/ecuador.svg" },
    { country: "Egypt", src: "flags/egypt.svg" },
    { country: "El Salvador", src: "flags/el-salvador.svg" },
    { country: "Equatorial Guinea", src: "flags/equatorial-guinea.svg" },
    { country: "Eritrea", src: "flags/eritrea.svg" },
    { country: "Estonia", src: "flags/estonia.svg" },
    { country: "Eswatini", src: "flags/eswatini.svg" },
    { country: "Ethiopia", src: "flags/ethiopia.svg" },
    { country: "Fiji", src: "flags/fiji.svg" },
    { country: "Finland", src: "flags/finland.svg" },
    { country: "France", src: "flags/france.svg" },
    { country: "Gabon", src: "flags/gabon.svg" },
    { country: "Gambia", src: "flags/gambia.svg" },
    { country: "Georgia", src: "flags/georgia.svg" },
    { country: "Germany", src: "flags/germany.svg" },
    { country: "Ghana", src: "flags/ghana.svg" },
    { country: "Greece", src: "flags/greece.svg" },
    { country: "Grenada", src: "flags/grenada.svg" },
    { country: "Guatemala", src: "flags/guatemala.svg" },
    { country: "Guinea", src: "flags/guinea.svg" },
    { country: "Guinea-Bissau", src: "flags/guinea-bissau.svg" },
    { country: "Guyana", src: "flags/guyana.svg" },
    { country: "Haiti", src: "flags/haiti.svg" },
    { country: "Honduras", src: "flags/honduras.svg" },
    { country: "Hungary", src: "flags/hungary.svg" },
    { country: "Iceland", src: "flags/iceland.svg" },
    { country: "India", src: "flags/india.svg" },
    { country: "Indonesia", src: "flags/indonesia.svg" },
    { country: "Iran", src: "flags/iran.svg" },
    { country: "Iraq", src: "flags/iraq.svg" },
    { country: "Ireland", src: "flags/ireland.svg" },
    { country: "Israel", src: "flags/israel.svg" },
    { country: "Italy", src: "flags/italy.svg" },
    { country: "Jamaica", src: "flags/jamaica.svg" },
    { country: "Japan", src: "flags/japan.svg" },
    { country: "Jordan", src: "flags/jordan.svg" },
    { country: "Kazakhstan", src: "flags/kazakhstan.svg" },
    { country: "Kenya", src: "flags/kenya.svg" },
    { country: "Kiribati", src: "flags/kiribati.svg" },
    { country: "North Korea", src: "flags/north-korea.svg" },
    { country: "South Korea", src: "flags/south-korea.svg" },
    { country: "Kosovo", src: "flags/kosovo.svg" },
    { country: "Kuwait", src: "flags/kuwait.svg" },
    { country: "Kyrgyzstan", src: "flags/kyrgyzstan.svg" },
    { country: "Laos", src: "flags/laos.svg" },
    { country: "Latvia", src: "flags/latvia.svg" },
    { country: "Lebanon", src: "flags/lebanon.svg" },
    { country: "Lesotho", src: "flags/lesotho.svg" },
    { country: "Liberia", src: "flags/liberia.svg" },
    { country: "Libya", src: "flags/libya.svg" },
    { country: "Liechtenstein", src: "flags/liechtenstein.svg" },
    { country: "Lithuania", src: "flags/lithuania.svg" },
    { country: "Luxembourg", src: "flags/luxembourg.svg" },
    { country: "Madagascar", src: "flags/madagascar.svg" },
    { country: "Malawi", src: "flags/malawi.svg" },
    { country: "Malaysia", src: "flags/malaysia.svg" },
    { country: "Maldives", src: "flags/maldives.svg" },
    { country: "Mali", src: "flags/mali.svg" },
    { country: "Malta", src: "flags/malta.svg" },
    { country: "Marshall Islands", src: "flags/marshall-islands.svg" },
    { country: "Mauritania", src: "flags/mauritania.svg" },
    { country: "Mauritius", src: "flags/mauritius.svg" },
    { country: "Mexico", src: "flags/mexico.svg" },
    { country: "Micronesia", src: "flags/micronesia.svg" },
    { country: "Moldova", src: "flags/moldova.svg" },
    { country: "Monaco", src: "flags/monaco.svg" },
    { country: "Mongolia", src: "flags/mongolia.svg" },
    { country: "Montenegro", src: "flags/montenegro.svg" },
    { country: "Morocco", src: "flags/morocco.svg" },
    { country: "Mozambique", src: "flags/mozambique.svg" },
    { country: "Myanmar", src: "flags/myanmar.svg" },
    { country: "Namibia", src: "flags/namibia.svg" },
    { country: "Nauru", src: "flags/nauru.svg" },
    { country: "Nepal", src: "flags/nepal.svg" },
    { country: "Netherlands", src: "flags/netherlands.svg" },
    { country: "New Zealand", src: "flags/new-zealand.svg" },
    { country: "Nicaragua", src: "flags/nicaragua.svg" },
    { country: "Niger", src: "flags/niger.svg" },
    { country: "Nigeria", src: "flags/nigeria.svg" },
    { country: "Niue", src: "flags/niue.svg" },
    { country: "North Macedonia", src: "flags/north-macedonia.svg" },
    { country: "Norway", src: "flags/norway.svg" },
    { country: "Oman", src: "flags/oman.svg" },
    { country: "Pakistan", src: "flags/pakistan.svg" },
    { country: "Palau", src: "flags/palau.svg" },
    { country: "Panama", src: "flags/panama.svg" },
    { country: "Papua New Guinea", src: "flags/papua-new-guinea.svg" },
    { country: "Paraguay", src: "flags/paraguay.svg" },
    { country: "Peru", src: "flags/peru.svg" },
    { country: "Philippines", src: "flags/philippines.svg" },
    { country: "Poland", src: "flags/poland.svg" },
    { country: "Portugal", src: "flags/portugal.svg" },
    { country: "Qatar", src: "flags/qatar.svg" },
    { country: "Romania", src: "flags/romania.svg" },
    { country: "Russia", src: "flags/russia.svg" },
    { country: "Rwanda", src: "flags/rwanda.svg" },
    { country: "Saint Kitts and Nevis", src: "flags/saint-kitts-and-nevis.svg" },
    { country: "Saint Lucia", src: "flags/saint-lucia.svg" },
    { country: "Saint Vincent and the Grenadines", src: "flags/saint-vincent-and-the-grenadines.svg" },
    { country: "Samoa", src: "flags/samoa.svg" },
    { country: "San Marino", src: "flags/san-marino.svg" },
    { country: "Saudi Arabia", src: "flags/saudi-arabia.svg" },
    { country: "Senegal", src: "flags/senegal.svg" },
    { country: "Serbia", src: "flags/serbia.svg" },
    { country: "Seychelles", src: "flags/seychelles.svg" },
    { country: "Sierra Leone", src: "flags/sierra-leone.svg" },
    { country: "Singapore", src: "flags/singapore.svg" },
    { country: "Slovakia", src: "flags/slovakia.svg" },
    { country: "Slovenia", src: "flags/slovenia.svg" },
    { country: "Solomon Islands", src: "flags/solomon-islands.svg" },
    { country: "Somalia", src: "flags/somalia.svg" },
    { country: "South Africa", src: "flags/south-africa.svg" },
    { country: "South Sudan", src: "flags/south-sudan.svg" },
    { country: "Spain", src: "flags/spain.svg" },
    { country: "Sri Lanka", src: "flags/sri-lanka.svg" },
    { country: "Sudan", src: "flags/sudan.svg" },
    { country: "Suriname", src: "flags/suriname.svg" },
    { country: "Sweden", src: "flags/sweden.svg" },
    { country: "Switzerland", src: "flags/switzerland.svg" },
    { country: "Syria", src: "flags/syria.svg" },
    { country: "Sao Tome and Principe", src: "flags/são-tomé-and-príncipe.svg" },
    { country: "Taiwan", src: "flags/taiwan.svg" },
    { country: "Tajikistan", src: "flags/tajikistan.svg" },
    { country: "Tanzania", src: "flags/tanzania.svg" },
    { country: "Thailand", src: "flags/thailand.svg" },
    { country: "Timor-Leste", src: "flags/timor-leste.svg" },
    { country: "Togo", src: "flags/togo.svg" },
    { country: "Tonga", src: "flags/tonga.svg" },
    { country: "Trinidad and Tobago", src: "flags/trinidad-and-tobago.svg" },
    { country: "Tunisia", src: "flags/tunisia.svg" },
    { country: "Turkey", src: "flags/turkey.svg" },
    { country: "Turkmenistan", src: "flags/turkmenistan.svg" },
    { country: "Tuvalu", src: "flags/tuvalu.svg" },
    { country: "Uganda", src: "flags/uganda.svg" },
    { country: "Ukraine", src: "flags/ukraine.svg" },
    { country: "United Arab Emirates", src: "flags/united-arab-emirates.svg" },
    { country: "United Kingdom", src: "flags/united-kingdom.svg" },
    { country: "United States of America", src: "flags/the-united-states-of-america.svg" },
    { country: "Uruguay", src: "flags/uruguay.svg" },
    { country: "Uzbekistan", src: "flags/uzbekistan.svg" },
    { country: "Vanuatu", src: "flags/vanuatu.svg" },
    { country: "Vatican City", src: "flags/vatican-city.svg" },
    { country: "Venezuela", src: "flags/venezuela.svg" },
    { country: "Vietnam", src: "flags/vietnam.svg" },
    { country: "Yemen", src: "flags/yemen.svg" },
    { country: "Zambia", src: "flags/zambia.svg" },
    { country: "Zimbabwe", src: "flags/zimbabwe.svg" }
];

let currentFlag = {};
let score = 0;
const timeTotal = 60;
let timeLeft = timeTotal;
let timerInterval;

function startGame() {
    timerInterval = setInterval(updateTimer, 1000);
    loadNewFlag();
}

function updateTimer() {
    timeLeft--;
    document.getElementById("time").textContent = timeLeft;
    if (timeLeft <= 0) {
        clearInterval(timerInterval);
        endGame();
    }
}

function loadNewFlag() {
    const randomFlag = flags[Math.floor(Math.random() * flags.length)];
    currentFlag = randomFlag;

    document.getElementById("flag").src = randomFlag.src;

    const options = [...flags].sort(() => Math.random() - 0.5).slice(0, 6);
    if (!options.includes(randomFlag)) {
        options[Math.floor(Math.random() * 6)] = randomFlag;
    }

    const optionButtons = document.querySelectorAll(".option-button");
    optionButtons.forEach((button, index) => {
        button.textContent = options[index].country;
    });
}

function guess(optionIndex) {
    const optionButtons = document.querySelectorAll(".option-button");
    const timePassed = timeTotal - timeLeft;
    if (optionButtons[optionIndex].textContent === currentFlag.country) {
        const points = timePassed * (timeLeft / timeTotal) + 25;
        const roundedPoints = Math.round(points * 100) / 100;
        score += roundedPoints;
        showFloatingScore(roundedPoints, 'correct');
    } else {
        const points = timePassed > 30 ? -timePassed / 1.75 : -timePassed * 1.30 - 3;
        const roundedPoints = Math.round(points * 100) / 100;
        score += roundedPoints;
        showFloatingScore(roundedPoints, 'incorrect');
    }

    const roundedScore = Math.round(score * 100) / 100;
    document.getElementById("score").textContent = roundedScore;
    loadNewFlag();
}

function showFloatingScore(points, type) {
    const floatingScore = document.getElementById("floating-score");
    floatingScore.textContent = (type === 'correct' ? '+' : '') + points;
    floatingScore.className = type;

    floatingScore.style.opacity = 1;
    floatingScore.style.transform = 'translate(-50%, -50%) translateY(-20px)';

    setTimeout(() => {
        floatingScore.style.opacity = 0;
        floatingScore.style.transform = 'translate(-50%, -50%) translateY(0)';
    }, 1000);
}

function saveScore(score) {
    fetch('http://localhost:8080/setScore', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(score)
    }).then(response => {
        if (response.ok) {
            return response;
        } else if (response.status >= 400 && response.status < 500) {
            return response.json().then(errorData => {
                throw new Error(errorData.message || 'Credenziali errate. Riprova.');
            });
        } else {
            throw new Error('Errore durante il login. Riprova più tardi.');
        }
    }).then(d => {
        console.log('Form submitted successfully');
    }).catch(error => {
        console.error('Form submission error:', error);
    });
}

function endGame() {
    const finalScorePopup = document.getElementById("final-score-popup");
    const roundedScore = Math.round(score * 100) / 100;
    document.getElementById("final-score").textContent = roundedScore;
    finalScorePopup.classList.add("visible");
    saveScore(roundedScore);
}

function closePopup() {
    const finalScorePopup = document.getElementById("final-score-popup");
    finalScorePopup.classList.remove("visible");
}

document.addEventListener("DOMContentLoaded", startGame);