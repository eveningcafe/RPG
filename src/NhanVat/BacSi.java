package NhanVat;

import RPG.BanDo;
import RPG.Nguoi;
import Interfaces.*;
import RPG.NhanVat;
import RPG.TPBanDo;

public class BacSi extends Nguoi implements PhepThuat {

    private String loaiPhep;
    private int tamSD;
    private int luongMauHoi;
    public BacSi(int capDo) {
        super(capDo);
        this.loaiPhep= new String("chua tri");
        HP=capDo*HPCoBan;
        satThuongCoBan=0;
        giap=capDo*giapCoBan;
        nangLuongA=1;
        nangLuongM=2;
        luongMauHoi=capDo*10;
        tamSD=3;
    }
    
    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        String kq =a.concat(", Loại phép "+loaiPhep);
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
            setNangLuongM(2);
        }
    }
    @Override
    public String getLoaiPhep() {
        return loaiPhep;
    }
    @Override
     public String diChuyen(int ngang, int doc, BanDo bando) {
        String a;
        int[] toado =timViTriBanThan(bando);// xác định vị trí bản thân
        //kiểm tra điều kiện về khoảng cách di chuyển
        if(!( bando.getXungQuanh(toado[0], toado[1], 1).contains(bando.getHave(ngang, doc)))){
            a = new String("Chỉ có thể di chuyển nhân vật ra 8 ô xung quanh nó");
            return a;
        }
        //kiểm tra điều kiện về ô đích
        if(bando.getHave(ngang, doc).getDiaHinh().equals("congvao")){
            a = new String("về màn trước");
        }else if(bando.getHave(ngang, doc).getDiaHinh().equals("congra")){
            a = new String("sang màn kế");
        }else if(getNangLuongM()==0){
            a =new String("Nhân vật này đã hết khả năng di chuyển");
        }else if(bando.getHave(ngang, doc).getDiaHinh().equals("tuong")){
            a =new String("Không thể vượt tường");
        }else if(bando.getHave(ngang, doc).getHave()!=null){
            a =new String("Ô này đã có nhân vật");
        }else{
        bando.getHave(ngang, doc).setHave(this);//nhân vật chuyển đến 1 ô mới
        bando.getHave(toado[0], toado[1]).nhanVatRoiDi();//không được đổi vị trí 2 lệnh này( rời đi trước chuyển đến). do con trỏ bị mất vị trí trỏ.
        if(!(bando.getHave(ngang, doc).getDiaHinh().equals("da")))
                setNangLuongM(getNangLuongM()-1);// nếu đi trên đá, không bị trừ năng lượng
        if(bando.getHave(ngang, doc).getDiaHinh().equals("bun"))
                setNangLuongM(getNangLuongM()-1);// nếu đi phải bùn. năng lượng bì trừ thêm 1
         a = new String();
        }
        return a;

    }

    @Override
    public String tanCong(int ngang, int doc, BanDo bando) {
        System.out.println("Bac Si khong the tan cong");
        String a= new String();
        return a;
    }

    @Override
    //truyền vào tọa độ Ngang, Dọc của TPBando cần dùng skill. trả về String: kết quả hồi máu. nếu kết quả null: hồi máu thành công
    public String skill(int ngang, int doc, BanDo bando) {
        int[]toado= timViTriBanThan(bando);
        String a;
        
        if(getNangLuongA()==0)
            a =new String("Nhân vật này đã hết khả năng dùng skill");
        else if(bando.getHave(ngang, doc).getHave()==null)
            a =new String("Không thể dùng phép vào mặt đất. hãy chọn mục tiêu quái đối phương");
        else if(bando.getHave(ngang, doc).getHave().getDoi().equals("do"))
            a =new String("Không thể hồi máu vào đối phương. hãy chọn mục tiêu là đồng minh");
        else if(bando.getHave(ngang, doc).getHave()==this)
            a=new String("Theo luật không thể hồi máu cho chính mình");
        else if(!( bando.getXungQuanh(toado[0], toado[1], tamSD).contains(bando.getHave(ngang, doc))))
            a = new String("Không thể hồi máu do mục tiêu ở quá xa, tầm xa chỉ là: "+tamSD);
        else if(bando.getHave(ngang, doc).getHave().getHP()
                ==bando.getHave(ngang, doc).getHave().getLuongHPToiDa()){
            a = new String("Đồng minh đã đạt lượng máu tối đa: ");
        }
        else{
            NhanVat temp = bando.getHave(ngang, doc).getHave();
            temp.setHP(temp.getHP()+luongMauHoi);
            System.out.println("hồi máu thành công cho mục tiêu tọa độ: "+temp.timViTriBanThan(bando)[0]+"-"+temp.timViTriBanThan(bando)[1]);
            setNangLuongA(getNangLuongA()-1);
            a= new String();
            setEXP(getEXP()+50);
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
    


