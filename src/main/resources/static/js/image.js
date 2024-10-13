document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll('.work-img'); // 페이지의 모든 img 요소 선택

    images.forEach(img => {
        img.addEventListener('load', () => {
            img.classList.add('loaded'); // 이미지를 로드한 후 .loaded 클래스 추가x
        });

        // 만약 이미지가 캐시에서 로드된 경우 load 이벤트가 발생하지 않으므로, 이 경우를 처리하기 위해 다음 코드 추가
        if (img.complete) {
            img.classList.add('loaded');
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const image = document.querySelector('.home-image'); // 페이지의 모든 img 요소 선택
    image.classList.add('loaded');

    // 만약 이미지가 캐시에서 로드된 경우 load 이벤트가 발생하지 않으므로, 이 경우를 처리하기 위해 다음 코드 추가
    if (image.complete) {
        image.classList.add('loaded');
    }
});

function closeModal() {
    const workModal = document.getElementById('work-modal');
    const workModalImg = document.querySelector('.work-modal-img-container img');

    workModal.style.display = 'none';
    document.body.style.overflow = 'visible';
    workModalImg.classList.remove('loaded');

    history.replaceState(null, "Modal Close", location.pathname);
}

document.addEventListener("DOMContentLoaded", function () {
    const openModalImgAll = document.querySelectorAll('.work-img');
    const workModal = document.getElementById('work-modal');
    const workModalImg = document.querySelector('.work-modal-img-container img');
    const workModalInfo = document.querySelector('.work-modal-info');
    const modalCloseBtn = document.getElementById('modal-close-btn');

    openModalImgAll.forEach(img => {
        img.addEventListener('click', function () {
            const workId = img.getAttribute('data-id');

            fetch(`/api/works/${workId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    workModalImg.src = `/admin/works/display?fileName=${data.fileName}`;
                    workModal.style.display = 'block';
                    workModalInfo.innerHTML = data.title;
                    document.body.style.overflow = 'hidden';
                    history.pushState({modalOpen: true}, "Modal Open", "#detail");

                    workModalImg.onload = function () {
                        workModalImg.classList.add('loaded');
                    }
                })
                .catch(e => console.log(e));
        });
    });

    modalCloseBtn.addEventListener('click', closeModal);
});

window.addEventListener('popstate', function (event) {
    if(!event.state) {
        closeModal();
    }
});

// 페이지 reload 시 #detail 없애기
window.addEventListener('load', function () {
    if (location.hash === '#detail') {
        history.replaceState(null, "Modal Close", location.pathname);
    }
});