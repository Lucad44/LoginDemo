function displayOutcome(message, ok) {
    const messageDiv = document.getElementById('message');
    if (ok) {
        messageDiv.className = 'message success';
    } else {
        messageDiv.className = 'message error';
    }
    messageDiv.textContent = message;
    messageDiv.style.display = 'block';
    document.getElementById('resetForm').reset();
}

document.getElementById('resetForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    var username = formData.get('username');
    var password = formData.get('password');
    var confirmPassword = formData.get('confirmPassword');
    var user = { username, password };
    var data = { user, confirmPassword };
    const errorMessage = 'La password inserita\nnon rispetta i requisiti\n\n\t   Riprova';
    const successMessage = 'Password cambiata con successo';

    fetch('http://localhost:8080/reset', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('Password reset successful');
            displayOutcome(successMessage, true);
        } else {
            response.json().then(errorData => {
                // Display the error message sent from the server
                displayOutcome(errorData.message || errorMessage, false);
            }).catch(() => {
                // Handle case where response is not JSON
                displayOutcome(errorMessage, false);
            });
            console.error('Password reset error');
        }
    }).catch(error => {
        displayOutcome('Errore nel cambio password: ' + error.message, false);
        console.error('Password reset error: ', error);
    });
});
