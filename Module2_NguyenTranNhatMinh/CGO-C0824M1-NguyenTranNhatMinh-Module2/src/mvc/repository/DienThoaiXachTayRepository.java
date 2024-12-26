package mvc.repository;

import mvc.entity.DienThoaiChinhHang;
import mvc.entity.DienThoaiXachTay;
import mvc.enums.PhamViBaoHanh;
import mvc.enums.TrangThaiDienThoai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DienThoaiXachTayRepository {
    public static final String DATA_CSV = "src/mvc/data/dataXachTay.csv";


    public List<DienThoaiXachTay> findAll() {
        File file = new File(DATA_CSV);
        List<DienThoaiXachTay> dienThoaiXachTayList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String[] temp;
            DienThoaiXachTay dienThoaiXachTay;
            while ((line = bufferedReader.readLine())!= null) {
                temp = line.split(",");
                TrangThaiDienThoai trangThaiDienThoai = TrangThaiDienThoai.valueOf(temp[6]); // Chuyển đổi chuỗi sang enum
                dienThoaiXachTay = new DienThoaiXachTay(Integer.parseInt(temp[0]), temp[1], Double.parseDouble(temp[2]), Integer.parseInt(temp[3]),temp[4],temp[5],trangThaiDienThoai);
                dienThoaiXachTayList.add(dienThoaiXachTay);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return dienThoaiXachTayList;
    }

    public List<DienThoaiXachTay> findAllByName(String name) {
        List<DienThoaiXachTay> result = new ArrayList<>();
        List<DienThoaiXachTay> dienThoaiXachTayList = findAll();
        for (DienThoaiXachTay dienThoaiXachTay : dienThoaiXachTayList) {
//            Tìm kiếm gần đúng
            if (dienThoaiXachTay.getTen().contains(name)) {
                result.add(dienThoaiXachTay);
            }
        }
        return result;
    }

    public void save(DienThoaiXachTay dienThoaiXachTay) {
        List<DienThoaiXachTay> dienThoaiXachTayList = new ArrayList<>();
        dienThoaiXachTayList.add(dienThoaiXachTay);
        writeFile(dienThoaiXachTayList, true);

    }

    //Chuyển đổi thuộc tính thành String trước khi ghi vào file
    private String dienThoaiXachTayToString(DienThoaiXachTay dienThoaiXachTay) {
        return dienThoaiXachTay.getId() + "," + dienThoaiXachTay.getTen()
                + "," + dienThoaiXachTay.getGia() + "," + dienThoaiXachTay.getSoLuong()
                + "," + dienThoaiXachTay.getNhaSanXuat() + "," + dienThoaiXachTay.getQuocGiaXachTay()
                + "," + dienThoaiXachTay.getTrangThaiTuiXach();
    }

    public DienThoaiXachTay findById(int id) {
        List<DienThoaiXachTay> dienThoaiXachTayList = findAll();
        for (DienThoaiXachTay dienThoaiXachTay : dienThoaiXachTayList) {
            if (dienThoaiXachTay.getId() == id) {
                return dienThoaiXachTay;
            }
        }
        return null;
    }

    public void remove(int id) {
        List<DienThoaiXachTay> dienThoaiXachTayList = findAll();
        for (DienThoaiXachTay dienThoaiXachTay : dienThoaiXachTayList) {
            if (dienThoaiXachTay.getId() == id) {
                dienThoaiXachTayList.remove(dienThoaiXachTay);
                break;
            }
        }
        writeFile(dienThoaiXachTayList, false);
    }

    public void writeFile(List<DienThoaiXachTay> dienThoaiXachTayList, boolean append) {
        File file = new File(DATA_CSV);
        try (FileWriter fileWriter = new FileWriter(file, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (DienThoaiXachTay dienThoaiXachTay: dienThoaiXachTayList) {
                bufferedWriter.write(dienThoaiXachTayToString(dienThoaiXachTay));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }
}