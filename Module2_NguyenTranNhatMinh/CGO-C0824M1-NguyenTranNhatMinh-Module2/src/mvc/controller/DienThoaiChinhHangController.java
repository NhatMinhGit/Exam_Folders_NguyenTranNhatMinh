package mvc.controller;

import mvc.entity.DienThoaiChinhHang;
import mvc.service.IDienThoaiChinhHangService;
import mvc.service.impl.DienThoaiChinhHangService;

import java.util.List;

public class DienThoaiChinhHangController {
    private IDienThoaiChinhHangService dienThoaiChinhHangService = new DienThoaiChinhHangService();
    public List<DienThoaiChinhHang> getAll() {
        return dienThoaiChinhHangService.getAll();
    }

    public List<DienThoaiChinhHang> findAllByName(String name) {
        return dienThoaiChinhHangService.findAllByName(name);
    }

    public void save(DienThoaiChinhHang dienThoaiChinhHang) {
        dienThoaiChinhHangService.save(dienThoaiChinhHang);
    }

    public DienThoaiChinhHang findById(int code) {
        return dienThoaiChinhHangService.findById(code);
    }

    public void remove(int code) {
        dienThoaiChinhHangService.remove(code);
    }
}