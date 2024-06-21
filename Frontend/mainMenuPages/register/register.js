function displayOutcome(message, ok) {
    const messageDiv = document.getElementById('message');
    if (ok) {
        messageDiv.className = 'message success';
    } else {
        messageDiv.className = 'message error';
    }
    messageDiv.textContent = message;
    messageDiv.style.display = 'block';
    document.getElementById('registerForm').reset();
}

document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    var username = formData.get('username');
    var password = formData.get('password');
    var confirmPassword = formData.get('confirmPassword');
    var user = { username, password };
    var data = { user, confirmPassword };
    const errorMessage = 'La password inserita\nnon rispetta i requisiti\n\nRiprova';
    const successMessage = 'Registrazione avvenuta con successo';

    fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('Registration successful');
            displayOutcome(successMessage, true);
        } else {
            // Check if the response is JSON or text
            response.text().then(text => {
                try {
                    // Try to parse JSON
                    const errorData = JSON.parse(text);
                    displayOutcome(errorData.message || errorMessage, false);
                } catch (e) {
                    // If parsing fails, assume it is plain text
                    displayOutcome(text || errorMessage, false);
                }
            }).catch(() => {
                displayOutcome(errorMessage, false);
            });
            console.error('Registration error');
        }
    }).catch(error => {
        displayOutcome('Errore nella registrazione: ' + error.message, false);
        console.error('Registration error: ', error);
    });
});
