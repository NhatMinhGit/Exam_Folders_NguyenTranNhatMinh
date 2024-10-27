function countPairs(arr, k) {
    let count = 0;
    const n = arr.length;

    for (let i = 0; i < n - 1; i++) {
        for (let j = i + 1; j < n; j++) {
            if ((arr[i] + arr[j]) % k === 0) {
                count++;
            }
        }
    }

    return count;
}
var n = prompt("Nhập kích thước mảng (không quá 50): ");
var k = parseInt(prompt("Nhập số nguyên dương k : "));
if(n>=20){
    console.log("Kích thước mảng lớn hơn 20");
}else {
    var mangSo =[];
    for (let i = 0; i < n; i++) {
        mangSo[i] = parseInt(prompt(`Nhập phần tử thứ ${i} :`));
    }
    alert("Mảng đã nhập : "+mangSo);
    console.log(`Số cặp có tổng chia hết cho ${k} là: ${countPairs(mangSo, k)} `);
}

