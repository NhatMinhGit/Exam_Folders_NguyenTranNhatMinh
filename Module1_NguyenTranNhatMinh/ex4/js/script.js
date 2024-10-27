class KhachHang{
    constructor(MaKhachHang,TenKhachHang,SoDienThoai,DiaChi,Email){
        this._MaKhachHang = MaKhachHang;
        this._TenKhachHang = TenKhachHang;
        this._SoDienThoai = SoDienThoai;
        this._DiaChi = DiaChi;
        this._Email = Email;
    }

    get MaKhachHang() {
        return this._MaKhachHang;
    }

    set MaKhachHang(value) {
        this._MaKhachHang = value;
    }

    get TenKhachHang() {
        return this._TenKhachHang;
    }

    set TenKhachHang(value) {
        this._TenKhachHang = value;
    }

    get SoDienThoai() {
        return this._SoDienThoai;
    }

    set SoDienThoai(value) {
        this._SoDienThoai = value;
    }

    get DiaChi() {
        return this._DiaChi;
    }

    set DiaChi(value) {
        this._DiaChi = value;
    }

    get Email() {
        return this._Email;
    }

    set Email(value) {
        this._Email = value;
    }
}

var KhachHangList = [];
// Hàm thêm khách hàng vào danh sách
function AddKhachHang() {
    var maKhachHang = document.getElementById('maKhachHang').value;
    var tenKhachHang = document.getElementById('tenKhachHang').value;
    var sodienThoai = document.getElementById('soDienThoai').value;
    var diaChi = document.getElementById('diaChi').value;
    var email = document.getElementById('email').value;
    var newKhachHang = new KhachHang(maKhachHang,tenKhachHang,sodienThoai,diaChi,email); // Khởi tạo khách hàng mới

    KhachHangList.push(newKhachHang); // Thêm khách hàng vào danh sách
    console.log(`Khách hàng ${tenKhachHang} đã được thêm vào danh sách!`);
    // Cập nhật danh sách hiển thị
    displayCustomers();

    // Làm sạch các trường nhập
    clearInputs();
}
// Hàm hiển thị danh sách khách hàng
function displayCustomers() {
    const customerBody = document.getElementById('customerBody');
    customerBody.innerHTML = ''; // Xóa danh sách hiện tại

    // Duyệt qua danh sách khách hàng và tạo hàng cho mỗi khách hàng
    KhachHangList.forEach(kh => {
        const row = document.createElement('tr');
        row.innerHTML = `
                    <td>${kh.MaKhachHang}</td>
                    <td>${kh.TenKhachHang}</td>
                    <td>${kh.SoDienThoai}</td>
                    <td>${kh.DiaChi}</td>
                    <td>${kh.Email}</td>
                `;
        customerBody.appendChild(row);
    });
}

// Hàm làm sạch các trường nhập
function clearInputs() {
    document.getElementById('maKhachHang').value = '';
    document.getElementById('tenKhachHang').value = '';
    document.getElementById('sodienThoai').value = '';
    document.getElementById('diaChi').value = '';
    document.getElementById('email').value = '';
}
// Kiểm tra danh sách khách hàng
//console.log(KhachHangList);


