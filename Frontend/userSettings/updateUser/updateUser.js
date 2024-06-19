function displayOutcome(message, ok) {
    const messageDiv = document.getElementById('message');
    if (ok) {
        messageDiv.className = 'message success';
    } else {
        messageDiv.className = 'message error';
    }
    messageDiv.textContent = message;
    messageDiv.style.display = 'block';
    document.getElementById('updateForm').reset();
}

document.getElementById('updateForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const successMessage = 'Username aggiornato con successo';
    const errorMessage = 'Credenziali errate\n\nRiprova';
    const formData = new FormData(this);
    var newUsername = formData.get('newUsername');
    var confirmUsername = formData.get('confirmUsername');
    console.log(newUsername + " " + confirmUsername);
    var data = { newUsername, confirmUsername };
    fetch('http://localhost:8080/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('Username updated successfully');
            displayOutcome(successMessage, true);
        } else {
            response.json().then(errorData => {
                displayOutcome(errorData.message || errorMessage, false);
            }).catch(() => {
                displayOutcome(errorMessage, false);
            });
            console.error('Username update error');
        }
    }).catch(error => {
        displayOutcome('Username update error: ' + error.message, false);
        console.error('Username update error: ', error);
    });
});

