package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
       /* AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/
        //MemberService memberService = new MemberServiceImpl(null);
       // OrderService orderService = new OrderServiceImpl(null, null);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 컨테이너 생성
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Long memberId = 1L;
        Member member1 = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member1);

        Order order = orderService.createOrder(memberId,"itemA",20000);
        System.out.println("order1 = " + order);
        System.out.println("order1.calculatePrice = " + order.calculatePrice());

        Member member2 = new Member(2L,"memberB", Grade.BASIC);
        memberService.join(member2);

        Order order2 = orderService.createOrder(2L,"itemA",10000);
        System.out.println("order2 = " + order2);
        System.out.println("order2.calculatePrice = " + order2.calculatePrice());

    }
}
