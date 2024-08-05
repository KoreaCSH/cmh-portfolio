function addYearInput() {
    const container = document.getElementById('profile-type-save-container');
    const index = container.querySelectorAll('.profile-type-save-form').length;
    const newField = document.createElement('div');
    newField.className = 'profile-type-save-form';
    newField.innerHTML = `
                <label for="profile-type-${index}" class="input-label">Type</label>
                <input id="profile-type-${index}" type="text" name="profileTypeDtoList[${index}].type" class="form-control" placeholder="Type" required>
            `;
    // 가장 앞에 새 요소를 추가
    container.insertBefore(newField, container.firstChild);
}

function removeYearInput() {
    const container = document.getElementById('profile-type-save-container');
    const firstField = container.querySelector('.profile-type-save-form');
    const length = container.querySelectorAll('.profile-type-save-form').length;

    if (firstField && length > 1) {
        container.removeChild(firstField);
    }
}