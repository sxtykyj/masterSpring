package aop_study.advice.aspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @Author: yk
 * @Date: 2020/1/16 18:06
 */
@Aspect
public class EnableSellerAspect {
    // 为NaiveWaiter添加接口实现
    @DeclareParents(value = "aop_study.advice.beforeAdvice.NaiveWaiter",defaultImpl = SmartSeller.class)
    // 要实现的目标接口
    public Seller seller;
}
