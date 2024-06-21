function openPopup(popupId) {
    document.getElementById(popupId).style.display = 'flex';
}

function closePopup(popupId) {
    clearResults(popupId);
    document.getElementById(popupId).style.display = 'none';
}

function clearResults(popupId) {
    if (popupId === 'suspendPopup') {
        document.getElementById('suspendUsername').textContent = '';
    } else if (popupId === 'deletePopup') {
        document.getElementById('deleteUsername').textContent = '';
    } else if (popupId === 'unsuspendPopup') {
        document.getElementById('unsuspendUsername').textContent = '';
    }
}

document.getElementById('suspendButton').addEventListener('click', function() {
    openPopup('suspendPopup');
});

document.getElementById('deleteButton').addEventListener('click', function() {
    openPopup('deletePopup');
});

document.getElementById('unsuspendButton').addEventListener('click', function() {
    openPopup('unsuspendPopup');
});

function printResult(where, message, ok, mod) {
    const messageDiv = document.getElementById(where);
    if (ok) {
        if (mod == 0) {
            messageDiv.className = 'suspendMessage success';
        } else if (mod == 1) {
            messageDiv.className = 'deleteMessage success';
        } else if (mod == 2) {
            messageDiv.className = 'unsuspendMessage success';
        }
    } else {
        if (mod == 0) {
            messageDiv.className = 'suspendMessage error';
        } else if (mod == 1) {
            messageDiv.className = 'deleteMessage error';
        } else if (mod == 2) {
            messageDiv.className = 'unsuspendMessage error';
        }
    }
    messageDiv.textContent = message;
    messageDiv.style.display = 'block';
    if (mod == 0) {
        clearResults('suspendPopup');
    } else if (mod == 1) {
        clearResults('deletePopup');
    } else if (mod == 2) {
        clearResults('unsuspendPopup');
    }
}

function suspendUser() {
    const username = document.getElementById('suspendUsername').value;
    fetch('http://localhost:8080/suspend', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username }) // Send as an object
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
            printResult('suspendMessage', 'Utente sospeso con successo', true, true);
        } else {
            response.text().then(text => {
                try {
                    const errorData = JSON.parse(text);
                    printResult('suspendMessage', errorData.message || 'Errore durante la sospensione dell\'utente', false, true);
                } catch (e) {
                    printResult('suspendMessage', text || 'Errore durante la sospensione dell\'utente', false, true);
                }
            }).catch(() => {
                printResult('suspendMessage', 'Errore durante la sospensione dell\'utente', false, true);
            });
            console.error('Form submission error');
        }
    }).catch(error => {
        printResult('suspendMessage', 'Errore durante la sospensione dell\'utente: ' + error.message, false, true);
        console.error('Form submission error:', error);
    });
}

function unsuspendUser() {
    const username = document.getElementById('unsuspendUsername').value;
    fetch('http://localhost:8080/unsuspend', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username })
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
            printResult('unsuspendMessage', 'Utente riattivato con successo', true, true);
        } else {
            response.text().then(text => {
                try {
                    const errorData = JSON.parse(text);
                    printResult('unsuspendMessage', errorData.message || 'Errore durante la riattivazione dell\'utente', false, true);
                } catch (e) {
                    printResult('unsuspendMessage', text || 'Errore durante la riattivazione dell\'utente', false, true);
                }
            }).catch(() => {
                printResult('suspendMessage', 'Errore durante la riattivazione dell\'utente', false, true);
            });
            console.error('Form submission error');
        }
    }).catch(error => {
        printResult('unsuspendMessage', 'Errore durante la riattivazione dell\'utente: ' + error.message, false, true);
        console.error('Form submission error:', error);
    });
}

function deleteUser() {
    const username = document.getElementById('deleteUsername').value;
    console.log(username);
    fetch('http://localhost:8080/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username })
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
            printResult('deleteMessage', 'Utente eliminato con successo', true, false);
        } else {
            response.text().then(text => {
                try {
                    const errorData = JSON.parse(text);
                    printResult('deleteMessage', errorData.message || 'Errore durante l\'eliminazione dell\'utente', false, false);
                } catch (e) {
                    printResult('deleteMessage', text || 'Errore durante l\'eliminazione dell\'utente', false, false);
                }
            }).catch(() => {
                printResult('deleteMessage', 'Errore durante l\'eliminazione dell\'utente', false, false);
            });
            console.error('Form submission error');
        }
    }).catch(error => {
        printResult('deleteMessage', 'Errore durante l\'eliminazione dell\'utente: ' + error.message, false, false);
        console.error('Form submission error:', error);
    });
}
