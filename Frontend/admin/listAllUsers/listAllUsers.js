document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/users', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.error('Error fetching users');
        }
    }).then(data => {
        if (data && Array.isArray(data)) {
            const usersContainer = document.getElementById('users-container');
            data.forEach(user => {
                const userElement = document.createElement('pre');
                userElement.className = 'user';
                userElement.innerText = JSON.stringify(user, null, 2);
                usersContainer.appendChild(userElement);
            });
        }
    }).catch(error => {
        console.error('Error fetching users:', error);
    });
});
