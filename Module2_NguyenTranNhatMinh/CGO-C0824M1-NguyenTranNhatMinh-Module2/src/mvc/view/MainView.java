package mvc.view;

import mvc.controller.DienThoaiChinhHangController;
import mvc.controller.DienThoaiXachTayController;
import mvc.entity.DienThoaiChinhHang;
import mvc.entity.DienThoaiXachTay;
import mvc.enums.PhamViBaoHanh;
import mvc.enums.TrangThaiDienThoai;
import mvc.service.impl.DienThoaiXachTayService;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------Chương trình quản lý CodeGym------------");
            System.out.println("1. Quản lý điện thoại chính hãng");
            System.out.println("2. Quản lý xách tay");
            System.out.println("3. Thoát");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choice = getChoice(scanner);
            switch (choice) {
                case 1:
                    menuDienThoaiChinhHang();
                    break;
                case 2:
                    menuDienThoaiXachTay();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Người dùng nhập sai, vui lòng nhập lại");
            }
        }
    }

    public static void menuDienThoaiChinhHang() {
        DienThoaiChinhHangController dienThoaiChinhHangController = new DienThoaiChinhHangController();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------Chương trình quản lý điện thoại chính hãng------------");
            System.out.println("1. Thêm điện thoại");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Xóa điện thoại");
            System.out.println("4. Tìm kiếm điện thoại theo tên");
            System.out.println("5. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            List<DienThoaiChinhHang> dienThoaiChinhHangList;
            int choice = getChoice(scanner);
            switch (choice) {
                case 1:
                    DienThoaiChinhHang dienThoaiChinhHang = inputDienThoaiChinhHang(scanner);
                    dienThoaiChinhHangController.save(dienThoaiChinhHang);
                    System.out.println("Thêm mới thành công!!!!!!");
                    break;
                case 2:
                    dienThoaiChinhHangList = dienThoaiChinhHangController.getAll();
                    printDienThoaiChinhHangTable(dienThoaiChinhHangList);
                    break;
                case 3:
                    System.out.print("Nhập vào mã điện thoại cần xóa: ");
                    int code = Integer.parseInt(scanner.nextLine());
                    dienThoaiChinhHang = dienThoaiChinhHangController.findById(code);
                    if(dienThoaiChinhHang == null) {
                        System.out.println("Không tìm thấy đối tượng cần xóa");
                    } else {
                        System.out.println("Thông tin của điện thoại: "+ dienThoaiChinhHang);
                        System.out.println("bạn có chắc muốn xóa hay không? Nếu có thì bấm Y, nếu không thì bấm N");
                        char confirm = scanner.nextLine().charAt(0);
                        if(confirm == 'y') {
                            dienThoaiChinhHangController.remove(code);
                            System.out.println("Xóa thành công");
                        }
                    }
                    break;
                case 4:

                    System.out.print("Nhập vào tên cần tìm: ");
                    String name = scanner.nextLine();
                    dienThoaiChinhHangList = dienThoaiChinhHangController.findAllByName(name);
                    printDienThoaiChinhHangTable(dienThoaiChinhHangList);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Người dùng nhập sai, vui lòng nhập lại");
            }
        }

    }
    public static void menuDienThoaiXachTay() {
        DienThoaiXachTayController dienThoaiXachTayController = new DienThoaiXachTayController();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------Chương trình quản lý điện thoại xách tay------------");
            System.out.println("1. Thêm điện thoại");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Xóa điện thoại");
            System.out.println("4. Tìm kiếm điện thoại theo tên");
            System.out.println("5. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            List<DienThoaiXachTay> dienThoaiXachTayList;
            int choice = getChoice(scanner);
            switch (choice) {
                case 1:
                    DienThoaiXachTay dienThoaiXachTay = inputDienThoaiXachTay(scanner);
                    dienThoaiXachTayController.save(dienThoaiXachTay);
                    System.out.println("Thêm mới thành công!!!!!!");
                    break;
                case 2:
                    dienThoaiXachTayList = dienThoaiXachTayController.getAll();
                    printDienThoaiXachTayTable(dienThoaiXachTayList);
                    break;
                case 3:
                    System.out.print("Nhập vào mã điện thoại cần xóa: ");
                    int code = Integer.parseInt(scanner.nextLine());
                    dienThoaiXachTay = dienThoaiXachTayController.findById(code);
                    if(dienThoaiXachTay == null) {
                        System.out.println("Không tìm thấy đối tượng cần xóa");
                    } else {
                        System.out.println("Thông tin của điện thoại: "+ dienThoaiXachTay);
                        System.out.println("bạn có chắc muốn xóa hay không? Nếu có thì bấm Y, nếu không thì bấm N");
                        char confirm = scanner.nextLine().charAt(0);
                        if(confirm == 'y') {
                            dienThoaiXachTayController.remove(code);
                            System.out.println("Xóa thành công");
                        }
                    }
                    break;
                case 4:

                    System.out.print("Nhập vào tên cần tìm: ");
                    String name = scanner.nextLine();
                    dienThoaiXachTayList = dienThoaiXachTayController.findAllByName(name);
                    printDienThoaiXachTayTable(dienThoaiXachTayList);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Người dùng nhập sai, vui lòng nhập lại");
            }
        }

    }

    public static int getChoice(Scanner scanner) throws NumberFormatException {
        int choice  = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > 10000000) {
            throw new NumberFormatException("Lỗi nhập choice");
        }
        return choice;
    }

    public static void printDienThoaiChinhHangTable(List<DienThoaiChinhHang> dienThoaiChinhHangList) {
        System.out.println("+------+----------------------+-----------------+---------------+----------------------+---------------------------+----------------------+");
        System.out.println("| Code | Name                 | Price           | Quantity      | Manufacturer         | Guarantee Time            | Guarantee distance   |");
        System.out.println("+------+----------------------+-----------------+---------------+----------------------+---------------------------+----------------------+");
        for (DienThoaiChinhHang temp : dienThoaiChinhHangList) {
            System.out.println(temp);
        }
        System.out.println("+------+----------------------+----------------------+--------+----------------------+----------------------+--------+--------------------+");
    }
    public static void printDienThoaiXachTayTable(List<DienThoaiXachTay> dienThoaiXachTayList) {
        System.out.println("+------+----------------------+-----------------+---------------+----------------------+---------------------------+----------------------+");
        System.out.println("| Code | Name                 | Price           | Quantity      | Manufacturer         | Come from                 | Status               |");
        System.out.println("+------+----------------------+-----------------+---------------+----------------------+---------------------------+----------------------+");
        for (DienThoaiXachTay temp : dienThoaiXachTayList) {
            System.out.println(temp);
        }
        System.out.println("+------+----------------------+----------------------+--------+----------------------+----------------------+--------+--------------------+");
    }

    public static DienThoaiChinhHang inputDienThoaiChinhHang(Scanner scanner) {
        System.out.print("Nhập mã điện thoại (ID): ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập tên điện thoại: ");
        String ten = scanner.nextLine();
        String regex = "^[A-Z]{1}[a-zA-Z0-9 ]+$";
        while (!ten.trim().matches(regex)) {
            System.out.println("Tên không đúng định dạng (phải bắt đầu bằng chữ in hoa).");
            System.out.print("Nhập lại tên điện thoại: ");
            ten = scanner.nextLine();
        }

        System.out.print("Nhập giá điện thoại: ");
        double gia = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập nhà sản xuất: ");
        String nhaSanXuat = scanner.nextLine();

        System.out.print("Nhập thời gian bảo hành (số ngày): ");
        int thoiGianBaoHanh = -1;
        while (thoiGianBaoHanh < 0 || thoiGianBaoHanh > 730) {
            try {
                thoiGianBaoHanh = Integer.parseInt(scanner.nextLine());
                if (thoiGianBaoHanh < 0 || thoiGianBaoHanh > 730) {
                    System.out.println("Thời gian bảo hành phải là số dương và không quá 730 ngày. Vui lòng nhập lại:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng nhập số nguyên dương:");
            }
        }

        System.out.println("Nhập phạm vi bảo hành (TrongNuoc/QuocTe): ");
        PhamViBaoHanh phamViBaoHanh = null;
        while (phamViBaoHanh == null) {
            try {
                phamViBaoHanh = PhamViBaoHanh.valueOf(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Phạm vi bảo hành không hợp lệ. Vui lòng nhập TrongNuoc hoặc QuocTe: ");
            }
        }


        return new DienThoaiChinhHang(id,
                ten,
                gia,
                soLuong,
                nhaSanXuat,
                thoiGianBaoHanh,
                phamViBaoHanh);
    }
    public static DienThoaiXachTay inputDienThoaiXachTay(Scanner scanner) {
        System.out.print("Nhập mã điện thoại (ID): ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập tên điện thoại: ");
        String ten = scanner.nextLine();
        String regex = "^[A-Z]{1}[a-zA-Z0-9 ]+$";
        while (!ten.trim().matches(regex)) {
            System.out.println("Tên không đúng định dạng (phải bắt đầu bằng chữ in hoa).");
            System.out.print("Nhập lại tên điện thoại: ");
            ten = scanner.nextLine();
        }

        System.out.print("Nhập giá điện thoại: ");
        double gia = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập nhà sản xuất: ");
        String nhaSanXuat = scanner.nextLine();

        System.out.print("Nhập quốc gia xách tay: ");
        String quocGiaXachTay = scanner.nextLine();

        System.out.println("Nhập phạm vi bảo hành (DaSuaChua/ChuaSuaChua): ");
        TrangThaiDienThoai trangThaiDienThoai = null;
        while (trangThaiDienThoai == null) {
            try {
                trangThaiDienThoai = TrangThaiDienThoai.valueOf(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Trạng thái điện thoại không hợp lệ. Vui lòng nhập DaSuaChua hoặc ChuaSuaChua: ");
            }
        }


        return new DienThoaiXachTay(id,
                ten,
                gia,
                soLuong,
                nhaSanXuat,
                quocGiaXachTay,
                trangThaiDienThoai);
    }
}