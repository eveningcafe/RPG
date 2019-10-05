package RPG;
public class TrongTai {
	private boolean ketThucGame;
	public void TrongTai() {
	ketThucGame=false;
	}
	public boolean getKetThucGame(){
            return ketThucGame;
        }
	public void chuyenLuot(String doiHienTai,String doiNhanLuot, BanDo bando) {
            //nạp năng lượng A,M cho đội nhận lượt và rút năng lượng A,M của đội hiện tại
        for(int i=0;i<bando.getCao();i++)
            for(int j=0;j<bando.getRong();j++){
                if(bando.getHave(i, j).getHave()!=null){
                    NhanVat temp= bando.getHave(i, j).getHave();
                    if(temp.getDoi().equals(doiHienTai))    
                    temp.chuyenTrangThai("kiet suc");
                    else if(temp.getDoi().equals(doiNhanLuot))
                    temp.chuyenTrangThai("san sang");  
                }
            }
	}
	
	public NhanVat timNVDiChuyen(String doiHienTai, BanDo bando){
            //tìm nhân vật còn khả năng di chuyển
            for(int i=0;i<bando.getCao();i++)
                for(int j=0;j<bando.getRong();j++){
                    
                    if(bando.getHave(i, j).getHave()!=null){
                        NhanVat temp= bando.getHave(i, j).getHave();
                        if(temp.getDoi().equals(doiHienTai)){
                            if(temp.getNangLuongM()>0) return temp;
                        }
                    }
      
                }
            return null;
	}
        public NhanVat timNVTanCong(String doiHienTai, BanDo bando){
            //tìm nhân vật còn khả năng tấn công của đội hiện tại
            for(int i=0;i<bando.getCao();i++)
                for(int j=0;j<bando.getRong();j++){
                    
                    if(bando.getHave(i, j).getHave()!=null){
                        NhanVat temp= bando.getHave(i, j).getHave();
                        if(temp.getDoi().equals(doiHienTai)){
                            if(temp.getNangLuongA()>0) return temp;
                        }
                    }
      
                }
            return null;
	}
        public boolean kiemTraThuaCuoc(String doiHienTai, BanDo bando){
            //nếu đội hiện tại còn nhân vật, đội hiện tại   CHƯA THUA. còn không. đội hiện tại Thua
            for(int i=0;i<bando.getCao();i++)
                for(int j=0;j<bando.getRong();j++){
                    if(bando.getHave(i, j).getHave()!=null){
                        NhanVat temp= bando.getHave(i, j).getHave();
                        if(temp.getDoi().equals(doiHienTai)) return ketThucGame;
                    }
                }
            //nếu không tìm được nhân vật nào của đội hiện tại có trên bản đồ
            ketThucGame=true;
            return ketThucGame;
        }
}
