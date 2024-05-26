document.getElementById('home-image-file').addEventListener('change', function(event) {
    const fileInput = event.target;
    const fileNameDisplay = document.getElementById('home-image-file-name');

    if (fileInput.files && fileInput.files.length > 0) {
        const fileName = fileInput.files[0].name;
        fileNameDisplay.textContent = fileName;
    } else {
        fileNameDisplay.textContent = '파일명';
    }
});