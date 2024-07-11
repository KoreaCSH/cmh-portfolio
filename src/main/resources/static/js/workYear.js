function addYearInput() {
    const container = document.getElementById('work-year-save-container');
    const index = container.querySelectorAll('.work-year-save-form').length;
    const newField = document.createElement('div');
    newField.className = 'work-year-save-form';
    newField.innerHTML = `
                <label for="work-year-${index}" class="input-label">Year</label>
                <input id="work-year-${index}" type="number" min="2015" name="workYearDtoList[${index}].year" class="form-control" placeholder="Year" required>
            `;
    // 가장 앞에 새 요소를 추가
    container.insertBefore(newField, container.firstChild);
}

function removeYearInput() {
    const container = document.getElementById('work-year-save-container');
    const firstField = container.querySelector('.work-year-save-form');
    const length = container.querySelectorAll('.work-year-save-form').length;

    if (firstField && length > 1) {
        container.removeChild(firstField);
    }
}