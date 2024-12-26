package mvc.repository;

import mvc.entity.DienThoaiChinhHang;
import mvc.enums.PhamViBaoHanh;

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

public class DienThoaiChinhHangRepository {
    public static final String DATA_CSV = "src/mvc/data/dataChinhHang.csv";


    public List<DienThoaiChinhHang> findAll() {
        File file = new File(DATA_CSV);
        List<DienThoaiChinhHang> dienThoaiChinhHangs = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String[] temp;
            DienThoaiChinhHang dienThoaiChinhHang;
            while ((line = bufferedReader.readLine())!= null) {
                temp = line.split(",");
                PhamViBaoHanh phamViBaoHanh = PhamViBaoHanh.valueOf(temp[6]); // Chuyển đổi chuỗi sang enum
                dienThoaiChinhHang = new DienThoaiChinhHang(Integer.parseInt(temp[0]), temp[1], Double.parseDouble(temp[2]), Integer.parseInt(temp[3]),temp[4],Integer.parseInt(temp[5]),phamViBaoHanh);
                dienThoaiChinhHangs.add(dienThoaiChinhHang);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return dienThoaiChinhHangs;
    }

    public List<DienThoaiChinhHang> findAllByName(String name) {
        List<DienThoaiChinhHang> result = new ArrayList<>();
        List<DienThoaiChinhHang> dienThoaiChinhHangList = findAll();
        for (DienThoaiChinhHang dienThoaiChinhHang : dienThoaiChinhHangList) {
//            Tìm kiếm gần đúng
            if (dienThoaiChinhHang.getTen().contains(name)) {
                result.add(dienThoaiChinhHang);
            }
        }
        return result;
    }

    public void save(DienThoaiChinhHang dienThoaiChinhHang) {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = new ArrayList<>();
        dienThoaiChinhHangList.add(dienThoaiChinhHang);
        writeFile(dienThoaiChinhHangList, true);

    }

    //Chuyển đổi thuộc tính thành String trước khi ghi vào file
    private String dienThoaiChinhHangToString(DienThoaiChinhHang dienThoaiChinhHang) {
        return dienThoaiChinhHang.getId() + "," + dienThoaiChinhHang.getTen()
                + "," + dienThoaiChinhHang.getGia() + "," + dienThoaiChinhHang.getSoLuong()
                + "," + dienThoaiChinhHang.getNhaSanXuat() + "," + dienThoaiChinhHang.getThoiGianBaoHanh()
                + "," + dienThoaiChinhHang.getPhamViBaoHanh();
    }

    public DienThoaiChinhHang findById(int id) {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = findAll();
        for (DienThoaiChinhHang dienThoaiChinhHang : dienThoaiChinhHangList) {
            if (dienThoaiChinhHang.getId() == id) {
                return dienThoaiChinhHang;
            }
        }
        return null;
    }

    public void remove(int id) {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = findAll();
        for (DienThoaiChinhHang dienThoaiChinhHang : dienThoaiChinhHangList) {
            if (dienThoaiChinhHang.getId() == id) {
                dienThoaiChinhHangList.remove(dienThoaiChinhHang);
                break;
            }
        }
        writeFile(dienThoaiChinhHangList, false);
    }

    public void writeFile(List<DienThoaiChinhHang> dienThoaiChinhHangList, boolean append) {
        File file = new File(DATA_CSV);
        try (FileWriter fileWriter = new FileWriter(file, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (DienThoaiChinhHang dienThoaiChinhHang: dienThoaiChinhHangList) {
                bufferedWriter.write(dienThoaiChinhHangToString(dienThoaiChinhHang));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }
}