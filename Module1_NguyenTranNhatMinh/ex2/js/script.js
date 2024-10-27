function roundScore(score) {
    // Nếu điểm nhỏ hơn 48, không làm tròn
    if (score < 48) {
        return score;
    }
    // Tìm bội số tiếp theo của 5
    const nextMultipleOf5 = Math.ceil(score / 5) * 5;
    // Nếu khoảng cách đến bội số tiếp theo nhỏ hơn 3, làm tròn
    if (nextMultipleOf5 - score < 3) {
        return nextMultipleOf5;
    }
    // Nếu không thì giữ nguyên điểm
    return score;
}

// Các ví dụ kiểm tra
console.log(roundScore(84));
console.log(roundScore(78));
console.log(roundScore(46));
