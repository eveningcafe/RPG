package NhanVat;

import RPG.Nguoi;
import Interfaces.*;
import RPG.BanDo;
import RPG.NhanVat;
import RPG.TPBanDo;

public class CungThu extends Nguoi implements DanhXa {

    private int tamXa;
    
    public CungThu(int capDo) {
        super(capDo);
        this.tamXa=3;
        HP= capDo*HPCoBan;
        satThuong=capDo*satThuongCoBan*3/2;
        giap=capDo*giapCoBan;
        nangLuongA=1;
        nangLuongM=4;
    }
    
    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Tầm Xa "+tamXa);
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
            setNangLuongM(4);
        }
    }
    @Override
    public int getTamXa() {
        return tamXa;
    }

    @Override
    public String tanCong(int ngang,int doc,BanDo bando) {
        // ngang, doc là tọa độ của thành phần bản đồ cần tấn công. 

        int[]toado= timViTriBanThan(bando);
        String a;
        
        if(getNangLuongA()==0)
            a =new String("Nhân vật này đã hết khả năng tấn công");
        else if(bando.getHave(ngang, doc).getHave()==null)
            a =new String("Không thể tấn công vào mặt đất. hãy chọn mục tiêu quái đối phương");
        else if(bando.getHave(ngang, doc).getHave().getDoi().equals("den"))
            a =new String("Không thể tấn công vào đồng minh. hãy chọn mục tiêu quái đối phương");
        else if(!( bando.getXungQuanh(toado[0], toado[1], tamXa).contains(bando.getHave(ngang, doc)))){
            a = new String("Không thể tấn công do mục tiêu ở quá xa, tầm bắn xa chỉ là: "+tamXa);
        }
        else{
            NhanVat temp = bando.getHave(ngang, doc).getHave();
            temp.setHP(temp.getHP()-this.getSatThuong()+temp.getGiap());
            System.out.println("Cung thủ vừa tấn công địch ở tọa độ: "+temp.timViTriBanThan(bando)[0]+"-"+temp.timViTriBanThan(bando)[1]);
            setNangLuongA(getNangLuongA()-1);
            setEXP(getEXP()+50);
            a= new String();
        }
        return a;
    }

    @Override
    public void thangCap() {
        // thăng cấp cho nhân vật khi đủ EXP (>=100). tăng máu, satsthuong và giáp
        if(getEXP()>=100){
            setCapDo(getCapDo()+1);
            setEXP(getEXP()-100);
            setHP(getCapDo()*getHPCoBan()*3/2);
            setSatThuong(getCapDo()*getSatThuongCoBan());
            setGiap(getCapDo()*getGiapCoBan()*2);
        }
    }

    @Override
    public int getLuongHPToiDa() {
        return getCapDo()*getHPCoBan();
    }
}
