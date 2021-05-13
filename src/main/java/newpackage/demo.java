package newpackage;


import Service.CategoryService;
import Service.ProductService;
import entity.Category;
import entity.Manufacturer;
import entity.Product;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nohop
 */
public class demo {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        try(Session session = factory.openSession()) {
            do {  
                showMenu();
                System.out.println("nhap vao lua chon cua ban:");
                n = sc.nextInt();
                switch(n)
                {
                    case 1:
                        ProductService pro = new ProductService();
                        List<Product> list = pro.getAll(session);
                        list.forEach((element)->{
                            System.out.println("product{" + "id = " + element.getId() + ", name = " + element.getName() + '}');
                        });
                        break;
                    case 2:
                        CategoryService cate = new CategoryService();
                        List<Category> listcates = cate.getAll(session);
                        listcates.forEach((element)->{
                            System.out.println("category{" + "id = " + element.getId() + ", name = " + element.getName() + '}');
                        });
                        break;
                    case 3:
                        String keyword;
                        Scanner out = new Scanner(System.in);
                        System.out.println("Tu khoa ban muon tim kiem:");
                        
                        keyword = out.nextLine();
                        ProductService pro1 = new ProductService();
                        List<Product> listpros = pro1.getByKeyword(keyword, session);
                        
                        listpros.forEach((element)->{
                            System.out.println("product{" + "id = " + element.getId() + ", name = " + element.getName() + '}');
                        });
                        break;
                    case 4:
                        Scanner out1 = new Scanner(System.in);
                        System.out.println("1. thêm mot san pham.");
                        System.out.println("2. thêm mot the loai.");
                        System.out.println("3. thêm mot nha san xuat.");
                        System.out.println("thu ban muon them:");
                        int choose = Integer.parseInt(out1.nextLine());
                        switch(choose)
                        {
                            case 1:
                            
                                session.getTransaction().begin();
                                System.out.println("nhap vao ma the loai:");
                                Category cate1 = session.get(Category.class,Integer.parseInt(out1.nextLine()));
                                System.out.println("nhap vao ma the loai:");
                                Manufacturer manunew = session.get(Manufacturer.class,Integer.parseInt(out1.nextLine()));
                                Product pr = new Product();
                                System.out.println("nhap vao ten san pham:");
                                pr.setName(out1.nextLine());
                                System.out.println("nhap vao mo ta san pham:");
                                pr.setDescription(out1.nextLine());
                                System.out.println("nhap vao gia san pham:");
                                pr.setPrice(Integer.parseInt(out1.nextLine()));
                                pr.setCt(cate1);
                                Set<Manufacturer> mSet = new HashSet<>();
                                mSet.add(manunew);
                                pr.setManu(mSet);
                                session.save(pr);
                                session.getTransaction().commit();
                                break;
                            case 2:
                                session.getTransaction().begin();
                                Category catenew = new Category();
                                System.out.println("nhap vao ten the loai:");
                                catenew.setName(out1.nextLine());
                                session.save(catenew);
                                session.getTransaction().commit();
                                break;
                            case 3:
                                session.getTransaction().begin();
                                Manufacturer manu = new Manufacturer();
                                System.out.println("nhap vao ten nha san xuat:");
                                manu.setName(out1.nextLine());
                                System.out.println("nhap vao quoc gia:");
                                manu.setCountry(out1.nextLine());
                                
                                session.save(manu);
                                session.getTransaction().commit();
                                break;
                        }
                        
                        
                        break;
                    case 5:
                        Scanner sc5 = new Scanner(System.in);
                        Transaction transaction;
                        ProductService productService = new ProductService();
                        System.out.println("nhap vao id san pham muon xoa:");
                        int delete_id = (Integer.parseInt(sc5.nextLine()));                       
                         transaction = session.beginTransaction();
                         Product product = productService.getById(delete_id, session);
                         System.out.println(product.getName());
                         session.delete(product);
                         transaction.commit();        
                        break;
                    case 6:
                        System.out.println("Good Bye !!!");
                        break;
                        
                }
                
            } while (n != 6);
            
        }
    }
    
    static void showMenu()
    {
        System.out.println("---------Menu---------");
        System.out.println("1. hien thi tat ca cac san pham.");
        System.out.println("2. hien thi tat ca cac the loai dt hien co.");
        System.out.println("3. tim kiem san pham theo tu khoa.");
        System.out.println("4. them moi.");
        System.out.println("5. xoa thong tin 1 san pham.");
        System.out.println("6. exits.");
    }
        
   
}
