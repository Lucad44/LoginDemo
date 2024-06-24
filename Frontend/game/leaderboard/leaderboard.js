document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/leaderboard', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error fetching leaderboard data');
        }
    }).then(data => {
        if (data && typeof data === 'object') {
            const leaderboardBody = document.getElementById('leaderboard-body');
            Object.keys(data).forEach(username => {
                const score = data[username];
                const row = document.createElement('tr');
                row.innerHTML = `<td>${username}</td><td>${score}</td>`;
                leaderboardBody.appendChild(row);
            });
        } else {
            throw new Error('Invalid data format');
        }
    }).catch(error => {
        console.error('Error fetching leaderboard:', error);
    });
});
