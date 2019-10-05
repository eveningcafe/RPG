package RPG;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import RPG.*;

public abstract class QuaiVat extends NhanVat {

    
    public QuaiVat(int capDo){
        super(capDo);
        doi="do";
        trangThai="kiet suc";
    }

    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        return a;
    }

    
    @Override
    //truyền vào tọa độ Ngang, Dọc của TPBando cần tấn công. trả về String: kết quả tấn công.  trả về null: tấn công thành công
    public abstract String tanCong(int ngang, int doc, BanDo bando);
    //tự dộng tìm ra tọa độ Ngang, Dọc của TPBando cần tấn công và gọi đến tanCong(int ngang, int doc, BanDo bando).
    public abstract void tuDongTanCong(BanDo bando); 
    @Override
    public abstract int getLuongHPToiDa();
    @Override
     //truyền vào tọa độ Ngang, Dọc của TPBando cần di chuyển đến. 
    public String diChuyen(int ngang, int doc, BanDo bando) {
         int[] toado =timViTriBanThan(bando);
         TPBanDo dich = bando.getHave(ngang, doc); 
            //System.out.println("đích là "+dich.getToaDoNgang()+dich.getToaDoDoc());
             dich.setHave(this);//nhân vật chuyển đến 1 ô mới
             bando.getHave(toado[0], toado[1]).nhanVatRoiDi();//ô cũ nhân vật rời đi
            switch (dich.getDiaHinh()) {
            // nếu địa hình là bùn trừ năng lương đi 2
                case "bun":
                    setNangLuongM(getNangLuongM()-2);
                    break;
                case "da":
                    break;
                default:
                    setNangLuongM(getNangLuongM()-1);// trường hợp khác trừ đi 1
                    break;
            }
        // thực tế không cần trả về thông báo cho người dùng về việc di chuyển thành công hay không của quái vật
        String a = null;
        return a;
    }
//tự dộng tìm ra tọa độ Ngang, Dọc của TPBando cần di chuyển đến và gọi đến diChuyen(int ngang, int doc, BanDo bando).
    public void tuDongDiChuyen(BanDo bando) {
        /*đây là phương thức giúp nhân vật tự động di chuyển. vậy nên không cần trả về thông báo gì
        quét các ô trong tầm di chuyển(8 ô xung quanh) :
        nếu có các ô di chuyển được thì di chuyển ngẫu nhiên đến 1 trong các ô đó.giảm năng lượng m đi 1( hoặc 0, hoặc 2)
        nếu không có ô di chuyển được thì đưa năng lượng m về 0.
        */
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        //System.out.println("bat dau tim xq, vi tri ban than la "+toado[0]+toado[1]);
        ArrayList xq =bando.getXungQuanh(toado[0], toado[1], 1);// xác định các ô xung quanh
        ArrayList<TPBanDo> diChuyenDuoc = new ArrayList();
        for(Object i: xq){
            TPBanDo o=(TPBanDo)i;
            //System.out.println("ô xq đang xét "+o.getToaDoNgang()+o.getToaDoDoc());
            if(o.getHave()==null){
                // quái vật bình thường không thể di chuyển trên nước, hoặc tường, cổng ra,cổng vào
                if(!(o.getDiaHinh().equals("nuoc")||o.getDiaHinh().equals("tuong")
                        ||o.getDiaHinh().equals("congra")||o.getDiaHinh().equals("congvao"))){
                    //System.out.println("add "+o.getToaDoNgang()+ o.getToaDoDoc());
                    diChuyenDuoc.add(o);
                }
            }
        }//các ô di chuyển được giờ đã nằm trong ArrayList diChuyenDuoc
        if(diChuyenDuoc.isEmpty())
            setNangLuongM(0);
        else{
             Random rd = new Random();
             TPBanDo dich = diChuyenDuoc.get(rd.nextInt(diChuyenDuoc.size())); 
             //System.out.println("đích là "+dich.getToaDoNgang()+dich.getToaDoDoc());
             this.diChuyen(dich.getToaDoNgang(), dich.getToaDoDoc(), bando);
        }
    
    }
    @Override
    public void chet(BanDo bando) {
        int[] toado =timViTriBanThan(bando);
        System.out.println("Quái vật ở tọa độ "+toado[0]+"-"+toado[1]+" vừa bị tiêu diệt");
        bando.getHave(toado[0], toado[1]).setHave(null);
    }
    
}
