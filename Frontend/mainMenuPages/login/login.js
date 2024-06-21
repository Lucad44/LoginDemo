document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    var username = formData.get('username');
    var password = formData.get('password');
    var data = { username, password };

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else if (response.status >= 400 && response.status < 500) {
            // Handle 4xx client errors
            return response.json().then(errorData => {
                throw new Error(errorData.message || 'Credenziali errate. Riprova.');
            });
        } else {
            // Handle other errors (e.g., 5xx server errors)
            throw new Error('Errore durante il login. Riprova più tardi.');
        }
    }).then(d => {
        console.log('Form submitted successfully');
        if (d.role === 'admin') {
            window.location.replace("../../admin/admin.html");
        } else {
            window.location.replace("../success.html");
        }
    }).catch(error => {
        console.error('Form submission error:', error);
        const messageDiv = document.getElementById('message');
        messageDiv.textContent = error.message || 'Errore durante il login. Riprova più tardi.';
        messageDiv.style.display = 'block';
        document.getElementById('loginForm').reset();
    });
});
