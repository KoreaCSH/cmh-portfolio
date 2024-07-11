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

function deleteHandler() {
    if(confirm("삭제하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}

function updateHandler() {
    alert("이미지는 수정할 수 없습니다.");
}

function toggleCheckbox(selectAllCheckBox) {
    const checkBoxes = document.querySelectorAll('.del-chk-box');
    checkBoxes.forEach(checkbox => checkbox.checked = selectAllCheckBox.checked);
}