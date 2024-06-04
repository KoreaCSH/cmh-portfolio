function sendGetRequest() {
    const profileSelect = document.getElementById("profile-type-select");
    const selectedOption = profileSelect.value;
    let url;
    if(selectedOption == 'All') {
        url = '/admin/profile/all';
    }
    else {
        url = `/admin/profile/${selectedOption}`;
    }

    window.location.href = url;
}

function deleteHandler() {
    if(confirm("선택한 내용을 삭제하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}

function sendDeleteFormRequest() {
    const profileSelect = document.getElementById("profile-type-select");
    const selectedOption = profileSelect.value;
    let url;
    if(selectedOption == 'All') {
        url = '/admin/profile/delete-form/all';
    }
    else {
        url = `/admin/profile/delete-form/${selectedOption}`;
    }

    window.location.href = url;
}