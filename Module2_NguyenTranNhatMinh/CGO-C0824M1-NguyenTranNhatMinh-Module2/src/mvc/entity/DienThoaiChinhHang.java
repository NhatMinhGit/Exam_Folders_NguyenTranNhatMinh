package mvc.entity;

import mvc.enums.PhamViBaoHanh;

import java.time.LocalDate;

public class DienThoaiChinhHang extends DienThoai{
    private int thoiGianBaoHanh;
    private PhamViBaoHanh phamViBaoHanh;

    public DienThoaiChinhHang(int id, String ten, double gia, int soLuong, String nhaSanXuat, int thoiGianBaoHanh, PhamViBaoHanh phamViBaoHanh) {
        super(id, ten, gia, soLuong, nhaSanXuat);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.phamViBaoHanh = phamViBaoHanh;
    }
    public DienThoaiChinhHang() {
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public PhamViBaoHanh getPhamViBaoHanh() {
        return phamViBaoHanh;
    }

    public void setPhamViBaoHanh(PhamViBaoHanh phamViBaoHanh) {
        this.phamViBaoHanh = phamViBaoHanh;
    }

    @Override
    public String toString() {
        return super.toString() + " DienThoaiChinhHang{" +
                "thoiGianBaoHanh=" + thoiGianBaoHanh +
                ", phamViBaoHanh=" + phamViBaoHanh +
                '}';
    }
}
