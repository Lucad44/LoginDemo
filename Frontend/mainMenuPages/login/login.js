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
        } else {
            throw new Error('Form submission error');
        }
    }).then(d => {
        console.dir(d);
        console.log('Form submitted successfully');
        if (d.role === 'admin') {
            window.location.replace("../../admin/admin.html");
        } else {
            window.location.replace("../success.html");
        }
    }).catch(error => {
        console.error('Form submission error:', error);
        const messageDiv = document.getElementById('message');
        messageDiv.textContent = 'Credenziali errate\n\nRiprova';
        messageDiv.style.display = 'block';
        document.getElementById('loginForm').reset();
    });
});
