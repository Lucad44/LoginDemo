const deleteButton = document.getElementById('deleteButton');

deleteButton.addEventListener('click', function(event) {
    event.preventDefault();
    fetch('http://localhost:8080/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(null)
    }).then(response => {
        if (response.ok) {
            console.log('Form submitted successfully');
            window.location.replace("deleteUser/deleteUser.html");
        } else {
            console.error('Form submission error');
        }
    }).catch(error => {
        console.error('Form submission error:', error);
    });
});
