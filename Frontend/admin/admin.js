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
function updateUser() {
    const username = document.getElementById('updateUsername').value;
    alert(`Sospendi utente: ${username}`);
    closePopup('updatePopup');
}

function deleteUser() {
    const username = document.getElementById('deleteUsername').value;
    alert(`Elimina utente: ${username}`);
    closePopup('deletePopup');
}