package com.RPG;

import NhanVat.*;
import RPG.*;
import Interfaces.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
public class InOut extends JPanel {
    private int GAP =1; //gap la do day vien
    private JLabel[][] o ;
    private int clickROW ;
    private int clickCOL ;
    private int clickROWT ;
    private int clickCOLT ;
    private String clickButton ;
    private boolean suKien;
    private ArrayList dataCacBanDo;
    private BanDo bando;
    private int soLuongMan;
    private TrongTai trongtai;
    JButton attack;
    JButton skill;
    JButton move;
    JButton back;
    JButton endTurn;
    //JTextField TextField1;
    //JTextField TextField2;
    public InOut(ArrayList dataCacBanDo,int manchoi,TrongTai trongtai){
        this.dataCacBanDo= dataCacBanDo;
        this.bando=new BanDo(1, dataCacBanDo);
        this.soLuongMan=dataCacBanDo.size()/2;
        this.trongtai=trongtai;
        clickROW=-1;
        clickCOL=-1;
        clickROWT=-1;
        clickCOLT=-1;
        clickButton=null;
        suKien=false;
        int cao= bando.getCao();
        int rong =bando.getRong();
        o = new JLabel[cao][rong];
        JPanel InOutPanel = new JPanel(new GridLayout(cao,rong,GAP,GAP)); // 2 chi so duoi la do day vien o
        InOutPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        InOutPanel.setBackground(Color.black);
         for ( int i = 0; i < cao; i++) 
            for ( int j = 0; j < rong ; j++) {
                o[i][j] = new JLabel();
                o[i][j].addMouseListener(new Grid(i, j));
                InOutPanel.add(o[i][j]);
                o[i][j].setOpaque(true);
                o[i][j].setBackground(Color.WHITE);  
                                
            }
        hienThiBanDo();
        setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(attack=new JButton("attack"));
        bottomPanel.add(skill=new JButton("skill"));
        bottomPanel.add(move=new JButton("move"));
        bottomPanel.add(back=new JButton("back"));
        bottomPanel.add(endTurn=new JButton("end turn"));
        //bottomPanel.add(TextField1 = new JTextField("abc"));
        //bottomPanel.add(TextField2 = new JTextField("abc"));
        add(InOutPanel, BorderLayout.NORTH);
        
        add(bottomPanel, BorderLayout.PAGE_END);
        attack.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickButton="attack";
                
            }
            
        });
        skill.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickButton="skill";
            }
            
        });

        move.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickButton="move";
                
                //System.out.println("da click vao button");
               
            }
            
        });
        back.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                clickButton="back";
                suKien=true;
                xuLySuKien();
                
            }
            
        });
        endTurn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                 
                clickButton="endTurn";
                suKien= true;
                
                xuLySuKien();
            }
            
        });
                move.setEnabled(false);
                back.setEnabled(false);
                skill.setEnabled(false);
                attack.setEnabled(false);
                endTurn.setEnabled(true);
                
    }




    
    private class Grid implements MouseListener{
        //nghe duoc su kien tu ô tuong ung may tinh se chay code o day.
        int row;
        int col;
        public Grid(int row,int col){
            this.row=row;
            this.col=col;

        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("da click"+row);
            //System.out.println("da click"+col);
            
                //xac dinh TPbando nguon
                if(clickButton==null){
                    if(bando.getHave(row, col).getHave()!= null)
                        if(bando.getHave(row, col).getHave().getDoi().equals("den")){
                        move.setEnabled(true);
                        move.setText("move"+ " ("+bando.getHave(row, col).getHave().getNangLuongM()+")");
                        back.setEnabled(true);
                        if(bando.getHave(row, col).getHave() instanceof BacSi ){
                        skill.setEnabled(true);
                        attack.setEnabled(false);
                        skill.setText("skill"+ " ("+bando.getHave(row, col).getHave().getNangLuongA()+")");
                        attack.setText("attack");
                        }else{
                        skill.setEnabled(false);
                        attack.setEnabled(true); 
                        skill.setText("skill");
                        attack.setText("attack"+ " ("+bando.getHave(row, col).getHave().getNangLuongA()+")");
                        }
                    }else if(bando.getHave(row, col).getHave().getDoi().equals("do")){//yeu cau nguoi choi chon doi "den"}
                    
                    JOptionPane.showMessageDialog(null, "hãy chọn đội đen");
                    }
                
                clickCOL=col; clickROW=row;
                //System.out.println(" vi tri dau"+clickCOL+clickROW);
                
                //xac dinh TPBanDo dich
                }else{
                clickCOLT=col; clickROWT=row;
                //System.out.println(" vi tri dich"+clickCOLT+clickROWT);
                suKien= true;
                
                xuLySuKien();
                //System.out.println(" "+suKien);
                move.setEnabled(false); move.setText("move");
                back.setEnabled(false);
                skill.setEnabled(false);skill.setText("skill");
                attack.setEnabled(false);attack.setText("attack");
                endTurn.setEnabled(true);
                }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }   
    }
//khi suKien=true . hàm này được InOut gọi tới. đây là hàm quan trọng. mọi người nên đọc.
    private void xuLySuKien() {
        
            if(clickButton=="move"){
                //System.out.println(" vi tri dau"+clickCOL+clickROW);
                //System.out.println(" vi tri dich"+clickCOLT+clickROWT);
                try{
                    NhanVat temp= bando.getHave(clickROW, clickCOL).getHave();
                    String m= temp.diChuyen(clickROWT,clickCOLT, bando);
                    if(m.equals("về màn trước")){
                        if(bando.getMan()==1){
                            JOptionPane.showMessageDialog(null,"Đây là màn đầu tiên!");
                        }else{
                            
                            bando= new BanDo(bando.getMan()-1, dataCacBanDo);
                            JOptionPane.showMessageDialog(null,"chuyển màn xong ");
                        }
                    }else if(m.equals("sang màn kế")){
                        if(bando.getMan()==this.soLuongMan){
                            JOptionPane.showMessageDialog(null,"Bạn đã phá đảo trò này !");
                            System.exit(0);
                        }else{
                            bando= new BanDo(bando.getMan()+1, dataCacBanDo);
                            JOptionPane.showMessageDialog(null,"chuyển màn xong ");
                        }
                    }else if(m!=null&&(!(m.equals("")))){
                        JOptionPane.showMessageDialog(null,m);
                    }
                    //System.out.println(""+clickROWT+clickCOLT);
                }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh");
                    clickButton=null;
                    hienThiBanDo();
                }catch(ArrayIndexOutOfBoundsException e){
                    clickButton=null;
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh" );
                    hienThiBanDo();
                }
                hienThiBanDo();
                //+ hiển thị thông tin chi phí khá cao . nên sửa lại về sau
                
            }else if(clickButton=="attack"){
               try{
                     String a =((Nguoi)bando.getHave(clickROW, clickCOL).getHave()).tanCong(clickROWT,clickCOLT, bando);
                if(a!=null&&(!(a.equals(""))))
                    JOptionPane.showMessageDialog(null,a);
                hienThiBanDo();
               }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh");
                    clickButton=null;
                    hienThiBanDo();
                }catch(ArrayIndexOutOfBoundsException e){
                    clickButton=null;
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh" );
                    hienThiBanDo();
                }
               
             }else if(clickButton=="skill"){
               try{
                    String a =((BacSi)bando.getHave(clickROW, clickCOL).getHave()).skill(clickROWT,clickCOLT, bando);
                if(a!=null&& !a.equals(""))
                    JOptionPane.showMessageDialog(null,a);
                hienThiBanDo();
               }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh");
                    clickButton=null;
                    hienThiBanDo();
                }catch(ArrayIndexOutOfBoundsException e){
                    clickButton=null;
                    JOptionPane.showMessageDialog(null,"bạn thao tác quá nhanh" );
                    hienThiBanDo();
                }   
            }else if(clickButton=="back"){// khi nguoi choi muon xoa hanh dong vua lam
                move.setEnabled(false); move.setText("move");
                back.setEnabled(false);
                skill.setEnabled(false);skill.setText("skill");
                attack.setEnabled(false);attack.setText("attack");
                endTurn.setEnabled(true);
                clickROW=-1; clickCOL=-1; clickROWT=-1; clickCOLT=-1;
            }else if(clickButton=="endTurn"){
                /*
                    truyền năng lượng cho team đỏ, rút năng lượng của team đen
                    while{tim quai vatco kha nang di chuyen != null)
                    nhan vat do di chuyen;}
                    while{trongtrai. tim quai vat co kha nang tan cong)
                    nhan vat do tan cong;
                    trongtai.kiem tra thua cuoc cua doi den};
                    
                   */
                clickROW=-1; clickCOL=-1; clickROWT=-1; clickCOLT=-1;
                //JOptionPane.showMessageDialog(null,"end turn nhé");
                move.setEnabled(false); move.setText("move");
                back.setEnabled(false);
                skill.setEnabled(false);skill.setText("skill");
                attack.setEnabled(false);attack.setText("attack");
                endTurn.setEnabled(false);
                trongtai.chuyenLuot("den","do", bando);
                while(trongtai.timNVDiChuyen("do", bando)!=null){
                    NhanVat temp = trongtai.timNVDiChuyen("do", bando);
                    ((QuaiVat)temp).tuDongDiChuyen(bando);
                    hienThiBanDo();
                }

                while(trongtai.timNVTanCong("do", bando)!=null){
                    NhanVat temp = trongtai.timNVTanCong("do", bando);
                    if(temp instanceof PhuThuy)
                        ((PhuThuy)temp).skill(bando);
                     else
                     ((QuaiVat)temp).tuDongTanCong(bando);
                    if(trongtai.kiemTraThuaCuoc("den", bando)==true){ 
                        endTurn.setEnabled(false);
                        ketThucGame(); break;}
                }
                trongtai.chuyenLuot("do","den", bando);
                hienThiBanDo();
            }
            clickButton=null;
            suKien=false;
            endTurn.setEnabled(true);
            
    }
       
    private void ketThucGame() {
        JOptionPane.showMessageDialog(null,"game over");
        System.exit(0);
    }
    private void hienThiThongTin(){
        // hiển thị thông tin nhân vật lên màn hình dưới dạng tip
        for(int i=0;i<bando.getCao();i++)
            for(int j=0; j<bando.getRong();j++){
                if(bando.getHave(i, j).getHave()!=null){
                    NhanVat temp=bando.getHave(i, j).getHave();
                    o[i][j].setToolTipText(temp.xemThongTin());
                }
            }
    }
    public void hienThiBanDo(){
        
        for(int i=0;i<bando.getCao();i++)
            for(int j=0; j<bando.getRong();j++){
                if(bando.getHave(i, j).getDiaHinh().equals("tuong")){                   
                   ImageIcon icon =new ImageIcon("res/tuong.png");
                    o[i][j].setIcon(icon);  
                }
                if(bando.getHave(i, j).getDiaHinh().equals("da")){                   
                   ImageIcon icon =new ImageIcon("res/rock.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("co")){
                   ImageIcon icon =new ImageIcon("res/glass.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("nuoc")){
                   ImageIcon icon =new ImageIcon("res/nuoc.jpg");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("cay")){
                   ImageIcon icon =new ImageIcon("res/tree.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("cau")){
                   ImageIcon icon =new ImageIcon("res/brigde.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("congvao")){
                   ImageIcon icon =new ImageIcon("res/congvao.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("congra")){
                   ImageIcon icon =new ImageIcon("res/congra.png");
                    o[i][j].setIcon(icon);  
                }
                else if(bando.getHave(i, j).getDiaHinh().equals("bun")){
                   ImageIcon icon =new ImageIcon("res/bun.png");
                    o[i][j].setIcon(icon);  
                }
                
                if(bando.getHave(i, j).getHave()!=null){
                    if(bando.getHave(i, j).getHave().getEXP()>=100)
                        bando.getHave(i, j).getHave().thangCap();
                    if(bando.getHave(i, j).getHave().getHP()<0)
                        bando.getHave(i, j).getHave().chet(bando);
                }
                
                if(bando.getHave(i, j).getHave() instanceof DauSi){
                    ImageIcon icon =new ImageIcon("res/DauSi.png");
                    o[i][j].setIcon(icon); 
                }
                else if(bando.getHave(i, j).getHave() instanceof CungThu){
                    ImageIcon icon =new ImageIcon("res/CungThu.png");
                    o[i][j].setIcon(icon);
                }
                else if(bando.getHave(i, j).getHave() instanceof BacSi){
                    ImageIcon icon =new ImageIcon("res/BacSi.png");
                    o[i][j].setIcon(icon);
                }
                else if(bando.getHave(i, j).getHave() instanceof DoTe){
                    ImageIcon icon =new ImageIcon("res/DoTe.png");
                    o[i][j].setIcon(icon);
                }
                else if(bando.getHave(i, j).getHave() instanceof QuaiNemDa){
                    ImageIcon icon =new ImageIcon("res/QuaiNemDa.png");
                    o[i][j].setIcon(icon);
                }
                else if(bando.getHave(i, j).getHave() instanceof PhuThuy){
                    ImageIcon icon =new ImageIcon("res/PhuThuy.png");
                    o[i][j].setIcon(icon);
                }
                
            }
        hienThiThongTin();
    }
    public static void main(String[] args) {

        int rong1=31;
        int cao1=18;
        int select1[][]={{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
                        {6,3,1,3,1,1,1,1,1,1,1,3,3,2,2,2,2,1,1,1,1,1,5,1,1,1,1,1,1,9,6},
                        {6,3,3,3,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,5,1,1,1,1,1,1,1,6},
                        {6,1,1,1,3,1,5,5,5,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,2,1,1,1,1,6},
                        {6,1,1,1,1,1,1,5,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,2,2,1,1,1,6},
                        {6,3,1,1,1,1,1,5,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,1,2,1,1,1,6},
                        {6,3,1,1,1,1,1,5,1,1,0,1,2,2,2,2,2,0,0,1,1,1,5,5,1,1,2,1,1,1,6},
                        {6,1,1,1,1,1,1,5,1,1,0,0,4,4,4,4,4,0,1,1,1,1,5,5,1,1,2,2,2,2,6},
                        {6,3,1,1,1,1,1,5,1,1,0,0,4,4,4,4,4,0,1,1,1,1,1,5,1,1,1,1,1,3,6},
                        {6,3,1,1,1,1,1,5,1,1,0,0,4,4,4,4,4,0,1,1,1,1,1,5,1,1,1,1,3,3,6},
                        {6,1,1,1,1,1,1,5,1,1,0,1,1,2,2,2,2,0,0,1,1,1,1,5,1,1,1,1,3,3,6},
                        {6,1,1,1,1,1,1,5,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,1,1,1,3,6},
                        {6,0,0,0,1,1,1,5,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,1,1,1,1,6},
                        {6,0,0,0,0,1,5,5,5,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,1,1,1,1,1,1,6},
                        {6,0,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,5,5,5,5,5,5,5,6},
                        {6,0,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,3,6},
                        {6,8,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,3,3,3,6},
                        {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}};
        System.out.println("cao "+select1.length+"rong"+select1[0].length);
        int nhanVatIn1[][]= {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,22,21,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,21,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23,22,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,13,0,0,11,0,0,0,0,0,0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,12,0,0,11,0,0,0,0,0,12,0,0,22,22,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,13,0,0,11,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        int select2[][]=   {{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
                            {6,1,1,1,1,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,9,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,3,3,3,3,3,3,3,3,3,3,3,3,6,0,0,0,0,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,3,5,5,5,5,5,5,5,5,5,3,3,6,0,0,0,0,6,1,1,1,1,6},
                            {6,1,1,1,1,6,4,2,2,3,3,3,3,3,3,3,3,3,3,1,1,0,6,6,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,2,3,5,5,5,5,3,3,3,3,1,3,3,3,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,3,2,3,3,3,3,3,3,3,1,3,3,3,3,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,3,2,2,3,3,3,3,1,1,3,3,3,3,5,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,5,3,2,2,3,3,1,1,3,3,3,3,3,5,5,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,5,3,3,2,2,2,4,2,2,2,3,3,3,5,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,5,5,3,3,3,1,1,3,3,2,2,2,3,3,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,5,3,3,1,1,3,3,3,3,3,3,2,2,2,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,3,3,3,1,1,3,3,5,5,5,5,3,3,3,2,2,2,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,1,6,6,0,1,3,3,3,3,3,3,3,3,3,3,3,1,2,4,6,1,1,1,1,6},
                            {6,1,1,1,1,6,0,0,0,0,6,3,3,5,5,5,5,5,5,5,5,5,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,0,0,0,0,6,3,3,3,3,3,3,3,3,3,3,3,3,3,1,6,1,1,1,1,6},
                            {6,1,1,1,1,6,8,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6,1,1,1,1,6},
                            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}};
        int nhanVatIn2[][] ={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,23,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,11,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        ArrayList dataCacBanDo= new ArrayList();
        dataCacBanDo.add(select1);dataCacBanDo.add(nhanVatIn1);
        dataCacBanDo.add(select2);dataCacBanDo.add(nhanVatIn2);
        TrongTai tai = new TrongTai();
        
        InOut mainPanel = new InOut(dataCacBanDo,1, tai);
        SwingUtilities.invokeLater(() -> {
            createAndShowGui(mainPanel);
        });
        
     
    }

    private static void createAndShowGui(InOut mainPanel) {
        JFrame frame = new JFrame("SimpleRPG");
        frame.setLocation(0, 0);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}