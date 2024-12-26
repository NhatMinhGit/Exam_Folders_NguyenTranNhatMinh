package mvc.controller;

import mvc.entity.DienThoaiChinhHang;
import mvc.entity.DienThoaiXachTay;
import mvc.service.IDienThoaiChinhHangService;
import mvc.service.IDienThoaiXachTayService;
import mvc.service.impl.DienThoaiChinhHangService;
import mvc.service.impl.DienThoaiXachTayService;

import java.util.List;

public class DienThoaiXachTayController {
    private IDienThoaiXachTayService dienThoaiXachTayService = new DienThoaiXachTayService();
    public List<DienThoaiXachTay> getAll() {
        return dienThoaiXachTayService.getAll();
    }

    public List<DienThoaiXachTay> findAllByName(String name) {
        return dienThoaiXachTayService.findAllByName(name);
    }

    public void save(DienThoaiXachTay dienThoaiXachTay) {
        dienThoaiXachTayService.save(dienThoaiXachTay);
    }

    public DienThoaiXachTay findById(int code) {
        return dienThoaiXachTayService.findById(code);
    }

    public void remove(int code) {
        dienThoaiXachTayService.remove(code);
    }
}