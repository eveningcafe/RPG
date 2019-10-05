package NhanVat;

import RPG.BanDo;
import Interfaces.*;
import RPG.NhanVat;
import RPG.QuaiVat;
import RPG.TPBanDo;
import java.util.ArrayList;
import java.util.Random;
public class QuaiNemDa extends QuaiVat implements DanhXa {

    private int tamXa;

    public QuaiNemDa(int capDo) {
        super(capDo);
        tamXa=3;
        HP=capDo*HPCoBan;
        satThuong=capDo*satThuongCoBan*3/2;
        giap=capDo*giapCoBan;
    }
    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Tầm Xa "+tamXa);
        return kq;
    }

    @Override
    public int getTamXa() {
        return tamXa;
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
            setNangLuongM(4);
        }
    }
    @Override
    public String tanCong(int ngang,int doc,BanDo bando) {
        // ngang, doc là tọa độ của thành phần bản đồ cần tấn công
        
        NhanVat temp = bando.getHave(ngang, doc).getHave();
        temp.setHP(temp.getHP()-this.getSatThuong()+temp.getGiap());
        System.out.println("Nhân vật ở tọa độ "+ngang+"-"+doc+" vừa bị quái ném đá ở tọa độ "
                            +this.timViTriBanThan(bando)[0]+"-"+this.timViTriBanThan(bando)[1]+" tấn công");
        
        setNangLuongA(getNangLuongA()-1);
        setEXP(getEXP()+50);
        String a =null;
        return a;
    }

    @Override
    public void tuDongTanCong(BanDo bando) {
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        ArrayList xq =bando.getXungQuanh(toado[0], toado[1], tamXa);// xác định các ô xung quanh
        ArrayList<TPBanDo> tanCongDuoc = new ArrayList();
        for(Object i: xq){
            TPBanDo o=(TPBanDo)i;
            if(o.getHave()!=null)
                if(o.getHave().getDoi().equals("den"))
                    tanCongDuoc.add(o);
        }
        if(tanCongDuoc.isEmpty())
            setNangLuongA(0);
        else{
             Random rd = new Random();
             TPBanDo dich = tanCongDuoc.get(rd.nextInt(tanCongDuoc.size()));//lấy ngẫu nhiên 1 phần từ trong các ô tấn công được 
             this.tanCong(dich.getToaDoNgang(), dich.getToaDoDoc(), bando);
        }
    }

    @Override
    public void thangCap() {
        // thăng cấp cho nhân vật khi đủ EXP (>=100). tăng máu, satsthuong và giáp
        if(getEXP()>=100){
            setCapDo(getCapDo()+1);
            setEXP(getEXP()-100);
            setHP(getCapDo()*getHPCoBan());
            setSatThuong(getCapDo()*getSatThuongCoBan()*3/2);
            setGiap(getCapDo()*getGiapCoBan());
        }
    }

    @Override
    public int getLuongHPToiDa() {
       return getCapDo()*getHPCoBan();
    }
    
    
}

