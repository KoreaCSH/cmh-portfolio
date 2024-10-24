function permitHandler() {
    if(confirm("가입을 허용하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}

function rejectHandler() {
    if(confirm("가입을 거부하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}

function withdrawHandler() {
    if(confirm("해당 계정을 탈퇴 처리 하시겠습니까?")) {
        return true;
    }
    else {
        return false;
    }
}