function roundScore(score) {
    // Nếu điểm nhỏ hơn 48, không làm tròn ( từ 47 trở xuống bị tính là rớt )
    if (score < 48) {
        return score;
    }
    // Tìm bội số tiếp theo của 5
    const nextMultipleOf5 = Math.ceil(score / 5) * 5;
    // Kiểu như là điểm = 8 thì nextMultipleOf5 sẽ = 10 và 10 - 8 nên ta return nextMultipleOf5 = 10 ( với mục đích để làm tròn )
    if (nextMultipleOf5 - score < 3) {
        return nextMultipleOf5;
    }
    // Nếu không thì giữ nguyên điểm
    return score;
}
function checkScore(roundedScore){
    if(roundedScore < 0 || roundedScore > 100){
        return "Điểm không hợp lệ";
    }
    else if(roundedScore < 50){
        return "Trượt";
    }else{
        return "Đạt";
    }
}

var diem = parseInt(prompt("Nhập điểm học sinh : " ));
alert((`Điểm của học sinh khi lên hệ thống ${diem} xét duyệt ${checkScore(roundScore(diem))}`))
