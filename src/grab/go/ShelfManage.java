/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import java.time.LocalDate;

/**
 *
 * @author FUJITSU
 */
public class ShelfManage {
    String Product_Id;
    String Product_Name;
    String Block_no;
    String Shelf_no;
    int Shelf_row,Shelf_col,OnShelf_quantity,addProQty;
    String Stock_Con;
    String Add_sts;
    LocalDate MFG_Date;
    LocalDate Exp_Date;

    public ShelfManage(String Product_Id, String Product_Name, String Block_no, String Shelf_no, int Shelf_row, int Shelf_col, LocalDate MFG_Date, LocalDate Exp_Date) {
        this.Product_Id = Product_Id;
        this.Product_Name = Product_Name;
        this.Block_no = Block_no;
        this.Shelf_no = Shelf_no;
        this.Shelf_row = Shelf_row;
        this.Shelf_col = Shelf_col;
        this.MFG_Date = MFG_Date;
        this.Exp_Date = Exp_Date;
    }

    public LocalDate getMFG_Date() {
        return MFG_Date;
    }

    public void setMFG_Date(LocalDate MFG_Date) {
        this.MFG_Date = MFG_Date;
    }

    public LocalDate getExp_Date() {
        return Exp_Date;
    }

    public void setExp_Date(LocalDate Exp_Date) {
        this.Exp_Date = Exp_Date;
    }

    public ShelfManage() {
    }

    public ShelfManage(String Product_Id, String Product_Name, String Block_no, String Shelf_no, int Shelf_row, int Shelf_col, int OnShelf_quantity, String Stock_Con, String Add_sts) {
        this.Product_Id = Product_Id;
        this.Product_Name = Product_Name;
        this.Block_no = Block_no;
        this.Shelf_no = Shelf_no;
        this.Shelf_row = Shelf_row;
        this.Shelf_col = Shelf_col;
        this.OnShelf_quantity = OnShelf_quantity;
        this.Stock_Con = Stock_Con;
        this.Add_sts = Add_sts;
    }

    ShelfManage(String Product_Id, String Product_Name, String Block_no, String Shelf_no, int Shelf_row, int Shelf_col, int addProQty, String Add_sts) {
        this.Product_Id = Product_Id;
        this.Product_Name = Product_Name;
        this.Block_no = Block_no;
        this.Shelf_no = Shelf_no;
        this.Shelf_row = Shelf_row;
        this.Shelf_col = Shelf_col;
        this.addProQty = addProQty;
        this.Add_sts = Add_sts;
    }

    public int getAddProQty() {
        return addProQty;
    }

    public void setAddProQty(int addProQty) {
        this.addProQty = addProQty;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String Product_Id) {
        this.Product_Id = Product_Id;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public String getBlock_no() {
        return Block_no;
    }

    public void setBlock_no(String Block_no) {
        this.Block_no = Block_no;
    }

    public String getShelf_no() {
        return Shelf_no;
    }

    public void setShelf_no(String Shelf_no) {
        this.Shelf_no = Shelf_no;
    }

    public int getShelf_row() {
        return Shelf_row;
    }

    public void setShelf_row(int Shelf_row) {
        this.Shelf_row = Shelf_row;
    }

    public int getShelf_col() {
        return Shelf_col;
    }

    public void setShelf_col(int Shelf_col) {
        this.Shelf_col = Shelf_col;
    }

    public int getOnShelf_quantity() {
        return OnShelf_quantity;
    }

    public void setOnShelf_quantity(int OnShelf_quantity) {
        this.OnShelf_quantity = OnShelf_quantity;
    }

    public String getStock_Con() {
        return Stock_Con;
    }

    public void setStock_Con(String Stock_Con) {
        this.Stock_Con = Stock_Con;
    }

    public String getAdd_sts() {
        return Add_sts;
    }

    public void setAdd_sts(String Add_sts) {
        this.Add_sts = Add_sts;
    }
    
    
}
