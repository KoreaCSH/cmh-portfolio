function sendGetRequest() {
    const workYearSelect = document.getElementById("work-year-select");
    const selectedOption = workYearSelect.value;
    let url;
    if(selectedOption == 'All') {
        url = '/admin/works';
    }
    else {
        url = `/admin/works/work-year/${selectedOption}`;
    }

    window.location.href = url;
}

function sendDeleteFormRequest() {
    const workYearSelect = document.getElementById("work-year-select");
    const selectedOption = workYearSelect.value;
    let url;
    if(selectedOption == 'All') {
        url = '/admin/works/delete-form';
    }
    else {
        url = `/admin/works/work-year/${selectedOption}/delete-form`;
    }

    window.location.href = url;
}