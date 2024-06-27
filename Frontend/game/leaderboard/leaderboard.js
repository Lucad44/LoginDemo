document.addEventListener('DOMContentLoaded', function () {
    fetchLeaderboardData();

    const sortDropdown = document.getElementById('sort-options');
    sortDropdown.addEventListener('change', function () {
        fetchLeaderboardData();
    });
});

function fetchLeaderboardData() {
    Promise.all([
        fetch('http://localhost:8080/leaderboard', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.ok ? response.json() : Promise.reject('Error fetching leaderboard data')),
        fetch('http://localhost:8080/bestTime', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.ok ? response.json() : Promise.reject('Error fetching best time data'))
    ])
        .then(([leaderboardData, bestTimeData]) => {
        if (leaderboardData && typeof leaderboardData === 'object' && bestTimeData && typeof bestTimeData === 'object') {
            const leaderboardBody = document.getElementById('leaderboard-body');
            leaderboardBody.innerHTML = '';
            const combinedData = Object.keys(leaderboardData).map(username => ({
                username: username,
                score: leaderboardData[username] === 0 ? 'N/A' : leaderboardData[username],
                bestTime: bestTimeData[username] || 'N/A'
            }));
            const sortOption = document.getElementById('sort-options').value;
            combinedData.sort((a, b) => {
                if (sortOption === 'score') {
                    return (a.score === 'N/A' ? Number.MAX_SAFE_INTEGER : b.score === 'N/A' ? 1 : b.score - a.score);
                } else if (sortOption === 'time') {
                    return parseTime(a.bestTime) - parseTime(b.bestTime);
                } else if (sortOption === 'username') {
                    return a.username.localeCompare(b.username);
                }
            });
            combinedData.forEach(data => {
                const row = document.createElement('tr');
                row.innerHTML = `<td>${data.username}</td><td>${data.score}</td><td>${data.bestTime}</td>`;
                leaderboardBody.appendChild(row);
            });
        } else {
            throw new Error('Invalid data format');
        }
    })
        .catch(error => {
        console.error('Error:', error);
    });
}

function parseTime(timeString) {
    if (timeString === 'N/A') return Number.MAX_SAFE_INTEGER;
    const [minutes, seconds] = timeString.split(':').map(Number);
    return minutes * 60 + seconds;
}
