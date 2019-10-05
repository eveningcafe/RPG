package Interfaces;

import RPG.BanDo;

public interface PhepThuat {
    public String getLoaiPhep();
    public String skill(int ngang, int doc, BanDo bando); 
    public abstract String diChuyen(int ngang, int doc, BanDo bando);
    // người dùng phép có thể di chuyển trên mặt nước.
}
