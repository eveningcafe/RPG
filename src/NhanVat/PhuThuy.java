package NhanVat;

import RPG.BanDo;
import Interfaces.*;
import RPG.NhanVat;
import RPG.QuaiVat;
import RPG.TPBanDo;
import java.util.ArrayList;
import java.util.Random;

public class PhuThuy extends QuaiVat implements PhepThuat {

    private String loaiPhep;
    private int tamSD;
    private int luongMauHoi;

public PhuThuy(int capDo) {
        super(capDo);
        HP=capDo*HPCoBan;
        satThuongCoBan=0;
        giap=capDo*giapCoBan;
        loaiPhep= new String("chua tri");
        luongMauHoi=capDo*10;
        tamSD=3;
    }

    @Override
    public String getLoaiPhep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Loại phép "+loaiPhep);
        return kq;
    }
    public void chuyenTrangThai(String trangThai) {
        //được gọi đến khi chuyển lượt(xem TrongTai). mỗi trạng thái có 1 năng lượng A, M khác nhau tùy vào nhân vật. 
        if(trangThai.equals("kiet suc")) {
            setTrangThai(trangThai);
            setNangLuongA(0);
            setNangLuongM(0);
        }else if(trangThai.equals("san sang")){
            setTrangThai(trangThai);
            setNangLuongA(1);
            setNangLuongM(2);
        }
    }

    /* ghi đè phương thức di chuyển của QuáiVật
     */
    @Override
    public String diChuyen(int ngang, int doc, BanDo bando) {
        int[] toado =timViTriBanThan(bando);
        TPBanDo dich = bando.getHave(ngang, doc); 
        dich.setHave(this);//nhân vật chuyển đến 1 ô mới
             bando.getHave(toado[0], toado[1]).nhanVatRoiDi();//ô cũ nhân vật rời đi
            switch (dich.getDiaHinh()) {
            // nếu địa hình là bùn trừ năng lương đi 2
                case "bun":
                    setNangLuongM(getNangLuongM()-2);
                    break;
                default:
                    setNangLuongM(getNangLuongM()-1);// trường hợp khác trừ đi 1
                    break;
            }
        String a = null;
        return a;
    
    }
    @Override
    public String tanCong(int ngang, int doc, BanDo bando) {
        System.out.println("Bac Si khong the tan cong");
        String a= new String();
        return a;
    }
    //tự dộng tìm ra tọa độ Ngang, Dọc của TPBando cần dùng skill và gọi đến skill(int ngang, int doc, BanDo bando).
    public void skill(BanDo bando) {
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        ArrayList xq =bando.getXungQuanh(toado[0], toado[1], tamSD);// xác định các ô xung quanh
        ArrayList<TPBanDo> hoiMauDuoc = new ArrayList();
        for(Object i: xq){
            TPBanDo o=(TPBanDo)i;
            if(o.getHave()!=null)
                if(o.getHave().getDoi().equals("do"))
                    hoiMauDuoc.add(o);
        }
        if(hoiMauDuoc.isEmpty())
            setNangLuongA(0);
        else{
             Random rd = new Random();
             TPBanDo mucTieu = hoiMauDuoc.get(rd.nextInt(hoiMauDuoc.size()));//lấy ngẫu nhiên 1 phần từ trong các ô hối máu được 
             this.skill(mucTieu.getToaDoNgang(), mucTieu.getToaDoDoc(), bando);
        }
    }
    @Override
    //truyền vào tọa độ Ngang, Dọc của TPBando cần dùng skill lên. 
    public String skill(int ngang, int doc, BanDo bando) {
        NhanVat temp = bando.getHave(ngang, doc).getHave();
        if(temp.getHP()<temp.getLuongHPToiDa()){// nếu đồng minh chưa đầy máu thì hồi
            temp.setHP(temp.getHP()+luongMauHoi);
            setEXP(getEXP()+50);
        }
        setNangLuongA(getNangLuongA()-1); //ở phù thủy. dù hồi máu đc hay không vẫn bị trừ năng lượng(do là phương thức tự động, giúp đảm bảo kết thúc được lượt của mình)
        String a= null;
        return a;
    }
    @Override 
    //tự dộng tìm ra tọa độ Ngang, Dọc của TPBando cần di chuyển đến và gọi đến diChuyen(int ngang, int doc, BanDo bando).
    public void tuDongDiChuyen(BanDo bando) {
        /*đây là phương thức giúp nhân vật tự động di chuyển. vậy nên không cần trả về thông báo gì
        quét các ô trong tầm di chuyển(8 ô xung quanh) :
        nếu có các ô di chuyển được thì di chuyển ngẫu nhiên đến 1 trong các ô đó.giảm năng lượng m đi 1( hoặc 0, hoặc 2)
        nếu không có ô di chuyển được thì đưa năng lượng m về 0.
        */
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        ArrayList xq =bando.getXungQuanh(toado[0], toado[1], 1);// xác định các ô xung quanh
        ArrayList<TPBanDo> diChuyenDuoc = new ArrayList();
        for(Object i: xq){
            TPBanDo o=(TPBanDo)i;
            if(o.getHave()==null)
                if(!(o.getDiaHinh().equals("tuong")
                        ||o.getDiaHinh().equals("congra")||o.getDiaHinh().equals("congvao"))){
                diChuyenDuoc.add(o);
            }
        }//các ô di chuyển được giờ đã nằm trong ArrayList diChuyenDuoc
        if(diChuyenDuoc.isEmpty())
            setNangLuongM(0);
        else{
             Random rd = new Random();
             TPBanDo dich = diChuyenDuoc.get(rd.nextInt(diChuyenDuoc.size())); 
             this.diChuyen(dich.getToaDoNgang(), dich.getToaDoDoc(), bando);
            }
             
             
        }
    
    
    

    @Override
    public void tuDongTanCong(BanDo bando) {
        System.out.println("Phù thủy làm hỗ trợ không tấn công");
    }

    @Override
    public void thangCap() {
        // thăng cấp cho nhân vật khi đủ EXP (>=100). tăng máu, khả năng hồi máu và giáp
        if(getEXP()>=100){
            setCapDo(getCapDo()+1);
            setEXP(getEXP()-100);
            setHP(getCapDo()*getHPCoBan());
            luongMauHoi=getCapDo()*10;
            setGiap(getCapDo()*getGiapCoBan());
        }
    }

    @Override
    public int getLuongHPToiDa() {
        return getCapDo()*getHPCoBan();
    }
}
