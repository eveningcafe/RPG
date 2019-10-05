package RPG;

import java.util.ArrayList;

public class BanDo{
        private int man;
	private int rong;
	private int cao;
        // mảng have lưu thành phần bản đồ
	private TPBanDo[][] have ;
        // ArrayList dieZone lưu các nhân vật đã chết trong màn 
        private ArrayList dieZone;
        
	public BanDo(int manchoi,ArrayList dataCacBanDo) {
        this. man= manchoi;
        int[][] datadiahinh= (int[][]) dataCacBanDo.get((this.man-1)*2);//do màn chơi bắt đầu từ 1. còn arraylist dataCacBanDo bắt đầu từ 0
        int[][] datanhanvat= (int[][]) dataCacBanDo.get((this.man-1)*2+1);
	this.cao= datadiahinh.length;
        this.rong=datadiahinh[0].length;
        have= new TPBanDo[cao][rong];
        for(int i=0;i<cao;i++)
            for(int j=0;j<rong;j++){
                have[i][j]= new TPBanDo(i, j, datadiahinh[i][j],datanhanvat[i][j],man);
            }
        dieZone = new ArrayList();
        }

        //truyền vào tọa độ của ô đang xét. trả về ArrayList<TPBanDo> là các ô xung quanh ô đang xét. 
	public ArrayList getXungQuanh(int ngang,int doc,int xa){
            ArrayList a =new ArrayList();
            int i,j;
            for(i=ngang-xa;i<=ngang+xa;i++){
                for(j=doc-xa;j<=doc+xa;j++){
                   if((i>=0&&i<this.cao)&&(j>=0&&j<this.rong))//kiểm tra điều kiện biên
                      if(!(i==ngang&&j==doc))
                       a.add(getHave(i, j));
                }
            }
            return a;
        }
//GETTER và SETTER
        public int getMan(){
            return man;
        }
        public void setMan(int manmoi){
            this.man=manmoi;
        }
        public ArrayList getdieZone(){
            return this.dieZone;
        }
        public int getRong() {
            return rong;
        }
	public void setRong() {
            this.rong = rong;
        }
        public int getCao() {
            return cao;
        }
	public void setCao() {
            this.cao = cao;
        }
        //không trả về mảng have mà trả về TPBanDo có row, col
	public TPBanDo getHave(int row,int col) {
	return have[row][col];
	}
}
