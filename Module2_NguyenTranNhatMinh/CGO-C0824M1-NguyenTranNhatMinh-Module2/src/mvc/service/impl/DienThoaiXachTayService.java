package mvc.service.impl;

import mvc.entity.DienThoaiChinhHang;
import mvc.entity.DienThoaiXachTay;
import mvc.repository.DienThoaiChinhHangRepository;
import mvc.repository.DienThoaiXachTayRepository;
import mvc.service.IDienThoaiChinhHangService;
import mvc.service.IDienThoaiXachTayService;

import java.util.List;

public class DienThoaiXachTayService implements IDienThoaiXachTayService {
    private static DienThoaiXachTayRepository dienThoaiXachTayRepository = new DienThoaiXachTayRepository();
    @Override
    public List<DienThoaiXachTay> getAll() {
        return dienThoaiXachTayRepository.findAll();//gọi từ repository qua
    }

    @Override
    public void save(DienThoaiXachTay dienThoaiXachTay) {
        dienThoaiXachTayRepository.save(dienThoaiXachTay);
    }

    @Override
    public void remove(int id) {
        dienThoaiXachTayRepository.remove(id);
    }

    @Override
    public void update(int id, DienThoaiXachTay dienThoaiXachTay) {

    }

    @Override
    public DienThoaiXachTay findById(int id) {
        return dienThoaiXachTayRepository.findById(id);
    }

    @Override
    public List<DienThoaiXachTay> findAllByName(String name) {
        return dienThoaiXachTayRepository.findAllByName(name);
    }
}