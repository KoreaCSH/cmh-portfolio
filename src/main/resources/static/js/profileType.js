function addYearInput() {
    const container = document.getElementById('profile-type-save-container');
    const index = container.querySelectorAll('.profile-type-save-form').length;
    const newField = document.createElement('div');
    newField.className = 'profile-type-save-form';
    newField.innerHTML = `
                <label for="profile-type-${index}" class="input-label">Type ${index+1}</label>
                <input id="profile-type-${index}" type="text" name="profileTypeDtoList[${index}].type" class="form-control" placeholder="Type" required>
                <label for="profile-type-en-${index}" class="input-label type-en-label">Type ${index+1} (영어)</label>
                <input id="profile-type-en-${index}" type="text" th:field="profileTypeDtoList[${index}].typeEn" class="form-control type-en-input" placeholder="Type (영어)" required>
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