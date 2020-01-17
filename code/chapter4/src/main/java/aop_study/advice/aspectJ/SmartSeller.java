package aop_study.advice.aspectJ;

/**
 * @Author: yk
 * @Date: 2020/1/16 18:07
 */
public class SmartSeller implements Seller {
    public void showGoods(String goods) {
        System.out.println("Seller shows the name of goods: " + goods + "...");
    }

    public void sell(String good, String customer) {
        System.out.println("sell " + good + " to " + customer + "...");
    }
}
