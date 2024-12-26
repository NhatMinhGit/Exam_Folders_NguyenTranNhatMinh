package mvc.service.impl;

import mvc.entity.DienThoaiChinhHang;
import mvc.repository.DienThoaiChinhHangRepository;
import mvc.service.IDienThoaiChinhHangService;

import java.util.List;

public class DienThoaiChinhHangService implements IDienThoaiChinhHangService {
    private static DienThoaiChinhHangRepository dienThoaiChinhHangRepository = new DienThoaiChinhHangRepository();
    @Override
    public List<DienThoaiChinhHang> getAll() {
        return dienThoaiChinhHangRepository.findAll();//gọi từ repository qua
    }

    @Override
    public void save(DienThoaiChinhHang dienThoaiChinhHang) {
        dienThoaiChinhHangRepository.save(dienThoaiChinhHang);
    }

    @Override
    public void remove(int id) {
        dienThoaiChinhHangRepository.remove(id);
    }

    @Override
    public void update(int id, DienThoaiChinhHang dienThoaiChinhHang) {

    }

    @Override
    public DienThoaiChinhHang findById(int id) {
        return dienThoaiChinhHangRepository.findById(id);
    }

    @Override
    public List<DienThoaiChinhHang> findAllByName(String name) {
        return dienThoaiChinhHangRepository.findAllByName(name);
    }
}