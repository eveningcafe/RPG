package RPG;

public abstract class NhanVat {

    protected String doi; //đội
    
    protected int HPCoBan;
    protected int satThuongCoBan; //dame
    protected int giapCoBan;
    
    protected int HP;
    protected int satThuong;
    protected int giap;
    protected String trangThai;
    protected int EXP;
    protected int capDo;
    protected int nangLuongA;
    protected int nangLuongM;
    public NhanVat(int capDo) {
        this.capDo =capDo;
        this.HPCoBan=20;
        this.satThuongCoBan=10;
        this.giapCoBan=1;
        this.EXP=0;
        this.nangLuongA=0;
        this.nangLuongM=0;
        this.trangThai= new String("kiet suc");
    }
    

//truyền vào tọa độ Ngang, Dọc của TPBando cần tấn công. trả về String: kết quả tấn công.  trả về null: tấn công thành công
public abstract String tanCong(int ngang, int doc, BanDo bando);
 //truyền vào tọa độ Ngang, Dọc của TPBando cần di chuyển đến. trả về String: kết quả di Chuyển. trả về null: di chuyển thành công
public  abstract String diChuyen(int ngang, int doc, BanDo bando);
//truyền vào trạng thái mới. và thay đổi nangLuongA nangLuongM ứng với mỗi trạng thái cụ thể
public abstract void chuyenTrangThai(String tt);
public abstract void thangCap(); // tăng cấp nhân vật ( khi exp>100)
public abstract void chet(BanDo bando);// loại bỏ nhân vật khỏi bản đồ.
// Xem thông tin nhân vật
    public String xemThongTin() {
        String a = new String("Level " +capDo +", Máu: " + HP + ", Sát Thương: " + satThuong + ", Giáp: " + giap + ", EXP: " + EXP +", Trạng thái: "+trangThai );
        return a;
    }
// Tìm Tọa độ  mà bản thân nó đang đứng trên. Trả về toado[0] là ngang, toado[1] là dọc
    public int[] timViTriBanThan(BanDo bando){
        int i, j = 0;
        int[] toado= new int[2];
        for (i = 0; i < bando.getCao(); i++) {
             for (j = 0; j < bando.getRong(); j++){
                if (bando.getHave(i, j).getHave() == this) {
                    break;
                }
            }
            if (j == bando.getRong()) {
                j--;
            }
            if (bando.getHave(i, j).getHave() == this) {
                break;
            }
        }
        toado[0]=i;toado[1]=j;
        
        return toado;
        
    }
    //trả về lượng hp tối đa theo lv của nhân vật. nếu đã đạt tối đa hp thì không thể hồi máu nữa
    public abstract int getLuongHPToiDa();

//GETTER VA SETTER
    public int getNangLuongA() {
        return nangLuongA;
    }
    public int getNangLuongM(){
        return nangLuongM;
    } 
    public void setNangLuongA(int nangLuong){
        if(nangLuong>=0)
        this.nangLuongA=nangLuong;
        
    }
    public void setNangLuongM(int nangLuong){
        if(nangLuong>=0)
         this.nangLuongM=nangLuong; 
    }
    public int getCapDo() {
        return capDo;
    }
    
    public int getHPCoBan() {    
        return HPCoBan;
    }

    public void setHPCoBan(int HPCoBan) {
        if(HPCoBan>=0)
        this.HPCoBan = HPCoBan;
    }

    public int getSatThuongCoBan() {
        return satThuongCoBan;
    }

    public void setSatThuongCoBan(int satThuongCoBan) {
        if(satThuongCoBan>=0)
        this.satThuongCoBan = satThuongCoBan;
    }

    public int getGiapCoBan() {
        return giapCoBan;
    }

    public void setGiapCoBan(int giapCoBan) {
        if(giapCoBan>=0)
        this.giapCoBan = giapCoBan; 
    }

    public void setCapDo(int capDo) {
        if(capDo>=0)
        this.capDo = capDo;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if(HP<getLuongHPToiDa()) // chỉ được hồi máu đến lượng máu tối đa
            this.HP = HP;
        else
            this.HP=getLuongHPToiDa();
    }

    public int getGiap() {
        return giap;
    }

    public void setGiap(int giap) {
        if(giap>=0)
        this.giap = giap;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        if(EXP>=0)
	this.EXP = EXP;
    }
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        if(doi.equals("den")||doi.equals("do"))
        this.doi = doi;
    }
    
    public int getSatThuong() {
        return satThuong;
    }
    public String getTrangThai(){
        return this.trangThai;
    }
    public void setTrangThai(String tt){
        this.trangThai=tt;
    }

    public void setSatThuong(int satThuong) {
        if(satThuong>=0)
        this.satThuong = satThuong;
    }
   
}
