package RPG;

public abstract class Nguoi extends NhanVat {

    public Nguoi(int capDo) {
        super(capDo);
        doi ="den";
        trangThai="san sang";
    }


    @Override
    public String xemThongTin() {
        String a = super.xemThongTin();
        return a;
    }

    /*trả về 1 String : "Tên nv A" tấn công "tên NV B". nếu nhân vật B chết : String có thêm:" B chết", và gọi đến phương thức chết của B. 
                nếu tấn công không đc. String null.
                công thức tính B mất máu : dame A - giáp B.
          nhận vào tọa độ TPBanDo bị tác động .
     */
    @Override
    //truyền vào tọa độ Ngang, Dọc của TPBando cần tấn công. trả về String: kết quả tấn công. nếu kết quả null: tấn công thành công
    public abstract String tanCong(int ngang, int doc, BanDo bando);
    @Override
    public abstract int getLuongHPToiDa();
    
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
        }else if(getNangLuongM()<=0){
            a =new String("Nhân vật này đã hết khả năng di chuyển");
        }else if(bando.getHave(ngang, doc).getDiaHinh().equals("nuoc")){
            a =new String("Nhân vật này không thể di chuyển trên mặt nước");
        }else if(bando.getHave(ngang, doc).getDiaHinh().equals("tuong")){
            a =new String("Nhân vật này không thể di chuyển trên tường");   
        }else if(bando.getHave(ngang, doc).getHave()!=null){
            a =new String("Ô này đã có nhân vật");
        }else{
        bando.getHave(ngang, doc).setHave(this);//nhân vật chuyển đến 1 ô mới
        bando.getHave(toado[0], toado[1]).nhanVatRoiDi();//không được đổi vị trí 2 lệnh này( rời đi trước chuyển đến). do con trỏ bị mất vị trí trỏ.
        if(!(bando.getHave(ngang, doc).getDiaHinh().equals("da")))
                setNangLuongM(getNangLuongM()-1);// nếu đi trên đá, không bị trừ năng lượng
        if(bando.getHave(ngang, doc).getDiaHinh().equals("bun"))
                setNangLuongM(getNangLuongM()-1);// nếu đi phải bùn. năng lượng bị trừ thêm 1
         a = new String();
        }
        return a;

    }

    @Override
    public void chet(BanDo bando) {
        int[] toado =timViTriBanThan(bando);
        bando.getdieZone().add(this);
        bando.getHave(toado[0], toado[1]).setHave(null);
        System.out.println("Người ở tọa độ "+toado[0]+"-"+toado[1]+" vừa hi sinh");
    }
}

