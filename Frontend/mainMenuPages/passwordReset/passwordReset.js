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
    var newPassword = formData.get('newPassword');
    var confirmPassword = formData.get('confirmPassword');
    var data = { username, newPassword, confirmPassword };
    const errorMessage = 'La password inserita\nnon rispetta i requisiti\n\nRiprova';
    const successMessage = 'Password cambiata con successo';

    fetch('http://localhost:8080/reset', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        console.log("ok");
        if (response.ok) {
            console.log('Password reset successful');
            displayOutcome(successMessage, true);
        } else {
            response.text().then(text => {
                try {
                    const errorData = JSON.parse(text);
                    displayOutcome(errorData.message || errorMessage, false);
                } catch (e) {
                    displayOutcome(text || errorMessage, false);
                }
            }).catch(() => {
                displayOutcome(errorMessage, false);
            });
            console.error('Password reset error');
        }
    }).catch(error => {
        displayOutcome('Errore nel cambio password: ' + error.message, false);
        console.error('Password reset error: ', error);
    });
});
