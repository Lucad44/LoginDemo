document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); 
    const formData = new FormData(this);
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
            window.location.replace("success.html");
        } else {
            console.error('Form submission error');
            const messageDiv = document.getElementById('message');
            messageDiv.textContent = 'Credenziali errate\n\n\tRiprova';
            messageDiv.style.display = 'block';
            document.getElementById('loginForm').reset();
        }
    }).catch(error => {
        console.error('Form submission error:', error);
    });
});

