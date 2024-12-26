// Hàm kiểm tra bội chung của 3 và 5
function laBoiChung(so) {
    return so % 3 === 0 && so % 5 === 0;
}

// Hàm tính tổng
function tinhTongBoiChung(mangSo) {
    let tong = 0;
    for (let i = 0; i < mangSo.length; i++) {
        if (laBoiChung(mangSo[i])) {// Tìm các bội chung của 3 và 5 trong một mảng
            tong += mangSo[i];// và cộng lại
        }
    }
    return tong;
}

var n = prompt("Nhập kích thước mảng (không quá 20):");
if(n>=20){
    console.log("Kích thước mảng lớn hơn 20");
}else {
    var mangSo =[];
    for (let i = 0; i < n; i++) {
        mangSo[i] = parseInt(prompt(`Nhập phần tử thứ ${i} :`));
    }
    alert("Mảng đã nhập : "+mangSo);
}


const ketQua = tinhTongBoiChung(mangSo);
console.log(`Tổng các bội chung của 3 và 5 là: ${ketQua} của mảng ${mangSo}`);