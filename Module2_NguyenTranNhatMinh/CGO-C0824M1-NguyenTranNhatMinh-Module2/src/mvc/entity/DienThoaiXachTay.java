package mvc.entity;

import mvc.enums.TrangThaiDienThoai;

public class DienThoaiXachTay extends DienThoai {
    private String quocGiaXachTay;
    private TrangThaiDienThoai trangThaiDienThoai;

    public DienThoaiXachTay(int id, String ten, double gia, int soLuong, String nhaSanXuat, String quocGiaXachTay, TrangThaiDienThoai trangThaiDienThoai) {
        super(id, ten, gia, soLuong, nhaSanXuat);
        this.quocGiaXachTay = quocGiaXachTay;
        this.trangThaiDienThoai = trangThaiDienThoai;
    }

    public DienThoaiXachTay() {
    }

    public String getQuocGiaXachTay() {
        return quocGiaXachTay;
    }

    public void setQuocGiaXachTay(String quocGiaXachTay) {
        this.quocGiaXachTay = quocGiaXachTay;
    }

    public TrangThaiDienThoai getTrangThaiTuiXach() {
        return trangThaiDienThoai;
    }

    public void setTrangThaiTuiXach(TrangThaiDienThoai trangThaiDienThoai) {
        this.trangThaiDienThoai = trangThaiDienThoai;
    }

    @Override
    public String toString() {
        return super.toString() + " DienThoaiXachTay{" +
                "quocGiaXachTay='" + quocGiaXachTay + '\'' +
                ", trangThaiTuiXach=" + trangThaiDienThoai +
                '}';
    }
}
