function sendGetRequest() {
    const workYearSelect = document.getElementById("profile-type-select");
    const selectedOption = workYearSelect.value;
    let url;
    if(selectedOption == 'All') {
        url = '/admin/profile/all';
    }
    else {
        url = `/admin/profile/${selectedOption}`;
    }

    window.location.href = url;
}