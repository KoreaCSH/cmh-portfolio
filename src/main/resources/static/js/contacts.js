function rowClick(event, contactId) {
    // 체크박스 클릭시 이벤트 전파 중지
    if (event.target.classList.contains('del-chk') || event.target.type === 'checkbox') {
        event.stopPropagation();
        return;
    }
    // 체크박스가 아닌 경우에만 페이지 이동
    location.href = '/admin/contact/' + contactId;
}

window.addEventListener("load", function() {
    const contactContainer = document.querySelector('.contact-container'); // 페이지의 모든 img 요소 선택
    contactContainer.classList.add('loaded');
});