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

/* 모바일 vh 대응 함수
function adjustHeight() {
    let vh = window.innerHeight * 0.01; // 실제 가시 영역의 1vh 계산
    document.documentElement.style.setProperty('--vh', `${vh}px`);
}

adjustHeight();
window.addEventListener('resize', adjustHeight);
*/