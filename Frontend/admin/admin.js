// Function to open the pop-up
function openPopup(popupId) {
    document.getElementById(popupId).style.display = 'flex';
}

// Function to close the pop-up
function closePopup(popupId) {
    document.getElementById(popupId).style.display = 'none';
}

// Event listeners for buttons
document.getElementById('updateButton').addEventListener('click', function() {
    openPopup('updatePopup');
});

document.getElementById('deleteButton').addEventListener('click', function() {
    openPopup('deletePopup');
});

// Placeholder functions for submit actions
function suspendUser() {
    const username = document.getElementById('updateUsername').value;
    alert(`Sospendi utente: ${username}`);
    closePopup('updatePopup');
}

function deleteUser() {
    fetch('http://localhost:8080/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
        } else {
            console.error('Form submission error');
        }
    }).catch(error => {
        console.error('Form submission error:', error);
    });
}