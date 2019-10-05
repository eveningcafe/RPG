package RPG;

import NhanVat.DauSi;
import NhanVat.QuaiNemDa;
import NhanVat.BacSi;
import NhanVat.CungThu;
import NhanVat.PhuThuy;
import NhanVat.DoTe;
import java.lang.String;

public class TPBanDo {

    private int toaDoNgang;
    private int toaDoDoc;
    private NhanVat have;
    private String diaHinh;

    public TPBanDo(int indexRow, int indexCol, int numberDiaHinh, int numberNhanVat,int manchoi) {
        toaDoNgang = indexRow;
        toaDoDoc = indexCol;
        if (numberDiaHinh == 0) {
            diaHinh = new String("da");
        }
        if (numberDiaHinh == 1) {
            diaHinh = new String("co");
        }
        if (numberDiaHinh == 2) {
            diaHinh = new String("nuoc");
        }
        if (numberDiaHinh == 3) {
            diaHinh = new String("cay");
        }
        if (numberDiaHinh == 4) {
            diaHinh = new String("cau");
        }
        if (numberDiaHinh == 5) {
            diaHinh = new String("bun");
        }
        if (numberDiaHinh == 6) {
            diaHinh = new String("tuong");
        }
        if (numberDiaHinh == 8) {
            diaHinh = new String("congvao");
        }
        if (numberDiaHinh == 9) {
            diaHinh = new String("congra");
        }
        //màn 1 thì cấp độ nhân vật là 1 được khởi tạo.màn 2 thì lv đầu của các nhân vật là 2, vv...
        if (numberNhanVat == 11) {
            have = new DauSi(manchoi);
        }
        if (numberNhanVat == 12) {
            have = new CungThu(manchoi);
        }
        if (numberNhanVat == 13) {
            have = new BacSi(manchoi);
        }
        if (numberNhanVat == 21) {
            have = new DoTe(manchoi);
        }
        if (numberNhanVat == 22) {
            have = new QuaiNemDa(manchoi);
        }
        if (numberNhanVat == 23) {
            have = new PhuThuy(manchoi);
        }
    }

    public void setDiaHinh(String diaHinhMoi) {
        this.diaHinh = diaHinhMoi;
    }

    public String getDiaHinh() {
        return diaHinh;
    }

    public NhanVat getHave() {
        return have;
    }

    public void setHave(NhanVat nhanvat) {
        this.have = nhanvat;
    }

    public void nhanVatRoiDi() {
        this.have = null;
    }
     public int getToaDoNgang() {
        return toaDoNgang;

    }

    public int getToaDoDoc() {
        return toaDoDoc;
    }
}
