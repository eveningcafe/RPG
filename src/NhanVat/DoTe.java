package NhanVat;

import Interfaces.*;
import RPG.*;

import java.util.ArrayList;
import java.util.Random;

public class DoTe extends QuaiVat implements DanhGan {

    private int tamLan;

public DoTe(int capDo) {
        super(capDo);
        this.tamLan=1;
        HP=capDo*HPCoBan*3/2;
        satThuong=capDo*satThuongCoBan;
        giap=capDo*giapCoBan*2;
    }
    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Tầm đánh lan: "+tamLan);
        return kq;
    }
    @Override
     public void chuyenTrangThai(String trangThai) {
        //được gọi đến khi chuyển lượt(xem TrongTai). mỗi trạng thái có 1 năng lượng A, M khác nhau tùy vào nhân vật. 
        if(trangThai.equals("kiet suc")) {
            setTrangThai(trangThai);
            setNangLuongA(0);
            setNangLuongM(0);
        }else if(trangThai.equals("san sang")){
            setTrangThai(trangThai);
            setNangLuongA(1);
            setNangLuongM(3);
        }
    }

    @Override
    public int getTamLan() {
        return tamLan;
    }

    ;
 @Override
     public String tanCong(int ngang, int doc, BanDo bando) {
        int[] toado =timViTriBanThan(bando);
        TPBanDo dich = bando.getHave(ngang, doc); 
        NhanVat temp = bando.getHave(ngang, doc).getHave();
            temp.setHP(temp.getHP()-this.getSatThuong()+temp.getGiap());
            System.out.println("Nhân vật ở tọa độ "+ngang+"-"+doc+" vừa bị đồ tể ở tọa độ "
                            +this.timViTriBanThan(bando)[0]+"-"+this.timViTriBanThan(bando)[1]+" tấn công");
        for(Object m : bando.getXungQuanh(ngang, doc, tamLan)){
                TPBanDo xq = (TPBanDo)m;
                if(xq.getHave()!=null){
                    if(xq.getHave().getDoi().equals("den")){
                        xq.getHave().setHP(xq.getHave().getHP()-this.getSatThuong()/2);
                        System.out.println("Hiệu ứng tấn công lan, nhân vật ở tọa độ "+xq.getHave().timViTriBanThan(bando)[0]+"-"+xq.getHave().timViTriBanThan(bando)[1]+" cũng mất máu");   
                    }
                }
            }
        setNangLuongA(getNangLuongA()-1);    
        setEXP(getEXP()+50);
        String a =null;
        return a;
         
        
    }

    @Override
    public void tuDongTanCong(BanDo bando) {
        /*đây là phương thức giúp nhân vật tự động tấn công. vậy nên không cần trả về thông báo gì
        quét các ô trong tầm di chuyển(8 ô xung quanh) :
        nếu có các ô tấn công được thì tấn công ngẫu nhiên đến 1 trong các ô đó.giảm năng lượng a đi 1
                                                    ( +exp và kiểm tra đối phương chết sau khi tấn công)
        nếu không có ô tấn công được thì đưa năng lượng a về 0.
        */
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        ArrayList xq =bando.getXungQuanh(toado[0], toado[1], 1);// xác định các ô xung quanh
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
            setHP(getCapDo()*getHPCoBan()*3/2);
            setSatThuong(getCapDo()*getSatThuongCoBan());
            setGiap(getCapDo()*getGiapCoBan()*2);
        }
    }

    @Override
    public int getLuongHPToiDa() {
        return getCapDo()*getHPCoBan()*3/2;
    }
}