$(document).ready(function() {
	$("#phone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9,-]/g, ""));
	});

	$("#submit").on("click", function() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		if (id == "") {
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		if (pw == "") {
			alert("비밀번호를 입력해주세요.");
			$("#pw").focus();
			return false;
		}
		if (name == "") {
			alert("이름을 확인해주세요.");
			$("#name").focus();
			return false;
		}
		if (phone.length != 13) {
			alert("휴대전화를 확인해주세요.");
			$("#phone").focus();
			return false;
		}
		var idChkVal = $("#idCheck").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);

		if (pw.length < 5 || pw.length > 15) {
			alert("5~15자리 이내로 입력해주세요.");
			$("#pw").focus();
			return false;
		} else if (pw.search(/\s/) != -1) {
			alert("비밀번호는 공백없이 입력해주세요.");
			$("#pw").focus();
			return false;
		} else if (num < 0 || eng < 0) {
			alert("영문,숫자를 혼합하여 입력해주세요.");
			$("#pw").focus();
			return false;
		}
		if (idChkVal == "N") {
			alert("중복확인 버튼을 눌러주세요.");
			return false;
		} else {
			var hashpw = CryptoJS.MD5(pw).toString();
			var data = {
					id : id,
					pw : hashpw,
					name : name,
					phone : phone
			}
			$.ajax({
				type : 'POST',
				url : '/join',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function() {
					alert("회원가입이 완료되었습니다.");
					location.href = "/";
				}
			});
		}
	});

	$('#idCheck').on('click', function() {
		$.ajax({
			type : 'POST',
			url : '/idCheck?id=' + $('#id').val(),
			success : function(data) {
				if (data == 0) {
					$("#idCheck").attr("value", "Y");
					alert("사용 가능한 ID 입니다.");

				} else {
					alert("이미 존재하는 ID 입니다.")
					$('#id').val('');

				}
			}
		});
	});
});

// 전화번호 - 자동 삽입
function inputPhoneNumber(obj) {

	var number = obj.value.replace(/[^0-9]/g, "");
	var phone = "";

	if (number.length < 4) {
		return number;
	} else if (number.length < 7) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3);
	} else if (number.length < 11) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 3);
		phone += "-";
		phone += number.substr(6);
	} else {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 4);
		phone += "-";
		phone += number.substr(7);
	}
	obj.value = phone;
}