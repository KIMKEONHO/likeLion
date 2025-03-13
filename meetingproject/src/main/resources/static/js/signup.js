function checkEmail() {
    const email = document.getElementById("email").value;

    if (!email) {
        alert("이메일을 입력해 주세요.");
        return;
    }

    fetch(`/auth/check-email?email=${encodeURIComponent(email)}`)
        .then(response => response.json())
        .then(data => {
            if (data.isDuplicate) {
                alert("이미 사용 중인 이메일입니다.");
            } else {
                alert("사용 가능한 이메일입니다.");
            }
        })
        .catch(error => {
            console.error("중복 확인 오류:", error);
            alert("중복 확인 중 오류가 발생했습니다.");
        });
}

function validatePassword() {
    const password = document.getElementById("password").value;
    const messageElement = document.getElementById("password-validation-message");

    if (!password) {
        messageElement.textContent = ""; // 비밀번호가 없으면 메시지 지우기
        return;
    }

    fetch(`/auth/check-password?password=${encodeURIComponent(password)}`)
        .then(response => response.json())
        .then(data => {
            if (data.isDuplicate) {
                messageElement.textContent = "이미 사용 중인 패스워드입니다.";
            } else {
                messageElement.textContent = "사용 가능한 패스워드입니다.";
            }
        })
        .catch(error => {
            console.error("중복 확인 오류:", error);
            messageElement.textContent = "중복 확인 중 오류가 발생했습니다.";
        });
}

function register() {
    const email = document.getElementById("email").value;
    const username = document.getElementById("name").value;
    const password = document.getElementById("password").value;

    // 유효성 검사 (필요하다면 추가)
    if (!email || !username || !password) {
        alert("모든 필드를 입력해 주세요.");
        return;
    }

    const data = {
        email: email,
        name: username,
        password: password
    };

    fetch("/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); }); // 서버의 오류 메시지 반환
            }
            return response.json();
        })
        .then(result => {
            alert("회원가입 성공: " + result.message);
            window.location.href = "/login"; // 로그인 페이지로 이동
        })
        .catch(error => {
            console.error("회원가입 오류:", error);
            alert("회원가입 중 오류가 발생했습니다: " + error.message); // 오류 메시지 포함
        });
}