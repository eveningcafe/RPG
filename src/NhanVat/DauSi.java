package NhanVat;

import Interfaces.*;
import RPG.*;
import RPG.Nguoi;

public class DauSi extends Nguoi implements DanhGan {

    private int tamLan;

    public DauSi(int capDo) {
        super(capDo);
        this.tamLan=1;
        HP=capDo*HPCoBan*3/2;
        satThuong=capDo*satThuongCoBan;
        giap=capDo*giapCoBan*2;
        nangLuongA=1;
        nangLuongM=3;
    }
    @Override
    public void chuyenTrangThai(String trangThai) {
        //được gọi đến khi chuyển lượt(xem thêm TrongTai). mỗi trạng thái có 1 năng lượng A, M khác nhau tùy vào nhân vật. 
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
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Tầm đánh lan: "+tamLan);
        return kq;
    }

    @Override
    public int getTamLan() {
        return tamLan;
    }
    @Override
    public String tanCong(int ngang,int doc,BanDo bando) {
        // ngang, doc là tọa độ của thành phần bản đồ cần tấn công. kiểm tra xem TPBanDo đó nhân vật không?
        int[]toado= timViTriBanThan(bando);
        String a;
        
        if(getNangLuongA()==0)
            a =new String("Nhân vật này đã hết khả năng tấn công");
        else if(bando.getHave(ngang, doc).getHave()==null)
            a =new String("Không thể tấn công vào mặt đất. hãy chọn mục tiêu quái đối phương");
        else if(bando.getHave(ngang, doc).getHave().getDoi().equals("den"))
            a =new String("Không thể tấn công vào đồng minh. hãy chọn mục tiêu quái đối phương");
        else if(!( bando.getXungQuanh(toado[0], toado[1], 1).contains(bando.getHave(ngang, doc)))){
            a = new String("Không thể tấn công do mục tiêu ở quá xa");
        }
        else{
            NhanVat temp = bando.getHave(ngang, doc).getHave();
            temp.setHP(temp.getHP()-this.getSatThuong()+temp.getGiap());
            System.out.println("Đấu sĩ vừa tấn công nhân vật ở tọa độ: "+temp.timViTriBanThan(bando)[0]+"-"+temp.timViTriBanThan(bando)[1]);
            for(Object m : bando.getXungQuanh(ngang, doc, tamLan)){
                TPBanDo xq = (TPBanDo)m;
                if(xq.getHave()!=null){
                    if(xq.getHave().getDoi().equals("do")){
                        xq.getHave().setHP(xq.getHave().getHP()-this.getSatThuong()/2);
                        System.out.println("hiệu ứng tấn công lan, nhân vật ở tọa độ: "+xq.getHave().timViTriBanThan(bando)[0]+"-"+xq.getHave().timViTriBanThan(bando)[1]+" cũng mất máu");
                    }
                }
            }
            setEXP(getEXP()+50);
            setNangLuongA(getNangLuongA()-1);
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
        return getCapDo()*getHPCoBan()*3/2;
    }
}
