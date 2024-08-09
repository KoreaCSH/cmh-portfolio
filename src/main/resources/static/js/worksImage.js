document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll('.work-img'); // 페이지의 모든 img 요소 선택

    images.forEach(img => {
        img.addEventListener('load', () => {
            img.classList.add('loaded'); // 이미지를 로드한 후 .loaded 클래스 추가
        });

        // 만약 이미지가 캐시에서 로드된 경우 load 이벤트가 발생하지 않으므로, 이 경우를 처리하기 위해 다음 코드 추가
        if (img.complete) {
            img.classList.add('loaded');
        }
    });
});